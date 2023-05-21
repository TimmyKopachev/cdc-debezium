package org.dzmitry.kapachou.spyglass.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(value = EverestDbConnectionProperties.class)
public class DebeziumConnectorConfig {

    final EverestDbConnectionProperties everestDbProperties;

    @Bean
    public io.debezium.config.Configuration everestDbConnector() throws IOException {
        File offsetStorageTempFile = File.createTempFile("offsets_everest_pg_", ".dat");
        return io.debezium.config.Configuration.create()
                .with("connector.class", "io.debezium.connector.postgresql.PostgresConnector")
                .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename", offsetStorageTempFile.getAbsolutePath())
                .with("offset.flush.interval.ms", 60000)
                .with("name", "student-postgres-connector")

                .with("database.server.name", String.format("%s-%s", everestDbProperties.getHostname(), everestDbProperties.getDbName()))
                .with("database.hostname", everestDbProperties.getHostname())
                .with("database.port", everestDbProperties.getPort())
                .with("database.user", everestDbProperties.getUser())
                .with("database.password", everestDbProperties.getPassword())
                .with("database.dbname", everestDbProperties.getDbName())

                .with("plugin.name", "pgoutput")

                .build();
    }

}
