package com.deinsoft.efacturador3commons.parser.pipe;

import com.deinsoft.efacturador3commons.model.Contribuyente;
import com.deinsoft.efacturador3commons.model.Empresa;
import com.deinsoft.efacturador3commons.model.FacturaElectronica;
import com.deinsoft.efacturador3commons.parser.Parser;
import com.deinsoft.efacturador3commons.parser.ParserException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PipeNotaDebitoParser
        extends PipeCpeAbstractParser
        implements Parser {

    private static final Logger log = LoggerFactory.getLogger(PipeNotaDebitoParser.class);

    private static String plantillaSeleccionada = "ConvertirNDebitoXML.ftl";

    private String archivoCabecera;

    private String archivoDetalle;

    private String nombreArchivo = "";

    private Empresa contri;

    private String archivoRelacionado = "";

    private String archivoAdiCabecera = "";

    private String archivoAdiDetalle = "";

    private String archivoLeyendas = "";

    private String archivoAdiTributos = "";

    private String archivoAdiVariableGlobal = "";
    private FacturaElectronica cabecera;

    public PipeNotaDebitoParser(Empresa contri, FacturaElectronica cabecera) {
        this.contri = contri;
        this.cabecera = cabecera;
    }

    public Map<String, Object> pipeToMap() throws ParserException {
        log.debug("SoftwareFacturadorController.formatoResumenBajas...Inicio Procesamiento");

        String[] datosArchivo = this.nombreArchivo.split("\\-");

        String identificadorFirmaSwf = "SIGN";
        Random calcularRnd = new Random();
        Integer codigoFacturadorSwf = Integer.valueOf((int) (calcularRnd.nextDouble() * 1000000.0D));

        log.debug("SoftwareFacturadorController.formatoResumenBajas...Leyendo Archivo: " + this.archivoCabecera);
        Map<String, Object> notaDebito = new HashMap<>();

        Path fileCabecera = Paths.get(this.archivoCabecera, new String[0]);

        if (!Files.exists(fileCabecera, new java.nio.file.LinkOption[0])) {
            throw new ParserException("El archivo no existe: " + this.archivoCabecera);
        }

        Integer error = new Integer(0);
        try (InputStream in = Files.newInputStream(fileCabecera, new java.nio.file.OpenOption[0]);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String cadena = null;

            while ((cadena = reader.readLine()) != null) {
                String[] registro = cadena.split("\\|");
                if (registro.length != 21 && error.intValue() == 0) {
                    error = new Integer(1);
                    throw new ParserException("El archivo cabecera no contiene la cantidad de datos esperada (21 columnas).");
                }

                notaDebito = new HashMap<>();
                notaDebito.put("tipOperacion", registro[0]);
                notaDebito.put("fecEmision", registro[1]);
                notaDebito.put("horEmision", registro[2]);
                notaDebito.put("codLocalEmisor", registro[3]);
                notaDebito.put("tipDocUsuario", registro[4]);
                notaDebito.put("numDocUsuario", registro[5]);
                notaDebito.put("rznSocialUsuario", registro[6]);
                notaDebito.put("moneda", registro[7]);

                notaDebito.put("codMotivo", registro[8]);
                notaDebito.put("desMotivo", registro[9]);
                notaDebito.put("tipDocAfectado", registro[10]);
                notaDebito.put("numDocAfectado", registro[11]);

                notaDebito.put("sumTotTributos", registro[12]);
                notaDebito.put("sumTotValVenta", registro[13]);
                notaDebito.put("sumPrecioVenta", registro[14]);
                notaDebito.put("sumDescTotal", registro[15]);
                notaDebito.put("sumOtrosCargos", registro[16]);
                notaDebito.put("sumTotalAnticipos", registro[17]);
                notaDebito.put("sumImpVenta", registro[18]);

                notaDebito.put("ublVersionId", registro[19]);
                notaDebito.put("customizationId", registro[20]);

                notaDebito.put("ublVersionIdSwf", "2.0");
                notaDebito.put("CustomizationIdSwf", "1.0");
                notaDebito.put("nroCdpSwf", datosArchivo[2] + "-" + datosArchivo[3]);
                notaDebito.put("tipCdpSwf", datosArchivo[1]);

                notaDebito.put("nroRucEmisorSwf", this.contri.getNumdoc());
                notaDebito.put("tipDocuEmisorSwf", "6");
                notaDebito.put("nombreComercialSwf", this.contri.getRazonSocial());
                notaDebito.put("razonSocialSwf", this.contri.getRazonSocial());
//                notaDebito.put("ubigeoDomFiscalSwf", this.contri.getDireccion().getUbigeo());
//                notaDebito.put("direccionDomFiscalSwf", this.contri.getDireccion().getDireccion());
//                notaDebito.put("deparSwf", this.contri.getDireccion().getDepar());
//                notaDebito.put("provinSwf", this.contri.getDireccion().getProvin());
//                notaDebito.put("distrSwf", this.contri.getDireccion().getDistr());
//                notaDebito.put("urbanizaSwf", this.contri.getDireccion().getUrbaniza());
                notaDebito.put("paisDomFiscalSwf", "PE");
                notaDebito.put("codigoMontoDescuentosSwf", "2005");
                notaDebito.put("codigoMontoOperGravadasSwf", "1001");
                notaDebito.put("codigoMontoOperInafectasSwf", "1002");
                notaDebito.put("codigoMontoOperExoneradasSwf", "1003");
                notaDebito.put("idIgv", "1000");
                notaDebito.put("codIgv", "IGV");
                notaDebito.put("codExtIgv", "VAT");
                notaDebito.put("idIsc", "2000");
                notaDebito.put("codIsc", "ISC");
                notaDebito.put("codExtIsc", "EXC");
                notaDebito.put("idOtr", "9999");
                notaDebito.put("codOtr", "OTROS");
                notaDebito.put("codExtOtr", "OTH");
                notaDebito.put("tipoCodigoMonedaSwf", "01");
                notaDebito.put("identificadorFacturadorSwf", "Elaborador por Sistema del contribuyente");
                notaDebito.put("codigoFacturadorSwf", codigoFacturadorSwf.toString());
                notaDebito.put("identificadorFirmaSwf", identificadorFirmaSwf);

            }

        } catch (IOException x) {
            throw new ParserException("No se pudo leer el archivo cabecera: " + this.archivoCabecera, x);
        }

        Path fileDetalle = Paths.get(this.archivoDetalle, new String[0]);

        if (!Files.exists(fileDetalle, new java.nio.file.LinkOption[0])) {
            throw new ParserException("El archivo no existe: " + this.archivoDetalle);
        }

        List<Map<String, Object>> listaDetalle = new ArrayList<>();
        Map<String, Object> detalle = null;

        try (InputStream in = Files.newInputStream(fileDetalle, new java.nio.file.OpenOption[0]);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String cadena = null;

            Integer linea = Integer.valueOf(0);

            while ((cadena = reader.readLine()) != null) {
                String[] registro = cadena.split("\\|");

                if (registro.length != 36) {
                    error = new Integer(1);
                    throw new ParserException("El archivo detalle no contiene la cantidad de datos esperada (30 columnas).");
                }

                linea = Integer.valueOf(linea.intValue() + 1);
                detalle = new HashMap<>();
                detalle.put("unidadMedida", registro[0]);
                detalle.put("ctdUnidadItem", registro[1]);
                detalle.put("codProducto", registro[2]);
                detalle.put("codProductoSUNAT", registro[3]);
                detalle.put("desItem", registro[4]);
                detalle.put("mtoValorUnitario", registro[5]);

                detalle.put("sumTotTributosItem", registro[6]);

                detalle.put("codTriIGV", registro[7]);
                detalle.put("mtoIgvItem", registro[8]);
                detalle.put("mtoBaseIgvItem", registro[9]);
                detalle.put("nomTributoIgvItem", registro[10]);
                detalle.put("codTipTributoIgvItem", registro[11]);
                detalle.put("tipAfeIGV", registro[12]);
                detalle.put("porIgvItem", registro[13]);

                detalle.put("codTriISC", registro[14]);
                detalle.put("mtoIscItem", registro[15]);
                detalle.put("mtoBaseIscItem", registro[16]);
                detalle.put("nomTributoIscItem", registro[17]);
                detalle.put("codTipTributoIscItem", registro[18]);
                detalle.put("tipSisISC", registro[19]);
                detalle.put("porIscItem", registro[20]);

                detalle.put("codTriOtro", registro[21]);
                detalle.put("mtoTriOtroItem", registro[22]);
                detalle.put("mtoBaseTriOtroItem", registro[23]);
                detalle.put("nomTributoOtroItem", registro[24]);
                detalle.put("codTipTributoOtroItem", registro[25]);
                detalle.put("porTriOtroItem", registro[26]);

                detalle.put("codTriIcbper", registro[27]);
                detalle.put("mtoTriIcbperItem", registro[28]);
                detalle.put("ctdBolsasTriIcbperItem", registro[29]);
                detalle.put("nomTributoIcbperItem", registro[30]);
                detalle.put("codTipTributoIcbperItem", registro[31]);
                detalle.put("mtoTriIcbperUnidad", registro[32]);

                detalle.put("mtoPrecioVentaUnitario", registro[33]);
                detalle.put("mtoValorVentaItem", registro[34]);

                detalle.put("mtoValorReferencialUnitario", registro[35]);

                detalle.put("lineaSwf", linea);
                detalle.put("tipoCodiMoneGratiSwf", "02");

                listaDetalle.add(detalle);
            }

            notaDebito.put("listaDetalle", listaDetalle);

            formatoComunes(notaDebito, this.archivoRelacionado, this.archivoAdiCabecera, this.archivoAdiDetalle, this.archivoLeyendas, this.archivoAdiTributos, this.archivoAdiVariableGlobal);

        } catch (IOException x) {
            throw new ParserException("No se pudo leer el archivo detalle: " + this.archivoDetalle, x);
        }

        log.debug("SoftwareFacturadorController.formatoResumenBajas...Fin Procesamiento");

        return notaDebito;
    }

    public byte[] parse(String templatesPath) throws ParserException {
        return parse(templatesPath, plantillaSeleccionada);
    }
}
