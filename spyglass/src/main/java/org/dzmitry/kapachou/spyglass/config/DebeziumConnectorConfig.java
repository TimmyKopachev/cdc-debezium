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
        File offsetStorageTempFile = File.createTempFile("offsets_", ".dat");
        File dbHistoryTempFile = File.createTempFile("dbhistory_", ".dat");
        return io.debezium.config.Configuration.create()
                .with("name", "customer-mysql-connector")
                .with("connector.class", "io.debezium.connector.mysql.MySqlConnector")
                .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename", offsetStorageTempFile.getAbsolutePath())
                .with("offset.flush.interval.ms", "60000")

                .with("database.hostname", everestDbProperties.getHostname())
                .with("database.port", everestDbProperties.getPort())
                .with("database.user", everestDbProperties.getUser())
                .with("database.password", everestDbProperties.getPassword())
                .with("database.dbname", everestDbProperties.getDbName())
                .with("database.include.list", everestDbProperties.getDbName())

                .with("include.schema.changes", "false")
                .with("database.allowPublicKeyRetrieval", "true")
                .with("database.server.id", "10181")
                .with("database.server.name", "customer-mysql-db-server")
                .with("database.history", "io.debezium.relational.history.FileDatabaseHistory")
                .with("database.history.file.filename", dbHistoryTempFile.getAbsolutePath())
                .build();
    }
}
