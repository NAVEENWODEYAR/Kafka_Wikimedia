package com.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfigTopic {

    @Bean
    public NewTopic newTopic(){
        return TopicBuilder.name("wikimedia_recent_change")
                .build();
    }
}
