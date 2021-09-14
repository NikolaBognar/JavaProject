/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servleti;

import beans.Korisnik;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

/**
 *
 * @author Nikola
 */
public class Logovanje extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession sesija = request.getSession();
        sesija.setMaxInactiveInterval(3600);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String msg = "";
        
        Korisnik korisnik = new Korisnik();
        korisnik.setUsername(username);
        korisnik.setPassword(password);   
        
        if(username.isEmpty() || password.isEmpty()){
            msg = "Morate popuniti oba polja!";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskop", "root", "localhost");
            
            String upit = "SELECT * FROM korisnik WHERE username='"+username +"' AND password='"+password +"'";
            st = con.createStatement();
            rs = st.executeQuery(upit);
            
            if(rs.next()){
                korisnik.setDatum( rs.getString("datum") );
                korisnik.setId(rs.getInt("idKorisnik") );
                korisnik.setIme(rs.getString("ime") );
                korisnik.setPrezime(rs.getString("prezime") );
                korisnik.setPol(rs.getString("pol") );
                korisnik.setTip(rs.getString("tip") );
                korisnik.setUsername(username);
                korisnik.setPassword(password); 
                korisnik.setEmail(rs.getString("email"));
                korisnik.setPoeni(rs.getInt("poeni"));
                
                sesija.setAttribute("korisnik", korisnik);
                
                st.close();
                con.close();                                          
            }else{
                msg = "Neispravno unet username ili password!";
                request.setAttribute("msg", msg);
                st.close();
                con.close();
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }catch (ClassNotFoundException ex) { }
        catch (SQLException ex) { 
            {
            sesija.invalidate();
            msg = ex.getMessage();
            request.setAttribute("msg", msg);           
            if(con != null){
                try {
                    st.close();
                    con.close();
                } catch (SQLException ex1) { }
            }
            request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } 
        msg = "Uspesno ste se prijavili!";
        request.setAttribute("msg", msg);  
        request.getRequestDispatcher("home.jsp").forward(request, response);
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
