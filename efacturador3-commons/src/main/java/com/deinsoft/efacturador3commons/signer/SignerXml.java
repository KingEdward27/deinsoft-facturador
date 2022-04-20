/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3commons.signer;
import com.deinsoft.efacturador3commons.model.Empresa;
import com.deinsoft.efacturador3commons.util.Constantes;
import com.deinsoft.efacturador3commons.util.FacturadorUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.xml.security.c14n.Canonicalizer;

import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.ElementProxy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 *
 * @author EDWARD-PC
 */
public class SignerXml {
    public static Map<String, Object> firmarXml(String rootPath, Empresa empresa, Document doc,String fileName) throws Exception {
        Map<String, Object> retorno = new HashMap<>();
        System.out.println("/ INICIO.");

        // Obtenemos las propiedades para firmar el documento.
        String sTipoAlmacen = "jks";
        String sAlmacen = rootPath + "ALMCERT/FacturadorKey.jks";
        String sClaveAlmacen = "SuN@TF4CT";
        String sClavePrivada = FacturadorUtil.Desencriptar(empresa.getCertPass());
        String sAlias = Constantes.PRIVATE_KEY_ALIAS + empresa.getNumdoc();

        org.apache.xml.security.Init.init();

//        Constants.setSignatureSpecNSprefix("ds"); // Sino, pone por defecto como prefijo: "ns"
        ElementProxy.setDefaultPrefix(Constants.SignatureSpecNS, "ds");
        // Cargamos el almacen de claves
        KeyStore ks  = KeyStore.getInstance(sTipoAlmacen);
        ks.load(new FileInputStream(sAlmacen), sClaveAlmacen.toCharArray());

        // Obtenemos la clave privada, pues la necesitaremos para encriptar.
        PrivateKey privateKey = (PrivateKey) ks.getKey(sAlias, sClavePrivada.toCharArray());

        File    signatureFile = new File("signature.xml");
        String  baseURI = signatureFile.toURI().toString();   // BaseURI para las URL Relativas.

        // Instanciamos un objeto XMLSignature desde el Document. El algoritmo de firma será RSA
        XMLSignature xmlSignature = new XMLSignature(doc, baseURI, XMLSignature.ALGO_ID_SIGNATURE_RSA);

        // Añadimos el nodo de la firma a la raiz antes de firmar.
        // Observe que ambos elementos pueden ser mezclados en una forma con referencias separadas
        doc.getDocumentElement().appendChild(xmlSignature.getElement());

        // Creamos el objeto que mapea: Document/Reference
        Transforms transforms = new Transforms(doc);
        transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
        transforms.addTransform(Transforms.TRANSFORM_C14N_OMIT_COMMENTS);

        // Añadimos lo anterior Documento / Referencia
        // ALGO_ID_DIGEST_SHA1 = "http://www.w3.org/2000/09/xmldsig#sha1";
        xmlSignature.addDocument("", transforms, Constants.ALGO_ID_DIGEST_SHA1);

        // Añadimos el KeyInfo del certificado cuya clave privada usamos
        X509Certificate cert = (X509Certificate) ks.getCertificate(sAlias);

        xmlSignature.addKeyInfo(cert.getPublicKey());
        xmlSignature.addKeyInfo(cert);

        // Realizamos la firma
        xmlSignature.sign(privateKey);
//        doc.getDocumentElement().appendChild(xmlSignature.getElement());
        Node node = doc.getDocumentElement().getElementsByTagName("ext:ExtensionContent").item(0);
        node.appendChild(xmlSignature.getElement());
        
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_WITH_COMMENTS).canonicalizeSubtree(doc));
        
        final OutputStream fileOutputStream = new FileOutputStream(fileName);
        try {
            fileOutputStream.write(outputStream.toByteArray());
            fileOutputStream.flush();
        } finally {
            IOUtils.closeQuietly(fileOutputStream);
        }
        
        String digestValue = "-";
        Element elementSignature = (Element) doc.getDocumentElement().getElementsByTagName("ds:Signature").item(0);
        elementSignature.setAttribute("Id", "SignSUNAT");
        
        NodeList nodeList = doc.getDocumentElement().getElementsByTagName("ds:DigestValue");
        for (int i = 0; i < nodeList.getLength(); i++) {
            digestValue = obtenerNodo(nodeList.item(i));
        }   
        System.out.println("\\ FIN.");
        retorno.put("xmlBase64",outputStream.toByteArray());
        retorno.put("xmlHash",digestValue);
        return retorno;
        
//        return doc;
    }
    private static String obtenerNodo(Node node) throws Exception {
        StringBuffer valorClave = new StringBuffer();
        valorClave.setLength(0);

        Integer tamano = Integer.valueOf(node.getChildNodes().getLength());

        for (int i = 0; i < tamano.intValue(); i++) {
            Node c = node.getChildNodes().item(i);
            if (c.getNodeType() == 3) {
                valorClave.append(c.getNodeValue());
            }
        }

        String nodo = valorClave.toString().trim();

        return nodo;
    }
//    public static void output(ByteArrayOutputStream signedOutputStream, String fileName) throws IOException {
//        final OutputStream fileOutputStream = new FileOutputStream(fileName);
//        try {
//            fileOutputStream.write(signedOutputStream.toByteArray());
//            fileOutputStream.flush();
//        } finally {
//            IOUtils.closeQuietly(fileOutputStream);
//        }
//    }
}
