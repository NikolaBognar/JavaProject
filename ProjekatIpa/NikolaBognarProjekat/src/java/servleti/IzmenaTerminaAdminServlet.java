/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servleti;

import beans.Termin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nikola
 */
public class IzmenaTerminaAdminServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
         Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {                    
            Class.forName("com.mysql.jdbc.Driver");                     
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskop", "root", "localhost");
            
            String idString = request.getParameter("id");
            int id = Integer.parseInt(idString);
            
            String upit = "SELECT * FROM film_repertoar WHERE id='"+id+"';";
            st = con.createStatement();           
            rs = st.executeQuery(upit);
                            
                                  
            if(rs.next()){
                Termin t = new Termin();
                t.setId(rs.getInt("id"));
                t.setDatum(rs.getDate("datum"));
                t.setVreme(rs.getTime("vreme"));
                t.setCena(rs.getDouble("cena"));
                t.setNazivFilma(rs.getString("nazivFilma"));
                t.setTehnologija(rs.getString("tehnologija"));
                t.setFilmId(rs.getInt("filmId"));
                t.setRepertoarId(rs.getInt("repertoarId"));
                t.setIdSala(rs.getInt("IdSala"));
                
                request.setAttribute("termin",  t);
                request.getRequestDispatcher("izmeniProjekciju.jsp").forward(request, response);  
            }else{
                request.setAttribute("msg", "greska");          
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
