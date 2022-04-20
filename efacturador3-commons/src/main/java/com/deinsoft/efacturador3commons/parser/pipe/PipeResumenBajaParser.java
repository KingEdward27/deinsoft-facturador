package com.deinsoft.efacturador3commons.parser.pipe;

import com.deinsoft.efacturador3commons.model.Contribuyente;
import com.deinsoft.efacturador3commons.model.Empresa;
import com.deinsoft.efacturador3commons.model.ResumenBaja;
import com.deinsoft.efacturador3commons.model.ResumenBajaDet;
import com.deinsoft.efacturador3commons.parser.Parser;
import com.deinsoft.efacturador3commons.parser.ParserException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PipeResumenBajaParser
        extends PipeCpeAbstractParser
        implements Parser {

    private static final Logger log = LoggerFactory.getLogger(PipeResumenBajaParser.class);

    private static String plantillaSeleccionada = "ConvertirRBajasXML.ftl";
    
    ResumenBaja resumenBaja;

    /*     */
    public PipeResumenBajaParser(ResumenBaja resumenBaja) {
//    this.contri = contri;

        this.resumenBaja = resumenBaja;
//    this.archivoCabecera = archivos[0];
    }

    public Map<String, Object> pipeToMap() throws ParserException {
        log.debug("SoftwareFacturadorController.formatoResumenBajas...Inicio Procesamiento");

        Integer error = new Integer(0);

//        log.debug("SoftwareFacturadorController.formatoResumenBajas...Leyendo Archivo: " + this.archivoCabecera);
        Map<String, Object> resumenGeneral = new HashMap<>();
        Map<String, Object> resumenBajas = null;
        List<Map<String, Object>> listaResumenBajas = new ArrayList<>();

//        Path file = Paths.get(this.archivoCabecera, new String[0]);
//    try(InputStream in = Files.newInputStream(file, new java.nio.file.OpenOption[0]); 
//        BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
//      String cadena = null;
//      
//      Integer linea = Integer.valueOf(0);
//      
//      while ((cadena = reader.readLine()) != null) {
//        
//        String[] registro = cadena.split("\\|");
//        
//        if (registro.length != 5 && error.intValue() == 0) {
//          error = new Integer(1);
//          throw new IllegalArgumentException("El archivo CBA no contiene la cantidad de columnas esperada (5 columnas).");
//        } 

//        Integer integer1 = linea, integer2 = linea = Integer.valueOf(linea.intValue() + 1);
        
//        String[] idArchivo = this.nombreArchivo.split("\\-");
        

//        ResumenBaja resumenBaja = listResumenBaja.get(0);
        log.debug("SoftwareFacturadorController.formatoResumenBajas...nombreArchivo: " + resumenBaja.getNombreArchivo());
        String[] idArchivo = resumenBaja.getNombreArchivo().split("\\-");
        String idComunicacion = idArchivo[1] + "-" + idArchivo[2] + "-" + idArchivo[3];
        String fechaDocumentoBaja = new SimpleDateFormat("yyyy-MM-dd").format(resumenBaja.getFechaDocumentoBaja());
        String fechaComunicacionBaja = new SimpleDateFormat("yyyy-MM-dd").format(resumenBaja.getFechaComunicacionBaja());
//        String tipoDocumento = resumenBaja.getTipoDocumentoBaja();
//        String serieNumeroDocumento = resumenBaja.getNroDocumentoBaja();
//        String motivoBajaDocumento = resumenBaja.getMotivoBajaDocumento();
//        String[] nroDocumento = serieNumeroDocumento.split("\\-");
        String identificadorFirmaSwf = "SIGN";
        Random calcularRnd = new Random();
        Integer codigoFacturadorSwf = Integer.valueOf((int) (calcularRnd.nextDouble() * 1000000.0D));

//        Empresa empresa = resumenBaja.getEmpresa();
//        if (linea.intValue() == 1) {
        resumenGeneral.put("nombreComercialSwf", resumenBaja.getEmpresa().getNombreComercial() == null? resumenBaja.getEmpresa().getRazonSocial():resumenBaja.getEmpresa().getNombreComercial());
        resumenGeneral.put("razonSocialSwf", resumenBaja.getEmpresa().getRazonSocial());
        resumenGeneral.put("nroRucEmisorSwf", resumenBaja.getEmpresa().getNumdoc());
        resumenGeneral.put("tipDocuEmisorSwf", "6");
        resumenGeneral.put("fechaDocumentoBaja", fechaDocumentoBaja);
        resumenGeneral.put("fechaComunicacioBaja", fechaComunicacionBaja);

        resumenGeneral.put("ublVersionIdSwf", "2.0");
        resumenGeneral.put("idComunicacion", idComunicacion);
        resumenGeneral.put("CustomizationIdSwf", "1.0");
        resumenGeneral.put("identificadorFacturadorSwf", "SISTEMA FACTURADOR DEFACTURADOR - DEINSOFT SRL");

        resumenGeneral.put("codigoFacturadorSwf", codigoFacturadorSwf.toString());
        resumenGeneral.put("identificadorFirmaSwf", identificadorFirmaSwf);

//          resumenBajas = new HashMap<>();
//          resumenBajas.put("tipoDocumentoBaja", tipoDocumento);
//          resumenBajas.put("serieDocumentoBaja", nroDocumento[0]);
//          resumenBajas.put("nroDocumentoBaja", nroDocumento[1]);
//          resumenBajas.put("motivoBajaDocumento", motivoBajaDocumento);
//          resumenBajas.put("linea", linea);
//        }
//        else {
        int linea = 0;
        for (ResumenBajaDet resumenBajaLinea : resumenBaja.getListResumenBajaDet()) {
            linea++;
            resumenBajas = new HashMap<>();
            resumenBajas.put("tipoDocumentoBaja", resumenBajaLinea.getTipoDocumentoBaja());
            resumenBajas.put("serieDocumentoBaja", resumenBajaLinea.getSerieDocumentoBaja());
            resumenBajas.put("nroDocumentoBaja", resumenBajaLinea.getNroDocumentoBaja());
            resumenBajas.put("motivoBajaDocumento", resumenBajaLinea.getMotivoBajaDocumento());
            resumenBajas.put("linea", linea);
        }

//        } 
        listaResumenBajas.add(resumenBajas);
//      } 

        resumenGeneral.put("listaResumen", listaResumenBajas);

        log.debug("SoftwareFacturadorController.formatoResumenBajas...Fin Procesamiento");

        return resumenGeneral;
//    }
//    catch (IOException x) {
//      throw new ParserException("No se pudo leer el archivo resumenBaja: " + this.archivoCabecera, x);
//    } 
    }

    public byte[] parse(String templatesPath) throws ParserException {
        return parse(templatesPath, plantillaSeleccionada);
    }
}
