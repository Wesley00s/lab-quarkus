package domain;

import java.util.Optional;

public record Candidate(String id,
                        Optional<String> photo,
                        String giveName,
                        String familyName,
                        String email,
                        Optional<String> phone,
                        Optional<String> jobTitle) {
}
