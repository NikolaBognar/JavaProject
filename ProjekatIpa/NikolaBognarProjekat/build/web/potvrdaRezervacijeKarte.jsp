<%-- 
    Author     : Nikola
--%>

<%@page import="beans.Termin"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.Korisnik"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
            #naslov{
                text-align: center;
                padding-top: 50px;
                padding-bottom: 50px;
                color: whitesmoke;
            }
            .content{
                color: whitesmoke;
                text-align: center;
            }
        </style>
    </head>
    <body >   
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
        <div class="content">
            <h1>Podaci o rezervaciji </h1>
            <%
                ArrayList<String> sedista = (ArrayList<String>)request.getAttribute("sedista");
                sesija.setAttribute("sedistaNiz", sedista);
                Termin t = (Termin)request.getAttribute("termin");
                String pom = "";
            %>
            <form method="post" action="Rezervacija">    
                <label for="korisnicko">Korisnicko ime:</label>
                <input type="text" name="korisnicko" value="<%=korisnik.getUsername()%>" readonly><br><br>
                <label for="imePrez">Ime i prezime:</label>
                <input type="text" name="imePrez" value="<%=korisnik.getIme()%> <%=korisnik.getPrezime()%>" readonly ><br><br>
                <label for="film">Film:</label>
               <input type="text" name="film" value="<%=t.getNazivFilma()%>" readonly><br><br>
               <label for="sala">Broj sale:</label>
                <input type="text" name="sala" value="<%=t.getIdSala()%>" readonly><br><br>
                <label for="datum">Datum:</label>
                <input type="text" name="datum" value="<%=t.getDatum()%>" readonly><br><br>
                <label for="vreme">Vreme:</label>
                <input type="text" name="vreme" value="<%=t.getVreme()%>" readonly><br><br> 
                <label for="sedista">Sedista:</label>
                <% for(String s : sedista){ 
                   pom += s +" ";  }
                %>
                <textarea  rows="2" name="sedistaTa" readonly><%=pom%></textarea> <br><br>  
                <input type="hidden" name="idTermina" value="<%=t.getId()%>">             
                <input type="hidden" name="sedista" value="<%=pom%>"> 
                <input type="hidden" name="idKorisnik" value="<%=korisnik.getId()%>"> 
                <input type="hidden" name="idBioskop" value="<%=t.getIdBioskop()%>"> 
                <input type="submit" value="Rezervisi">
            </form>
        
    </body>
</html>
