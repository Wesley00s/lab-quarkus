package domain;

import com.sun.jna.platform.win32.Sspi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Candidates")
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
