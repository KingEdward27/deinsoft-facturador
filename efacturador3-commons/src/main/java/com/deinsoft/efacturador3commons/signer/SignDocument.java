package com.deinsoft.efacturador3commons.signer;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyStoreException;

public interface SignDocument {
  ByteArrayOutputStream signDocumento(InputStream paramInputStream) throws KeyStoreException, SignDocumentException;
}
