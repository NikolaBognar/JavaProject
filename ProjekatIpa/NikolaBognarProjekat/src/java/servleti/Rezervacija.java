/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servleti;

import beans.RezervacijaBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nikola
 */
public class Rezervacija extends HttpServlet {

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
         
            String upit = "INSERT INTO rezervacija(username,imePrezime,nazivFilma,sala, datum,vreme,sedista,idProj,idKorisnik, idBioskop) VALUES(?, ?, ?, ?, ?, ?, ?, ?,?,?)";
            
            String korisnicko = request.getParameter("korisnicko");
            String imePrez = request.getParameter("imePrez");
            String film = request.getParameter("film");
            int sala = Integer.parseInt(request.getParameter("sala"));
            String datum = request.getParameter("datum");

            String vreme = request.getParameter("vreme");
            String sedista = request.getParameter("sedista");
            int idTermina = Integer.parseInt(request.getParameter("idTermina"));
            int idKorisnik = Integer.parseInt(request.getParameter("idKorisnik"));
            int idBioskop = Integer.parseInt(request.getParameter("idBioskop"));
            
            pst= con.prepareStatement(upit);
            
            pst.setString(1, korisnicko);
            pst.setString(2, imePrez);
            pst.setString(3, film);
            pst.setInt(4, sala);
            pst.setString(5, datum);
            pst.setString(6,  vreme);
            pst.setString(7, sedista);
            pst.setInt(8, idTermina);
            pst.setInt(9, idKorisnik);
            pst.setInt(10, idBioskop);
            
            RezervacijaBean rez = new RezervacijaBean(korisnicko, imePrez, film, sala, datum, vreme, sedista, idTermina, idKorisnik, idBioskop);
            request.setAttribute("rezervacija", rez);
            
            pst.execute();
                    
            
            String upit2 = "SELECT id FROM rezervacija WHERE idProj='"+idTermina 
                            +"' AND username='"+korisnicko+"';";
            
            st = con.createStatement();
            rs = st.executeQuery(upit2);
            
            if(rs.next()){
                int idRezervacije = rs.getInt("id");
                request.setAttribute("idRezervacije", idRezervacije);              
            }
            
            ArrayList<String> sedistaNiz = (ArrayList<String>)sesija.getAttribute("sedistaNiz");
            
            String upit3="";
            
            for(String s: sedistaNiz){               
                if(!s.equals("")){
                    upit3 = "UPDATE sediste SET rezervisano='da' WHERE oznaka='"+s+"' AND salaId='"+sala+"'";
                
                    st.executeUpdate(upit3);
                }
            }                    
            
            request.getRequestDispatcher("uspesnaRezervacija.jsp").forward(request, response);
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
