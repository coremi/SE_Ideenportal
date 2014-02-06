<%-- 
    Document   : login
    Created on : Feb 6, 2014, 8:39:08 AM
    Author     : corina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
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
        
        <form class="login" action="j_security_check" method="post">
            <div class="bg_layer">&nbsp;</div>
            <div class="row">
                <div class="label">
                    Benutzername:
                </div>
                <div class="form-control">
                    
            <h:inputText id="j_username" />
                </div>
            </div>
            <div class="row">
                <div class="label">
                    Passwort:
                </div>
                <div class="form-control">
            <h:inputSecret id="j_password" />
                </div>
            </div>
            <div class="row" style="text-align: right">
                <h:commandButton type="submit"  value="Einloggen" />
            </div>
            
        </form>
        </body>
    </html>
</f:view>
