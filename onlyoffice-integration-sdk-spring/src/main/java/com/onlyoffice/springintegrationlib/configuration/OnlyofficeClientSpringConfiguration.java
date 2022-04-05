package com.onlyoffice.springintegrationlib.configuration;

import base.client.OnlyofficeDefaultCommandClient;
import base.client.OnlyofficeDefaultConverterClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.client.OnlyofficeCommandClient;
import core.client.OnlyofficeConverterClient;
import core.security.OnlyofficeJwtSecurity;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "onlyoffice.client")
public class OnlyofficeClientSpringConfiguration {
    private int commandConnectTimeout;
    private int commandResponseTimeout;
    private int converterConnectTimeout;
    private int converterResponseTimeout;

    @ConditionalOnMissingBean
    @Bean
    public OnlyofficeCommandClient onlyofficeCommandClient(
            OnlyofficeJwtSecurity jwtSecurity,
            ObjectMapper objectMapper
    ) {
        OnlyofficeDefaultCommandClient commandClient = new OnlyofficeDefaultCommandClient(jwtSecurity, objectMapper);
        if (this.commandConnectTimeout > 0) commandClient.setConnectTimeout(this.commandConnectTimeout);
        if (this.commandResponseTimeout > 0) commandClient.setResponseTimeout(this.commandResponseTimeout);
        return commandClient;
    }

    @ConditionalOnMissingBean
    @Bean
    public OnlyofficeConverterClient onlyofficeConverterClient(
            OnlyofficeJwtSecurity jwtSecurity,
            ObjectMapper objectMapper
    ) {
        OnlyofficeDefaultConverterClient converterClient = new OnlyofficeDefaultConverterClient(jwtSecurity, objectMapper);
        if (this.converterConnectTimeout > 0) converterClient.setConnectTimeout(this.converterConnectTimeout);
        if (this.converterResponseTimeout > 0) converterClient.setResponseTimeout(this.converterResponseTimeout);
        return converterClient;
    }
}
