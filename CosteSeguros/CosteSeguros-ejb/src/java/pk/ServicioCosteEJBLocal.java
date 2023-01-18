package pk;

import java.util.LinkedList;
import javax.ejb.Local;

@Local
public interface ServicioCosteEJBLocal {
    int CalcularCoste(int edad);
    LinkedList<String> ListOfertas();
}
