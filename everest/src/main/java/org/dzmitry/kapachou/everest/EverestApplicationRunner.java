package org.dzmitry.kapachou.everest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dzmitry.kapachou.everest.entity.EverestPortfolio;
import org.dzmitry.kapachou.everest.repo.PortfolioRepository;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@EnableScheduling
@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class EverestApplicationRunner {

    private final PortfolioRepository portfolioRepository;

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(EverestApplicationRunner.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

    @Scheduled(fixedDelay = 15000)
    public void pushRequestToSaveDataInEverest() {
        try {
            portfolioRepository.save(createEverestPortfolio());
            log.info("portfolios #: [{}]", portfolioRepository.count());
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
    }

    private static EverestPortfolio createEverestPortfolio() {
        EverestPortfolio ep = new EverestPortfolio();
        ep.setName(String.format("portfolio#-%s", UUID.randomUUID().getLeastSignificantBits()));
        ep.setCash(ThreadLocalRandom.current().nextInt(0, 100));
        log.info("new portfolio has been created: [{}]", ep);
        return ep;
    }
}
