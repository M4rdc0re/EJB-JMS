package pk;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "VerOfertas_servlet", urlPatterns = {"/VerOfertas_servlet"})
public class VerOfertas_servlet extends HttpServlet {
    @EJB
    private ServicioCosteEJBLocal servicioCosteEJB;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        LinkedList <String> lista = new LinkedList();
        lista = servicioCosteEJB.ListOfertas();
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VerOfertas_servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerOfertas_servlet at " + request.getContextPath() + "</h1>");
            out.println("<form>");
            out.println("<br>");
            out.println("<br>");
            out.println("<label for='Ofertas'>Offers: </label>");
            for (String linea : lista) {
                out.println("<br>");
                out.println(linea);
            }
            out.println("<br>");
            out.println("<br>");
            out.println("<a href='CalculoCoste_servlet'> Calculate cost");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "See offers";
    }
}
