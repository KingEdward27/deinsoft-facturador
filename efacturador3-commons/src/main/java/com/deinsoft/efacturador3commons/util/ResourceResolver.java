package com.deinsoft.efacturador3commons.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;

public class ResourceResolver implements LSResourceResolver {
  private static final Logger log = LoggerFactory.getLogger(ResourceResolver.class);
  
  public ResourceResolver(String path){
    this.path = path;
  }
  private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
  
  

  
  public LSInput resolveResource(String type, String namespaceURI, String publicId, String systemId, 
          String baseURI){
    String rutaArchivo = "";
    
    rutaArchivo = systemId.replace("../", "");
    log.debug("rutaArchivo Primera Depuracion: " + rutaArchivo);
    
    if (rutaArchivo.indexOf("common/") == -1) {
      rutaArchivo = "common/" + systemId;
    }
    log.debug("rutaArchivo Segunda Depuracion: " + rutaArchivo);
    InputStream resourceAsStream = null;
      try {
          resourceAsStream = new FileInputStream(this.path+ rutaArchivo);
      } catch (Exception e) {
          e.printStackTrace();
      }
    return new Input(publicId, rutaArchivo, resourceAsStream);
  }
}