/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servleti;

import beans.Korisnik;
import beans.Film;
import beans.Termin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nikola
 */
public class FormaZaRezervaciju extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String poruka = request.getParameter("naziv");
          
        
        String idTerminaString = request.getParameter("idTermina");
        int idTermina = Integer.parseInt(idTerminaString);
        
       
        String[] redA = new String[16];
        String[] redB = new String[16];
        String[] redC = new String[16];
        
        redA = request.getParameterValues("redA");     
        redB = request.getParameterValues("redB");
        redC = request.getParameterValues("redC");
        
        ArrayList<String> sedista = new ArrayList<String>();
        
        if((request.getParameterValues("redA")) != null){
            for( int i=0; i < redA.length; i++){
                if(!redA[i].equals(""))
                    sedista.add(redA[i]);
            }
        }
        
        if((request.getParameterValues("redB")) != null){
            for( int i=0; i < redB.length; i++){
               if(!redB[i].equals(""))
                    sedista.add(redB[i]);
            }
        }
        
        if((request.getParameterValues("redC")) != null){
            for( int i=0; i < redC.length; i++){
                if(!redC[i].equals(""))
                    sedista.add(redC[i]);
            }
        }
        
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
                     
        try {                    
            Class.forName("com.mysql.jdbc.Driver");                     
            
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskop", "root", "localhost");
            
            String naziv = request.getParameter("naziv");
            
            String upit = "SELECT * FROM film_repertoar WHERE id='"+idTermina+"'";
            st = con.createStatement();           
            rs = st.executeQuery(upit);        
                                        
            Termin t = new Termin(); 
            
            if(rs.next()){               
                 int id = rs.getInt("id");
                String nazivFilma = rs.getString("nazivFilma");
                Double cena = rs.getDouble("cena");
                Date datum = rs.getDate("datum");
                Time vreme = rs.getTime("vreme");
                String tehnologija = rs.getString("tehnologija");
                int idSala = rs.getInt("idSala");
                int idBioskop = rs.getInt("idBioskop");
                
                t.setId(id);
                t.setNazivFilma(nazivFilma);
                t.setCena(cena);
                t.setDatum(datum);
                t.setVreme(vreme);
                t.setTehnologija(tehnologija);
                t.setIdSala(idSala); 
                t.setIdBioskop(idBioskop);
                
                
                
                request.setAttribute("sedista", sedista);
                request.setAttribute("termin", t);
                request.getRequestDispatcher("potvrdaRezervacijeKarte.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            }           
            
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
