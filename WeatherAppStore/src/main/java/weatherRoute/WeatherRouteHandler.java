/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherRoute;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

/**
 *
 * @author andrew
 */
@WebServlet(name = "WeatherRouteHandler", urlPatterns = {"/WeatherRouteHandler"})
public class WeatherRouteHandler extends HttpServlet {

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
              
        //System.out.println("TESTING tile Handler");
        
        //set up inputs from form
        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");       
        
        //System.out.println("toLocation: " + destination);
        //System.out.println("fromLocation: " + origin);
        
        //create tiles
        TileHandler th = new TileHandler();
        ArrayList<Tile> tiles = th.getTiles(origin, destination);
        
        // use this for loop for testing outputs and things...
        for (Tile tile : tiles) {         
            //System.out.println("TEST tile: " + tile);
        }
        
        //this is how you get tiles to JSP
        request.setAttribute("tiles", tiles);
        request.getRequestDispatcher("results.jsp").forward(request, response);
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
