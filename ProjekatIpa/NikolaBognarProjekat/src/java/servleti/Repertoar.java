/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servleti;

import beans.Termin;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

/**
 *
 * @author Nikola
 */
public class Repertoar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        ArrayList<Termin> termini = new ArrayList<>();
        
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String msg = "";
        
        try {  
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskop", "root", "localhost");
            
            String upit = "SELECT * FROM film_repertoar;";
            st = con.createStatement();
            rs=st.executeQuery(upit);
            
            while(rs.next()){
                int id = rs.getInt("id");
                String nazivFilma = rs.getString("nazivFilma");
                Double cena = rs.getDouble("cena");
                Date datum = rs.getDate("datum");
                Time vreme = rs.getTime("vreme");
                String tehnologija = rs.getString("tehnologija");
                int idSala = rs.getInt("idSala");
                int idBioskop = rs.getInt("idBioskop");
                
                Termin t = new Termin();
                t.setId(id);
                t.setNazivFilma(nazivFilma);
                t.setCena(cena);
                t.setDatum(datum);
                t.setVreme(vreme);
                t.setTehnologija(tehnologija);
                t.setIdSala(idSala);
                t.setIdBioskop(idBioskop);
                
                termini.add(t);
            }
            
            request.setAttribute("termini", termini);
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("repertoar.jsp").forward(request, response);
            
          }   
         catch (Exception ex) {                
                request.setAttribute("msg", ex.getMessage());
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
                }
                finally{
                    if(con!= null){
                        try {
                            st.close();
                            con.close();
                        } catch (SQLException ex) {
                            request.setAttribute("msg", ex.getMessage());
                            request.getRequestDispatcher("errorPage.jsp").forward(request, response); }
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
