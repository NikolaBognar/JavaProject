<%-- 
    Author     : Nikola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"> --> 
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <style>
            body{
                background-image: url(img/cinema.jpg);
                background-size: cover;
                background-attachment: fixed;
            }
            .figure-caption{
                color: grey;
                text-align: center;
            }
            a{
                color: grey;
            }
            h2{
                color: black;
            }
            .content{
                text-align: center;
            }
            .reg {
                text-align: center;
                background-color: silver;
                width: 500px;
                height: 580px;
                margin-left: 35%;
                margin-top: 5%;
           }  
           .reg h2{
               margin-bottom: 40px;
               padding-top: 20px;
           }
           #lozinka{
               margin-left: 14%;
           }
           #msg {
                color: red;
           }
        </style>  
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-md navbar-light" style="background-color: lightsteelblue" center>
            <a class="navbar-brand"><img src="img/logo2.png" width="70" heigh="50"></a>
            <span class="navbar-text" style="color:darkgray"><b>Nas bioskop</b></span>
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
                    <li class="nav-item">
                        <a class="nav-link" href="login.jsp">PRIJAVA</a>
                    </li>
                    <li class="nav-item" >
                        <a class="nav-link" href="registracija.jsp">REGISTRACIJA</a>
                    </li>                   
                </ul>
        </nav>
        <div class="content">
            <div class="reg">
                <h2>Registracija</h2>
                    <div class="forma">
                        <form method="post" action="Registracija">
                            <label for="username">Korisnicko ime:</label>
                            <input type="text" name="username"> <br><br>

                            <label for="password">Lozinka:</label>
                            <input type="password" name="password"> <br><br>

                            <label for="ime">Ime:</label>
                            <input type="text" name="ime"> <br><br>

                            <label for="prezime">Vase prezime:</label>
                            <input type="text" name="prezime"> <br><br>

                            <label for="email">E-Mail adresa:</label>
                            <input type="email" name="email"> <br><br> 

                            <label for="pol">Pol:</label>                    
                            <input type="radio" name="pol" id="musko" value="musko">
                            <label for="musko" >Musko</label> &emsp;                  
                            <input type="radio" name="pol" id="zensko" value="zensko"> 
                            <label for="zensko" >Zensko</label> <br><br>

                            <label for="ime">Datum rodjenja:</label>
                            <input type="date" name="datum"> <br><br> 

                            <input type="submit" class="potvrdi" value="Potvrdi">
                            <p id="msg">  <% String msg = (String)request.getAttribute("msg");
                            if(msg != null) { %>
                            <%= msg %>
                            <% } %>
                            </p>
                        </form>
                    </div>
            </div>
        </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    </body>
</html>
