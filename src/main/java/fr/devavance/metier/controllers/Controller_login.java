package fr.devavance.metier.controllers;

import fr.devavance.metier.exceptions.CredentialException;
import fr.devavance.metier.exceptions.LoginException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Controller_login", urlPatterns = {"/controller_login"})
public class Controller_login extends HttpServlet implements IController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (is_connected(request)) {
            dispatch(CONTROLLER, request, response);
        } else {
            dispatch(VUE_LOGIN, request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process_login_user(request, response);
    }

    private boolean is_connected(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return false;

        Object status = session.getAttribute(KEY_IS_CONNECTED);
        return (status != null && status.equals(CONNECTED));
    }

    
    private void process_login_user(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter(KEY_LOGIN);
        String password = request.getParameter(KEY_PASSWORD);

        try {
            verify_credential(login, password);
            
            HttpSession session = request.getSession(true);
            session.setAttribute(KEY_IS_CONNECTED, CONNECTED);
            session.setAttribute(KEY_LOGIN, login);

            
            dispatch(VUE_CHOICE, request, response);
         
        } catch (CredentialException | LoginException e) {
            request.setAttribute("error", e.getMessage());
            dispatch(VUE_LOGIN, request, response);
        }
    }

    private void verify_credential(String login, String password)
            throws CredentialException, LoginException {

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            throw new CredentialException(CREDENTIALS_EMPTY_MESSAGE);
        }

        if (!"admin".equals(login) || !"admin".equals(password)) {
            throw new LoginException(ERROR_CREDENTIALS_MESSAGE);
        }
    }

    @Override
    public void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }
}