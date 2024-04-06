package infrastructure.repository;

import domain.CandidateRepository;
import domain.CandidateRepositoryTest;
import infrastructure.repositories.SQLCandidateRepository;
import io.quarkus.test.junit.QuarkusTest;

import javax.inject.Inject;

@QuarkusTest
public class SQLCandidateRepositoryTest extends CandidateRepositoryTest {
    @Inject
    SQLCandidateRepository repository;

    @Override
    public CandidateRepository repository() {
        return repository;
    }
}
