package cn.demo.service0.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

//@Configuration
public class AmqpConfig {
    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public RabbitMessagingTemplate rabbitMessagingTemplate(RabbitTemplate rabbitTemplate) {
        RabbitMessagingTemplate rabbitMessagingTemplate = new RabbitMessagingTemplate();
        rabbitMessagingTemplate.setMessageConverter(new MappingJackson2MessageConverter());
        rabbitMessagingTemplate.setRabbitTemplate(rabbitTemplate);
        return rabbitMessagingTemplate;
    }

    @Bean
    DirectExchange contractDirectExchange(RabbitAdmin rabbitAdmin) {
        DirectExchange contractDirectExchange = new DirectExchange(RabbitmqExchange.TRANSACTION_EXCHANGE);
        rabbitAdmin.declareExchange(contractDirectExchange);
        return contractDirectExchange;
    }

    @Bean
    Binding bindingExchangeContract(Queue queueContract, DirectExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queueContract).to(exchange).with(RabbitmqQueue.TRANSACTION_QUEUE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    Queue queueContract(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(RabbitmqQueue.TRANSACTION_QUEUE, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    public interface RabbitmqExchange {
        String TRANSACTION_EXCHANGE = "Transaction_Exchange";
    }

    public interface RabbitmqQueue {
        String TRANSACTION_QUEUE = "Transaction_queue";
    }
}
