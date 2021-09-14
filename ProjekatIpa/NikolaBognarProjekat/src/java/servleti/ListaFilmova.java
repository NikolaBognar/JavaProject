/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import beans.Film;
import java.util.ArrayList;


/**
 *
 * @author Nikola
 */
public class ListaFilmova extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        response.setContentType("text/html;charset=UTF-8");
        
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {                    
            Class.forName("com.mysql.jdbc.Driver");                     
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskop", "root", "localhost");
            
            String upit = "SELECT * FROM film";
            st = con.createStatement();           
            rs = st.executeQuery(upit);
            
            
            ArrayList<Film> filmovi  = new ArrayList<>();
                                  
            while(rs.next()){
                Film f = new Film();
                f.setIdFilm(rs.getInt("idFilm"));
                f.setNaziv(rs.getString("naziv"));
                f.setTrajanje(rs.getString("trajanje"));
                f.setOpis(rs.getString("opis"));
                f.setTrailer(rs.getString("trailer"));
                f.setSlika(rs.getString("slika"));
                
                filmovi.add(f);  
            }
            
            request.setAttribute("filmovi", filmovi);
            request.getRequestDispatcher("filmovi.jsp").forward(request, response);
        } catch (ClassNotFoundException ex) {
                request.setAttribute("msg", ex.getMessage());          
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        } catch (SQLException ex) {
                request.setAttribute("msg", ex.getMessage());          
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        } finally{
            try {
                st.close();
                con.close();
            } catch (SQLException ex) {
                request.setAttribute("msg", ex.getMessage());          
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
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
