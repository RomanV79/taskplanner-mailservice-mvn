package ru.vlasov.taskplannermailservicemvn.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.vlasov.taskplannermailservicemvn.dto.MessageDto;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerService {

    private final EmailSenderService emailSenderService;

    @RabbitListener(queues = "${app.rabbit.queue}")
    public void consume(MessageDto messageDto){
        log.info("Received message ... -> " + messageDto);
        emailSenderService.sendEmail(messageDto);
    }
}
