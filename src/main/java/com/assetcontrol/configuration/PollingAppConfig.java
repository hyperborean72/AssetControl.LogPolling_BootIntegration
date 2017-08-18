package com.assetcontrol.configuration;


import com.assetcontrol.integration.filter.LastModifiedFileFilter;
import com.assetcontrol.integration.processor.FileProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.CompositeFileListFilter;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;

@Configuration

/*no need to explicitly enable @ComponentScan
* in case of @SpringBootApplication(scanBasePackages={"xx.xx.."})*/
@ComponentScan(basePackages = { "com.assetcontrol.*" })

@EnableIntegration
@EnableScheduling

@PropertySource("classpath:config.properties")

public class PollingAppConfig {

    @Value("${log.location}")
    String logLocation;

    static String FILE_PATTERN = "*.txt";

    //To resolve ${} in @Value
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }


    @Bean
    //By default, the bean name will be the same as the method name
    public MessageChannel logFileChannel() {
        return new DirectChannel();
    }
    /*
        inbound channel adapter
        returns file reading message source responsible for polling the file system directory for files
        and creating a message from each found file

        SimplePatternFileListFilter: Filter provided by Spring. Only files with the specified extension will be polled. In this case, only text files will be accepted
        LastModifiedFileFilter: Custom filter. This filter keeps track of already polled files and will filter out files not modified since the last time it was tracked.
    */
    @Bean
    @InboundChannelAdapter(value = "logFileChannel", poller = @Poller(fixedDelay = "${polling.interval}"))
    public MessageSource<File> fileReadingMessageSource() {
        FileReadingMessageSource sourceReader = new FileReadingMessageSource();

        CompositeFileListFilter<File> filters = new CompositeFileListFilter<File>();
        filters.addFilter(new SimplePatternFileListFilter(FILE_PATTERN));
        filters.addFilter(new LastModifiedFileFilter());

        sourceReader.setDirectory(new File(new File(logLocation).getParent()));
        sourceReader.setFilter(filters);

        return sourceReader;
    }

    @Bean
    public FileToStringTransformer fileToStringTransformer() {
        return new FileToStringTransformer();
    }

    @Bean
    public FileProcessor fileProcessor() {
        return new FileProcessor();
    }

    // https://github.com/spring-projects/spring-integration-java-dsl/wiki/spring-integration-java-dsl-reference#inboundAdapters
    @Bean
    public IntegrationFlow processFileFlow() {
        return IntegrationFlows
                .from("logFileChannel")
                .transform(fileToStringTransformer())
                .handle("fileProcessor", "process").get();//bean name | method
    }
}