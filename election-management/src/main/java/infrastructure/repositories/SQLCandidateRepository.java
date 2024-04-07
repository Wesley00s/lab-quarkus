package infrastructure.repositories;

import domain.Candidate;
import domain.CandidateQuery;
import domain.CandidateRepository;
import infrastructure.repositories.entity.CandidateEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.Predicate;
import java.util.stream.Stream;

@ApplicationScoped
public class SQLCandidateRepository implements CandidateRepository {
    private final EntityManager entityManager;

    public SQLCandidateRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(List<Candidate> candidates) {
        candidates.stream()
                .map(CandidateEntity::fromDomain)
                .forEach(entityManager::merge);
    }

    @Override
    public List<Candidate> find(CandidateQuery query) {
        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(CandidateEntity.class);
        var root = cq.from(CandidateEntity.class);

        cq.select(root).where(conditions(query, cb, root));

        return entityManager.createQuery(cq)
                .getResultStream()
                .map(CandidateEntity::toDomain)
                .toList();
    }

    private Predicate[] conditions(CandidateQuery query,
                                  CriteriaBuilder cb,
                                  Root<CandidateEntity> root) {
        return Stream.of(
                query.ids().map(id -> cb.in(root.get("id")).value(id)),
                query.name().map(name -> cb.or(cb.like(cb.lower(root.get("familyName")), name.toLowerCase() + "%"),
                        cb.like(cb.lower(root.get("giveName")), name.toLowerCase() + "%"))))
                .flatMap(Optional::stream)
                .toArray(Predicate[]::new);
    }
}
