/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.parqueowebapp.boundary.servlet;

import com.mycompany.parqueowebapp.app.entity.TipoEspacio;
import com.mycompany.parqueowebapp.control.TipoEspacioBean;
import jakarta.inject.Inject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniloues
 */
@WebServlet(name = "TipoEspacioServlet", urlPatterns = {"/TipoEspacioServlet"})
public class TipoEspacioServlet extends HttpServlet {
    
    @Inject
    TipoEspacioBean teBean;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String nombre = null;
        
        
        if(request != null){
            if(request.getParameter("nombre") != null){
                nombre = request.getParameter("nombre");
            }
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TipoEspacioServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TipoEspacioServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            if(nombre != null){
                TipoEspacio nuevo = new TipoEspacio();
                // CAMBIAR VALORES POR LOS METODOS DE TIPO ESPACIO
                nuevo.setNombre(nombre);
                try{
                    teBean.create(nuevo);
                    out.println("<p>Hola mundo</p>");
                } catch(Exception ex){
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);

                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
