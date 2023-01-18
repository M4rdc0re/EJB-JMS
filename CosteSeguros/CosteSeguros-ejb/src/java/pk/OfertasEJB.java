package pk;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "TopicoOfertas"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "TopicoOfertas"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "TopicoOfertas"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class OfertasEJB implements MessageListener {
    
    public OfertasEJB() {
    }
    
    @Override
    public void onMessage(Message message) {
        File oferta = new File("Ofertas.txt");
        TextMessage mensaje = (TextMessage) message;
        FileWriter fw = null;
        try {
            fw = new FileWriter(oferta, true);
        } catch (IOException ex) {
            Logger.getLogger(OfertasEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedWriter bw = new BufferedWriter(fw);
        if (!oferta.exists()){
            try {
                oferta.createNewFile();
                try {
                    bw.write(mensaje.getText());
                } catch (IOException | JMSException ex) {
                    Logger.getLogger(OfertasEJB.class.getName()).log(Level.SEVERE, null, ex);
                } finally{
                try {
                    bw.close();
                } catch (IOException ex) {
                    Logger.getLogger(OfertasEJB.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            } catch (IOException ex) {
                Logger.getLogger(OfertasEJB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                bw.write("\r\n" + mensaje.getText());
            } catch (IOException | JMSException ex) {
                Logger.getLogger(OfertasEJB.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    bw.close();
                } catch (IOException ex) {
                    Logger.getLogger(OfertasEJB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
