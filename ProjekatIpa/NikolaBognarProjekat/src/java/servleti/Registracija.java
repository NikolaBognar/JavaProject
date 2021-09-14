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
import beans.Korisnik;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

/**
 *
 * @author Nikola
 */

public class Registracija extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String ime = request.getParameter("ime");
        String prezime = request.getParameter("prezime");
        String pol = request.getParameter("pol");
        String datum = request.getParameter("datum");
        String email = request.getParameter("email");
        String tip = "korisnik";
        
        Connection con = null;
        PreparedStatement pst = null; 
        Statement st = null;
        ResultSet rs = null;
        
        if(username != null && username.length() > 0 && password != null && password.length() > 0 
           && ime != null && ime.length() > 0 && prezime != null && prezime.length() > 0
           && pol != null && pol.length() > 0 && datum != null && datum.length() > 0 
           && email!= null && email.length() > 0){
            
            try {                                                        
                Class.forName("com.mysql.jdbc.Driver");  
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskop", "root", "localhost");
    
                String upit = "SELECT * FROM korisnik WHERE username='"+username+"'";
                st = con.createStatement();
                rs = st.executeQuery(upit);
                
                if(rs.next()){
                    request.setAttribute("msg", "Korisnicko ime vec postoji!");
                    request.getRequestDispatcher("registracija.jsp").forward(request, response);
                }else{
                    String upit1 = "INSERT INTO korisnik(username, password, ime, prezime, datum, pol, tip, email, poeni)"
                        + " VALUES (?,?,?,?,?,?,?,?, ?)";

                    pst = con.prepareStatement(upit1);
                    pst.setString(1, username);
                    pst.setString(2, password);
                    pst.setString(3, ime);
                    pst.setString(4, prezime);
                    pst.setString(5, datum);
                    pst.setString(6, pol);
                    pst.setString(7, tip);
                    pst.setString(8, email);               
                    pst.setInt(9, 0);  
                    
                    pst.executeUpdate();

                    String msg = "Uspesno ste se registrovali! Mozete se ulogovati.";
                    request.setAttribute("msg", msg);
                    request.getRequestDispatcher("home.jsp").forward(request, response);
                }                                                                           
            } catch (Exception ex) {                
                request.setAttribute("msg", ex.getMessage());
                request.getRequestDispatcher("registracija.jsp").forward(request, response);
                }
                finally{
                    if(con!= null){
                        try {
                            st.close();
                            pst.close();
                            con.close();
                        } catch (SQLException ex) {
                            request.setAttribute("msg", ex.getMessage());
                            request.getRequestDispatcher("registracija.jsp").forward(request, response); }
                        }
                }
        }else{
                request.setAttribute("msg", "Morate popuniti sva polja!");
                request.getRequestDispatcher("registracija.jsp").forward(request, response);  
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
