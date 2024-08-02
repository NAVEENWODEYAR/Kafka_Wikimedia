package com.kafka.service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {

    private static final Logger log = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private final KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage() throws InterruptedException {
        String topicName = "wikimedia_recent_change";

        /*read real time stream data from event source,*/
        EventHandler backgroundEventHandler = new WikimediaChangesHandler(kafkaTemplate,topicName);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";

        EventSource.Builder builder = new EventSource.Builder(backgroundEventHandler, URI.create(url));
        EventSource eventSource = builder.build();
                    eventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }
}
