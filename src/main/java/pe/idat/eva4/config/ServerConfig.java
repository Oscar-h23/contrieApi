package pe.idat.eva4.config;

import org.springframework.context.annotation.Configuration;
import pe.idat.eva4.controller.CountryController;
import pe.idat.eva4.security.apiKeyAuthFilter;
import org.glassfish.jersey.server.ResourceConfig;


@Configuration
public class ServerConfig extends ResourceConfig {
    public ServerConfig() {
        register(apiKeyAuthFilter.class);
        register(CountryController.class);
    }
}
