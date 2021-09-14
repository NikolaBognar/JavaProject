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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nikola
 */
public class PrikazFilmaServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        
        String poruka = request.getParameter("naziv");
               
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        
        try {                    
            Class.forName("com.mysql.jdbc.Driver");                     
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskop", "root", "localhost");
            
            String naziv = request.getParameter("naziv");
            
            String upit = "SELECT * FROM film WHERE naziv='"+naziv+"'";
            st = con.createStatement();           
            rs = st.executeQuery(upit);        
                   
            Film f = new Film();
            
            if(rs.next()){               
                f.setIdFilm(rs.getInt("idFilm"));
                f.setNaziv(rs.getString("naziv"));
                f.setTrajanje(rs.getString("trajanje"));
                f.setOpis(rs.getString("opis"));
                f.setTrailer(rs.getString("trailer"));
                f.setSlika(rs.getString("slika"));        
                
                request.setAttribute("film", f);
                request.getRequestDispatcher("film.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            }           
            
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
