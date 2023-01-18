package pk;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

class MsgListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        TextMessage mensaje = (TextMessage) message;
        try {
            System.out.println("Offer received: "+mensaje.getText());
        } catch (JMSException ex) {
            Logger.getLogger(MsgListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
