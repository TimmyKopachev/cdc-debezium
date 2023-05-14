package org.dzmitry.kapachou.everest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dzmitry.kapachou.everest.repo.PortfolioRepository;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class EverestApplicationRunner {

    private final PortfolioRepository portfolioRepository;

    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(EverestApplicationRunner.class).bannerMode(Banner.Mode.OFF).run(args);
    }

    @Scheduled(fixedDelay = 15000)
    public void pushRequestToSaveDataInEverest() {
        portfolioRepository.findAll().forEach(p -> log.info("portfolio: {}", p));
    }
}
