package infrastructure.repositories;

import domain.Candidate;
import domain.Election;
import domain.ElectionRepository;
import domain.annotations.Principal;
import infrastructure.repositories.entity.ElectionCandidateEntity;
import infrastructure.repositories.entity.ElectionEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

@Principal
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

//    public List<Election> findAll() {
//        Stream<Object[]> stream = entityManager.createNativeQuery("""
//                SELECT e.id AS election_id, c.id AS candidate_id, c.photo, c.given_name, c.family_name, c.email, c.phone, c.job_title, ec.votes
//                FROM Election AS e
//                INNER JOIN Election_candidate AS ec
//                ON ec.election_id = e.id
//                INNER JOIN Candidate AS c
//                ON c.id = ec.candidate_id\s""").getResultStream();
//
//        Map<String, List<Object[]>> map = stream.collect(groupingBy(o -> (String) o[0]));
//
//        return map.entrySet()
//                .stream()
//                .map(entry -> {
//                    Map.Entry<Candidate, Integer>[] candidates = entry.getValue()
//                            .stream()
//                            .map(row -> Map.entry(new Candidate(
//                                    (String) row[1],
//                                    Optional.ofNullable((String) row[2]),
//                                    (String) row[3],
//                                    (String) row[4],
//                                    (String) row[5],
//                                    Optional.ofNullable((String) row[6]),
//                                    Optional.ofNullable((String) row[7])),
//                                    (Integer) row[8]))
//                            .toArray(Map.Entry[]::new);
//                    return new Election(entry.getKey(), Map.ofEntries(candidates));
//                })
//                .toList();
//    }

    @Override
    public List<Election> findAll() {
        Query nativeQuery = entityManager.createNativeQuery("""
                SELECT e.id AS election_id, c.id AS candidate_id, c.photo, c.given_name, c.family_name, c.email, c.phone, c.job_title, ec.votes
                FROM Election AS e
                INNER JOIN Election_candidate AS ec
                ON ec.election_id = e.id
                INNER JOIN Candidate AS c
                ON c.id = ec.candidate_id
                """);

        List<Object[]> resultList = nativeQuery.getResultList();

        Map<String, List<Object[]>> groupedResults = resultList.stream()
                .collect(Collectors.groupingBy(row -> (String) row[0]));

        return groupedResults.entrySet().stream()
                .map(entry -> {
                    String electionId = entry.getKey();
                    List<Object[]> rows = entry.getValue();

                    Map<Candidate, Integer> candidatesMap = rows.stream()
                            .map(row -> {
                                Candidate candidate = new Candidate(
                                        (String) row[1],
                                        Optional.ofNullable((String) row[2]), // photo
                                        (String) row[3],                      // given_name
                                        (String) row[4],                      // job_title
                                        (String) row[5],                      // email
                                        Optional.ofNullable((String) row[6]), // phone
                                        Optional.ofNullable((String) row[7])  // family_name
                                );
                                Integer votes = (Integer) row[8];
                                return Map.entry(candidate, votes);
                            })
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

                    return new Election(electionId, candidatesMap);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void sync(Election election) {
        election.votes()
                .entrySet()
                .stream()
                .map(entry -> ElectionCandidateEntity.fromDomain(election, entry.getKey(), entry.getValue()))
                .forEach(entityManager::merge);
    }
}
