package br.com.sicredi.consumer.controller;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class Publisher {

    private final RabbitTemplate rabbitTemplate;
    private final Binding binding;

    @Autowired
    public Publisher(RabbitTemplate rabbitTemplate, Binding binding) {
        this.rabbitTemplate = rabbitTemplate;
        this.binding = binding;
    }

    @GetMapping(value = "/sincronizar/{arquivo}")
    @ResponseStatus(code = HttpStatus.OK)
    public String send(@PathVariable("arquivo") final String arquivo) {
        rabbitTemplate.convertAndSend(binding.getExchange(), binding.getRoutingKey(), arquivo);
        return "Arquivo criado com sucesso";
    }

}
