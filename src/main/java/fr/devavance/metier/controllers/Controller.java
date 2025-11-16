package fr.devavance.metier.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Controller", urlPatterns = {"/" + IController.CONTROLLER})
public class Controller extends HttpServlet implements IController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter(KEY_ACTION);

        if (IController.ACTION_LOGOUT.equals(action)) {
            HttpSession session = request.getSession(false); // Récupère la session SANS la créer
            if (session != null) {
                session.invalidate(); // Détruit la session
            }
            dispatch(VUE_LOGIN, request, response); // Renvoie à la page de login
            return; // Important: arrêter l'exécution ici
        }

        if (is_connected(request)) {
            dispatch(VUE_CHOICE, request, response);
        } else {
            dispatch(VUE_LOGIN, request, response);
        }
    }

   

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (!is_connected(request)) {
            dispatch(VUE_LOGIN, request, response);
            return;
        }

        String action = request.getParameter(KEY_ACTION);

        switch (action) {
            case SEANCE_COURS:
                dispatch(VUE_COURS, request, response);
                break;
            case SEANCE_TD:
                dispatch(VUE_TD, request, response);
                break;
            case SEANCE_TP:
                dispatch(VUE_TP, request, response);
                break;
            default:
                dispatch(VUE_CHOICE, request, response);
                break;
        }
    }

    private boolean is_connected(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return false;

        Object status = session.getAttribute(KEY_IS_CONNECTED);
        return (status != null && status.equals(CONNECTED));
    }
    
    @Override
    public void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }
}