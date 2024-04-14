package infrastructure.scheduler;

import domain.Election;
import domain.annotations.Principal;
import infrastructure.repositories.RedisElectionRepository;
import infrastructure.repositories.SQLElectionRepository;
import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Sync {
    private final SQLElectionRepository sqlRepository;
    private final RedisElectionRepository redisRepository;

    public Sync(@Principal SQLElectionRepository sqlRepository, RedisElectionRepository redisRepository) {
        this.sqlRepository = sqlRepository;
        this.redisRepository = redisRepository;
    }

    @Scheduled(cron = "*/10 * * * * ?")
    void sync() {
        sqlRepository.findAll().forEach(election -> {
            Election updated = redisRepository.sync(election);
            sqlRepository.sync(updated);
        });
    }
}
