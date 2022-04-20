package com.deinsoft.efacturador3commons.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class FacturadorUtil
{
  public static String executeCommand(String command) throws Exception {
    StringBuffer output = new StringBuffer();

    
    BufferedReader reader = null;
    Process p = Runtime.getRuntime().exec(command);
    p.waitFor();
    reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
    
    String line = "";
    while ((line = reader.readLine()) != null) {
      output.append(line + "\n");
    }
    
    reader.close();
    
    return output.toString();
  }

  public static void comprimirArchivo(OutputStream salida, InputStream entrada, String nombre) throws Exception {
    byte[] buffer = new byte[1024];

    ZipOutputStream zos = new ZipOutputStream(salida);
    ZipEntry ze = new ZipEntry(nombre);
    zos.putNextEntry(ze);

    int len;
    while ((len = entrada.read(buffer)) > 0) {
      zos.write(buffer, 0, len);
    }

    entrada.close();
    zos.closeEntry();

    zos.close();
  }
  public static InputStream comprimirArchivo(InputStream entrada, String nombre) throws Exception {
    byte[] buffer = new byte[1024];
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ZipOutputStream zos = new ZipOutputStream(bos);
    ZipEntry ze = new ZipEntry(nombre);
    zos.putNextEntry(ze);
    
    int len;
    while ((len = entrada.read(buffer)) > 0) {
      zos.write(buffer, 0, len);
    }
    
    entrada.close();
    zos.closeEntry();
    
    zos.close();
      return new ByteArrayInputStream(bos.toByteArray());
  }

  
//  public static String convertirListaJson(List<Documento> listaConvertir) throws Exception {
//    StringBuilder strListado = new StringBuilder();
//    
//    if (listaConvertir.size() > 0) {
//      
//      ObjectMapper mapper = new ObjectMapper();
//      String lista = mapper.writeValueAsString(listaConvertir);
//      strListado = new StringBuilder(lista);
//    } else {
//      
//      strListado.setLength(0);
//      strListado.append("{").append("\"sEcho\": 1,").append("\"iTotalRecords\": 0,")
//        .append("\"iTotalDisplayRecords\": 0,").append("\"aaData\":[").append("]").append("}");
//    } 
//    
//    return strListado.toString();
//  }
  
  public static String obtenerCodigoError(String rutaArchivo, Integer lineaArchivo) throws Exception {
    String linea = "";
    Integer contador = Integer.valueOf(1);
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader(rutaArchivo));
      while ((linea = br.readLine()) != null && 
        contador.intValue() != lineaArchivo.intValue())
      {
        Integer integer1 = contador, integer2 = contador = Integer.valueOf(contador.intValue() + 1);
      }
      br.close();
    } catch (Exception e) {
      throw new Exception("Error en el utilitario obtenerLineaArchivo: " + e.getMessage());
    } 
    
    if (linea == null) {
      linea = "";
    }
    return linea;
  }
  
  public static String obtenerNumeroEnCadena(String mensaje) {
    Integer posicion = Integer.valueOf(mensaje.indexOf("errorCode"));
    if (posicion.intValue() > 0) {
      mensaje = mensaje.substring(posicion.intValue());
    }
    Integer largo = Integer.valueOf(mensaje.length());
    String numero = "";
    int endError = 0;
    for (int i = 0; i < largo.intValue() && 
      endError != 4; i++) {
      
      if (Character.isDigit(mensaje.charAt(i))) {
        numero = numero + mensaje.charAt(i);
        endError++;
      } 
    } 

    
    return numero;
  }
  
  public static Boolean esNumerico(String cadena) {
    Boolean retorno = Boolean.valueOf(cadena.matches("^[0-9]{1,2}$"));
    return retorno;
  }
  
  public static String completarCeros(String cadena, String lado, Integer cantidad) {
    String cadenaCompletada = "";
    
    if ("D".equals(lado)) {
      cadenaCompletada = String.format("%1$-" + cantidad + "s", new Object[] { cadena }).replace(" ", "0");
    } else {
      cadenaCompletada = String.format("%1$" + cantidad + "s", new Object[] { cadena }).replace(" ", "0");
    } 
    return cadenaCompletada;
  }


  
  public static void crearArchivoZip(String rutaArchivo, byte[] archivoZip) {
    try {
      ZipInputStream zipStream = new ZipInputStream(new ByteArrayInputStream(archivoZip));
      ZipEntry entry = null;
      FileOutputStream out = null;
      while ((entry = zipStream.getNextEntry()) != null) {
        
        String entryName = rutaArchivo + entry.getName();
        
        out = new FileOutputStream(entryName);
        
        byte[] byteBuff = new byte[4096];
        int bytesRead = 0;
        while ((bytesRead = zipStream.read(byteBuff)) != -1) {
          out.write(byteBuff, 0, bytesRead);
        }
        
        out.close();
        zipStream.closeEntry();
      } 
      zipStream.close();
    }
    catch (Exception e) {
      throw new RuntimeException("Error al crear archivo ZIP", e);
    } 
  }
  public static String Desencriptar(String textoEncriptado) {
        String secretKey = "qualityinfosolutions";

        String base64EncryptedString = "";

        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(2, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, "UTF-8");

            return base64EncryptedString;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error al des encriptar", e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al des encriptar", e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException("Error al des encriptar", e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Error al des encriptar", e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException("Error al des encriptar", e);
        } catch (BadPaddingException e) {
            throw new RuntimeException("Error al des encriptar", e);
        }
    }

    public static String Encriptar(String texto) {
        try {
            String secretKey = "qualityinfosolutions";
            String base64EncryptedString = "";

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(1, key);

            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

            return base64EncryptedString;
        } catch (Exception e) {
            throw new RuntimeException("Error al des encriptar", e);
        }
    }
    public static boolean isNullOrEmpty(Object object){
        if(object == null) return true;
        if(String.valueOf(object).equals("")) return true;
        return false;
    }
    public static boolean isNumeric(Object object){
        if(object == null) return false;
        if(String.valueOf(object).equals("")) return false;
        try {
            Float.parseFloat(String.valueOf(object));
//            BigDecimal test = new BigDecimal(String.valueOf(object));
//            if(new BigDecimal(String.valueOf(object)) == BigDecimal.ZERO) return true;
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}