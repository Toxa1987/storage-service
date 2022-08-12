package com.epam.esm.storageservice.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aws")
public class S3Config {
    @Value("${aws.endpoint}")
    private String endpoint;
    @Value("${aws.region}")
    private String region;

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new EndpointConfiguration(endpoint, region))
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withPathStyleAccessEnabled(true)
                .build();
    }
}