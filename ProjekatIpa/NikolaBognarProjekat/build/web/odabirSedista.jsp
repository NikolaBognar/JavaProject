<%-- 
    Author     : Nikola
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="beans.Sediste"%>
<%@page import="beans.Korisnik"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Index stranica</title>
        <!--  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"> --> 
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">       
        <style>
            body{
                background-image: url(img/cinema.jpg);
                background-size: cover;
                background-attachment: fixed;
            }
            a{
                color: grey;
            }
            h2{
                margin-left: 40%;
                margin-top: 40px;
                color: whitesmoke;
            }
            .container{
                color: black;  
                margin-left: 33%;
            }
            .rezervacija{
                background-color: silver;
                width: 600px;
                height: 420px;
                text-align: center;
                margin-top: 5%;
            }
            .rezervacija table{
                width: 500px;
                height: 300px;
            }
            .rezervacija p {
                text-align: left;
                padding-left: 20px;
            }
            .rezervacija input[type=submit]{
                margin-left: 70%;
            }
        </style>
    <body>
        <nav class="navbar navbar-expand-md navbar-light" style="background-color: lightsteelblue" center>
            <a class="navbar-brand"><img src="img/logo2.png" width="70" heigh="50"></a>
            <span class="navbar-text" style="color:darkgray"><b>Nas bioskop</b></span>
                    <%
                        HttpSession sesija = request.getSession();
                        Korisnik korisnik = (Korisnik)sesija.getAttribute("korisnik"); 
                 %>
                <ul class="navbar-nav" style="padding-left: 20px; ">
                    <li class="nav-item" ">
                        <a class="nav-link" href="index.jsp">POCETNA</a>
                    </li>
                     <li class="nav-item" >
                        <a class="nav-link" href="BioskopiServlet">BIOSKOPI</a>
                    </li>
                    <li class="nav-item" >
                        <a class="nav-link" href="ListaFilmova">FILMOVI</a>
                    </li>
                    <li class="nav-item" >
                        <a class="nav-link" href="Repertoar">REPERTOAR</a>
                    </li>
                    <li class="nav-item" style="margin-right: 60%">
                        <a class="nav-link" href="ClanstvoServlet" >CLANSTVO</a>
                    </li>
                    <%
                        if(korisnik!= null){
                        if(korisnik.getTip().equals("admin")){
                    %>
                    <li class="nav-item" >
                        <a class="nav-link" href="adminStranica.jsp">ADMIN</a>
                    </li>
                    <% }else if(korisnik.getTip().equals("menadzer")){%>
                    <li class="nav-item" >
                        <a class="nav-link" href="menadzerStranica.jsp">MENADZER</a>
                    </li>
                    <% }} %>
                    <%
                        if(korisnik != null){
                    %><li class="nav-item" >
                        <a class="nav-link" href="profil.jsp"> PROFIL</a>
                    </li>
                    <li class="nav-item" >
                        <a class="nav-link" href="OdjavaServlet">ODJAVA</a>
                    </li><% }else{ %>
                    <li class="nav-item">
                        <a class="nav-link" href="login.jsp" >PRIJAVA</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="registracija.jsp">REGISTRACIJA</a>
                    </li>
                    <% } %>
                </ul>
        </nav>  
        <div class="container">
        <h2 id="msg">  
            <% String msg = (String)request.getAttribute("msg");
                if(msg != null) { %>
                <%= msg  %>
            <% } %>
        </h2>
        
        <div class="rezervacija">
        <form action="FormaZaRezervaciju" method="get">      
            <table>
                <tr>
                   <td colspan="15"><div class="screen">EKRAN</div></td>
                   <br/>
                </tr>          
                <tr>
                   <td></td>
                   <td>1</td>
                   <td>2</td>
                   <td>3</td>
                   <td>4</td>
                   <td>5</td>
                </tr>
                 <% 
                ArrayList<Sediste> sedistaA = (ArrayList<Sediste>)request.getAttribute("sedistaA"); 
                ArrayList<Sediste> sedistaB = (ArrayList<Sediste>)request.getAttribute("sedistaB"); 
                ArrayList<Sediste> sedistaC = (ArrayList<Sediste>)request.getAttribute("sedistaC"); 

                String idTermina = (String)request.getParameter("idTermina");
                %>

                <tr>
                   <td>A</td>
                    <% for(Sediste sA : sedistaA){ 
                        if(sA.getRezervisano().equals("ne")){%>
                        <td> <div class="stolica">  <input name="redA" type="checkbox" value="<%=sA.getOznaka()%>"> </div> </td> 
                        <%}else{%>
                        <td> <div class="stolica">  <input name="redA" type="checkbox" value="<%=sA.getOznaka()%>" disabled> </div> </td> 
                        <% } %>
                        <% } %>
                </tr>

                 <tr>
                   <td>B</td>
                    <% for(Sediste sB : sedistaB){ 
                        if(sB.getRezervisano().equals("ne")){%>
                        <td> <div class="stolica">  <input name="redB" type="checkbox" value="<%=sB.getOznaka()%>"> </div> </td> 
                        <%}else{%>
                        <td> <div class="stolica">  <input name="redB" type="checkbox" value="<%=sB.getOznaka()%>" disabled> </div> </td> 
                        <% } %>
                        <% } %>
                 </tr>

                 <tr>
                   <td>C</td>
                   <% for(Sediste sC : sedistaC){ 
                        if(sC.getRezervisano().equals("ne")){%>
                        <td> <div class="stolica">  <input name="redC" type="checkbox" value="<%=sC.getOznaka()%>"> </div> </td> 
                        <%}else{%>
                        <td> <div class="stolica">  <input name="redC" type="checkbox" value="<%=sC.getOznaka()%>" disabled> </div> </td> 
                        <% } %>
                        <% } %>
               </tr>         
            </table>        
            <p>Sedista u redu C su VIP sedista i kostaju 100 dinara vise!</p>
            <input type="text"  name="idTermina" value="<%=idTermina%>" hidden>
            <%
                if(korisnik != null){
            %>
            <input type="submit" value="REZERVISI">
            <%
                }else{
            %>
            <a href="morateSeUlogovati.jsp" ><input type="button" value="REZERVISI"></a>
            <% } %>
        </form>
        </div>
    </div>   
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    </body>
</html>
