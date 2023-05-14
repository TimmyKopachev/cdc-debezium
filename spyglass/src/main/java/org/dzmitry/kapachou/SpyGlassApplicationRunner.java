package org.dzmitry.kapachou;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpyGlassApplicationRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(SpyGlassApplicationRunner.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
