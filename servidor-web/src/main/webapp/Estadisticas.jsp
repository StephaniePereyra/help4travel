<%-- 
    Document   : Resultado
    Created on : 20/11/2016, 02:06:20
    Author     : juan
--%>


<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uy.edu.cure.servidor.central.dto.Ranking"%>
<%@page import="uy.edu.cure.servidor.central.lib.RankignService"%>
<%@page import="uy.edu.cure.servidor.central.lib.RankingServiceImpl"%>
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

            RankingServiceImpl rankigService = new RankingServiceImpl();

            out.println("***************RANKIG**********************");
             for (int i = 0; i < rankigService.obtenerRanking().size(); i++) {
                out.println("<br/> servicio: " + rankigService.obtenerRanking().get(i).getServicio() + " proveedor: " + 
                       rankigService.obtenerRanking().get(i).getProveedor()
                        +" " + rankigService.obtenerRanking().get(i).getVisitas());
                
            }
            
           /* 
            // La clase ranking implenta comparable para "visitas" lo que permite ordenarlo Arrays.sort(arrayRankig)
             int j = rankigService.obtenerRanking().size();
            Ranking[] arrayRankig = new Ranking[j];
            for (int i = 0; i < j; i++) {

                arrayRankig[i] = rankigService.obtenerRanking().get(i);

            }
            Arrays.sort(arrayRankig); //ORDENA            
            
            for (int i = 0; i < j; i++) {
                out.println("<br/> servicio: " + arrayRankig[i].getServicio() + "proveedor: " + arrayRankig[i].getProveedor() +" " +arrayRankig[i].getVisitas());
                
            }
            */
            out.println("<br/> ***************Estadisticas**********************");
            
            EstadisticaServiceImpl estadisticaService = new EstadisticaServiceImpl();
            List<Estadisticas> estadisticas = estadisticaService.obtenerTodosEstadisticas();
            for (int i = 0; i < estadisticas.size(); i++) {
                out.println("<br/> IP: " + estadisticas.get(i).getIpAdd() + "  URL:  " + estadisticas.get(i).getUrl() + "  Navegador:  "
                        + estadisticas.get(i).getBrowser() + "  SO:  " + estadisticas.get(i).getSo());
            }


        %>
    </body>
</html>