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
/*     */ 
/*     */ 
/*     */ public class PipeRetencionParser
/*     */   extends PipeCpeAbstractParser
/*     */   implements Parser
/*     */ {
/*  29 */   private static final Logger log = LoggerFactory.getLogger(PipeRetencionParser.class);
/*     */ 
/*     */   
/*  32 */   private static String plantillaSeleccionada = "ConvertirRetencionXML.ftl";
/*     */ 
/*     */   
/*     */   private String archivoCabecera;
/*     */ 
/*     */   
/*     */   private String archivoDetalle;
/*     */   
/*     */   private String nombreArchivo;
/*     */   
/*     */   private Contribuyente contri;
/*     */ 
/*     */   /*     */ 
/*     */   
/*     */   public PipeRetencionParser(Contribuyente contri, String[] archivos, String nombreArchivo) {
/*  46 */     this.contri = contri;
/*     */     
/*  48 */     this.nombreArchivo = nombreArchivo;
/*     */     
/*  50 */     this.archivoCabecera = archivos[0];
/*  51 */     this.archivoDetalle = archivos[1];
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Object> pipeToMap() throws ParserException {
/*  56 */     log.debug("SoftwareFacturadorController.formatoResumenBajas...Inicio Procesamiento");
/*     */ 
/*     */     
/*  59 */     String[] datosArchivo = this.nombreArchivo.split("\\-");
/*     */     
/*  61 */     String identificadorFirmaSwf = "SIGN";
/*  62 */     Random calcularRnd = new Random();
/*  63 */     Integer codigoFacturadorSwf = Integer.valueOf((int)(calcularRnd.nextDouble() * 1000000.0D));
/*     */ 
/*     */     
/*  66 */     log.debug("SoftwareFacturadorController.formatoResumenBajas...Leyendo Archivo: " + this.archivoCabecera);
/*  67 */     Map<String, Object> retencion = new HashMap<>();
/*     */ 
/*     */     
/*  70 */     Path fileCabecera = Paths.get(this.archivoCabecera, new String[0]);
/*     */     
/*  72 */     if (!Files.exists(fileCabecera, new java.nio.file.LinkOption[0]))
/*     */     {
/*  74 */       throw new ParserException("El archivo no existe: " + this.archivoCabecera);
/*     */     }
/*     */ 
/*     */     
/*  78 */     try(InputStream in = Files.newInputStream(fileCabecera, new java.nio.file.OpenOption[0]); 
/*  79 */         BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
/*  80 */       String cadena = null;
/*     */       
/*  82 */       while ((cadena = reader.readLine()) != null) {
/*  83 */         String[] registro = cadena.split("\\|");
/*     */         
/*  85 */         if (registro.length != 19) {
/*  86 */           throw new ParserException("El archivo cabecera no continene la cantidad de datos esperada (19 columnas).");
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*  91 */         retencion = new HashMap<>();
/*  92 */         retencion.put("fecEmision", registro[0]);
/*  93 */         retencion.put("nroDocIdeReceptor", registro[1]);
/*  94 */         retencion.put("tipDocIdeReceptor", registro[2]);
/*  95 */         retencion.put("desNomComReceptor", registro[3]);
/*     */         
/*  97 */         retencion.put("desUbiReceptor", registro[4]);
/*  98 */         retencion.put("desDirReceptor", registro[5]);
/*  99 */         retencion.put("desUrbReceptor", registro[6]);
/* 100 */         retencion.put("desDepReceptor", registro[7]);
/* 101 */         retencion.put("desProReceptor", registro[8]);
/* 102 */         retencion.put("desDisReceptor", registro[9]);
/* 103 */         retencion.put("codPaisReceptor", registro[10]);
/* 104 */         retencion.put("rznSocialReceptor", registro[11]);
/*     */         
/* 106 */         retencion.put("codRegRetencion", registro[12]);
/* 107 */         retencion.put("tasRetencion", registro[13]);
/* 108 */         retencion.put("desObsRetencion", registro[14]);
/* 109 */         retencion.put("mtoTotRetencion", registro[15]);
/* 110 */         retencion.put("tipMonRetencion", registro[16]);
/* 111 */         retencion.put("mtoImpTotPagRetencion", registro[17]);
/* 112 */         retencion.put("tipMonImpTotPagRetencion", registro[18]);
/*     */ 
/*     */         
/* 115 */         retencion.put("ublVersionIdSwf", "2.0");
/* 116 */         retencion.put("CustomizationIdSwf", "1.0");
/* 117 */         retencion.put("nroCdpSwf", datosArchivo[2] + "-" + datosArchivo[3]);
/* 118 */         retencion.put("tipCdpSwf", datosArchivo[1]);
/*     */         
/* 120 */         retencion.put("nroRucEmisorSwf", this.contri.getNumRuc());
/* 121 */         retencion.put("tipDocuEmisorSwf", "6");
/* 122 */         retencion.put("nombreComercialSwf", this.contri.getNombreComercial());
/* 123 */         retencion.put("razonSocialSwf", this.contri.getRazonSocial());
/* 124 */         retencion.put("ubigeoDomFiscalSwf", this.contri.getDireccion().getUbigeo());
/* 125 */         retencion.put("direccionDomFiscalSwf", this.contri.getDireccion().getDireccion());
/* 126 */         retencion.put("deparSwf", this.contri.getDireccion().getDepar());
/* 127 */         retencion.put("provinSwf", this.contri.getDireccion().getProvin());
/* 128 */         retencion.put("distrSwf", this.contri.getDireccion().getDistr());
/* 129 */         retencion.put("urbanizaSwf", this.contri.getDireccion().getUrbaniza());
/*     */         
/* 131 */         retencion.put("paisDomFiscalSwf", "PE");
/* 132 */         retencion.put("tipoCodigoMonedaSwf", "01");
/*     */         
/* 134 */         retencion.put("identificadorFacturadorSwf", "Elaborado por Sistema de Emision Electronica Facturador SUNAT (SEE-SFS) 1.3.2");
/* 135 */         retencion.put("codigoFacturadorSwf", codigoFacturadorSwf.toString());
/* 136 */         retencion.put("identificadorFirmaSwf", identificadorFirmaSwf);
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 144 */     catch (IOException x) {
/* 145 */       throw new ParserException("No se pudo leer el archivo cabecera: " + this.archivoCabecera, x);
/*     */     } 
/*     */ 
/*     */     
/* 149 */     Path fileDetalle = Paths.get(this.archivoDetalle, new String[0]);
/*     */     
/* 151 */     if (!Files.exists(fileDetalle, new java.nio.file.LinkOption[0]))
/*     */     {
/* 153 */       throw new ParserException("El archivo no existe: " + this.archivoDetalle);
/*     */     }
/*     */ 
/*     */     
/* 157 */     List<Map<String, Object>> listaDetalle = new ArrayList<>();
/* 158 */     Map<String, Object> detalle = null;
/*     */     
/* 160 */     try(InputStream in = Files.newInputStream(fileDetalle, new java.nio.file.OpenOption[0]); 
/* 161 */         BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
/* 162 */       String cadena = null;
/*     */       
/* 164 */       Integer linea = Integer.valueOf(0);
/*     */       
/* 166 */       while ((cadena = reader.readLine()) != null) {
/* 167 */         String[] registro = cadena.split("\\|");
/* 168 */         if (registro.length != 18 && registro.length != 5)
/*     */         {
/* 170 */           throw new ParserException("El archivo detalle no continene la cantidad de datos esperada (18 columnas).");
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 175 */         linea = Integer.valueOf(linea.intValue() + 1);
/* 176 */         detalle = new HashMap<>();
/* 177 */         detalle.put("tipDocRelacionado", registro[0]);
/* 178 */         detalle.put("nroDocRelacionado", registro[1]);
/* 179 */         detalle.put("fecEmiDocRelacionado", registro[2]);
/* 180 */         detalle.put("mtoImpTotDocRelacionado", registro[3]);
/* 181 */         detalle.put("tipMonDocRelacionado", registro[4]);
/* 182 */         detalle.put("fecPagDocRelacionado", registro[5]);
/* 183 */         detalle.put("nroPagDocRelacionado", registro[6]);
/* 184 */         detalle.put("mtoPagDocRelacionado", registro[7]);
/* 185 */         detalle.put("tipMonPagDocRelacionado", registro[8]);
/*     */         
/* 187 */         detalle.put("mtoRetDocRelacionado", registro[9]);
/* 188 */         detalle.put("tipMonRetDocRelacionado", registro[10]);
/* 189 */         detalle.put("fecRetDocRelacionado", registro[11]);
/* 190 */         detalle.put("mtoTotPagNetoDocRelacionado", registro[12]);
/* 191 */         detalle.put("tipMonTotPagNetoDocRelacionado", registro[13]);
/*     */         
/* 193 */         detalle.put("tipMonRefTipCambio", registro[14]);
/* 194 */         detalle.put("tipMonObjTipCambio", registro[15]);
/* 195 */         detalle.put("facTipCambio", registro[16]);
/* 196 */         detalle.put("fecTipCambio", registro[17]);
/*     */ 
/*     */         
/* 199 */         detalle.put("lineaSwf", linea);
/* 200 */         retencion.put("nroCdpSwf", datosArchivo[2] + "-" + datosArchivo[3]);
/* 201 */         retencion.put("tipCdpSwf", datosArchivo[1]);
/*     */ 
/*     */ 
/*     */         
/* 205 */         retencion.put("codigofacturadorSwf", codigoFacturadorSwf.toString());
/* 206 */         retencion.put("identificadorFirmaSwf", identificadorFirmaSwf);
/*     */         
/* 208 */         listaDetalle.add(detalle);
/*     */       } 
/*     */ 
/*     */       
/* 212 */       retencion.put("listaDetalle", listaDetalle);
/*     */ 
/*     */     
/*     */     }
/* 216 */     catch (IOException x) {
/* 217 */       throw new ParserException("No se pudo leer el archivo detalle: " + this.archivoDetalle, x);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 222 */     log.debug("SoftwareFacturadorController.formatoRetenciones..Fin Procesamiento");
/*     */     
/* 224 */     return retencion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] parse(String templatesPath) throws ParserException {
/* 232 */     return parse(templatesPath, plantillaSeleccionada);
/*     */   }
/*     */ }


/* Location:              D:\Proyectos\facturacion\EFacturador\java\SFS_v1.3.2\facturadorApp-1.3.2.jar!\facturador\parser\pipe\PipeRetencionParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */