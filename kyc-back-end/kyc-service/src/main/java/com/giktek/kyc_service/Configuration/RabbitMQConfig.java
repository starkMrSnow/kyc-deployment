package com.giktek.kyc_service.Configuration;

// import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
// import org.springframework.amqp.rabbit.connection.ConnectionFactory;
// import org.springframework.amqp.rabbit.core.RabbitTemplate;
// import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
// import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;


//  Create an exchange    
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Queue Queue(){
        return new Queue(queue);
    }

    @Bean
    public Binding Binding(){
        return BindingBuilder.
        bind(Queue()).
        to(exchange()).
        with(routingKey);
    }

//  Converter to allow RabbitTemplate to send JSON messages
    // @Bean
    // public MessageConverter converter(){
    //     return new Jackson2JsonMessageConverter(); 
    // }


//  Creates a rabbitTemoplate that can send JSON messages
    // @Bean
    // public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
    //     RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    //     rabbitTemplate.setMessageConverter(converter());
    //     return rabbitTemplate;

    // }


//  Spring autoconfigures all these Beans
    //  ConnectionFactory
    //  RabbitTemplate
    //  RabbitAdmin

}
