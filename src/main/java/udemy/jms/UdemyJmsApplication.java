package udemy.jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@SpringBootApplication
public class UdemyJmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UdemyJmsApplication.class, args);

        InitialContext initialContext = null;
        try {
            initialContext = new InitialContext();
            ConnectionFactory cf = (ConnectionFactory) initialContext.lookup("ConnectionFactory");
            Connection connection = cf.createConnection();
            Session session = connection.createSession();
            Queue queue = (Queue) initialContext.lookup("queue.queue/myQueue");
            MessageProducer producer = session.createProducer(queue);
            TextMessage message = session.createTextMessage("I am a the creator.");
            producer.send(message);
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }

    }
}
