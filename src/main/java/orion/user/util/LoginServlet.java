package orion.user.util;

import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/servlet")
public class LoginServlet extends HttpServlet {
 

        private static final long serialVersionUID = 1L;

        @Override 
        protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        
                String email = request.getParameter("email");
                PrintWriter out = response.getWriter();
                out.println("<html><body>Empresa " + email + " cadastrada</body></html>");
                System.out.println(email);
        }
 
}