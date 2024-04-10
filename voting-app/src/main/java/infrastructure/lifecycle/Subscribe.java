package infrastructure.lifecycle;

import domain.Election;
import infrastructure.repository.RedisElectionRepository;
import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.runtime.Startup;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.infrastructure.Infrastructure;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@Startup
@ApplicationScoped
public class Subscribe {
    private static final Logger LOGGER = Logger.getLogger(Subscribe.class.getName());

    public Subscribe(ReactiveRedisDataSource redisDataSource, RedisElectionRepository repository) {
        LOGGER.info("Startup: Subscribe started");

        Multi<String> sub = redisDataSource.pubsub(String.class)
                .subscribe("elections");

        sub.emitOn(Infrastructure.getDefaultWorkerPool())
                .subscribe()
                .with(id -> {
                    LOGGER.info("Subscribed: " + id + " received");
                    Election election = repository.findById(id);
                    LOGGER.info("Election " + election.id() + " starting");
                });
    }
}
