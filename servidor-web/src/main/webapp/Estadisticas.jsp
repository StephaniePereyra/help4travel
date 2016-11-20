<%-- 
    Document   : Resultado
    Created on : 20/11/2016, 02:06:20
    Author     : juan
--%>


<%@page import="uy.edu.cure.servidor.central.dto.Estadisticas"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="uy.edu.cure.servidor.central.lib.EstadisticaServiceImpl"%>
<%@page import="uy.edu.cure.servidor.central.lib.EstadisticaControllerImpl"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap" %>

<%@page import="java.util.Set" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert title here</title>
    </head>
    <body>
        <%

            HashMap<String, Integer> mapa = (HashMap<String, Integer>) application.getAttribute("estadistica");

            Set<String> conjunto = mapa.keySet();

            for (String clave : conjunto) {

                out.println("pagina :" + clave + " visitas :" + mapa.get(clave) + "<br/>");

            }
            
            EstadisticaServiceImpl estadisticaService = new EstadisticaServiceImpl();
            
            List<Estadisticas> estadisticas =  estadisticaService.obtenerTodosEstadisticas();
            
            for (int i = 0; i<estadisticas.size();i++){
                
                out.println("IP: " + estadisticas.get(i).getIpAdd() + "  URL:  " + estadisticas.get(i).getUrl() + "  Navegador:  " +
                        estadisticas.get(i).getBrowser() + "  SO:  " + estadisticas.get(i).getSo() + "<br/>");
            }
            

        %>
    </body>
</html>