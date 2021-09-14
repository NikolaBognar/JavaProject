/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servleti;

import beans.ClanKluba;
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
public class PrikazClanovaKlubovaMenadzerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String msg = "";
        
        try {  
            Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskop", "root", "localhost");
            
            String upit = "SELECT * FROM clanstvo_korisnik;";
            st = con.createStatement();
            rs=st.executeQuery(upit);
            
            java.util.ArrayList<ClanKluba> clanovi = new java.util.ArrayList<ClanKluba>();
            
            while(rs.next()){
                String naziv = rs.getString("naziv");
                String imeKorisnika = rs.getString("imeKorisnika");
                String datum = rs.getString("datum");
                String slika = rs.getString("slika");
                
                ClanKluba c = new ClanKluba(naziv, imeKorisnika, datum, slika);

                clanovi.add(c);
            }
            
            request.setAttribute("clanovi", clanovi);
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("pregledClanovaKlubaMenadzer.jsp").forward(request, response);
            
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
