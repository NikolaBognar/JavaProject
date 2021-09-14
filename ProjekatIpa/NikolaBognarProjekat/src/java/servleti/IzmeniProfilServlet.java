/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servleti;

import beans.Korisnik;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nikola
 */
public class IzmeniProfilServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        HttpSession sesija = request.getSession();
        Korisnik korisnik = (Korisnik)sesija.getAttribute("korisnik");
        String msg="";
        
        Connection con =null;
        PreparedStatement pst=null;
        ResultSet rs =null; 
        
        try{                                        
            int id = Integer.parseInt(request.getParameter("id"));
            String password = request.getParameter("password");             
            String ime = request.getParameter("ime");
            String prezime = request.getParameter("prezime");
            String datum = request.getParameter("datum");
            String pol = request.getParameter("pol");
            String email = request.getParameter("email");
            

            if(password.isEmpty() || ime.isEmpty() || prezime.isEmpty() ||
                    datum.isEmpty() || pol.isEmpty() || email.isEmpty()){
                msg = "Morate popuniti sva polja!";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("izmeniProfil.jsp").forward(request, response);
            }       
      
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskop", "root", "localhost");
            
            String upit = "Update korisnik"
                    + " SET password = ?, ime = ?, prezime = ?, datum = ?, pol = ?, email = ? WHERE idKorisnik = ?";
            pst = con.prepareStatement(upit);
            
            pst.setString(1, password);
            pst.setString(2, ime);
            pst.setString(3, prezime);
            pst.setString(4, datum);         
            pst.setString(5, pol);
            pst.setString(6, email);
            
            pst.setInt(7, id);
            
            int rowsAffected = pst.executeUpdate();
            
            korisnik.setPassword(password);
            korisnik.setIme(ime);
            korisnik.setPrezime(prezime);
            korisnik.setDatum(datum);
            korisnik.setPol(pol);
            korisnik.setEmail(email);
            
            request.getRequestDispatcher("profil.jsp").forward(request, response);
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
