//package com.riderguru.rider_guru.libs;
//
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AmqpIntegrationConfiguration {
//
//    private static final String QUEUE = "user_queue";
//
//    @Bean
//    Binding binding(Queue queue, Exchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(QUEUE).noargs();
//    }
//
//    @Bean
//    Exchange exchange() {
//        return ExchangeBuilder.directExchange(QUEUE).build();
//    }
//
//    @Bean
//    Queue queue() {
//        return QueueBuilder.durable(QUEUE).build();
//    }
//
//}
