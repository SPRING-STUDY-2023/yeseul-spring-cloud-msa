package com.example.messageQueue;

import com.example.catalogservice.jpa.CatalogEntity;
import com.example.catalogservice.jpa.CatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {
  private final CatalogRepository catalogRepository;

  @KafkaListener(topics = "example-catalog-topic")
  @Transactional
  public void updateQty(String kafkaMessage) {
    log.info("Kafka Message: ->" + kafkaMessage);

    Map<Object, Object> map = new HashMap<>();
    ObjectMapper mapper = new ObjectMapper();
    try {
      map = mapper.readValue(kafkaMessage, new TypeReference<>() {}); // kafkaMessage의 String 타입을 JSON 타입으로 변환
    } catch (JsonProcessingException ex) {
      ex.printStackTrace();
    }

    CatalogEntity entity = catalogRepository.findByProductId((String)map.get("productId"));
    if (entity != null) {
      entity.setStock(entity.getStock() - (Integer)map.get("qty"));
      catalogRepository.save(entity);
    }

  }



}
