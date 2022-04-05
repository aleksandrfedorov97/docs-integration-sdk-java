package com.onlyoffice.springintegrationlib.configuration;

import base.processor.OnlyofficeDefaultCallbackProcessor;
import base.processor.OnlyofficeDefaultEditorProcessor;
import base.processor.post.OnlyofficeDefaultCallbackPostProcessor;
import base.processor.post.OnlyofficeDefaultEditorPostProcessor;
import base.processor.pre.OnlyofficeDefaultCallbackPreProcessor;
import base.processor.pre.OnlyofficeDefaultEditorPreProcessor;
import base.registry.OnlyofficeDefaultCallbackRegistry;
import core.OnlyofficeIntegrationSDK;
import core.client.OnlyofficeCommandClient;
import core.client.OnlyofficeConverterClient;
import core.model.converter.request.ConverterRequest;
import core.processor.OnlyofficeCallbackProcessor;
import core.processor.OnlyofficeEditorProcessor;
import core.processor.post.OnlyofficeCallbackPostProcessor;
import core.processor.post.OnlyofficeEditorPostProcessor;
import core.processor.pre.OnlyofficeCallbackPreProcessor;
import core.processor.pre.OnlyofficeEditorPreProcessor;
import core.registry.OnlyofficeCallbackRegistry;
import core.runner.OnlyofficeCallbackRunner;
import core.runner.OnlyofficeEditorRunner;
import core.runner.callback.OnlyofficeDefaultCallbackRunner;
import core.runner.editor.OnlyofficeDefaultEditorRunner;
import core.security.OnlyofficeJwtSecurity;
import core.uploader.OnlyofficeUploaderRunner;
import core.util.OnlyofficeConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;

@Configuration
public class OnlyofficeCoreSpringConfiguration {
    @ConditionalOnMissingBean
    @Bean
    public OnlyofficeCallbackRegistry onlyofficeCallbackRegistry() {
        return new OnlyofficeDefaultCallbackRegistry();
    }

    @ConditionalOnMissingBean(value = OnlyofficeEditorProcessor.class)
    @Bean
    public OnlyofficeEditorProcessor onlyofficeEditorProcessor(
            OnlyofficeConfig configUtil,
            OnlyofficeJwtSecurity jwtManager
    ) {
        return new OnlyofficeDefaultEditorProcessor(configUtil, jwtManager);
    }

    @Bean
    public OnlyofficeEditorPreProcessor defaultConfigPreProcessor() {
        return new OnlyofficeDefaultEditorPreProcessor();
    }

    @ConditionalOnMissingBean(value = OnlyofficeEditorPreProcessor.class)
    @Bean
    public OnlyofficeEditorPreProcessor emptyConfigOnlyofficePreProcessor() {
        return new OnlyofficeEditorPreProcessor() {
            @Override
            public String preprocessorName() {
                return UUID.randomUUID().toString();
            }
        };
    }

    @ConditionalOnMissingBean(value = OnlyofficeEditorPostProcessor.class)
    @Bean
    public OnlyofficeEditorPostProcessor emptyConfigOnlyofficePostProcessor() {
        return new OnlyofficeDefaultEditorPostProcessor();
    }

    @ConditionalOnMissingBean(value = OnlyofficeCallbackProcessor.class)
    @Bean
    public OnlyofficeCallbackProcessor onlyofficeCallbackProcessor(
            OnlyofficeCallbackRegistry registry,
            OnlyofficeJwtSecurity jwtManager
    ) {
        return new OnlyofficeDefaultCallbackProcessor(registry, jwtManager);
    }

    @Bean
    public OnlyofficeCallbackPreProcessor baseCallbackPreProcessor() {
        return new OnlyofficeDefaultCallbackPreProcessor();
    }

    @ConditionalOnMissingBean(value = OnlyofficeCallbackPreProcessor.class)
    @Bean
    public OnlyofficeCallbackPreProcessor emptyCallbackPreProcessor() {
        return new OnlyofficeCallbackPreProcessor() {
            @Override
            public String preprocessorName() {
                return UUID.randomUUID().toString();
            }
        };
    }

    @ConditionalOnMissingBean(value = OnlyofficeCallbackPostProcessor.class)
    @Bean
    public OnlyofficeCallbackPostProcessor emptyCallbackPostProcessor() {
        return new OnlyofficeDefaultCallbackPostProcessor();
    }

    @ConditionalOnMissingBean(value = OnlyofficeEditorRunner.class)
    @Bean
    public OnlyofficeEditorRunner onlyofficeEditorRunner(
            OnlyofficeEditorProcessor editorProcessor,
            List<OnlyofficeEditorPreProcessor> editorPreProcessors,
            List<OnlyofficeEditorPostProcessor> editorPostProcessors
    ) {
        return new OnlyofficeDefaultEditorRunner(editorProcessor, editorPreProcessors, editorPostProcessors);
    }

    @ConditionalOnMissingBean(value = OnlyofficeCallbackRunner.class)
    @Bean
    public OnlyofficeCallbackRunner onlyofficeCallbackRunner(
            OnlyofficeCallbackProcessor callbackProcessor,
            List<OnlyofficeCallbackPreProcessor> callbackPreProcessors,
            List<OnlyofficeCallbackPostProcessor> callbackPostProcessors
    ) {
        return new OnlyofficeDefaultCallbackRunner(callbackProcessor, callbackPreProcessors, callbackPostProcessors);
    }

    @Bean
    public OnlyofficeIntegrationSDK integrationSDK(
            OnlyofficeCallbackRunner callbackRunner,
            OnlyofficeEditorRunner editorRunner,
            OnlyofficeUploaderRunner<ConverterRequest> converterRunner,
            OnlyofficeCommandClient commandClient,
            OnlyofficeConverterClient converterClient
    ) {
        return new OnlyofficeIntegrationSDK(callbackRunner, editorRunner, converterRunner, commandClient, converterClient);
    }
}
