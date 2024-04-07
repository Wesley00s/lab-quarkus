package domain;

import java.util.Optional;
import java.util.UUID;


public record Candidate(
        String id,
        Optional<String> photo,
        String givenName,
        String familyName,
        String email,
        Optional<String> phone,
        Optional<String> jobTitle) {

    public static Candidate create(
            Optional<String> photo,
            String givenName,
            String familyName,
            String email,
            Optional<String> phone,
            Optional<String> jobTile) {

        return new Candidate(
                UUID.randomUUID().toString(),
                photo,
                givenName,
                familyName,
                email,
                phone,
                jobTile
        );
    }
}
