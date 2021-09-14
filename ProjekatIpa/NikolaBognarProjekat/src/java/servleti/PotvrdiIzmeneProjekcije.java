/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servleti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Nikola
 */
public class PotvrdiIzmeneProjekcije extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        String msg="";
        
        Connection con =null;
        PreparedStatement pst=null;
        ResultSet rs =null; 
        
        try{                                        
            int id = Integer.parseInt(request.getParameter("id"));
            
            Date datum = Date.valueOf(request.getParameter("datum"));
            Time vreme = Time.valueOf(request.getParameter("vreme"));
            Double cena = Double.parseDouble(request.getParameter("cena"));
            String naziv = request.getParameter("nazivFilma");
            String tehnologija = request.getParameter("tehnologija");
            int filmId = Integer.parseInt(request.getParameter("filmId"));
            int repertoarId = Integer.parseInt(request.getParameter("repertoarId"));
            int IdSala = Integer.parseInt(request.getParameter("IdSala"));
            
            if(  tehnologija.isEmpty() || naziv.isEmpty() ){
                msg = "Morate popuniti sva polja!";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("IzmenaTerminaAdminServlet?id="+id+"").forward(request, response);
            }       
      
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskop", "root", "localhost");
            
            String upit = "Update film_repertoar"
                    + " SET datum = ?, vreme = ?, cena = ?, nazivFilma = ?, tehnologija = ?, filmId= ?, repertoarId = ?, IdSala = ?"
                    + " WHERE id = ?";
            pst = con.prepareStatement(upit);
            
            pst.setDate(1, datum);
            pst.setTime(2, vreme);
            pst.setDouble(3, cena);
            pst.setString(4, naziv); 
            pst.setString(5, tehnologija);
            pst.setInt(6, filmId);
            pst.setInt(7, repertoarId);
            pst.setInt(8, IdSala);
            pst.setInt(9, id);
            
            int rowsAffected = pst.executeUpdate();
                      
            request.getRequestDispatcher("AdministriranjeProjekcijaAdmin").forward(request, response);
        }catch (Exception ex) {             
            msg = ex.getMessage();
            request.setAttribute("msg", msg);           
           request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            }                 
        finally{
            if(con!= null){
                try {
                    pst.close();
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
