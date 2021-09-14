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
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nikola
 */
public class UclaniSe extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession sesija = request.getSession();
        
        Connection con = null;
        PreparedStatement pst = null; 
        Statement st = null;
        ResultSet rs = null;
        
        try {                                                        
            Class.forName("com.mysql.jdbc.Driver");  
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskop", "root", "localhost");
         
            String upit = "INSERT INTO clanstvo_korisnik(naziv, imeKorisnika, datum, slika, id_clanstvo, id_korisnik) VALUES(?, ?, ?, ?, ?, ?)";
            
            Korisnik k = (Korisnik)sesija.getAttribute("korisnik");
            
            String naziv = request.getParameter("naziv");            
            String ime = k.getIme();          
            String datum = LocalDate.now().toString();
            String slika = request.getParameter("slika");
            int idKorisnika = k.getId();
            int idClanstvo = Integer.parseInt(request.getParameter("idc"));
                       
            pst= con.prepareStatement(upit);
            
            pst.setString(1, naziv);
            pst.setString(2, ime);
            pst.setString(3, datum);
            pst.setString(4, slika);
            pst.setInt(5, idClanstvo);
            pst.setInt(6,  idKorisnika);
            
            pst.execute();

            request.getRequestDispatcher("uspesnoClanstvo.jsp").forward(request, response);
       }   
         catch (Exception ex) {                
                request.setAttribute("msg", ex.getMessage());
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
                }
                finally{
                    if(con!= null){
                        try {
                            st.close();
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
