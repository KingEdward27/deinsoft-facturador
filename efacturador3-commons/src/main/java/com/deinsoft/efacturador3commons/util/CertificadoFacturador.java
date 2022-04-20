package com.deinsoft.efacturador3commons.util;

import com.deinsoft.efacturador3commons.util.Constantes;
import com.deinsoft.efacturador3commons.util.FacturadorUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Enumeration;
import java.util.HashMap;
import org.jboss.weld.exceptions.IllegalArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CertificadoFacturador {
  private static final Logger log = LoggerFactory.getLogger(CertificadoFacturador.class);

  
  public String validaCertificado(String input, String numRuc, String passPrivateKey) {
    FileInputStream fis;
    log.debug("GenerarDocumentosServiceImpl.validaCertificado...Iniciando validacion de certificado");
    String validacion = "", alias = "", cadena = "";
    String cadenaValidar = numRuc;
    Boolean certificadoCorrecto = Boolean.valueOf(false);
    Certificate cf = null;
    
    try {
      fis = new FileInputStream(input);
    } catch (FileNotFoundException e1) {
      throw new IllegalArgumentException("No se pudo encontrar el archivo: " + input, e1);
    } 
    
    try {
      KeyStore ks = KeyStore.getInstance("PKCS12");
      
      ks.load(fis, passPrivateKey.toCharArray());
      
      for (Enumeration<String> e = ks.aliases(); e.hasMoreElements() && 
        !certificadoCorrecto.booleanValue(); ) {
        alias = e.nextElement();
        log.debug("GenerarDocumentosServiceImpl.validaCertificado...Alias: " + alias);
        if (ks.isKeyEntry(alias)) {
          cf = ks.getCertificate(alias);
          log.debug("GenerarDocumentosServiceImpl.validaCertificado...cf.toString: " + cf.toString());
          cadena = cf.toString();
          
          if (cadena.indexOf(cadenaValidar) > 0) {
            certificadoCorrecto = Boolean.valueOf(true);
          }
        }
      
      }
    
    } catch (KeyStoreException e1) {
      try {
        fis.close();
      } catch (IOException iOException) {}
      
      throw new IllegalArgumentException("No se pudo procesar el certificado: " + input, e1);
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalArgumentException("No se pudo procesar el certificado: " + input, e);
    } catch (CertificateException e) {
      throw new IllegalArgumentException("No se pudo procesar el certificado: " + input, e);
    } catch (IOException e) {
      throw new IllegalArgumentException("No se pudo procesar el certificado: " + input, e);
    } 


    
    if (!certificadoCorrecto.booleanValue()) {
      validacion = "El propietario del certificado no es el RUC " + numRuc;
    } else {
      validacion = "[ALIAS]:" + alias;
    } 
    log.debug("GenerarDocumentosServiceImpl.validaCertificado...Finalizando validacion de certificado");
    return validacion;
  }
  public HashMap<String, Object> importarCertificado(HashMap<String, Object> obj) throws Exception {
        String aliasPfx = "";
        Integer error = Integer.valueOf(0);
        HashMap<String, Object> resultado = new HashMap<>();
        String nombreCertificado = (obj.get("nombreCertificado") != null) ? (String) obj.get("nombreCertificado") : "";
        String passPrivateKey = (obj.get("passPrivateKey") != null) ? (String) obj.get("passPrivateKey") : "";
        String numDoc = (obj.get("numDoc") != null) ? (String) obj.get("numDoc") : "";
        String rootPath = (obj.get("rootPath") != null) ? (String) obj.get("rootPath") : "";
        String companyPath = rootPath + numDoc;
        String rutaCertificado = companyPath + "/" + Constantes.CONSTANTE_CERT + "/" + nombreCertificado;
        String certGenericPath = rootPath + "ALMCERT/";
        log.info(rutaCertificado);
        log.info(certGenericPath+"FacturadorKey.jks");
        resultado.put("validacion", "EXITO");
        if ("".equals(rutaCertificado)) {
            resultado.put("validacion", "Debe ingresar la ruta del certificado");
            error = Integer.valueOf(1);
        }

        if (rutaCertificado.indexOf(".pfx") == -1 && rutaCertificado.indexOf(".p12") == -1 && error.intValue() == 0) {
            resultado.put("validacion", "Archivo cargado debe ser de tipo \"pfx o p12\" ");
            error = Integer.valueOf(1);
        }

        if ("".equals(passPrivateKey) && error.intValue() == 0) {
            resultado.put("validacion", "Debe ingresar su contraseña de certificado");
            error = Integer.valueOf(1);
        }

        if (error.intValue() == 0) {

            try {
                String output = this.validaCertificado(rutaCertificado, numDoc, passPrivateKey);
                log.debug("Metodo importarCertificado: Salida de generarDocumentosService.validaCertificado " + output);
                if (!output.contains("[ALIAS]")) {
                    resultado.put("validacion", "Certificado, no esta configurado con el valor del RUC");
                    error = Integer.valueOf(1);
                } else {
                    Integer position = Integer.valueOf(output.indexOf(":") + 1);
                    aliasPfx = output.substring(position.intValue());
                }
            } catch (Exception e) {
                log.debug("Mensaje de Error: " + e.getMessage());
            }
        }

        if (error.intValue() == 0) {

            String salida = FacturadorUtil.executeCommand("keytool -delete -alias "+Constantes.PRIVATE_KEY_ALIAS+numDoc+" -storepass SuN@TF4CT -keystore " + certGenericPath + "FacturadorKey.jks");

            log.debug("Metodo importarCertificado: Salida de keytool -delete " + salida);
            String command;
            if(aliasPfx.contains(" ")){
                command = "keytool -importkeystore -srcalias \"" + aliasPfx + "\" -srckeystore " + rutaCertificado + " -srcstoretype pkcs12 -srcstorepass " + passPrivateKey + " -destkeystore " + certGenericPath + "FacturadorKey.jks -deststoretype JKS -destalias "+Constantes.PRIVATE_KEY_ALIAS+numDoc+" -deststorepass SuN@TF4CT";
            }else{
                command = "keytool -importkeystore -srcalias " + aliasPfx + " -srckeystore " + rutaCertificado + " -srcstoretype pkcs12 -srcstorepass " + passPrivateKey + " -destkeystore " + certGenericPath + "FacturadorKey.jks -deststoretype JKS -destalias "+Constantes.PRIVATE_KEY_ALIAS+numDoc+" -deststorepass SuN@TF4CT";
            }
            
            log.debug("command: "+command);
            salida = FacturadorUtil.executeCommand(command);

            log.debug("Metodo importarCertificado: Salida de keytool -importkeystore " + salida);
            if (!"".equals(salida)) {
                resultado.put("validacion", "Hubo un error, el certificado no fue creado");
                error = Integer.valueOf(1);
            }
        }
        return resultado;
    }
}
