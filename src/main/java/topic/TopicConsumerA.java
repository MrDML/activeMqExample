package topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 订阅模式 接收方
 * @Auter MrDML
 * @Date 2019-10-08
 */
public class TopicConsumerA {

    public static void main(String[] args) throws Exception {

        // 1. 创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://120.27.2.108:61616");

        // 2. 获取连接
        Connection connection = connectionFactory.createConnection();

        //3. 启动连接
        connection.start();

        //4. 获取连接会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //5. 创建主题模式
        Topic topic = session.createTopic("jms-topic");

        //6. 创建消费者
        MessageConsumer consumer = session.createConsumer(topic);

        //7. 监听消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {

                TextMessage  textMessage =  (TextMessage)message;

                try {
                    System.out.println("A接收到消息 "+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        //8. 等待键盘输入
        System.in.read();
        //9. 关闭资源
        consumer.close();
        session.close();
        connection.close();
    }


}
