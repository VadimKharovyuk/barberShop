//package com.example.barbershop.config;
//
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
//import org.springframework.amqp.support.converter.Jackson2JavaTypeMapper;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//@Configuration
//public class RabbitMQConfig {
//
//    public static final String QUEUE_NAME = "LucckyQueue";
//
//    @Bean
//    public Queue emailQueue() {
//        return new Queue(QUEUE_NAME, true);
//    }
//
//    @Bean
//    public Jackson2JsonMessageConverter jsonMessageConverter() {
//        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
//        DefaultJackson2JavaTypeMapper javaTypeMapper = new DefaultJackson2JavaTypeMapper();
//        javaTypeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.INFERRED);
//
//        // Разрешенные классы для десериализации
//        Map<String, Class<?>> idClassMapping = new HashMap<>();
//        idClassMapping.put("com.example.barbershop.config.EmailMessage", EmailMessage.class);
//
//        javaTypeMapper.setIdClassMapping(idClassMapping);
//        converter.setJavaTypeMapper(javaTypeMapper);
//
//        return converter;
//    }
//}
//

package com.example.barbershop.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "LucckyQueue";
    public static final String EXCHANGE_NAME = "Lucky";

    @Bean
    public Queue emailQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public FanoutExchange emailExchange() {
        return ExchangeBuilder.fanoutExchange(EXCHANGE_NAME).durable(true).build();
    }

    @Bean
    public Binding binding(Queue emailQueue, FanoutExchange emailExchange) {
        return BindingBuilder.bind(emailQueue).to(emailExchange);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        DefaultJackson2JavaTypeMapper javaTypeMapper = new DefaultJackson2JavaTypeMapper();
        javaTypeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.INFERRED);

        // Разрешенные классы для десериализации
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("com.example.barbershop.config.EmailMessage", EmailMessage.class);

        javaTypeMapper.setIdClassMapping(idClassMapping);
        converter.setJavaTypeMapper(javaTypeMapper);

        return converter;
    }
}


