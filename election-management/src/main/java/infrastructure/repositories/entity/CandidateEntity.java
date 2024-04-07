package infrastructure.repositories.entity;

import domain.Candidate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Candidates")
public class CandidateEntity {
    @Id
    private String id;

    private String photo;

    @Column(name = "given_name", nullable = false)
    private String giveName;

    @Column(name = "family_name", nullable = false)
    private String familyName;

    @Column(nullable = false)
    private String email;

    private String phone;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    public static CandidateEntity fromDomain(Candidate domain) {
        CandidateEntity entity = new CandidateEntity();

        entity.setId(domain.id());
        entity.setPhoto(domain.photo().orElse(null));
        entity.setGiveName(domain.givenName());
        entity.setFamilyName(domain.familyName());
        entity.setEmail(domain.email());
        entity.setPhone(domain.phone().orElse(null));
        entity.setJobTitle(domain.jobTitle().orElse(null));

        return entity;
    }

    public Candidate toDomain() {
        return new Candidate(
                getId(),
                Optional.ofNullable(getPhoto()),
                getGiveName(),
                getFamilyName(),
                getEmail(),
                Optional.ofNullable(getPhone()),
                Optional.ofNullable(getJobTitle())
        );
    }
}
