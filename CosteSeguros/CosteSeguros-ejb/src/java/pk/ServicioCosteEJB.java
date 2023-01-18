package pk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;

@Stateless
public class ServicioCosteEJB implements ServicioCosteEJBLocal {
    
    @Resource(mappedName="factoriaTopicos")
    private TopicConnectionFactory tcf;
    @Resource(mappedName="TopicoOfertas")
    private Topic t;


    @Override
    public int CalcularCoste(int edad) {
        int coste=100;
        if(edad >= 18 && edad <= 80){
            for (int i = 0; i < edad; i++) {
                coste+=2;
            }
            return coste;
        }else{
            return -1;
        }
    }

    @Override
    public LinkedList<String> ListOfertas() {
        File oferta = new File ("Ofertas.txt");
        FileReader fr = null;
        LinkedList <String> lista = new LinkedList();
        if (oferta.exists()){
            try {
                fr = new FileReader (oferta);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ServicioCosteEJB.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedReader br = new BufferedReader(fr);
            String linea;
            try {
                while((linea=br.readLine())!=null){
                    lista.add(linea);
                }
            } catch (IOException ex) {
                Logger.getLogger(ServicioCosteEJB.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                if(null != fr){   
                    try {     
                        fr.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ServicioCosteEJB.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }  
            }
        }
        return lista;   
    }
}
