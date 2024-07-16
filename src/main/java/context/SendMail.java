package context;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SendMail {
    public static void main (String [] args){


        final String username = "akshay.karkare@gmail.com";
        final String password = "hwgq kmub npfk rsca";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() { return new
                    PasswordAuthentication(username, password); } });

        session.setDebug(true);
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("akshay.karkare@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("akshay.karkare@gmail.com"));
            //message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("meetshriyash@gmail.com"));

            message.setSubject("UI Automation Report");
            message.setText("Please refer to the AutomationExtentOrSparkReport.html for the test results");

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();

            messageBodyPart = new MimeBodyPart(); String automationReportPath =
                    System.getProperty("user.dir");
            System.out.println("Cucumber Report File Path="+automationReportPath);

            String file = System.getProperty("user.dir")+"/target/cucumber-reports/advance-reports/cucumber-html-reports/overview-features.html";
            String fileName = "overview-features.html";

            //ziping the target report folder
            String sourceFolderPath = System.getProperty("user.dir")+"/target/cucumber-reports/advance-reports";
            String zipPath = System.getProperty("user.dir")+"/report.zip";
            FileOutputStream fos = new FileOutputStream(zipPath);
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            File fileToZip = new File(sourceFolderPath);

            zipFile(fileToZip, fileToZip.getName(), zipOut);
            zipOut.close();
            fos.close();

            DataSource source = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);

            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            System.out.println("Sending");
            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) { e.printStackTrace(); }
        catch (FileNotFoundException fileNotFoundException){}
        catch (IOException ioException){}
        System.out.println("Email hook ending");

    }


    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }
}
