package infrastructure.resources;

import api.dto.ElectionDTO;
import infrastructure.rest.ElectionManagement;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestStreamElementType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.util.List;

@Path("/")
public class ResultResource {
    private final ElectionManagement electionManagement;

    public ResultResource(@RestClient ElectionManagement electionManagement) {
        this.electionManagement = electionManagement;
    }

    @GET
    @RestStreamElementType(MediaType.APPLICATION_JSON)
    public Multi<List<ElectionDTO>> results() {
        return Multi.createFrom()
                .ticks()
                .every(Duration.ofSeconds(5))
                .onItem()
                .transformToMultiAndMerge(n -> electionManagement.getElections().toMulti());
    }
}
