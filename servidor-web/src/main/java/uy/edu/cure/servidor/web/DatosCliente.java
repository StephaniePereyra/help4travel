/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import uy.edu.cure.servidor.central.dto.Factura;
import uy.edu.cure.servidor.central.soap.client.UsuarioWS;
import uy.edu.cure.servidor.central.soap.client.UsuarioWSImplService;

/**
 *
 * @author guido
 */
@ManagedBean
@ViewScoped
public class DatosCliente {

    private MensajeDatosUsuario datosusuarioMnsj = new MensajeDatosUsuario();
    private String nickName, nombre, apellido, correo, ruta, passWord, passWordConfirm, mensaje, mensajeDefault = "*No pueden existir campos vacios*";
    private int dia, mes, anio;
    private boolean mostrarMensaje = false;
    private List<Integer> dias, meses, anios;
    private Part imagen;
    private UsuarioWSImplService usuarioWSImplService;
    private UsuarioWS port;
    
    public DatosCliente() {
        
        try {
            usuarioWSImplService = new UsuarioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/UsuarioWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(VerReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
         port = usuarioWSImplService.getUsuarioWSImplPort();

        dias = new ArrayList();
        meses = new ArrayList();
        anios = new ArrayList();
        for (int i = 1; i < 32; i++) {
            dias.add(i);
        }
        for (int i = 1; i < 13; i++) {
            meses.add(i);
        }
        for (int i = 2016; i >= 1900; i--) {
            anios.add(i);
        }
    }

    public String getPassWordConfirm() {
        return passWordConfirm;
    }

    public void setPassWordConfirm(String passWordConfirm) {
        this.passWordConfirm = passWordConfirm;
    }

    public boolean isMostrarMensaje() {
        return mostrarMensaje;
    }

    public void setMostrarMensaje(boolean mostrarMensaje) {
        this.mostrarMensaje = mostrarMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public MensajeDatosUsuario getDatosusuarioMnsj() {
        return datosusuarioMnsj;
    }

    public void setDatosusuarioMnsj(MensajeDatosUsuario datosusuarioMnsj) {
        this.datosusuarioMnsj = datosusuarioMnsj;
    }

    public String getMensajeDefault() {
        return mensajeDefault;
    }

    public void setMensajeDefault(String mensajeDefault) {
        this.mensajeDefault = mensajeDefault;
    }

    public List<Integer> getDias() {
        return dias;
    }

    public void setDias(List<Integer> dias) {
        this.dias = dias;
    }

    public List<Integer> getMeses() {
        return meses;
    }

    public void setMeses(List<Integer> meses) {
        this.meses = meses;
    }

    public List<Integer> getAnios() {
        return anios;
    }

    public void setAnios(List<Integer> anios) {
        this.anios = anios;
    }

    public Part getImagen() {
        return imagen;
    }

    public void setImagen(Part imagen) {
        this.imagen = imagen;
    }

    public void action() throws IOException {
        InputStream input;
        OutputStream output;
        String rutaUp = "";
        int resultado;
        mostrarMensaje = true;

        if (imagen == null) {
            rutaUp = "images/perfil/default.png";
            resultado = port.crearClienteWS(nickName, nombre, apellido, correo, dia, mes, anio, rutaUp, passWord, passWordConfirm);
        } else {

            String tipo = imagen.getContentType();
            String formato1 = "image/png";
            String formato2 = "image/jpeg";
            String formato3 = "image/jpg";

            if (!formato1.equals(tipo) && !formato2.equals(tipo) && !formato3.equals(tipo)) {
                resultado = 6;
            } else {
                input = imagen.getInputStream();
                Date date = new Date();
                File perfil = new File("C:/Users/SCN/help4travel/servidor-web/src/main/webapp/images/perfil/" + date.getTime() + ".png");
                output = new FileOutputStream(perfil);

                byte[] buffer = new byte[8 * 1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
                rutaUp = "images/perfil/" + date.getTime() + ".png";
                resultado = port.crearClienteWS(nickName, nombre, apellido, correo, dia, mes, anio, rutaUp, passWord, passWordConfirm);
            }
        }

        if (nickName.equals("") || nombre.equals("") || apellido.equals("") || correo.equals("")) {
            mensaje = mensajeDefault;
        } else {
            mensaje = datosusuarioMnsj.retornoMensajeUsuario(resultado);
        }
    }

    public String actionPDF(Integer nroReserva) throws IOException, DocumentException{
        Factura facturaAux;
        String url;
        String numeroReserva = nroReserva.toString();
        
        url = "http://localhost:8080/servidor-central-webapp/rest/api/ObtenerFactura/traer/"+numeroReserva;
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        ObjectMapper mapper = new ObjectMapper();
        HttpResponse response = null; 
        String result = null;
            response = client.execute(request);
            result = getStringFromInputStream(response.getEntity().getContent());
            facturaAux = mapper.readValue(result, Factura.class);
        
        Document documento = new Document();
        Date date = new Date();
        FileOutputStream rutaPDF = null;
        
            rutaPDF = new FileOutputStream( "/home/guido/help4travel/servidor-web/src/main/webapp/pdf/" + date.getTime() + ".pdf" );
            PdfWriter.getInstance(documento, rutaPDF);
            documento.open();
            documento.add(new Paragraph("asdasda"));
            documento.close();
            String ruta = "pdf/" + date.getTime() + ".pdf";
            return ruta;
    }
        private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
}
