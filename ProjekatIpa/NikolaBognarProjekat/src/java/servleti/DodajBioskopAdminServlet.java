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
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nikola
 */
public class DodajBioskopAdminServlet extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String naziv = request.getParameter("naziv");
        String grad = request.getParameter("grad");
        String slika = "img/"+request.getParameter("slika");
        String tehnologije = request.getParameter("tehnologije");
        String radnoVreme = request.getParameter("radnoVreme");
        String kontakt = request.getParameter("kontakt");
        
        Connection con = null;
        PreparedStatement pst = null; 
        Statement st = null;
        ResultSet rs = null;
        
        if(naziv != null && naziv.length() > 0 && grad != null && grad.length() > 0 
           && tehnologije != null && tehnologije.length() > 0
           && radnoVreme != null && radnoVreme.length() > 0 && kontakt != null && kontakt.length() > 0 ){
            
            try {                                                        
                Class.forName("com.mysql.jdbc.Driver");  
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskop", "root", "localhost");
                
                String upit = "SELECT * FROM bioskop WHERE naziv='"+naziv+"'";
                st = con.createStatement();
                rs = st.executeQuery(upit);
                
                if(rs.next()){
                    request.setAttribute("msg", "Bioskop sa ovim imenom vec postoji!");
                    request.getRequestDispatcher("dodajBioskopAdmin.jsp").forward(request, response);
                }else{
                    String upit1 = "INSERT INTO bioskop(naziv, grad, slika, tehnologije, radnoVreme, kontakt)"
                        + " VALUES (?,?,?,?,?,?)";

                    pst = con.prepareStatement(upit1);
                    pst.setString(1, naziv);
                    pst.setString(2, grad);
                    pst.setString(3, slika);
                    pst.setString(4, tehnologije);
                    pst.setString(5, radnoVreme);
                    pst.setString(6, kontakt); 
                  
                    pst.executeUpdate();

                    String msg = "Uspesno ste se dodali bioskop!";
                    request.setAttribute("msg", msg);
                    request.getRequestDispatcher("AdministriranjeBioskopaServlet").forward(request, response);
                }                                                                           
            } catch (Exception ex) {                
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
        }else{
                request.setAttribute("msg", "Morate popuniti sva polja!");
                request.getRequestDispatcher("dodajBioskopAdmin.jsp").forward(request, response);  
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
