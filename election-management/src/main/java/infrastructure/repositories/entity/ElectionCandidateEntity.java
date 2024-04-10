package infrastructure.repositories.entity;

import domain.Candidate;
import domain.Election;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@Setter
@Entity(name = "Elections_candidate")
public class ElectionCandidateEntity {
    @EmbeddedId
    private ElectionCandidateId id;
    private Integer votes;

    public static ElectionCandidateEntity fromDomain(Election election, Candidate candidate, Integer votes) {
        ElectionCandidateEntity entity = new ElectionCandidateEntity();
        ElectionCandidateId id = new ElectionCandidateId();
        id.setElectionId(election.id());
        id.setCandidateId(candidate.id());

        entity.setId(id);
        entity.setVotes(votes);

        return entity;
    }
}
