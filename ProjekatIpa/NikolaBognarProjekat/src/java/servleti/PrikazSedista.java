/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servleti;

import beans.Sediste;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nikola
 */
public class PrikazSedista extends HttpServlet {

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
        Statement stA = null;
        Statement stB = null;
        Statement stC = null;
        ResultSet rsA = null;
        ResultSet rsB = null;
        ResultSet rsC = null;
        
        try {                    
            Class.forName("com.mysql.jdbc.Driver");                     
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskop", "root", "localhost");
            
            String salaIdString = request.getParameter("idSale");
            int salaId = Integer.parseInt(salaIdString);
            
            String upitA = "SELECT * FROM sediste WHERE salaId='"+salaId+"' AND red='A'";
            String upitB = "SELECT * FROM sediste WHERE salaId='"+salaId+"' AND red='B'";
            String upitC = "SELECT * FROM sediste WHERE salaId='"+salaId+"' AND red='C'";
            stA = con.createStatement();           
            rsA = stA.executeQuery(upitA);        
                            
            ArrayList<Sediste> sedistaA = new ArrayList<>();
            ArrayList<Sediste> sedistaB = new ArrayList<>();
            ArrayList<Sediste> sedistaC = new ArrayList<>();
            
            while(rsA.next()){ 
                Sediste s = new Sediste();
                s.setId(rsA.getInt("id"));
                s.setOznaka(rsA.getString("oznaka"));
                s.setRezervisano(rsA.getString("rezervisano"));
                s.setSalaId(rsA.getInt("salaId"));
                s.setVrsta(rsA.getString("vrsta"));
                
                sedistaA.add(s);                                      
            }
            
            stB = con.createStatement();           
            rsB = stB.executeQuery(upitB);
            
            while(rsB.next()){
                Sediste s = new Sediste();
                s.setId(rsB.getInt("id"));
                s.setOznaka(rsB.getString("oznaka"));
                s.setRezervisano(rsB.getString("rezervisano"));
                s.setSalaId(rsB.getInt("salaId"));
                s.setVrsta(rsB.getString("vrsta"));
                
                sedistaB.add(s);                                      
            }
            
            stC = con.createStatement();           
            rsC = stC.executeQuery(upitC);
            
            while(rsC.next()){ 
                Sediste s = new Sediste();
                s.setId(rsC.getInt("id"));
                s.setOznaka(rsC.getString("oznaka"));
                s.setRezervisano(rsC.getString("rezervisano"));
                s.setSalaId(rsC.getInt("salaId"));
                s.setVrsta(rsC.getString("vrsta"));
                
                sedistaC.add(s);                                      
            }
            
            request.setAttribute("sedistaA", sedistaA);
            request.setAttribute("sedistaB", sedistaB);
            request.setAttribute("sedistaC", sedistaC);
            
            String idTermina = request.getParameter("idTermina");
            request.setAttribute("idTermina", idTermina);
            
            request.getRequestDispatcher("odabirSedista.jsp").forward(request, response);
        } catch (ClassNotFoundException ex) {
                request.setAttribute("msg", ex.getMessage());          
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        } catch (SQLException ex) {
                request.setAttribute("msg", ex.getMessage());          
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        } finally{
            try {
                stA.close();
                stB.close();
                stC.close();
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
