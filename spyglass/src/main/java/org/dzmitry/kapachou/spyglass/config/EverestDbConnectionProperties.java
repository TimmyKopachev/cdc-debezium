package org.dzmitry.kapachou.spyglass.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "everest.db.connection")
public class EverestDbConnectionProperties {

    private String hostname;
    private String port;
    private String user;
    private String password;
    private String dbName;


}
