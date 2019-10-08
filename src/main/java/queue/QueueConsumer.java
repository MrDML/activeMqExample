package queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 *  http://localhost:8161(管理端口) 浏览器访问
 *  tcp://127.0.0.1:61616(服务端口) 代码访问
 *  =====================================
 *  http://127.0.0.1:8161(管理端口)
 *  tcp://127.0.0.1:61616(服务端口)
 * 点对点模式 接收方
 * @Auter MrDML
 * @Date 2019-10-08
 */
public class QueueConsumer {

    public static void main(String[] args) throws Exception{
        //1.创建连接工厂
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        //2.获取连接
        Connection connection = connectionFactory.createConnection();
        //3.启动连接
        connection.start();
        //4.获取session  (参数1：是否启动事务,参数2：消息确认模式)
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建队列对象
        Queue queue = session.createQueue("msg-queue");
        //6.创建消息消费
        MessageConsumer consumer = session.createConsumer(queue);

        //7.监听消息
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage=(TextMessage)message;
                try {
                    System.out.println("接收到消息："+textMessage.getText());
                } catch (JMSException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        //8.等待键盘输入
        System.in.read();
        //9.关闭资源
        consumer.close();
        session.close();
        connection.close();

    }
}























