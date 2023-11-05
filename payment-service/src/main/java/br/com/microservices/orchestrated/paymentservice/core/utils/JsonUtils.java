package br.com.microservices.orchestrated.paymentservice.core.utils;

import br.com.microservices.orchestrated.paymentservice.core.dto.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@AllArgsConstructor
public class JsonUtils {
    private final ObjectMapper objectMapper;

    public  String toJson(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception ex){
            log.error(ex);
            return "";
        }
    }

    public Event toEvent(String json){
        try {
            return objectMapper.readValue(json, Event.class);
        } catch (Exception ex){
            log.error(ex);
            return null;
        }
    }
}
