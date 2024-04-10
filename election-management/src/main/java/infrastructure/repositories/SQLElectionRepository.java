package infrastructure.repositories;

import domain.Election;
import domain.ElectionRepository;
import infrastructure.repositories.entity.ElectionCandidateEntity;
import infrastructure.repositories.entity.ElectionEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;

@ApplicationScoped
public class SQLElectionRepository implements ElectionRepository {
    private final EntityManager entityManager;

    public SQLElectionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void submit(Election election) {
        ElectionEntity entity = ElectionEntity.fromDomain(election);
        entityManager.merge(entity);

        election.votes()
                .entrySet()
                .stream()
                .map(entry -> ElectionCandidateEntity.fromDomain(election, entry.getKey(), entry.getValue()))
                .forEach(entityManager::merge);
    }
}
