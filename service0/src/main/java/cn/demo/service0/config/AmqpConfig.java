package cn.demo.service0.config;

//@Configuration
public class AmqpConfig {
//    @Bean
//    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//        return new RabbitAdmin(connectionFactory);
//    }
//
//    @Bean
//    public RabbitMessagingTemplate rabbitMessagingTemplate(RabbitTemplate rabbitTemplate) {
//        RabbitMessagingTemplate rabbitMessagingTemplate = new RabbitMessagingTemplate();
//        rabbitMessagingTemplate.setMessageConverter(new MappingJackson2MessageConverter());
//        rabbitMessagingTemplate.setRabbitTemplate(rabbitTemplate);
//        return rabbitMessagingTemplate;
//    }
//
//    @Bean
//    DirectExchange contractDirectExchange(RabbitAdmin rabbitAdmin) {
//        DirectExchange contractDirectExchange = new DirectExchange(RabbitmqExchange.TRANSACTION_EXCHANGE);
//        rabbitAdmin.declareExchange(contractDirectExchange);
//        return contractDirectExchange;
//    }
//
//    @Bean
//    Binding bindingExchangeContract(Queue queueContract, DirectExchange exchange, RabbitAdmin rabbitAdmin) {
//        Binding binding = BindingBuilder.bind(queueContract).to(exchange).with(RabbitmqQueue.TRANSACTION_QUEUE);
//        rabbitAdmin.declareBinding(binding);
//        return binding;
//    }
//
//    @Bean
//    Queue queueContract(RabbitAdmin rabbitAdmin) {
//        Queue queue = new Queue(RabbitmqQueue.TRANSACTION_QUEUE, true);
//        rabbitAdmin.declareQueue(queue);
//        return queue;
//    }
//
    public interface RabbitmqExchange {
        String TRANSACTION_EXCHANGE = "Transaction_Exchange";
    }

    public interface RabbitmqQueue {
        String TRANSACTION_QUEUE = "Transaction_queue";
    }
}
