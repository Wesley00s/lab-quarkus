package infrastructure.lifecycle;

import infrastructure.repository.RedisElectionRepository;
import io.quarkus.runtime.Startup;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@Startup
@ApplicationScoped
public class Cache {
    private static final Logger LOGGER = Logger.getLogger(Cache.class.getName());

    public Cache(RedisElectionRepository repository) {
        LOGGER.info("Starting Cache");
        repository.findAll();
    }
}
