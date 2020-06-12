/**
 * @License
 * Copyright 2020 Orion Services
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package orion.user.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Data
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "EMAIL", unique = true)
    private String email;
    
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;



    @ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable(name="EMAIL_ROLES",
        joinColumns = {@JoinColumn(name="email_id", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}
    )

    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String setPassword(String md5) {
        try {
             java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
             byte[] array = md.digest(md5.getBytes());
             StringBuffer sb = new StringBuffer();
             for (int i = 0; i < array.length; ++i) {
               sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
             return this.password = sb.toString();
         } catch (java.security.NoSuchAlgorithmException e) {
         }
         return null;
     }

     public String getPassword(String md5) {
        try {
             java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
             byte[] array = md.digest(md5.getBytes());
             StringBuffer sb = new StringBuffer();
             for (int i = 0; i < array.length; ++i) {
               sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
             return this.password = sb.toString();
         } catch (java.security.NoSuchAlgorithmException e) {
         }
         return null;
     }


     public void sendEmail(String email) {
        //Declare recipient's & sender's e-mail id.
        String destmailid = email;
        String sendrmailid = "guilhermemoreiramex@gmail.com";	  
       //Mention user name and password as per your configuration
        final String uname = "guilhermemoreiramex@gmail.com";
        final String pwd = "taturanaLOL171";
        //We are using relay.jangosmtp.net for sending emails
        String smtphost = "smtp.gmail.com";
       //Set properties and their values
        Properties propvls = new Properties();
        propvls.put("mail.smtp.auth", "true");
        propvls.put("mail.smtp.starttls.enable", "true");
        propvls.put("mail.smtp.host", smtphost);
        propvls.put("mail.smtp.port", "587");
        //Create a Session object & authenticate uid and pwd
        Session sessionobj = Session.getInstance(propvls,
           new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                 return new PasswordAuthentication(uname, pwd);
         }
           });
  
        try {
         //Create MimeMessage object & set values
         Message messageobj = new MimeMessage(sessionobj);
         messageobj.setFrom(new InternetAddress(sendrmailid));
         messageobj.setRecipients(Message.RecipientType.TO,InternetAddress.parse(destmailid));
         messageobj.setSubject("This is test Subject");
         messageobj.setText("Checking sending emails by using JavaMail APIs");
        //Now send the message
         Transport.send(messageobj);
         System.out.println("Your email sent successfully....");
        } catch (MessagingException exp) {
           throw new RuntimeException(exp);
        }
     }
}