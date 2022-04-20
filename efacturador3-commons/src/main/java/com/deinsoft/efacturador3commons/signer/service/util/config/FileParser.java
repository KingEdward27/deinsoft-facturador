package com.deinsoft.efacturador3commons.signer.service.util.config;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;




public class FileParser
{
  public File getExistingReadableFile(String key, String value) {
    value = getFileName(key, value);
    File file = new File(value);
    if (!file.exists()) {
      throw new InvalidPropertyException(key, value, "existing file (doesn't exist)");
    }
    if (!file.canRead()) {
      throw new InvalidPropertyException(key, value, "readable file (can't read)");
    }
    return file;
  }










  
  public File getWriteableFile(String key, String value) {
    value = getFileName(key, value);
    File file = new File(value);
    
    try {
      if (!file.exists()) {


        
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists() && 
          !parentFile.mkdirs()) {
          throw new InvalidPropertyException(key, value, "writeable file (can't create directories '" + parentFile + "')");
        }

        
        if (!file.createNewFile()) {
          throw new InvalidPropertyException(key, value, "writeable file (can't create)");
        }
      } 
      if (!file.canWrite()) {
        throw new InvalidPropertyException(key, value, "writeable file (can't write)");
      }
      return file;
    } catch (IOException e) {
      throw new InvalidPropertyException(key, value, "writeable file (I/O exception)");
    } 
  }

  
  protected String getFileName(String key, String value) {
    if (containsStrangeWhiteSpace(value)) {
      throw new InvalidPropertyException(key, value, "writeable file (strange whitespace in name; wrong slashes used?)");
    }
    try {
      URL url = new URL(value);
      value = url.getFile().toString();
    } catch (MalformedURLException malformedURLException) {}


    
    return value;
  }







  
  private boolean containsStrangeWhiteSpace(String fileName) {
    char[] cs = fileName.toCharArray();
    for (int i = 0; i < cs.length; i++) {
      if ("\t\n".indexOf(cs[i]) >= 0) {
        return true;
      }
    } 
    return false;
  }
}
