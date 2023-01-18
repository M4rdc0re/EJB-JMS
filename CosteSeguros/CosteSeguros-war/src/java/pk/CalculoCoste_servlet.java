package pk;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CalculoCoste_servlet", urlPatterns = {"/CalculoCoste_servlet"})
public class CalculoCoste_servlet extends HttpServlet {
    @EJB
    private ServicioCosteEJBLocal servicioCosteEJB;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String nombre;
        String sedad;
        int edad=0;
        int coste=0;
        nombre=request.getParameter("name");
        sedad=request.getParameter("edad");
        if (sedad != null && sedad != "") edad=Integer.parseInt(sedad);
        coste=servicioCosteEJB.CalcularCoste(edad); // Invocacion a session bean
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CalculoCoste_servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CalculoCoste_servlet at " + request.getContextPath() + "</h1>");
            out.println("<form>");
            out.println("<br>");
            out.println("<br>");
            out.println("<label for='Nombre'>Name:</label><br>");
            out.println("<input type='text' id='name' name='name' required='required'><br>");
            out.println("<label for='Edad'>Age:</label><br>");
            out.println("<input type='number' id='edad' name='edad' min='18' max='80'>");
            out.println("<input type='submit'>");
            out.println("<br>");
            out.println("<br>");
            out.println("<label for='Coste'>Cost: </label>");
            out.println(coste);
            out.println("<br>");
            out.println("<br>");
            out.println("<a href='VerOfertas_servlet'> See offers");
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
        return "Calculate cost";
    }
}
