/*     */ package com.deinsoft.efacturador3commons.parser.pipe;
/*     */ 
/*     */ import com.deinsoft.efacturador3commons.model.Contribuyente;
/*     */ import com.deinsoft.efacturador3commons.parser.Parser;
/*     */ import com.deinsoft.efacturador3commons.parser.ParserException;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PipeResumenReversionParser
/*     */   extends PipeCpeAbstractParser
/*     */   implements Parser
/*     */ {
/*  27 */   private static final Logger log = LoggerFactory.getLogger(PipeResumenReversionParser.class);
/*     */ 
/*     */   
/*  30 */   private static String plantillaSeleccionada = "ConvertirRReversionXML.ftl";
/*     */   
/*     */   private String archivoCabecera;
/*     */   
/*     */   private String nombreArchivo;
/*     */   
/*     */   private Contribuyente contri;
/*     */   /*     */   
/*     */   public PipeResumenReversionParser(Contribuyente contri, String[] archivos, String nombreArchivo) {
/*  39 */     this.contri = contri;
/*     */     
/*  41 */     this.nombreArchivo = nombreArchivo;
/*  42 */     this.archivoCabecera = archivos[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Object> pipeToMap() throws ParserException {
/*  47 */     log.debug("SoftwareFacturadorController.formatoResumenBajas...Inicio Procesamiento");
/*     */     
/*  49 */     Integer error = new Integer(0);
/*     */ 
/*     */     
/*  52 */     log.debug("SoftwareFacturadorController.formatoResumenBajas...Leyendo Archivo: " + this.archivoCabecera);
/*  53 */     Map<String, Object> reversionGeneral = new HashMap<>();
/*  54 */     Map<String, Object> resumenReversion = null;
/*  55 */     List<Map<String, Object>> listaResumenReversiones = new ArrayList<>();
/*     */     
/*  57 */     Path file = Paths.get(this.archivoCabecera, new String[0]);
/*  58 */     try(InputStream in = Files.newInputStream(file, new java.nio.file.OpenOption[0]); 
/*  59 */         BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
/*  60 */       String cadena = null;
/*     */       
/*  62 */       Integer linea = Integer.valueOf(0);
/*     */       
/*  64 */       while ((cadena = reader.readLine()) != null) {
/*  65 */         String[] registro = cadena.split("\\|");
/*     */         
/*  67 */         if (registro.length != 5 && error.intValue() == 0) {
/*  68 */           error = new Integer(1);
/*  69 */           throw new ParserException("El archivo CBA no contiene la cantidad de columnas esperada (5 columnas).");
/*     */         } 
/*     */         
/*  72 */         Integer integer1 = linea, integer2 = linea = Integer.valueOf(linea.intValue() + 1);
/*     */ 
/*     */         
/*  75 */         log.debug("SoftwareFacturadorController.formatoResumenBajas...nombreArchivo: " + this.nombreArchivo);
/*  76 */         String[] idArchivo = this.nombreArchivo.split("\\-");
/*  77 */         String idComunicacion = idArchivo[1] + "-" + idArchivo[2] + "-" + idArchivo[3];
/*     */ 
/*     */         
/*  80 */         String fechaDocumentoReversion = registro[0];
/*  81 */         String fechaComunicacionReversion = registro[1];
/*  82 */         String tipoDocumento = registro[2];
/*  83 */         String serieNumeroDocumento = registro[3];
/*  84 */         String motivoReversionDocumento = registro[4];
/*  85 */         String[] nroDocumento = serieNumeroDocumento.split("\\-");
/*  86 */         String identificadorFirmaSwf = "SIGN";
/*  87 */         Random calcularRnd = new Random();
/*  88 */         Integer codigoFacturadorSwf = Integer.valueOf((int)(calcularRnd.nextDouble() * 1000000.0D));
/*     */         
/*  90 */         if (linea.intValue() == 1) {
/*  91 */           reversionGeneral.put("nombreComercialSwf", this.contri.getNombreComercial());
/*  92 */           reversionGeneral.put("razonSocialSwf", this.contri.getRazonSocial());
/*  93 */           reversionGeneral.put("nroRucEmisorSwf", this.contri.getNumRuc());
/*  94 */           reversionGeneral.put("tipDocuEmisorSwf", "6");
/*  95 */           reversionGeneral.put("fechaDocumentoBaja", fechaDocumentoReversion);
/*  96 */           reversionGeneral.put("fechaComunicacioBaja", fechaComunicacionReversion);
/*     */           
/*  98 */           reversionGeneral.put("ublVersionIdSwf", "2.0");
/*  99 */           reversionGeneral.put("idComunicacion", idComunicacion);
/* 100 */           reversionGeneral.put("CustomizationIdSwf", "1.0");
/* 101 */           reversionGeneral.put("identificadorFacturadorSwf", "Elaborado por Sistema de Emision Electronica Facturador SUNAT (SEE-SFS) 1.3.2");
/*     */           
/* 103 */           reversionGeneral.put("codigoFacturadorSwf", codigoFacturadorSwf.toString());
/* 104 */           reversionGeneral.put("identificadorFirmaSwf", identificadorFirmaSwf);
/*     */           
/* 106 */           resumenReversion = new HashMap<>();
/* 107 */           resumenReversion.put("tipoDocumentoBaja", tipoDocumento);
/* 108 */           resumenReversion.put("serieDocumentoBaja", nroDocumento[0]);
/* 109 */           resumenReversion.put("nroDocumentoBaja", nroDocumento[1]);
/* 110 */           resumenReversion.put("motivoBajaDocumento", motivoReversionDocumento);
/* 111 */           resumenReversion.put("linea", linea);
/*     */         }
/*     */         else {
/*     */           
/* 115 */           resumenReversion = new HashMap<>();
/* 116 */           resumenReversion.put("tipoDocumentoBaja", tipoDocumento);
/* 117 */           resumenReversion.put("serieDocumentoBaja", nroDocumento[0]);
/* 118 */           resumenReversion.put("nroDocumentoBaja", nroDocumento[1]);
/* 119 */           resumenReversion.put("motivoBajaDocumento", motivoReversionDocumento);
/* 120 */           resumenReversion.put("linea", linea);
/*     */         } 
/*     */         
/* 123 */         listaResumenReversiones.add(resumenReversion);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 129 */       reversionGeneral.put("listaResumen", listaResumenReversiones);
/*     */       
/* 131 */       log.debug("SoftwareFacturadorController.formatoResumenBajas...Fin Procesamiento");
/*     */       
/* 133 */       return reversionGeneral;
/*     */     }
/* 135 */     catch (IOException x) {
/* 136 */       throw new ParserException("No se pudo leer el archivo cabecera: " + this.archivoCabecera, x);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] parse(String templatesPath) throws ParserException {
/* 143 */     return parse(templatesPath, plantillaSeleccionada);
/*     */   }
/*     */ }


/* Location:              D:\Proyectos\facturacion\EFacturador\java\SFS_v1.3.2\facturadorApp-1.3.2.jar!\facturador\parser\pipe\PipeResumenReversionParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */