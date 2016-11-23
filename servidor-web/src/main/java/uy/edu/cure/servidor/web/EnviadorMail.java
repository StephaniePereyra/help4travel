package uy.edu.cure.servidor.web;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class EnviadorMail {
    
    final String miCorreo = "progapplabo@gmail.com";
    final String miContraseña = "programaciondeaplicaciones123";
    final String servidorSMTP = "smtp.gmail.com";
    final String puertoEnvio = "465";
    String[] files;
    String mailReceptor = null;
    String asunto = null;
    String cuerpo = null;

    public EnviadorMail (String mailReceptor, String asunto,
            String cuerpo, String[] files) {
        this.mailReceptor = mailReceptor;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.files = files;

        Properties props = new Properties();
        props.put("mail.smtp.user", miCorreo);
        props.put("mail.smtp.host", servidorSMTP);
        props.put("mail.smtp.port", puertoEnvio);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", puertoEnvio);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        SecurityManager security = System.getSecurityManager();
        
        //Para el envio de archivos
        Multipart mp = new MimeMultipart();

        try {
            Authenticator auth = new autentificadorSMTP();
            Session session = Session.getInstance(props, auth);
            // session.setDebug(true);

            MimeMessage msg = new MimeMessage(session);
            msg.setText(cuerpo);
            msg.setSubject(asunto);
            
            //carga al cuerpo el archivo/s
            for (int i = 0; i < files.length; i++) {
                System.out.println("archivo" + i + "=" + files[i]);
                MimeBodyPart adjunto = new MimeBodyPart();
                adjunto.setDataHandler(new DataHandler(new FileDataSource(files[i])));
                adjunto.setFileName(new FileDataSource(files[i]).getName());
                mp.addBodyPart(adjunto);
}
            msg.setContent(mp);
            msg.setFrom(new InternetAddress(miCorreo));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    mailReceptor));
            Transport.send(msg);
        } catch (Exception mex) {
            mex.printStackTrace();
        }

    }

    private class autentificadorSMTP extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(miCorreo, miContraseña);
        }
    }
    
   
	public static void main(String[] args) {
         //   String[] files = {"C:\\Users\\SCN\\Pictures\\txt.txt"};
        // EnviadorMail EnviadorMail = new EnviadorMail("naturamarley@gmail.com","Este es el asunto de mi correo", "Este es el cuerpo de mi correo", files);
            
   }
}
