package pk;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Main {

    public static void main(String[] args) {
        InitialContext iniCtx = null;
        TopicConnection cc = null;
        TopicSession qs = null;
        TopicPublisher tp = null;
        TopicSubscriber ts = null;
        Topic t = null;
        MsgListener listener = new MsgListener();
        TopicConnectionFactory tmp = null;
        String mensaje = "";
        try {
            iniCtx = new InitialContext();
            tmp = (TopicConnectionFactory) iniCtx.lookup("factoriaTopicos");
            t = (Topic) iniCtx.lookup("TopicoOfertas");
        } catch (NamingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cc = tmp.createTopicConnection();
            qs=cc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            tp = qs.createPublisher(t);
            ts = qs.createSubscriber(t);
            ts.setMessageListener(listener);
            cc.start();
        } catch (JMSException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scanner entrada=new Scanner(System.in);
        while(!"QUIT".equals(mensaje)){
            System.out.println("Enter the message of the offer, if you do not want to publish any offer enter QUIT to finish: ");
            mensaje = entrada.nextLine();
            if(!"QUIT".equals(mensaje) && mensaje.length()!=0){
                TextMessage msg = null;
                try {
                    msg = qs.createTextMessage(mensaje);
                    tp.publish(msg);
                } catch (JMSException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try {
            cc.close();
        } catch (JMSException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
