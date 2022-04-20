package com.deinsoft.efacturador3commons.signer;







public class SignDocumentException
  extends Exception
{
  private static final long serialVersionUID = 1L;
  
  public SignDocumentException() {
    super("No se pudo crear el documento a firmar");
  }
  
  public SignDocumentException(Throwable e) {
    super("No se pudo crear el documento a firmar", e);
  }
}
