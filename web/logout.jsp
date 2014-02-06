<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Lights - das Ideenportal</title>
        <style type="text/css">        
            <jsp:include page="resources/css/cssLayout.css" />
        </style>
    </head>
    <body style="background-image: url('resources/img/bg.svg');">  
        <div class="row">
            <img class="login_title" src="resources/img/title.svg"></img>
        </div>        
        <% request.getSession(false).invalidate(); %>
        <form class="login" action="login.jsp">
            Sie haben sich erfolgreich abgemeldet.
            <div class="row" style="text-align: right">
                <input type="submit" value="Erneut anmelden" />
            <div/>
        </form>
    </body>
</html>
