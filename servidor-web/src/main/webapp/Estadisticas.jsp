<%-- 
    Document   : Resultado
    Created on : 20/11/2016, 02:06:20
    Author     : juan
--%>


<%@page import="java.io.IOException"%>
<%@page import="org.apache.http.HttpResponse"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="org.apache.http.client.methods.HttpGet"%>
<%@page import="org.apache.http.client.HttpClient"%>
<%@page import="org.apache.http.impl.client.HttpClientBuilder"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uy.edu.cure.servidor.central.dto.Ranking"%>
<%@page import="uy.edu.cure.servidor.central.lib.RankignService"%>
<%@page import="uy.edu.cure.servidor.central.lib.RankingServiceImpl"%>
<%@page import="uy.edu.cure.servidor.central.dto.Historial"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="uy.edu.cure.servidor.central.lib.HistorialerviceImpl"%>
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
                out.println("<br/> servicio: " + rankigService.obtenerRanking().get(i).getServicio() + " proveedor: "
                        + rankigService.obtenerRanking().get(i).getProveedor()
                        + " " + rankigService.obtenerRanking().get(i).getVisitas());

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
            //Se realiza la query usar debugg para ver contenido

            List<Historial> historial = null;

            String url;
            url = "http://localhost:8080/servidor-central-webapp/rest/api/ObtenerFactura/traer/1";
            HttpClient client1 = HttpClientBuilder.create().build();
            HttpGet request1 = new HttpGet(url);
            ObjectMapper mapper1 = new ObjectMapper();
            HttpResponse response1 = null;
            String result1 = null;
            try {
                response1 = client1.execute(request1);
                result1 = getStringFromInputStream1(response1.getEntity().getContent());

                historial = mapper1.readValue(result1, List.class);
            } catch (IOException ex) {

            }

            out.println("<br/> ***************Historial**********************");

            HistorialerviceImpl Historialervice = new HistorialerviceImpl();
            List<Historial> Historial = Historialervice.obtenerTodosHistorial();
            for (int i = 0; i < Historial.size(); i++) {
                out.println("<br/> IP: " + Historial.get(i).getIpAdd() + "  URL:  " + Historial.get(i).getUrl() + "  Navegador:  "
                        + Historial.get(i).getBrowser() + "  SO:  " + Historial.get(i).getSo());
            }


        %>
    </body>
</html>