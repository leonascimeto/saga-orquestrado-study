package br.com.microservices.orchestrated.orchestratorservice.core.consumer;

import br.com.microservices.orchestrated.orchestratorservice.core.service.OrchestratorService;
import br.com.microservices.orchestrated.orchestratorservice.core.utils.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class SagaOrchestratorConsumer {
    private final JsonUtils jsonUtils;
    private final OrchestratorService orchestratorService;

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}",
            topics =  "${spring.kafka.topic.start-saga}")
    public void consumerStartSagaEvent(String payload){
        log.info("Receiving event {} from start-saga topic", payload);
        var event = jsonUtils.toEvent(payload);
        orchestratorService.startSaga(event);
    }

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}",
            topics =  "${spring.kafka.topic.orchestrator}")
    public void consumerOrchestratorEvent(String payload){
        log.info("Receiving event {} from orchestrator topic", payload);
        var event = jsonUtils.toEvent(payload);
        orchestratorService.continueSaga(event);
    }

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}",
            topics =  "${spring.kafka.topic.finish-success}")
    public void consumerFinishSuccessEvent(String payload){
        log.info("Receiving event {} from finish-success topic", payload);
        var event = jsonUtils.toEvent(payload);
        orchestratorService.finishSagaSuccess(event);
    }

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}",
            topics =  "${spring.kafka.topic.finish-fail}")
    public void consumerFinishFailEvent(String payload){
        log.info("Receiving event {} from finish-fail topic", payload);
        var event = jsonUtils.toEvent(payload);
        orchestratorService.finishSagaFail(event);
    }
}
