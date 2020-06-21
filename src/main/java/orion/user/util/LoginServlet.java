package orion.user.util;

import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
 
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public void doGet(
                HttpServletRequest request,
                HttpServletResponse response) throws ServletException, IOException {

                 // read form fields
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                        // do some processing here...
         
                // get response writer
                PrintWriter writer = response.getWriter();
                
                // build HTML code
                String htmlRespone = "<html>";
                htmlRespone += "<h2>Your email is: " + email + "<br/>";      
                htmlRespone += "Your password is: " + password + "</h2>";    
                htmlRespone += "</html>";
                
                // return response
                writer.println(htmlRespone);
                }

        protected void doPost(
                        HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
         
        // read form fields
        String email = request.getParameter("email");
        String password = request.getParameter("password");
         
        System.out.println("email: " + email);
        System.out.println("password: " + password);
 
        // do some processing here...
         
        // get response writer
        PrintWriter writer = response.getWriter();
         
        // build HTML code
        String htmlRespone = "<html>";
        htmlRespone += "<h2>Your email is: " + email + "<br/>";      
        htmlRespone += "Your password is: " + password + "</h2>";    
        htmlRespone += "</html>";
         
        // return response
        writer.println(htmlRespone);
         
    }
 
}