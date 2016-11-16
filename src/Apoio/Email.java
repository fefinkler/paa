/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apoio;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author asimon
 */
public class Email {

    public static void sendEmail(String he) throws EmailException {

        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("serviceshelplog@gmail.com", "services123!@"));
        email.setSSLOnConnect(true);
        email.setFrom("serviceshelplog@gmail.com");
        email.setSubject("Log de Erro ServicesHelp ");
        email.setMsg(he);
        email.addTo("saimon@universo.univates.br");
        email.send();

    }

}
