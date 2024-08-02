package com.kafka.service;

import com.kafka.constants.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaWikimediaConsumer {

    public final String topic = AppConstants.TOPIC_NAME;
    String groupId = AppConstants.GROUP_ID;
    private final static Logger log = LoggerFactory.getLogger(KafkaWikimediaConsumer.class);

    @KafkaListener(
            topics = "wikimedia",
            groupId = "wikimediaGroup"
    )
    public void consume(String eventMessage){
        log.info(String.format("Event message received -> %s",eventMessage));
        log.warn(String.format("Event message received -> %s",eventMessage));
    }
}
