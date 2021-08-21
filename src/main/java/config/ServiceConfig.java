package config;

import client.IHttpClient;
import client.RestAssuredClient;
import client.RestTemplateClient;
import utils.PropertiesReader;

public class ServiceConfig {
    public static final String HOST = ServiceConfig.getHost();
    public static final IHttpClient CLIENT = new RestTemplateClient();

    private static String getHost() {
        return new PropertiesReader("config.properties").get("host");
    }
}
