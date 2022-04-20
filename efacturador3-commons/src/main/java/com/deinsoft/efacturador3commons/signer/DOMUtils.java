/*    */ package com.deinsoft.efacturador3commons.signer;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.OutputStream;
/*    */ import javax.xml.parsers.DocumentBuilder;
/*    */ import javax.xml.parsers.DocumentBuilderFactory;
/*    */ import javax.xml.transform.Transformer;
/*    */ import javax.xml.transform.TransformerException;
/*    */ import javax.xml.transform.TransformerFactory;
/*    */ import javax.xml.transform.dom.DOMSource;
/*    */ import javax.xml.transform.stream.StreamResult;
/*    */ import org.w3c.dom.Document;
/*    */ import org.w3c.dom.Element;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DOMUtils
/*    */ {
/*    */   public static void outputDocToFile(Document doc, File file) throws Exception {
/* 31 */     OutputStream f = new FileOutputStream(file);
/* 32 */     TransformerFactory factory = TransformerFactory.newInstance();
/* 33 */     Transformer transformer = factory.newTransformer();
/*    */     
/* 35 */     transformer.setOutputProperty("omit-xml-declaration", "yes");
/* 36 */     transformer.setOutputProperty("encoding", "ISO-8859-1");
/*    */     
/* 38 */     transformer.transform(new DOMSource(doc), new StreamResult(f));
/*    */     
/* 40 */     f.close();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Document loadDocumentFromFile(File file) throws Exception {
/* 47 */     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/* 48 */     DocumentBuilder builder = null;
/*    */     
/* 50 */     factory.setNamespaceAware(true);
/*    */     
/* 52 */     builder = factory.newDocumentBuilder();
/*    */     
/* 54 */     return builder.parse(file);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Element createNode(Document document, String tag, String value) {
/* 61 */     Element node = document.createElement(tag);
/* 62 */     if (value != null) {
/* 63 */       node.appendChild(document.createTextNode(value));
/*    */     }
/* 65 */     return node;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Document createSampleDocument() throws Exception {
/* 73 */     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/* 74 */     DocumentBuilder builder = factory.newDocumentBuilder();
/* 75 */     Document document = builder.newDocument();
/*    */     
/* 77 */     Element person = document.createElement("persona");
/* 78 */     person.setAttribute("id", "468300000");
/*    */     
/* 80 */     person.appendChild(createNode(document, "nombre", "Pepito"));
/* 81 */     person.appendChild(createNode(document, "apellidos", "Pérez Luna"));
/* 82 */     person.appendChild(createNode(document, "email", "pepito.perez@servidor.com"));
/*    */     
/* 84 */     document.appendChild(person);
/*    */     
/* 86 */     return document;
/*    */   }
/*    */   
/*    */   public static void outputDocToOutputStream(Document doc, ByteArrayOutputStream signatureFile) throws TransformerException {
/* 90 */     TransformerFactory factory = TransformerFactory.newInstance();
/* 91 */     Transformer transformer = factory.newTransformer();
/*    */     
/* 93 */     transformer.setOutputProperty("omit-xml-declaration", "no");
/* 94 */     transformer.setOutputProperty("encoding", "ISO-8859-1");
/*    */     
/* 96 */     transformer.transform(new DOMSource(doc), new StreamResult(signatureFile));
/*    */   }
/*    */ }


/* Location:              D:\Proyectos\facturacion\EFacturador\java\SFS_v1.3.2\facturadorApp-1.3.2.jar!\document\signer\xml\DOMUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */