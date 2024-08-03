package com.kafka.service;

import com.kafka.entity.WikimediaData;
import com.kafka.repository.WikimediaDataRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaWikimediaConsumer {
    private final static Logger log = LoggerFactory.getLogger(KafkaWikimediaConsumer.class);

    @Autowired
    private WikimediaDataRepo wikimediaDataRepo;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(String eventMessage){
        log.info(String.format("Event message received -> %s",eventMessage));

        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikimediaData(eventMessage);

        wikimediaDataRepo.save(wikimediaData);
    }
}
