/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servleti;

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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nikola
 */
public class DodajProjekcijuAdminServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
                      
        Connection con = null;
        PreparedStatement pst = null; 
        Statement st = null;
        ResultSet rs = null;           
            
            try {  
                String datumString = request.getParameter("datum") ;
                String vremeString = request.getParameter("vreme");
                String cenaString = request.getParameter("cena");
                String nazivFilma = request.getParameter("nazivFilma");
                String tehnologija = request.getParameter("tehnologije");
                String filmIdString = request.getParameter("filmId");
                String repertoarIdString = request.getParameter("repertoarId");
                String IdSalaString = request.getParameter("IdSala");
                
                  if(datumString.length()>0 && vremeString.length()>0 && nazivFilma != null 
                && nazivFilma.length() > 0 && tehnologija != null && tehnologija.length() > 0 && cenaString.length()>0 
                          && filmIdString != null && filmIdString.length() > 0 && repertoarIdString != null && repertoarIdString.length() > 0
                          && IdSalaString != null && IdSalaString.length() > 0 ){                             
                    
                    Double cena = Double.parseDouble(cenaString);
                    Date datum = Date.valueOf(datumString);
                    Time vreme = Time.valueOf(vremeString);
                    int filmId = Integer.parseInt(filmIdString);
                    int repertoarId = Integer.parseInt(repertoarIdString);
                    int IdSala = Integer.parseInt(IdSalaString);
                      
                    Class.forName("com.mysql.jdbc.Driver");  
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskop", "root", "localhost");
                                          
                    String upit1 = "INSERT INTO film_repertoar(datum, vreme, cena, nazivFilma, tehnologija, filmId, repertoarId, IdSala)"
                        + " VALUES (?,?,?,?,?,?,?, ?)";

                    pst = con.prepareStatement(upit1);
                    pst.setDate(1, datum);
                    pst.setTime(2, vreme);
                    pst.setDouble(3, cena);
                    pst.setString(4, nazivFilma);
                    pst.setString(5, tehnologija);
                    pst.setInt(6, filmId);
                    pst.setInt(7, repertoarId); 
                    pst.setInt(8, IdSala); 
                  
                    pst.executeUpdate();

                    String msg = "Uspesno ste se dodali projekciju!";
                    request.setAttribute("msg", msg);
                    request.getRequestDispatcher("AdministriranjeProjekcijaAdmin").forward(request, response);
                                                                                           
                }else{                   
                    request.setAttribute("msg", "Morate popuniti sva polja!");
                    request.getRequestDispatcher("dodajProjekcijuAdmin.jsp").forward(request, response);  
                  }
            } catch (Exception ex) {                
                request.setAttribute("msg", ex.getMessage());
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
