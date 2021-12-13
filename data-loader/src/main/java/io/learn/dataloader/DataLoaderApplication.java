package io.learn.dataloader;

import java.nio.file.Path;

import com.datastax.oss.driver.api.core.CqlSessionBuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import io.learn.dataloader.connection.DataStaxConnectionProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties( DataStaxConnectionProperties.class)

public class DataLoaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataLoaderApplication.class, args);
	}

	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxConnectionProperties astraProperties){
			Path bundle = astraProperties.getSecureConnectBundle().toPath();
			return   builder -> builder.withCloudSecureConnectBundle(bundle);
	}

}
