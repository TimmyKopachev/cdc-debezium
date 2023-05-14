package org.dzmitry.kapachou.everest;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
public class EverestApplicationRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(EverestApplicationRunner.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

    @Scheduled(fixedDelay = 5000)
    public void pushRequestToSaveDataInEverest() {

    }
}
