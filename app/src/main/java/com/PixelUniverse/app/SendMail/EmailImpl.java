package com.PixelUniverse.app.SendMail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class EmailImpl implements EmailService {
    @Autowired private JavaMailSender javaMailSender;
    @Value("${MAIL_USERNAME}") private String sender;
    @Override
    public ResponseEntity<?> sendEmailToConfirmOrder(EmailConfirmOrder emailConfirmOrder) {
        String htmlBody = getContentEmail(emailConfirmOrder);
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            helper.setFrom(sender);
            helper.setTo(emailConfirmOrder.getEmailTo());
            helper.setText(htmlBody,true);
            helper.setSubject("Payment success fully");
            javaMailSender.send(mimeMessage);
            System.out.println("send email active success..");
            return ResponseEntity.ok().body("da gui");
        }
        catch (MessagingException e){
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("sai..");
    }

//    private String getContentEmail(){
//        String htmlBody =  "<div style=\"background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); margin: 0 auto; max-width: 600px; font-family: Arial, sans-serif;\">\n" +
//                "    <div style=\"text-align: center; border-bottom: 1px solid #eeeeee; padding-bottom: 10px; margin-bottom: 20px;\">\n" +
//                "        <h1 style=\"color: #327dba; margin: 0; font-size: 24px;\">Purchased successfully!</h1>\n" +
//                "    </div>\n" +
//                "    <div style=\"color: #333333; line-height: 1.6;\">\n" +
//                "        <p>Dear <strong>VU HAI NAM</strong>,</p>\n" +
//                "        <p>Thank you for your recent order from <strong>SHOPPING</strong>!</p>\n" +
//                "        <p>We are pleased to confirm that your order has been successfully processed. Please find your order details below:</p>\n" +
//                "        <p><strong>Invoice Number:</strong> 123123123</p>\n" +
//                "        <h3 style=\"color: #333333; margin: 20px 0 10px; font-size: 18px;\">Product Information:</h3>\n" +
//                "        <table style=\"width: 100%; border-collapse: collapse; margin: 20px 0; font-family: Arial, sans-serif;\">\n" +
//                "            <thead style=\"background-color: #f4f4f4; color: #333;\">\n" +
//                "                <tr style=\"border-bottom: solid 1px black;\">\n" +
//                "                    <th style=\"padding: 12px; text-align: left; border-bottom: 2px solid #ddd;\">Infomation</th>\n" +
//                "                    <th style=\"padding: 12px; text-align: left; border-bottom: 2px solid #ddd;\">Quantity</th>\n" +
//                "                    <th style=\"padding: 12px; text-align: left; border-bottom: 2px solid #ddd;\">Price</th>\n" +
//                "                    <th style=\"padding: 12px; text-align: left; border-bottom: 2px solid #ddd;\">Fee ship</th>\n" +
//                "                    <th style=\"padding: 12px; text-align: left; border-bottom: 2px solid #ddd;\">Total</th>\n" +
//                "                </tr>\n" +
//                "            </thead>\n" +
//                "            <tbody>\n" +
//                "                <tr>\n" +
//                "                    <td style=\"padding: 10px; border-bottom: 1px solid #ddd; vertical-align: top;\"> <strong>That lung da dep zai so 1 the gioi</strong> <br>\n" +
//                "                        <span>Size 1 color pink</span> </td>\n" +
//                "                    <td style=\"text-align: center; padding: 10px; border-bottom: 1px solid #ddd;\">12</td>\n" +
//                "                    <td style=\"padding: 10px; border-bottom: 1px solid #ddd;\">12</td>\n" +
//                "                    <td style=\"text-align: center; padding: 10px; border-bottom: 1px solid #ddd;\">12</td>\n" +
//                "                    <td style=\"padding: 10px; border-bottom: 1px solid #ddd;\"><strong>12$</strong></td>\n" +
//                "                </tr>\n" +
//                "            </tbody>\n" +
//                "        </table>\n" +
//                "        <h3 style=\"color: #333333; margin: 20px 0 10px; font-size: 18px;\">Shipping Address:</h3>\n" +
//                "        <p>tp lang son tinh lang son </p>\n" +
//                "        <hr>\n" +
//                "        <h3 style=\"color: #333333; margin: 20px 0 10px; font-size: 18px;\">Contact Information:</h3>\n" +
//                "        <p> If you have any questions or concerns regarding your order, please feel free to contact us at <a href=\"mailto:info@bestsell.io\" style=\"color: #327dba; text-decoration: none;\">info@shoping</a>. We are always happy to assist you.</p>\n" +
//                "        <p>Thank you again for your order! We hope you enjoy your new purchase.</p>\n" +
//                "    </div>\n" +
//                "    <div style=\"text-align: center; border-top: 1px solid #eeeeee; padding-top: 10px; margin-top: 20px;\"> <a href=\"/\" style=\"display: inline-block; margin-top: 10px; padding: 10px 20px; color: #ffffff; background-color: #327dba; border: none; border-radius: 5px; text-decoration: none; font-size: 16px;\">Continues shopping</a> </div>\n" +
//                "</div>";
//
//        return htmlBody;
//    }

    private String getContentEmail(EmailConfirmOrder emailConfirmOrder){
        //dear customer
        String name = emailConfirmOrder.getFirstName()+emailConfirmOrder.getLastName();
        String dearCustomer = String.format("<p>Dear <strong>%s</strong>,</p>\n", name);
        String invoiceNumber = String.format("        <p><strong>Invoice Number:</strong> %s</p>\n",emailConfirmOrder.getInvoicePrefix()+emailConfirmOrder.getInvoiceNo());
        String address = STR."\{emailConfirmOrder.getShippingAddress1()} ,\{emailConfirmOrder.getShippingAddress2()} ,\{emailConfirmOrder.getShippingCity()}";
        StringBuilder productContent = new StringBuilder();
        for (EmailConfirmOrderDetails details : emailConfirmOrder.getEmailConfirmOrderDetailsList()){
//            String decodedName = new String(Base64.getDecoder().decode(details.getName()), StandardCharsets.UTF_8);
            String decodedName = details.getName();

            String childContent ="                <tr style=\"border-bottom: solid 1px black;\">\n" +
                    "                    <td style=\"padding: 10px; border-bottom: 1px solid #ddd; vertical-align: top;\"> <strong>" + decodedName + "</strong> <br>\n" +
                    "                        <span>"+details.getOptions()+"</span> </td>\n" +
                    "                    <td style=\"text-align: center; padding: 10px; border-bottom: 1px solid #ddd;\">"+details.getQuantity()+"$</td>\n" +
                    "                    <td style=\"padding: 10px; border-bottom: 1px solid #ddd;\">"+details.getPrice()+"</td>\n" +
                    "                    <td style=\"text-align: center; padding: 10px; border-bottom: 1px solid #ddd;\">"+details.getFeeship()+"$</td>\n" +
                    "                    <td style=\"padding: 10px; border-bottom: 1px solid #ddd;\"><strong>"+details.getTotal()+"$</strong></td>\n" +
                    "                </tr>\n";
            productContent.append(childContent);
        }

        String htmlBody =  "<div style=\"background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); margin: 0 auto; max-width: 600px; font-family: Arial, sans-serif;\">\n" +
                "    <div style=\"text-align: center; border-bottom: 1px solid #eeeeee; padding-bottom: 10px; margin-bottom: 20px;\">\n" +
                "        <h1 style=\"color: #327dba; margin: 0; font-size: 24px;\">Order Placed Successfully!</h1>\n" +
                "    </div>\n" +
                "    <div style=\"color: #333333; line-height: 1.6;\">\n" +
                dearCustomer +
                "        <p>Thank you for your recent order from <strong>"+ emailConfirmOrder.getDomainStore() +"</strong>!</p>\n" +
                "        <p>We are pleased to confirm that your order has been successfully processed. Please find your order details below:</p>\n" +
                invoiceNumber +
                "        <h3 style=\"color: #333333; margin: 20px 0 10px; font-size: 18px;\">Product Information:</h3>\n" +
                "        <table style=\"width: 100%; border-collapse: collapse; margin: 20px 0; font-family: Arial, sans-serif;\">\n" +
                "            <thead style=\"background-color: #f4f4f4; color: #333;\">\n" +
                "                <tr style=\"border-bottom: solid 1px black;\">\n" +
                "                    <th style=\"padding: 12px; text-align: left; border-bottom: 2px solid #ddd;\">Infomation</th>\n" +
                "                    <th style=\"padding: 12px; text-align: left; border-bottom: 2px solid #ddd;\">Quantity</th>\n" +
                "                    <th style=\"padding: 12px; text-align: left; border-bottom: 2px solid #ddd;\">Price</th>\n" +
                "                    <th style=\"padding: 12px; text-align: left; border-bottom: 2px solid #ddd;\">Fee ship</th>\n" +
                "                    <th style=\"padding: 12px; text-align: left; border-bottom: 2px solid #ddd;\">Total</th>\n" +
                "                </tr>\n" +
                "            </thead>\n" +
                "            <tbody>\n" +
                productContent +
                "            </tbody>\n" +
                "        </table>\n" +
                "        <h3 style=\"color: #333333; margin: 20px 0 10px; font-size: 18px;\">Shipping Address:</h3>\n" +
                "        <p>"+ address +" </p>\n" +
                "        <hr>\n" +
                "        <h3 style=\"color: #333333; margin: 20px 0 10px; font-size: 18px;\">Contact Information:</h3>\n" +
                "        <p> If you have any questions or concerns regarding your order, please feel free to contact us at <a href=\"mailto:info@bestsell.io\" style=\"color: #327dba; text-decoration: none;\">info@"+emailConfirmOrder.getDomainStore().replace("https://","")+"</a>. We are always happy to assist you.</p>\n" +
                "        <p>Thank you again for your order! We hope you enjoy your new purchase.</p>\n" +
                "    </div>\n" +
                "    <div style=\"text-align: center; border-top: 1px solid #eeeeee; padding-top: 10px; margin-top: 20px;\"> <a href=\"/\" style=\"display: inline-block; margin-top: 10px; padding: 10px 20px; color: #ffffff; background-color: #327dba; border: none; border-radius: 5px; text-decoration: none; font-size: 16px;\">Continues shopping</a> </div>\n" +
                "</div>";
        return htmlBody;
    }
}
