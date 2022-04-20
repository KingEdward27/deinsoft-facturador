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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PipeGuiaParser
/*     */   extends PipeCpeAbstractParser
/*     */   implements Parser
/*     */ {
/*  43 */   private static final Logger log = LoggerFactory.getLogger(PipeGuiaParser.class);
/*     */ 
/*     */   
/*  46 */   private static String plantillaSeleccionada = "ConvertirGuiaXML.ftl";
/*     */   
/*     */   private String archivoCabecera;
/*     */   
/*     */   private String archivoDetalle;
/*     */   
/*     */   private String nombreArchivo;
/*     */   
/*     */   private Contribuyente contri;
/*     */   
/*     */   private String archivoRelacionado;
/*     */   
/*     */   private String archivoAdiCabecera;
/*     */   
/*     */   private String archivoAdiDetalle;
/*     */   
/*     */   private String archivoLeyendas;
/*     */   
/*     */   private String archivoAdiTributos;
/*     */   
/*     */   private String archivoAdiVariableGlobal;
/*     */ 
/*     */   /*     */ 
/*     */   
/*     */   public PipeGuiaParser(Contribuyente contri, String[] archivos, String nombreArchivo) {
/*  70 */     this.contri = contri;
/*     */     
/*  72 */     this.nombreArchivo = nombreArchivo;
/*     */     
/*  74 */     this.archivoCabecera = archivos[0];
/*  75 */     this.archivoDetalle = archivos[1];
/*  76 */     this.archivoRelacionado = archivos[2];
/*  77 */     this.archivoAdiCabecera = archivos[3];
/*  78 */     this.archivoAdiDetalle = archivos[4];
/*  79 */     this.archivoLeyendas = archivos[5];
/*  80 */     this.archivoAdiTributos = archivos[6];
/*  81 */     this.archivoAdiVariableGlobal = archivos[7];
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Object> pipeToMap() throws ParserException {
/*  86 */     log.debug("SoftwareFacturadorController.formatoResumenBajas...Inicio Procesamiento");
/*     */ 
/*     */     
/*  89 */     String[] datosArchivo = this.nombreArchivo.split("\\-");
/*     */     
/*  91 */     String identificadorFirmaSwf = "SIGN";
/*  92 */     Random calcularRnd = new Random();
/*  93 */     Integer codigoFacturadorSwf = Integer.valueOf((int)(calcularRnd.nextDouble() * 1000000.0D));
/*     */ 
/*     */     
/*  96 */     log.debug("SoftwareFacturadorController.formatoResumenBajas...Leyendo Archivo: " + this.archivoCabecera);
/*  97 */     Map<String, Object> factura = new HashMap<>();
/*     */ 
/*     */     
/* 100 */     Path fileCabecera = Paths.get(this.archivoCabecera, new String[0]);
/*     */     
/* 102 */     if (!Files.exists(fileCabecera, new java.nio.file.LinkOption[0]))
/*     */     {
/* 104 */       throw new ParserException("El archivo no existe: " + this.archivoCabecera);
/*     */     }
/*     */ 
/*     */     
/* 108 */     try(InputStream in = Files.newInputStream(fileCabecera, new java.nio.file.OpenOption[0]); 
/* 109 */         BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
/* 110 */       String cadena = null;
/*     */       
/* 112 */       while ((cadena = reader.readLine()) != null) {
/* 113 */         String[] registro = cadena.split("\\|");
/*     */         
/* 115 */         if (registro.length != 18) {
/* 116 */           throw new ParserException("El archivo cabecera no continene la cantidad de datos esperada (18 columnas).");
/*     */         }
/*     */ 
/*     */         
/* 120 */         factura = new HashMap<>();
/* 121 */         factura.put("tipOperacion", registro[0]);
/* 122 */         factura.put("fecEmision", registro[1]);
/* 123 */         factura.put("horEmision", registro[2]);
/* 124 */         factura.put("fecVencimiento", registro[3]);
/* 125 */         factura.put("codLocalEmisor", registro[4]);
/* 126 */         factura.put("tipDocUsuario", registro[5]);
/* 127 */         factura.put("numDocUsuario", registro[6]);
/* 128 */         factura.put("rznSocialUsuario", registro[7]);
/* 129 */         factura.put("moneda", registro[8]);
/*     */         
/* 131 */         factura.put("sumTotTributos", registro[9]);
/* 132 */         factura.put("sumTotValVenta", registro[10]);
/* 133 */         factura.put("sumPrecioVenta", registro[11]);
/* 134 */         factura.put("sumDescTotal", registro[12]);
/* 135 */         factura.put("sumOtrosCargos", registro[13]);
/* 136 */         factura.put("sumTotalAnticipos", registro[14]);
/* 137 */         factura.put("sumImpVenta", registro[15]);
/*     */         
/* 139 */         factura.put("ublVersionId", registro[16]);
/* 140 */         factura.put("customizationId", registro[17]);
/*     */ 
/*     */         
/* 143 */         factura.put("ublVersionIdSwf", "2.0");
/* 144 */         factura.put("CustomizationIdSwf", "1.0");
/* 145 */         factura.put("nroCdpSwf", datosArchivo[2] + "-" + datosArchivo[3]);
/* 146 */         factura.put("tipCdpSwf", datosArchivo[1]);
/* 147 */         factura.put("nroRucEmisorSwf", this.contri.getNumRuc());
/* 148 */         factura.put("tipDocuEmisorSwf", "6");
/* 149 */         factura.put("nombreComercialSwf", this.contri.getNombreComercial());
/* 150 */         factura.put("razonSocialSwf", this.contri.getRazonSocial());
/* 151 */         factura.put("ubigeoDomFiscalSwf", this.contri.getDireccion().getUbigeo());
/* 152 */         factura.put("direccionDomFiscalSwf", this.contri.getDireccion().getDireccion());
/* 153 */         factura.put("deparSwf", this.contri.getDireccion().getDepar());
/* 154 */         factura.put("provinSwf", this.contri.getDireccion().getProvin());
/* 155 */         factura.put("distrSwf", this.contri.getDireccion().getDistr());
/* 156 */         factura.put("urbanizaSwf", this.contri.getDireccion().getUrbaniza());
/* 157 */         factura.put("paisDomFiscalSwf", "PE");
/* 158 */         factura.put("codigoMontoDescuentosSwf", "2005");
/* 159 */         factura.put("codigoMontoOperGravadasSwf", "1001");
/* 160 */         factura.put("codigoMontoOperInafectasSwf", "1002");
/* 161 */         factura.put("codigoMontoOperExoneradasSwf", "1003");
/* 162 */         factura.put("idIgv", "1000");
/* 163 */         factura.put("codIgv", "IGV");
/* 164 */         factura.put("codExtIgv", "VAT");
/* 165 */         factura.put("idIsc", "2000");
/* 166 */         factura.put("codIsc", "ISC");
/* 167 */         factura.put("codExtIsc", "EXC");
/* 168 */         factura.put("idOtr", "9999");
/* 169 */         factura.put("codOtr", "OTROS");
/* 170 */         factura.put("codExtOtr", "OTH");
/* 171 */         factura.put("tipoCodigoMonedaSwf", "01");
/* 172 */         factura.put("identificadorFacturadorSwf", "Elaborado por Sistema de Emision Electronica Facturador SUNAT (SEE-SFS) 1.3.2");
/* 173 */         factura.put("codigoFacturadorSwf", codigoFacturadorSwf.toString());
/* 174 */         factura.put("identificadorFirmaSwf", identificadorFirmaSwf);
/* 175 */         log.debug("cabecera factura plano:" + factura);
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 182 */     catch (IOException x) {
/* 183 */       throw new ParserException("No se pudo leer el archivo cabecera: " + this.archivoCabecera, x);
/*     */     } 
/*     */ 
/*     */     
/* 187 */     Path fileDetalle = Paths.get(this.archivoDetalle, new String[0]);
/*     */     
/* 189 */     if (!Files.exists(fileDetalle, new java.nio.file.LinkOption[0]))
/*     */     {
/* 191 */       throw new ParserException("El archivo no existe: " + this.archivoDetalle);
/*     */     }
/*     */ 
/*     */     
/* 195 */     List<Map<String, Object>> listaDetalle = new ArrayList<>();
/* 196 */     Map<String, Object> detalle = null;
/*     */     
/* 198 */     try(InputStream in = Files.newInputStream(fileDetalle, new java.nio.file.OpenOption[0]); 
/* 199 */         BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
/* 200 */       String cadena = null;
/*     */       
/* 202 */       Integer linea = Integer.valueOf(0);
/*     */       
/* 204 */       while ((cadena = reader.readLine()) != null) {
/* 205 */         String[] registro = cadena.split("\\|");
/* 206 */         if (registro.length != 30) {
/* 207 */           throw new ParserException("El archivo detalle no continene la cantidad de datos esperada (30 columnas).");
/*     */         }
/*     */ 
/*     */         
/* 211 */         linea = Integer.valueOf(linea.intValue() + 1);
/* 212 */         detalle = new HashMap<>();
/* 213 */         detalle.put("unidadMedida", registro[0]);
/* 214 */         detalle.put("ctdUnidadItem", registro[1]);
/* 215 */         detalle.put("codProducto", registro[2]);
/* 216 */         detalle.put("codProductoSUNAT", registro[3]);
/* 217 */         detalle.put("desItem", registro[4]);
/* 218 */         detalle.put("mtoValorUnitario", registro[5]);
/*     */         
/* 220 */         detalle.put("sumTotTributosItem", registro[6]);
/*     */         
/* 222 */         detalle.put("codTriIGV", registro[7]);
/* 223 */         detalle.put("mtoIgvItem", registro[8]);
/* 224 */         detalle.put("mtoBaseIgvItem", registro[9]);
/* 225 */         detalle.put("nomTributoIgvItem", registro[10]);
/* 226 */         detalle.put("codTipTributoIgvItem", registro[11]);
/* 227 */         detalle.put("tipAfeIGV", registro[12]);
/* 228 */         detalle.put("porIgvItem", registro[13]);
/*     */         
/* 230 */         detalle.put("codTriISC", registro[14]);
/* 231 */         detalle.put("mtoIscItem", registro[15]);
/* 232 */         detalle.put("mtoBaseIscItem", registro[16]);
/* 233 */         detalle.put("nomTributoIscItem", registro[17]);
/* 234 */         detalle.put("codTipTributoIscItem", registro[18]);
/* 235 */         detalle.put("tipSisISC", registro[19]);
/* 236 */         detalle.put("porIscItem", registro[20]);
/*     */         
/* 238 */         detalle.put("codTriOtro", registro[21]);
/* 239 */         detalle.put("mtoTriOtroItem", registro[22]);
/* 240 */         detalle.put("mtoBaseTriOtroItem", registro[23]);
/* 241 */         detalle.put("nomTributoIOtroItem", registro[24]);
/* 242 */         detalle.put("codTipTributoIOtroItem", registro[25]);
/* 243 */         detalle.put("porTriOtroItem", registro[26]);
/*     */         
/* 245 */         detalle.put("mtoPrecioVentaUnitario", registro[27]);
/* 246 */         detalle.put("mtoValorVentaItem", registro[28]);
/*     */         
/* 248 */         detalle.put("mtoValorReferencialUnitario", registro[29]);
/*     */ 
/*     */         
/* 251 */         detalle.put("lineaSwf", linea);
/* 252 */         detalle.put("tipoCodiMoneGratiSwf", "02");
/*     */         
/* 254 */         listaDetalle.add(detalle);
/*     */       } 
/*     */ 
/*     */       
/* 258 */       factura.put("listaDetalle", listaDetalle);
/*     */       
/* 260 */       formatoComunes(factura, this.archivoRelacionado, this.archivoAdiCabecera, this.archivoAdiDetalle, this.archivoLeyendas, this.archivoAdiTributos, this.archivoAdiVariableGlobal);
/*     */ 
/*     */     
/*     */     }
/* 264 */     catch (IOException x) {
/* 265 */       throw new ParserException("No se pudo leer el archivo detalle: " + this.archivoDetalle, x);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 270 */     log.debug("SoftwareFacturadorController.formatoResumenBajas...Fin Procesamiento");
/*     */     
/* 272 */     return factura;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] parse(String templatesPath) throws ParserException {
/* 280 */     return parse(templatesPath, plantillaSeleccionada);
/*     */   }
/*     */ }


/* Location:              D:\Proyectos\facturacion\EFacturador\java\SFS_v1.3.2\facturadorApp-1.3.2.jar!\facturador\parser\pipe\PipeGuiaParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */