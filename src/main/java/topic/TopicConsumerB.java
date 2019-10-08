package topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 订阅模式  接收方
 * @Auter MrDML
 * @Date 2019-10-08
 */
public class TopicConsumerB {

    public static void main(String[] args)throws Exception {

        // 1. 创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        // 2. 获取连接
        Connection connection = connectionFactory.createConnection();

        //3. 启动连接
        connection.start();

        //4. 获取会话 (参数1：是否启动事务,参数2：消息确认模式)
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //5. 创建主题模式
        Topic topic = session.createTopic("jms-topic");

        //5. 创建消费者
        MessageConsumer consumer = session.createConsumer(topic);

        // 6. 监听消息
       consumer.setMessageListener(new MessageListener() {
           @Override
           public void onMessage(Message message) {
               TextMessage textMessage = (TextMessage)message;

               try {

                   System.out.println("B接收到消息 "+textMessage.getText());
               } catch (JMSException e) {
                   e.printStackTrace();
               }
           }
       });

       //8. 等待键盘输入
       System.in.read();
       //9. 关闭连接
       consumer.close();
       session.close();
       connection.close();
    }

}
