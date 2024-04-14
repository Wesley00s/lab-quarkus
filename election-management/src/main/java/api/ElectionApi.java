package api;

import api.dto.out.ElectionOut;
import domain.Election;
import domain.ElectionService;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ElectionApi {
    private final ElectionService service;

    public ElectionApi(ElectionService service) {
        this.service = service;
    }

    public void submit() {
        service.submit();
    }

    public List<ElectionOut> findAll() {
        return service.findAll()
                .stream()
                .map(ElectionOut::fromDomain)
                .toList();
    }
}
