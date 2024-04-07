package infrastructure.resources;

import api.CandidateApi;
import api.dto.in.CreateCandidate;
import api.dto.in.UpdateCandidate;
import api.dto.out.CandidateOut;
import domain.Candidate;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.instancio.Instancio;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import java.util.Arrays;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
@TestHTTPEndpoint(CandidateResource.class)
class CandidateResourceTest {
    @InjectMock
    CandidateApi api;

    @Test
    void create() {
        var in = Instancio.create(CreateCandidate.class);

        given().contentType(MediaType.APPLICATION_JSON).body(in)
                        .when().post()
                        .then().statusCode(RestResponse.StatusCode.CREATED);

        verify(api).create(in);
        verifyNoMoreInteractions(api);
    }

    @Test
    void update() {
        var id = UUID.randomUUID().toString();

        var out = Instancio.create(UpdateCandidate.class);

        Candidate candidate = new Candidate(
                id,
                out.photo(),
                out.givenName(),
                out.familyName(),
                out.email(),
                out.phone(),
                out.jobTitle());

        var candidateOut = CandidateOut.fromDomain(candidate);

        when(api.update(eq(id), eq(out))).thenReturn(candidateOut);

        var result = api.update(id, out);

        verify(api).update(eq(id), eq(out));
        assertNotNull(result);

        verifyNoMoreInteractions(api);
    }

    @Test
    void list() {
        var out = Instancio.stream(CandidateOut.class).limit(10).toList();
        when(api.list()).thenReturn(out);

        var response = given()
                .when().get()
                .then().statusCode(RestResponse.StatusCode.OK).extract().as(CandidateOut[].class);

        verify(api).list();
        verifyNoMoreInteractions(api);

        assertEquals(out, Arrays.stream(response).toList());
    }

}