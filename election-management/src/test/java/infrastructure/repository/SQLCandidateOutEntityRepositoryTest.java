package infrastructure.repository;

import domain.CandidateRepository;
import domain.CandidateOutRepositoryTest;
import infrastructure.repositories.SQLCandidateRepository;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import javax.inject.Inject;
import javax.persistence.EntityManager;

@QuarkusTest
public class SQLCandidateOutEntityRepositoryTest extends CandidateOutRepositoryTest {
    @Inject
    SQLCandidateRepository repository;

    @Inject
    EntityManager entityManager;

    @Override
    public CandidateRepository repository() {
        return repository;
    }

    @BeforeEach
    @TestTransaction
    void tearDown() {
        entityManager.createNativeQuery("TRUNCATE TABLE Candidate").executeUpdate();
    }
}
