package org.dzmitry.kapachou.spyglass.config;

import io.debezium.connector.postgresql.PostgresConnectorConfig;
import lombok.AllArgsConstructor;
import org.dzmitry.kapachou.spyglass.service.EverestDebeziumListener;
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
        File dbHistoryTempFile = File.createTempFile("dbhistory_everest_pg_", ".dat");
        return io.debezium.config.Configuration.create()
                .with("name", "everest-postgres-connector")
                .with("connector.class", "io.debezium.connector.postgresql.PostgresConnector")
                .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename", offsetStorageTempFile.getAbsolutePath())
                .with("offset.flush.interval.ms", "60000")

                .with("database.hostname", everestDbProperties.getHostname())
                .with("database.port", everestDbProperties.getPort())
                .with("database.user", everestDbProperties.getUser())
                .with("database.password", everestDbProperties.getPassword())
                .with("database.dbname", everestDbProperties.getDbName())

                .with("database.include", "public")
                .with("include.schema.changes", "false")
                .with("database.allowPublicKeyRetrieval", "true")
                .with("database.server.name", "everest-db")
                .with("database.history", "io.debezium.relational.history.FileDatabaseHistory")
                .with("database.history.file.filename", dbHistoryTempFile.getAbsolutePath())
                .with("plugin.name", "pgoutput")
                .with("slot.name", everestDbProperties.getDbName())
                .with(PostgresConnectorConfig.SNAPSHOT_MODE, PostgresConnectorConfig.SnapshotMode.NEVER)
                .build();
    }

}
