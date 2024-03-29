package topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Auter MrDML
 * @Date 2019-10-08
 */
public class TopicProducer {

    public static void main(String[] args) throws Exception {

        // 1. 创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        // 2. 获取连接
        Connection connection = connectionFactory.createConnection();
        // 3. 启动连接
        connection.start();
        // 4. 获取session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 5. 创建主题对象
        Topic topic = session.createTopic("jms-topic");

        // 6. 创建消息的生产者
        MessageProducer producer = session.createProducer(topic);
        // 7. 创建消息
        TextMessage textMessage = session.createTextMessage("发送短信验证码");
        // 8. 发送消息
        producer.send(textMessage);
        // 9. 关闭资源
        producer.close();
        session.close();
        connection.close();

    }
}
