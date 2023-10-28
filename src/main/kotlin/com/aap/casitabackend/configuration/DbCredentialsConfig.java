package com.aap.casitabackend.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "casita.db")
public class DbCredentialsConfig {

    private final String username;
    private final String password;

    private final String mongoUri;

    public DbCredentialsConfig() {
        this.username = System.getenv("CASITA_DB_USERNAME");
        this.password = System.getenv("CASITA_DB_PASSWORD");
        System.out.println(this.username);
        this.mongoUri = "mongodb+srv://" + this.username + ":" + this.password + "@casita.rwwsawg.mongodb.net/?retryWrites=true&w=majority";
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
