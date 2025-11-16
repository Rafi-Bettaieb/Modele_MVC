package fr.devavance.metier.views.interfaces;

import fr.devavance.metier.controllers.IController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginView", urlPatterns = {"/login"})
public class LoginView extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        String errorMessage = (String) request.getAttribute("error");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Connexion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Veuillez vous identifier</h1>");
            
            if (errorMessage != null) {
                out.println("<p style='color:red;'>" + errorMessage + "</p>");
            }
            out.println("<form action='" + IController.CONTROLLER_LOGIN + "' method='POST'>");
            out.println("<div>");
            out.println("   <label for='login'>Login :</label>");
            out.println("   <input type='text' id='login' name='" + IController.KEY_LOGIN + "'>");
            out.println("</div>");
            out.println("<div>");
            out.println("   <label for='password'>Mot de passe :</label>");
            out.println("   <input type='password' id='password' name='" + IController.KEY_PASSWORD + "'>");
            out.println("</div>");
            out.println("<div>");
            out.println("   <input type='submit' value='Se connecter'>");
            out.println("</div>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * La méthode POST n'est pas censée être appelée directement sur la vue.
     * On redirige vers doGet au cas où.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}