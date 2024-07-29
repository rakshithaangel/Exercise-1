class Configuration {
    private String databaseUrl;
    private int databasePort;
    private String databaseUsername;
    private String databasePassword;
    private boolean enableLogging;

    public Configuration(String databaseUrl, int databasePort, String databaseUsername, String databasePassword, boolean enableLogging) {
        this.databaseUrl = databaseUrl;
        this.databasePort = databasePort;
        this.databaseUsername = databaseUsername;
        this.databasePassword = databasePassword;
        this.enableLogging = enableLogging;
    }

    // Getters
    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public int getDatabasePort() {
        return databasePort;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public boolean isEnableLogging() {
        return enableLogging;
    }
}

class ConfigurationBuilder {
    private String databaseUrl;
    private int databasePort;
    private String databaseUsername;
    private String databasePassword;
    private boolean enableLogging;

    public ConfigurationBuilder setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
        return this;
    }

    public ConfigurationBuilder setDatabasePort(int databasePort) {
        this.databasePort = databasePort;
        return this;
    }

    public ConfigurationBuilder setDatabaseUsername(String databaseUsername) {
        this.databaseUsername = databaseUsername;
        return this;
    }

    public ConfigurationBuilder setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
        return this;
    }

    public ConfigurationBuilder setEnableLogging(boolean enableLogging) {
        this.enableLogging = enableLogging;
        return this;
    }

    public Configuration build() {
        return new Configuration(databaseUrl, databasePort, databaseUsername, databasePassword, enableLogging);
    }
}

public class BuilderDemo {
    public static void main(String[] args) {
        Configuration config = new ConfigurationBuilder()
                .setDatabaseUrl("jdbc:mysql://localhost:3306/mydb")
                .setDatabasePort(3306)
                .setDatabaseUsername("myuser")
                .setDatabasePassword("mypassword")
                .setEnableLogging(true)
                .build();

        // Use the configuration object
        System.out.println("Database URL: " + config.getDatabaseUrl());
        System.out.println("Database Port: " + config.getDatabasePort());
        System.out.println("Database Username: " + config.getDatabaseUsername());
        System.out.println("Enable Logging: " + config.isEnableLogging());
    }
}