/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fr.devavance.metier.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface implémentée par les contrôleur
 * @author B. LEMAIRE
 * @version 2023
 */
public interface IController {
     
    // Vues gérés par les controleurs
    String VUE_CHOICE = "/choice";
    String VUE_LOGIN = "/login";
    String VUE_SEANCE = "/seance";
    String VUE_COURS = "cours";
    String VUE_TD = "td";
    String VUE_TP = "tp";
    
    
    // Les contrôleurs
    String CONTROLLER = "controller";
    String CONTROLLER_LOGIN = "controller_login";
        
    // Actions gérées par les contrôleurs

    String ACTION_LOGIN = "login";
    String ACTION_SEANCE="seance";
    
    String ACTION_HOME = "home";
    String ACTION_LOGOUT = "logout";
    
    // Utilisés pour le partage des informations en tant que clefs
    String KEY_ACTION = "action";
    String KEY_LOGIN = "login";
    String KEY_PASSWORD = "password";
    String KEY_IS_CONNECTED = "is_connected";

    // Constantes 
    String CONNECTED = "connected";
    String ERROR_CREDENTIALS_MESSAGE = "login/password not correct !";
    String CREDENTIALS_EMPTY_MESSAGE = "login/password empty !";
    
    // Seances gérées par les contrôleurs
    String SEANCE_COURS = "COURS";
    String SEANCE_TD = "TD";
    String SEANCE_TP = "TP";
    

    
  
  /**
   * Permet d'activer la vue dont les chemins ets indikquées par path
   * @param path
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException 
   */
  public void dispatch(String path, HttpServletRequest request, 
             HttpServletResponse response)  throws ServletException, IOException;

}
