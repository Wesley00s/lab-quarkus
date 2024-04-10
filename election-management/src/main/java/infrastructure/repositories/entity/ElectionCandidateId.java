package infrastructure.repositories.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ElectionCandidateId implements Serializable {
    private static final long serialVersionUID = 263272534302449203L;
    @Column(name = "election_id")
    private String electionId;
    @Column(name = "candidate_id")
    private String candidateId;
}
