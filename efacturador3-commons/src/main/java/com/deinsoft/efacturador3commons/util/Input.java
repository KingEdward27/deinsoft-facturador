package com.deinsoft.efacturador3commons.util;
 
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.ls.LSInput;
 
 
public class Input implements LSInput
{
   private String publicId;
   private String systemId;
   private BufferedInputStream inputStream;
   
   public String getPublicId() {
     return this.publicId;
   }
   
   public void setPublicId(String publicId) {
     this.publicId = publicId;
   }
   
   public String getBaseURI() {
     return null;
   }
   
   public InputStream getByteStream() {
     return null;
   }
   
   public boolean getCertifiedText() {
     return false;
   }
   
   public Reader getCharacterStream() {
     return null;
   }
   
   public String getEncoding() {
     return null;
   }
   /*    */   
   public String getStringData() {
     synchronized (this.inputStream) {
       
       byte[] input = null;

        try {
            input = new byte[this.inputStream.available()];
        } catch (IOException ex) {
            Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
        }
            try {
                this.inputStream.read(input);
            } catch (IOException ex) {
                Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
            }
         String contents = new String(input);
       return contents;
     } 
   }
 
 
   
   public void setBaseURI(String baseURI) {}
 
 
   
   public void setByteStream(InputStream byteStream) {}
 
 
   
   public void setCertifiedText(boolean certifiedText) {}
 
 
   
   public void setCharacterStream(Reader characterStream) {}
 
   
   public void setEncoding(String encoding) {}
 
   
   public void setStringData(String stringData) {}
 
   
   public String getSystemId() {
     return this.systemId;
   }
   
   public void setSystemId(String systemId) {
     this.systemId = systemId;
   }
   
   public BufferedInputStream getInputStream() {
     return this.inputStream;
   }
   
   public void setInputStream(BufferedInputStream inputStream) {
     this.inputStream = inputStream;
   }
 
 
   /*    */ 
 
   
   public Input(String publicId, String sysId, InputStream input) {
     this.publicId = publicId;
     this.systemId = sysId;
     this.inputStream = new BufferedInputStream(input);
   }
}
