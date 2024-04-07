package api.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import domain.Candidate;

import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CandidateOut(
        String id,
        Optional<String> photo,
        String fullName,
        String email,
        Optional<String> phone,
        Optional<String> jobTitle
) {
    public static CandidateOut fromDomain(Candidate candidate) {
        return new CandidateOut(
                candidate.id(),
                candidate.photo(),
                candidate.givenName() + " " + candidate.familyName(),
                candidate.email(),
                candidate.phone(),
                candidate.jobTitle()
        );
    }
}
