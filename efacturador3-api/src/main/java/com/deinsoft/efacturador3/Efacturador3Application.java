package com.deinsoft.efacturador3;

import com.deinsoft.efacturador3commons.service.FacturaElectronicaService;
import io.github.project.openubl.xmlsenderws.webservices.managers.BillServiceManager;
import io.github.project.openubl.xmlsenderws.webservices.providers.BillServiceModel;
import io.github.project.openubl.xmlsenderws.webservices.wrappers.ServiceConfig;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages="com.deinsoft")
public class Efacturador3Application {

    @Autowired
    FacturaElectronicaService facturaElectronicaService;
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Efacturador3Application.class, args);
//        ServiceConfig config = new ServiceConfig.Builder()
//            .url("https://e-factura.sunat.gob.pe/ol-ti-itcpfegem/billService")
//            .username("10414316595EBOLETAS") 
//            .password("Eboletas123")
//            .build();
//            
//        BillServiceModel result =   BillServiceManager.getStatus("202208987586277", config);//202208980058201, 202208984136401
//        System.out.println("result "+result.getStatus());
//        System.out.println("result "+result.getDescription());
//        System.out.println("result "+result.getCode());
//        System.out.println("result "+result.toString());
//        String myByteArray = "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";
//        try (FileOutputStream fos = new FileOutputStream("D:/ARCHIVO.zip")) {
//            fos.write(myByteArray. getBytes());
//            //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
//         }
    }

//    @Scheduled(fixedDelay = 10000)
    void sendSunat() {
        facturaElectronicaService.sendToSUNAT();
    }
//    public static String Encriptar(String texto) {
//        try {
//            String secretKey = "qualityinfosolutions";
//            String base64EncryptedString = "";
//
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
//            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
//
//            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
//            Cipher cipher = Cipher.getInstance("DESede");
//            cipher.init(1, key);
//
//            byte[] plainTextBytes = texto.getBytes("utf-8");
//            byte[] buf = cipher.doFinal(plainTextBytes);
//            byte[] base64Bytes = Base64.encodeBase64(buf);
//            base64EncryptedString = new String(base64Bytes);
//
//            return base64EncryptedString;
//        } catch (Exception e) {
//            throw new RuntimeException("Error al des encriptar", e);
//        }
//    }

}
