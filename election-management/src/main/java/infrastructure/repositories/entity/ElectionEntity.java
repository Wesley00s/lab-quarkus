package infrastructure.repositories.entity;

import domain.Election;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Election")
public class ElectionEntity {
    @Id
    private String id;

    public static ElectionEntity fromDomain(Election domain) {
        ElectionEntity entity = new ElectionEntity();
        entity.setId(domain.id());
        return entity;
    }
}
