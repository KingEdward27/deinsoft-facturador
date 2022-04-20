<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:regexp="http://exslt.org/regular-expressions" xmlns="urn:oasis:names:specification:ubl:schema:xsd:Invoice-2" xmlns:ds="http://www.w3.org/2000/09/xmldsig#" xmlns:ext="urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2" xmlns:sac="urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:dp="http://www.datapower.com/extensions" extension-element-prefixes="dp" exclude-result-prefixes="dp" version="1.0">
  <!-- xsl:include href="../../../commons/error/validate_utils.xsl" dp:ignore-multiple="yes" / -->
  <xsl:include href="local:///commons/error/validate_utils.xsl" dp:ignore-multiple="yes"/>
  <!-- key Documentos Relacionados Duplicados -->
  <xsl:key name="by-document-despatch-reference" match="*[local-name()='Invoice']/cac:DespatchDocumentReference" use="concat(cbc:DocumentTypeCode,' ', cbc:ID)"/>
  <xsl:key name="by-document-additional-reference" match="*[local-name()='Invoice']/cac:AdditionalDocumentReference" use="concat(cbc:DocumentTypeCode,' ', cbc:ID)"/>
  <!-- key Numero de lineas duplicados fin -->
  <xsl:key name="by-invoiceLine-id" match="*[local-name()='Invoice']/cac:InvoiceLine" use="number(cbc:ID)"/>
  <!-- key tributos duplicados por linea -->
  <xsl:key name="by-tributos-in-line" match="cac:InvoiceLine/cac:TaxTotal/cac:TaxSubtotal" use="concat(cac:TaxCategory/cac:TaxScheme/cbc:ID,'-', ../../cbc:ID)"/>
  <!-- key tributos duplicados por cabecera -->
  <xsl:key name="by-tributos-in-root" match="*[local-name()='Invoice']/cac:TaxTotal/cac:TaxSubtotal" use="cac:TaxCategory/cac:TaxScheme/cbc:ID"/>
  <!-- key AdditionalMonetaryTotal duplicados -->
  <xsl:key name="by-AdditionalMonetaryTotal" match="ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal" use="cbc:ID"/>
  <xsl:template match="/*">
    <!--  =========================================================================================================================================== Variables   =========================================================================================================================================== -->
    <!-- Validando que el nombre del archivo coincida con la informacion enviada en el XML -->
    <xsl:variable name="numeroRuc" select="substring(dp:variable('var://context/cpe/nombreArchivoEnviado'), 1, 11)"/>
    <xsl:variable name="tipoComprobante" select="substring(dp:variable('var://context/cpe/nombreArchivoEnviado'), 13, 2)"/>
    <xsl:variable name="numeroSerie" select="substring(dp:variable('var://context/cpe/nombreArchivoEnviado'), 16, 4)"/>
    <xsl:variable name="numeroComprobante" select="substring(dp:variable('var://context/cpe/nombreArchivoEnviado'), 21, string-length(dp:variable('var://context/cpe/nombreArchivoEnviado')) - 24)"/>
    <!-- Numero de RUC del nombre del archivo no coincide con el consignado en el contenido del archivo XML -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'1034'"/>
      <xsl:with-param name="node" select="cac:AccountingSupplierParty/cbc:CustomerAssignedAccountID"/>
      <xsl:with-param name="expresion" select="$numeroRuc != cac:AccountingSupplierParty/cbc:CustomerAssignedAccountID"/>
    </xsl:call-template>
    <!-- Numero de Serie del nombre del archivo no coincide con el consignado en el contenido del archivo XML -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'1035'"/>
      <xsl:with-param name="node" select="cbc:ID"/>
      <xsl:with-param name="expresion" select="$numeroSerie != substring(cbc:ID, 1, 4)"/>
    </xsl:call-template>
    <!-- Numero de documento en el nombre del archivo no coincide con el consignado en el contenido del XML -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'1036'"/>
      <xsl:with-param name="node" select="cbc:ID"/>
      <xsl:with-param name="expresion" select="$numeroComprobante != substring(cbc:ID, 6)"/>
    </xsl:call-template>
    <!-- Variables -->
    <xsl:variable name="cbcUBLVersionID" select="cbc:UBLVersionID"/>
    <xsl:variable name="cbcCustomizationID" select="cbc:CustomizationID"/>
    <xsl:variable name="monedaComprobante" select="cbc:DocumentCurrencyCode/text()"/>
    <xsl:variable name="tipoOperacion" select="ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:SUNATTransaction/cbc:ID/text()"/>
    <!--  =========================================================================================================================================== Variables   =========================================================================================================================================== -->
    <!--  ===========================================================================================================================================  Datos de la Factura electronica    =========================================================================================================================================== -->
    <!-- cbc:InvoiceTypeCode No existe el Tag UBL ERROR 1001 (Verificar que el error ocurra)-->
    <!--         <xsl:call-template name="existAndRegexpValidateElement"> -->
    <!--             <xsl:with-param name="errorCodeNotExist" select="'1001'"/> -->
    <!--             <xsl:with-param name="errorCodeValidate" select="'1001'"/> -->
    <!--             <xsl:with-param name="node" select="cbc:ID"/> -->
    <!--             <xsl:with-param name="regexp" select="'^(([F][A-Z0-9]{3})|([\d]{1,4}))-[0-9]{1,8}$'"/> -->
    <!--         </xsl:call-template> -->
    <!-- cbc:UBLVersionID No existe el Tag UBL ERROR 2075 -->
    <!--  El valor del Tag UBL es diferente de "2.0" ERROR 2074 -->
    <xsl:call-template name="existAndRegexpValidateElement">
      <xsl:with-param name="errorCodeNotExist" select="'2075'"/>
      <xsl:with-param name="errorCodeValidate" select="'2074'"/>
      <xsl:with-param name="node" select="$cbcUBLVersionID"/>
      <xsl:with-param name="regexp" select="'^(2.0)$'"/>
    </xsl:call-template>
    <!-- cbc:CustomizationID No existe el Tag UBL ERROR 2073 -->
    <!--  Vigente hasta el 01/01/2018   -->
    <!--  El valor del Tag UBL es diferente de "1.0" ERROR 2072 -->
    <xsl:call-template name="existAndRegexpValidateElement">
      <xsl:with-param name="errorCodeNotExist" select="'2073'"/>
      <xsl:with-param name="errorCodeValidate" select="'2072'"/>
      <xsl:with-param name="node" select="$cbcCustomizationID"/>
      <!-- Ini PAS20171U210300055 factura-percepcion -->
      <!-- <xsl:with-param name="regexp" select="'^(1.0)$'"/> -->
      <xsl:with-param name="regexp" select="'^(1.1)$'"/>
      <!-- Fin PAS20171U210300055 factura-percepcion -->
    </xsl:call-template>
    <!-- ================================== Verificar con el flujo o con Java ============================================================= -->
    <!-- cbc:ID El número de serie del Tag UBL es diferente al número de serie del archivo ERROR 1035 -->
    <!--  El número de comprobante del Tag UBL es diferente al número de comprobante del archivo ERROR 1036 -->
    <!--  El valor del Tag UBL se encuentra en el listado con indicador de estado igual a 0 o 1 ERROR 1033 -->
    <!--  El valor del Tag UBL se encuentra en el listado con indicador de estado igual a 2 ERROR 1032 -->
    <!-- cbc:IssueDate La diferencia entre la fecha de recepción del XML y el valor del Tag UBL es mayor al límite del listado ERROR 2108 -->
    <!--  El valor del Tag UBL es mayor a dos días de la fecha de envío del comprobante ERROR 2329 -->
    <!-- cbc:InvoiceTypeCode No existe el Tag UBL ERROR 1004 (Verificar que el error ocurra)-->
    <xsl:call-template name="existAndRegexpValidateElement">
      <xsl:with-param name="errorCodeNotExist" select="'1004'"/>
      <xsl:with-param name="errorCodeValidate" select="'1003'"/>
      <xsl:with-param name="node" select="cbc:InvoiceTypeCode"/>
      <xsl:with-param name="regexp" select="'^01$'"/>
    </xsl:call-template>
    <!--  El valor del Tag UBL es diferente al tipo de documento del archivo ERROR 1003 -->
    <!-- ================================================================================================================================ -->
    <!-- cbc:DocumentCurrencyCode No existe el Tag UBL ERROR 2070 -->
    <!--  El formato del Tag UBL es diferente a alfabético de 3 caracteres ERROR 2069 -->
    <xsl:call-template name="existAndRegexpValidateElement">
      <xsl:with-param name="errorCodeNotExist" select="'2070'"/>
      <xsl:with-param name="errorCodeValidate" select="'2069'"/>
      <xsl:with-param name="node" select="$monedaComprobante"/>
      <xsl:with-param name="regexp" select="'^([\w\d]{3})$'"/>
    </xsl:call-template>
    <!--  La moneda de los totales de línea y totales de comprobantes (excepto para los totales de Percepción (2001) y Detracción (2003)) es diferente al valor del Tag UBL ERROR 2071 -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2071'"/>
      <xsl:with-param name="node" select="descendant::*[@currencyID != $monedaComprobante and not(ancestor-or-self::sac:AdditionalMonetaryTotal[cbc:ID='2001' or cbc:ID='2003'])]/@currencyID"/>
      <xsl:with-param name="expresion" select="descendant::*[@currencyID != $monedaComprobante and not(ancestor-or-self::sac:AdditionalMonetaryTotal[cbc:ID='2001' or cbc:ID='2003'])]"/>
    </xsl:call-template>
    <!--  ===========================================================================================================================================  Fin Datos de la Factura electronica    =========================================================================================================================================== -->
    <!--  ===========================================================================================================================================  Datos del Emisor  =========================================================================================================================================== -->
    <xsl:apply-templates select="cac:AccountingSupplierParty"/>
    <xsl:apply-templates select="cac:SellerSupplierParty"/>
    <!--  ===========================================================================================================================================  Fin Datos del Emisor  =========================================================================================================================================== -->
    <!--  ===========================================================================================================================================  Datos del cliente o receptor  =========================================================================================================================================== -->
    <xsl:apply-templates select="cac:AccountingCustomerParty">
      <xsl:with-param name="tipoOPeracion" select="$tipoOperacion"/>
    </xsl:apply-templates>
    <!--  ===========================================================================================================================================  fin Datos del cliente o receptor  =========================================================================================================================================== -->
    <!--  ===========================================================================================================================================  Documentos de referencia  =========================================================================================================================================== -->
    <xsl:apply-templates select="cac:DespatchDocumentReference"/>
    <xsl:apply-templates select="cac:AdditionalDocumentReference"/>
    <!--  ===========================================================================================================================================  Documentos de referencia  =========================================================================================================================================== -->
    <!--  ===========================================================================================================================================  Datos del detalle o Ítem de la Factura  =========================================================================================================================================== -->
    <!--  cac:InvoiceLine/cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cbc:TaxExemptionReasonCode  Si "Código de tributo por línea" es 1000 (IGV) y el valor del Tag UBL es "40" (Exportación), no debe haber otro "Afectación a IGV por la línea" diferente a "40"  ERROR 2655  <xsl:variable name="afectacionIgvExportacion" select="count(cac:InvoiceLine/cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory[cac:TaxScheme/cbc:ID='1000']/cbc:TaxExemptionReasonCode[text() = '40'])"/><xsl:variable name="afectacionIgvNoExportacion" select="count(cac:InvoiceLine/cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory[cac:TaxScheme/cbc:ID='1000']/cbc:TaxExemptionReasonCode[text() != '40'])"/><xsl:call-template name="isTrueExpresion"><xsl:with-param name="errorCodeValidate" select="'2655'" /><xsl:with-param name="node" select="cac:InvoiceLine/cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory[cac:TaxScheme/cbc:ID='1000']/cbc:TaxExemptionReasonCode" /><xsl:with-param name="expresion" select="($afectacionIgvExportacion > 0) and ($afectacionIgvNoExportacion > 0)" /></xsl:call-template>-->
    <xsl:apply-templates select="cac:InvoiceLine">
      <xsl:with-param name="root" select="."/>
    </xsl:apply-templates>
    <!--  ===========================================================================================================================================  Datos del detalle o Ítem de la Factura  =========================================================================================================================================== -->
    <!--  ===========================================================================================================================================  Totales de la Factura  =========================================================================================================================================== -->
    <!-- ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation El Tag UBL no debe repetirse en el /Invoice  ERROR 2427 -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2427'"/>
      <xsl:with-param name="node" select="ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation"/>
      <xsl:with-param name="expresion" select="count(ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation) &gt; 1"/>
    </xsl:call-template>
    <!-- ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal/cbc:ID  El valor del Tag UBL debe tener por lo menos uno de los siguientes valores en el /Invoice: 1001 (Gravada), 1002 (Inafecta), 1003 (Exonerada), 1004 (Gratuita) o 3001 (FISE)  ERROR 2047 -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2047'"/>
      <xsl:with-param name="node" select="ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal/cbc:ID"/>
      <xsl:with-param name="expresion" select="not(ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal/cbc:ID[text()='1001' or text()='1002' or text()='1003' or text()='1004' or text()='3001'])"/>
    </xsl:call-template>
    <!-- cac:LegalMonetaryTotal/cbc:AllowanceTotalAmount El formato del Tag UBL es diferente de decimal de 12 enteros y hasta 2 decimales  ERROR 2065 -->
    <xsl:call-template name="validateValueTwoDecimalIfExist">
      <xsl:with-param name="errorCodeNotExist" select="'2065'"/>
      <xsl:with-param name="errorCodeValidate" select="'2065'"/>
      <xsl:with-param name="node" select="cac:LegalMonetaryTotal/cbc:AllowanceTotalAmount"/>
      <xsl:with-param name="isGreaterCero" select="false()"/>
    </xsl:call-template>
    <!-- cac:LegalMonetaryTotal/cbc:ChargeTotalAmount El formato del Tag UBL es diferente de decimal de 12 enteros y hasta 2 decimales  ERROR 2064 -->
    <!-- cac:LegalMonetaryTotal/cbc:ChargeTotalAmount     El formato del Tag UBL es diferente de decimal de 12 enteros y hasta 2 decimales    ERROR    2064 -->
    <xsl:call-template name="validateValueTwoDecimalIfExist">
      <xsl:with-param name="errorCodeNotExist" select="'2064'"/>
      <xsl:with-param name="errorCodeValidate" select="'2064'"/>
      <xsl:with-param name="node" select="cac:LegalMonetaryTotal/cbc:ChargeTotalAmount"/>
      <xsl:with-param name="isGreaterCero" select="false()"/>
    </xsl:call-template>
    <!-- cac:LegalMonetaryTotal/cbc:PayableAmount El formato del Tag UBL es diferente de decimal de 12 enteros y hasta 2 decimales  ERROR 2062 -->
    <xsl:call-template name="existAndValidateValueTwoDecimal">
      <xsl:with-param name="errorCodeNotExist" select="'2062'"/>
      <xsl:with-param name="errorCodeValidate" select="'2062'"/>
      <xsl:with-param name="node" select="cac:LegalMonetaryTotal/cbc:PayableAmount"/>
      <xsl:with-param name="isGreaterCero" select="false()"/>
    </xsl:call-template>
    <!-- cac:LegalMonetaryTotal/cbc:PayableAmount Si  "Total valor de venta - operaciones gravadas" más  "Total valor de venta - operaciones inafectas" más  "Total valor de venta - operaciones exoneradas" más  "Sumatoria IGV" más  "Sumatoria ISC" más  "Sumatoria otros tributos" más  "Sumatoria otros cargos", es diferente al valor del Tag UBL (con una tolerancia de más/menos uno)  OBSERV 4027 -->
    <xsl:variable name="totalOperacionesGravadas" select="ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal[cbc:ID='1001']/cbc:PayableAmount"/>
    <xsl:variable name="TotalOperacionesInafectas" select="ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal[cbc:ID='1002']/cbc:PayableAmount"/>
    <xsl:variable name="TotalOperacionesExoneradas" select="ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal[cbc:ID='1003']/cbc:PayableAmount"/>
    <xsl:variable name="SumatoriaIGV" select="cac:TaxTotal[cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:TaxTypeCode/text()='1000']/cbc:TaxAmount"/>
    <xsl:variable name="SumatoriaISC" select="cac:TaxTotal[cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:TaxTypeCode/text()='2000']/cbc:TaxAmount"/>
    <xsl:variable name="SumatoriaOtrosTributos" select="cac:TaxTotal[cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:TaxTypeCode/text()='9999']/cbc:TaxAmount"/>
    <xsl:variable name="SumatoriaOtrosCargos" select="cac:LegalMonetaryTotal/cbc:ChargeTotalAmount"/>
    <xsl:variable name="ImporteTotal" select="cac:LegalMonetaryTotal/cbc:PayableAmount"/>
    <xsl:variable name="sumMontosTotales" select="$totalOperacionesGravadas + $TotalOperacionesInafectas + $TotalOperacionesExoneradas + $SumatoriaIGV + $SumatoriaISC + $SumatoriaOtrosTributos + $SumatoriaOtrosCargos"/>
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'4027'"/>
      <xsl:with-param name="node" select="cbc:PayableAmount"/>
      <xsl:with-param name="expresion" select="($ImporteTotal + 1 ) &lt; $sumMontosTotales or ($ImporteTotal -1) &gt; $sumMontosTotales"/>
      <xsl:with-param name="isError" select="false()"/>
    </xsl:call-template>
    <!-- sac:AdditionalMonetaryTotal -->
    <xsl:apply-templates select="ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal">
      <xsl:with-param name="root" select="."/>
    </xsl:apply-templates>
    <!-- Tributos duplicados por cabecera -->
    <xsl:apply-templates select="cac:TaxTotal/cac:TaxSubtotal" mode="cabecera"/>
    <!-- Tributos de la cabecera-->
    <xsl:apply-templates select="cac:TaxTotal" mode="cabecera">
      <xsl:with-param name="root" select="."/>
    </xsl:apply-templates>
    <!-- cac:TaxTotal/cbc:TaxAmount Si existe una línea con "Código de tributo por línea" igual a "2000" y "Monto ISC por línea" mayor a cero, el valor del Tag UBL es menor igual a 0 (cero)  OBSERV 4020 -->
    <xsl:variable name="detalleIscGreaterCero" select="cac:InvoiceLine/cac:TaxTotal[cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID='2000']/cbc:TaxAmount[text() &gt; 0] "/>
    <xsl:if test="$detalleIscGreaterCero">
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'4020'"/>
        <xsl:with-param name="node" select="cac:TaxTotal/cbc:TaxAmount[../cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID='2000']"/>
        <xsl:with-param name="expresion" select="not(cac:TaxTotal/cbc:TaxAmount[../cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID='2000' and text() &gt; 0])"/>
        <xsl:with-param name="isError" select="false()"/>
      </xsl:call-template>
    </xsl:if>
    <!--  ===========================================================================================================================================  Fin Totales de la Factura  =========================================================================================================================================== -->
    <!--  ===========================================================================================================================================  Información Adicional  - Anticipos  =========================================================================================================================================== -->
    <!-- /Invoice/cac:LegalMonetaryTotal/cbc:PrepaidAmount Si existe "Tipo de comprobante que se realizó el anticipo" igual a "02", la suma de "Monto anticipado" es diferente al valor del Tag UBL  ERROR 2509 -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2509'"/>
      <xsl:with-param name="node" select="cac:PrepaidPayment[cbc:ID/@schemeID='02']/cbc:PaidAmount"/>
      <xsl:with-param name="expresion" select="cac:PrepaidPayment[cbc:ID/@schemeID='02'] and sum(cac:PrepaidPayment/cbc:PaidAmount) != number(cac:LegalMonetaryTotal/cbc:PrepaidAmount)"/>
      <!-- Ini PAS20171U210300071 -->
      <xsl:with-param name="isError" select="false()"/>
      <!-- Fin PAS20171U210300071 -->
    </xsl:call-template>
    <!-- /Invoice/cac:LegalMonetaryTotal/cbc:PrepaidAmount Si no existe "Tipo de comprobante que se realizó el anticipo" igual a "02",  el valor del Tag UBL es menor igual a cero (0)  ERROR 2508 -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2508'"/>
      <xsl:with-param name="node" select="cac:LegalMonetaryTotal/cbc:PrepaidAmount"/>
      <xsl:with-param name="expresion" select="not(cac:PrepaidPayment[cbc:ID/@schemeID='02']/cbc:PaidAmount) and (number(cac:LegalMonetaryTotal/cbc:PrepaidAmount) &gt; 0)"/>
      <!-- Ini PAS20171U210300071 -->
      <xsl:with-param name="isError" select="false()"/>
      <!-- Fin PAS20171U210300071 -->
    </xsl:call-template>
    <xsl:apply-templates select="cac:PrepaidPayment" mode="cabecera"/>
    <!--  ===========================================================================================================================================  Fin Información Adicional  - Anticipos  =========================================================================================================================================== -->
    <!--  ===========================================================================================================================================  Información Adicional  =========================================================================================================================================== -->
    <!-- /invoice/ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:SUNATTransaction/cbc:ID Si existe el Tag UBL y es diferente al listado  OBSERV 4042 -->
    <xsl:if test="ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:SUNATTransaction">
      <xsl:call-template name="findElementInCatalog">
        <xsl:with-param name="catalogo" select="'17'"/>
        <xsl:with-param name="idCatalogo" select="ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:SUNATTransaction/cbc:ID"/>
        <xsl:with-param name="errorCodeValidate" select="'4042'"/>
        <xsl:with-param name="isError" select="false()"/>
      </xsl:call-template>
    </xsl:if>
    <!-- /Invoice/ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalProperty/cbc:ID Si "Tipo de operación" es 07 (IVAP) y no existe el Tag UBL con valor 2007  ERROR 2651 -->
    <xsl:if test="$tipoOperacion ='07'">
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'2651'"/>
        <xsl:with-param name="node" select="ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalProperty/cbc:ID"/>
        <xsl:with-param name="expresion" select="not(ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalProperty/cbc:ID[text()='2007'])"/>
      </xsl:call-template>
    </xsl:if>
    <!-- /Invoice/ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalProperty/cbc:ID El valor del Tag UBL (1000, 1001, 1002, 2000, 2001, 2002, 2003) no debe repetirse en el /Invoice  ERROR 2407 -->
    <xsl:variable name="additionalProperty" select="ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalProperty"/>
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2407'"/>
      <xsl:with-param name="node" select="cbc:ID"/>
      <xsl:with-param name="expresion" select="count($additionalProperty/cbc:ID[text()='1000']) &gt; 1 or  count($additionalProperty/cbc:ID[text()='1001']) &gt; 1 or  count($additionalProperty/cbc:ID[text()='1002']) &gt; 1 or  count($additionalProperty/cbc:ID[text()='2000']) &gt; 1 or  count($additionalProperty/cbc:ID[text()='2001']) &gt; 1 or  count($additionalProperty/cbc:ID[text()='2002']) &gt; 1 or  count($additionalProperty/cbc:ID[text()='2003']) &gt; 1 "/>
    </xsl:call-template>
    <xsl:apply-templates select="ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalProperty">
      <xsl:with-param name="tipoOPeracion" select="$tipoOperacion"/>
    </xsl:apply-templates>
    <!--  ===========================================================================================================================================  Fin Información Adicional  =========================================================================================================================================== -->
    <!-- Retornamos el comprobante al flujo necesario para lotes -->
    <xsl:copy-of select="."/>
  </xsl:template>
  <!--  =========================================================================================================================================== ******************************************************************************************************************************************* TEMPLATES ******************************************************************************************************************************************* =========================================================================================================================================== -->
  <!--  ===========================================================================================================================================  ============================================ Template cac:AccountingSupplierParty =========================================================  =========================================================================================================================================== -->
  <xsl:template match="cac:AccountingSupplierParty">
    <!-- cac:AccountingSupplierParty/cbc:CustomerAssignedAccountID No existe el Tag UBL ERROR 1006 -->
    <!--  El valor del Tag UBL es diferente al RUC del nombre del XML ERROR 1034 -->
    <!--  El valor del Tag UBL no existe en el listado ERROR 2104 -->
    <!--  El valor del Tag UBL tiene un ind_estado diferente "00" en el listado ERROR 2010 -->
    <!--  El valor del Tag UBL tiene un ind_condicion diferente "00" en el listado ERROR 2011 -->
    <!-- cac:AccountingSupplierParty/cbc:AdditionalAccountID No existe el Tag UBL ERROR 1008 -->
    <!--  El valor del Tag UBL es diferente a "6" ERROR 1007 -->
    <!--  Existe más de un Tag UBL en el XML ERROR 2362 -->
    <!-- Tipo de documento -->
    <xsl:call-template name="existAndRegexpValidateElement">
      <xsl:with-param name="errorCodeNotExist" select="'1008'"/>
      <xsl:with-param name="errorCodeValidate" select="'1007'"/>
      <xsl:with-param name="node" select="cbc:AdditionalAccountID"/>
      <xsl:with-param name="regexp" select="'^(6)$'"/>
    </xsl:call-template>
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2362'"/>
      <xsl:with-param name="node" select="cbc:AdditionalAccountID"/>
      <xsl:with-param name="expresion" select="count(cbc:AdditionalAccountID)&gt;1"/>
    </xsl:call-template>
    <!-- Apellidos y nombres, denominación o razón social -->
    <!-- cac:AccountingSupplierParty/cac:Party/cac:PartyLegalEntity/cbc:RegistrationName No existe el Tag UBL ERROR 1037 -->
    <!--  El formato del Tag UBL es diferente a alfanumérico de hasta 1000 caracteres ERROR 1038 -->
    <xsl:call-template name="existAndRegexpValidateElement">
      <xsl:with-param name="errorCodeNotExist" select="'1037'"/>
      <xsl:with-param name="errorCodeValidate" select="'1038'"/>
      <xsl:with-param name="node" select="cac:Party/cac:PartyLegalEntity/cbc:RegistrationName"/>
      <xsl:with-param name="regexp" select="'^(?!\s*$)[^\s].{2,999}$'"/>
      <!-- de tres a 1000 caracteres que no inicie por espacio -->
      <!-- Ini PAS20171U210300071 -->
      <xsl:with-param name="isError" select="false()"/>
      <!-- Fin PAS20171U210300071 -->
    </xsl:call-template>
  </xsl:template>
  <!--  ===========================================================================================================================================  =========================================== fin Template cac:AccountingSupplierParty ======================================================  =========================================================================================================================================== -->
  <!--  ===========================================================================================================================================  =========================================== Template cac:SellerSupplierParty ===========================================   =========================================================================================================================================== -->
  <xsl:template match="cac:SellerSupplierParty">
    <!-- Necesitamos el catalogo de ubigeo -->
    <!-- cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cbc:PostalZone Si el Tag UBL existe, el valor del Tag UBL debe estar en el listado  OBSERV 4200 -->
    <xsl:if test="cac:Party/cac:PostalAddress/cbc:PostalZone">
      <xsl:call-template name="findElementInCatalog">
        <xsl:with-param name="catalogo" select="'13'"/>
        <xsl:with-param name="idCatalogo" select="cac:Party/cac:PostalAddress/cbc:PostalZone"/>
        <xsl:with-param name="errorCodeValidate" select="'4200'"/>
        <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
        <xsl:with-param name="isError" select="false()"/>
      </xsl:call-template>
    </xsl:if>
    <!-- cac:SellerSupplierParty/cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode Si el Tag UBL existe, el valor del Tag UBL es diferente a PE  OBSERV 4041 -->
    <xsl:call-template name="regexpValidateElementIfExist">
      <xsl:with-param name="errorCodeValidate" select="'4041'"/>
      <xsl:with-param name="node" select="cac:Party/cac:PostalAddress/cac:Country/cbc:IdentificationCode"/>
      <xsl:with-param name="regexp" select="'^(PE)$'"/>
      <xsl:with-param name="isError" select="false()"/>
    </xsl:call-template>
  </xsl:template>
  <!--  ===========================================================================================================================================  =========================================== fin Template cac:SellerSupplierParty ===========================================   =========================================================================================================================================== -->
  <!--  ===========================================================================================================================================  =========================================== Template cac:AccountingCustomerParty ===========================================   =========================================================================================================================================== -->
  <xsl:template match="cac:AccountingCustomerParty">
    <xsl:param name="tipoOPeracion" select="'-'"/>
    <!-- cac:AccountingCustomerParty/cbc:CustomerAssignedAccountID No existe el Tag UBL  ERROR 2014 -->
    <!-- numero de documento -->
    <xsl:call-template name="existElement">
      <xsl:with-param name="errorCodeNotExist" select="'2014'"/>
      <xsl:with-param name="node" select="cbc:CustomerAssignedAccountID"/>
    </xsl:call-template>
    <!--  Si "Tipo de documento de identidad del adquiriente" es 6, el formato del Tag UBL es diferente a numérico de 11 dígitos  ERROR 2017 -->
    <xsl:if test="cbc:AdditionalAccountID ='6'">
      <xsl:call-template name="regexpValidateElementIfExist">
        <xsl:with-param name="errorCodeValidate" select="'2017'"/>
        <xsl:with-param name="node" select="cbc:CustomerAssignedAccountID"/>
        <xsl:with-param name="regexp" select="'^[\d]{11}$'"/>
      </xsl:call-template>
    </xsl:if>
    <!--  Si "Tipo de operación" es 13 y el "Tipo de documento de identidad del adquiriente o usuario" es 1, el formato del Tag UBL es diferente de numérico de 8 dígitos  ERROR 2801 -->
    <xsl:if test="cbc:AdditionalAccountID ='1'">
      <xsl:call-template name="regexpValidateElementIfExist">
        <xsl:with-param name="errorCodeValidate" select="'2801'"/>
        <xsl:with-param name="node" select="cbc:CustomerAssignedAccountID"/>
        <xsl:with-param name="regexp" select="'^[\d]{8}$'"/>
      </xsl:call-template>
    </xsl:if>
    <!-- cac:AccountingCustomerParty/cbc:AdditionalAccountID No existe el Tag UBL  ERROR 2015 -->
    <!-- Tipo de documento -->
    <xsl:call-template name="existElement">
      <xsl:with-param name="errorCodeNotExist" select="'2015'"/>
      <xsl:with-param name="node" select="cbc:AdditionalAccountID"/>
    </xsl:call-template>
    <!--  Existe más de un Tag UBL en el XML  ERROR 2363 -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2363'"/>
      <xsl:with-param name="node" select="cbc:AdditionalAccountID"/>
      <xsl:with-param name="expresion" select="count(cbc:AdditionalAccountID)&gt;1"/>
    </xsl:call-template>
    <xsl:choose>
      <!-- Exportacion  en el catalogo 06 menos RUC (6)-->
      <!--  Si "Tipo de operación" es 02, el valor del Tag UBL es diferente al listado o guión "-"  ERROR 2016 -->
      <xsl:when test="$tipoOPeracion='02'">
        <xsl:call-template name="regexpValidateElementIfExist">
          <xsl:with-param name="errorCodeValidate" select="'2016'"/>
          <xsl:with-param name="node" select="cbc:AdditionalAccountID"/>
          <xsl:with-param name="regexp" select="'^[0147A\-]$'"/>
          <!-- Ini PAS20171U210300071 -->
          <xsl:with-param name="isError" select="false()"/>
          <!-- Fin PAS20171U210300071 -->
        </xsl:call-template>
      </xsl:when>
      <!--  Si "Tipo de operación" es 13, el valor del Tag UBL es diferente de 1 o 6  ERROR 2800 -->
      <!-- Gasto deducible  -->
      <xsl:when test="$tipoOPeracion='13'">
        <xsl:call-template name="regexpValidateElementIfExist">
          <xsl:with-param name="errorCodeValidate" select="'2800'"/>
          <xsl:with-param name="node" select="cbc:AdditionalAccountID"/>
          <xsl:with-param name="regexp" select="'^[16]$'"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:otherwise>
        <!--  Si no se cumple las dos validaciones anteriores, el valor del Tag UBL es diferente de 6  ERROR 2016 -->
        <xsl:call-template name="regexpValidateElementIfExist">
          <xsl:with-param name="errorCodeValidate" select="'2016'"/>
          <xsl:with-param name="node" select="cbc:AdditionalAccountID"/>
          <xsl:with-param name="regexp" select="'^[6]$'"/>
          <!-- Ini PAS20171U210300071 -->
          <xsl:with-param name="isError" select="false()"/>
          <!-- Fin PAS20171U210300071 -->
        </xsl:call-template>
      </xsl:otherwise>
    </xsl:choose>
    <!-- cac:AccountingCustomerParty/cac:Party/cac:PartyLegalEntity/cbc:RegistrationName No existe el Tag UBL ERROR 2021 -->
    <!--  El formato del Tag UBL es diferente a alfanumérico de 3 hasta 1000 caracteres  ERROR 2022 -->
    <!-- Apellidos y nombres, denominación o razón social -->
    <xsl:call-template name="existAndRegexpValidateElement">
      <xsl:with-param name="errorCodeNotExist" select="'2021'"/>
      <xsl:with-param name="errorCodeValidate" select="'2022'"/>
      <xsl:with-param name="node" select="cac:Party/cac:PartyLegalEntity/cbc:RegistrationName"/>
      <xsl:with-param name="regexp" select="'^(?!\s*$)[^\s].{2,999}$'"/>
      <!-- de tres a 1000 caracteres que no inicie por espacio -->
      <!-- Ini PAS20171U210300071 -->
      <xsl:with-param name="isError" select="false()"/>
      <!-- Fin PAS20171U210300071 -->
    </xsl:call-template>
  </xsl:template>
  <!--  ===========================================================================================================================================  =========================================== fin Template cac:AccountingCustomerParty ===========================================   =========================================================================================================================================== -->
  <!--  ===========================================================================================================================================  =========================================== Template cac:DespatchDocumentReference ===========================================   =========================================================================================================================================== -->
  <xsl:template match="cac:DespatchDocumentReference">
    <!--  El "Tipo de la guía de remisión relacionada" concatenada con el valor del Tag UBL no debe repetirse en el /Invoice  ERROR 2364 -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2364'"/>
      <xsl:with-param name="node" select="cbc:ID"/>
      <xsl:with-param name="expresion" select="count(key('by-document-despatch-reference', concat(cbc:DocumentTypeCode,' ',cbc:ID))) &gt; 1"/>
    </xsl:call-template>
    <!-- cac:DespatchDocumentReference/cbc:ID "Si el Tag UBL existe, el formato del Tag UBL es diferente a:   (.){1,}-[0-9]{1,} [T][0-9]{3}-[0-9]{1,8} [0-9]{4}-[0-9]{1,8}"  OBSERV 4006 -->
    <xsl:call-template name="existAndRegexpValidateElement">
      <xsl:with-param name="errorCodeNotExist" select="'4006'"/>
      <xsl:with-param name="errorCodeValidate" select="'4006'"/>
      <xsl:with-param name="node" select="cbc:ID"/>
      <xsl:with-param name="regexp" select="'^(([T][0-9]{3}-[0-9]{1,8})|([0-9]{4}-[0-9]{1,8})|([E][G][0-9]{2}-[0-9]{1,8})|([G][0-9]{3}-[0-9]{1,8}))$'"/>
      <xsl:with-param name="isError" select="false()"/>
    </xsl:call-template>
    <!-- cac:DespatchDocumentReference/cbc:DocumentTypeCode Si existe el "Número de la guía de remisión relacionada", el formato del Tag UBL es diferente de "09" o "31"  OBSERV 4005 -->
    <xsl:call-template name="existAndRegexpValidateElement">
      <xsl:with-param name="errorCodeNotExist" select="'4005'"/>
      <xsl:with-param name="errorCodeValidate" select="'4005'"/>
      <xsl:with-param name="node" select="cbc:DocumentTypeCode"/>
      <xsl:with-param name="regexp" select="'^(31)|(09)$'"/>
      <xsl:with-param name="isError" select="false()"/>
    </xsl:call-template>
  </xsl:template>
  <!--  ===========================================================================================================================================  =========================================== fin Template cac:DespatchDocumentReference ===========================================   =========================================================================================================================================== -->
  <!--  ===========================================================================================================================================  =========================================== Template cac:AdditionalDocumentReference ===========================================   =========================================================================================================================================== -->
  <xsl:template match="cac:AdditionalDocumentReference">
    <!--  El "Tipo de otro documento relacionado" concatenada con el valor del Tag UBL no debe repetirse en el /Invoice ERROR 2365 -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2365'"/>
      <xsl:with-param name="node" select="cbc:ID"/>
      <xsl:with-param name="expresion" select="count(key('by-document-additional-reference', concat(cbc:DocumentTypeCode,' ',cbc:ID))) &gt; 1"/>
    </xsl:call-template>
    <!-- cac:AdditionalDocumentReference/cbc:ID Si el Tag UBL existe, el formato del Tag UBL es diferente a alfanumérico de hasta 100 caracteres  OBSERV 4010 -->
    <xsl:call-template name="existAndRegexpValidateElement">
      <xsl:with-param name="errorCodeNotExist" select="'4010'"/>
      <xsl:with-param name="errorCodeValidate" select="'4010'"/>
      <xsl:with-param name="node" select="cbc:ID"/>
      <xsl:with-param name="regexp" select="'^(?!\s*$)[^\s]{1,100}$'"/>
      <xsl:with-param name="isError" select="false()"/>
    </xsl:call-template>
    <!-- cac:AdditionalDocumentReference/cbc:DocumentTypeCode Si existe el "Número de otro documento relacionado", el formato del Tag UBL es diferente de "04" o "05" o "99" o "01"  OBSERV 4009 -->
    <xsl:call-template name="existAndRegexpValidateElement">
      <xsl:with-param name="errorCodeNotExist" select="'4009'"/>
      <xsl:with-param name="errorCodeValidate" select="'4009'"/>
      <xsl:with-param name="node" select="cbc:DocumentTypeCode"/>
      <xsl:with-param name="regexp" select="'^(0[145]|99)$'"/>
      <xsl:with-param name="isError" select="false()"/>
    </xsl:call-template>
  </xsl:template>
  <!--  ===========================================================================================================================================  =========================================== fin Template cac:AdditionalDocumentReference ===========================================   =========================================================================================================================================== -->
  <!--  ===========================================================================================================================================  =========================================== fin Template cac:InvoiceLine ===========================================   =========================================================================================================================================== -->
  <xsl:template match="cac:InvoiceLine">
    <xsl:param name="root"/>
    <xsl:variable name="nroLinea" select="cbc:ID"/>
    <xsl:variable name="tipoOperacion" select="$root/ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:SUNATTransaction/cbc:ID/text()"/>
    <xsl:variable name="codigoPrecio" select="cac:PricingReference/cac:AlternativeConditionPrice/cbc:PriceTypeCode/text()"/>
    <!-- cac:InvoiceLine/cbc:ID El formato del Tag UBL es diferente de numérico de 3 dígitos ERROR 2023 -->
    <!-- Numero de item -->
    <xsl:call-template name="existAndRegexpValidateElement">
      <xsl:with-param name="errorCodeNotExist" select="'2023'"/>
      <xsl:with-param name="errorCodeValidate" select="'2023'"/>
      <xsl:with-param name="node" select="cbc:ID"/>
      <xsl:with-param name="regexp" select="'^(?!0*$)\d{1,3}$'"/>
      <!-- de tres numeros como maximo, no cero -->
      <xsl:with-param name="descripcion" select="concat('Error en la linea:', position(), '. ')"/>
    </xsl:call-template>
    <!--  El valor del Tag UBL no debe repetirse en el /Invoice ERROR 2752 -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2752'"/>
      <xsl:with-param name="node" select="cbc:ID"/>
      <xsl:with-param name="expresion" select="count(key('by-invoiceLine-id', number(cbc:ID))) &gt; 1"/>
      <xsl:with-param name="descripcion" select="concat('Error en la linea:', position(), '. ')"/>
    </xsl:call-template>
    <!-- cac:InvoiceLine/cbc:InvoicedQuantity/@unitCode No existe el atributo del Tag UBL ERROR 2883 -->
    <!-- Unidad de medida por item -->
    <xsl:if test="cbc:InvoicedQuantity">
      <xsl:call-template name="existElement">
        <xsl:with-param name="errorCodeNotExist" select="'2883'"/>
        <xsl:with-param name="node" select="cbc:InvoicedQuantity/@unitCode"/>
        <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
      </xsl:call-template>
    </xsl:if>
    <!-- cac:InvoiceLine/cbc:InvoicedQuantity No existe el Tag UBL ERROR 2024 -->
    <!--  El formato del Tag UBL es diferente de decimal de 12 enteros y hasta 10 decimales ERROR 2025 -->
    <!-- Cantidad de unidades por item -->
    <xsl:call-template name="existAndValidateValueTenDecimal">
      <xsl:with-param name="errorCodeNotExist" select="'2024'"/>
      <xsl:with-param name="errorCodeValidate" select="'2025'"/>
      <xsl:with-param name="node" select="cbc:InvoicedQuantity"/>
      <xsl:with-param name="isGreaterCero" select="false()"/>
      <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
    </xsl:call-template>
    <!-- cac:InvoiceLine/cac:Item/cbc:Description No existe el Tag UBL ERROR 2026 -->
    <!--  El formato del Tag UBL es diferente a alfanumérico de 1 hasta 250 caracteres ERROR 2027 -->
    <!-- Descripción detallada del servicio prestado, bien vendido o cedido en uso, indicando las características. -->
    <xsl:call-template name="existAndRegexpValidateElement">
      <xsl:with-param name="errorCodeNotExist" select="'2026'"/>
      <xsl:with-param name="errorCodeValidate" select="'2027'"/>
      <xsl:with-param name="node" select="cac:Item/cbc:Description"/>
      <xsl:with-param name="regexp" select="'^((?!\s*$)[\s\S]{1,250})$'"/>
      <!-- Texto de un caracter a 250. acepta saltos de linea, no permite que inicie con espacios -->
      <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
      <!-- Ini PAS20171U210300071 -->
      <xsl:with-param name="isError" select="false()"/>
      <!-- Fin PAS20171U210300071 -->
    </xsl:call-template>
    <!-- cac:InvoiceLine/cac:Price/cbc:PriceAmount No existe el Tag UBL ERROR 2068 -->
    <!--  El formato del Tag UBL es diferente de decimal de 12 enteros y hasta 10 decimales ERROR 2369 -->
    <!-- Valor unitario por ítem -->
    <xsl:call-template name="existAndValidateValueTenDecimal">
      <xsl:with-param name="errorCodeNotExist" select="'2068'"/>
      <xsl:with-param name="errorCodeValidate" select="'2369'"/>
      <xsl:with-param name="node" select="cac:Price/cbc:PriceAmount"/>
      <xsl:with-param name="isGreaterCero" select="false()"/>
      <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
    </xsl:call-template>
    <!-- cac:InvoiceLine/cac:PricingReference/cac:AlternativeConditionPrice/cbc:PriceTypeCode El valor del Tag UBL es diferente al listado ERROR 2410 -->
    <!-- Código de precio unitario -->
    <xsl:for-each select="cac:PricingReference/cac:AlternativeConditionPrice">
      <xsl:call-template name="findElementInCatalog">
        <xsl:with-param name="catalogo" select="'16'"/>
        <xsl:with-param name="idCatalogo" select="cbc:PriceTypeCode"/>
        <xsl:with-param name="errorCodeValidate" select="'2410'"/>
        <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
      </xsl:call-template>
    </xsl:for-each>
    <xsl:if test="$codigoPrecio='02'">
      <!--  cac:InvoiceLine/cac:Price/cbc:PriceAmount Si "Código de precio" es 02 (Gratuita), el valor del Tag UBL es mayor a 0 (cero)  ERROR 2640 -->
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'2640'"/>
        <xsl:with-param name="node" select="cac:Price/cbc:PriceAmount"/>
        <xsl:with-param name="expresion" select="cac:Price/cbc:PriceAmount &gt; 0"/>
        <xsl:with-param name="descripcion" select="concat('Error en la linea:', position(), '. ')"/>
        <!-- Ini PAS20171U210300071 -->
        <xsl:with-param name="isError" select="false()"/>
        <!-- Fin PAS20171U210300071 -->
      </xsl:call-template>
      <!-- cac:InvoiceLine/cac:PricingReference/cac:AlternativeConditionPrice/cbc:PriceAmount Si  "Afectación al IGV por línea" es 10 (Gravado), 20 (Exonerado) o 30 (Inafecto) y "cac:PricingReference/cac:AlternativeConditionPrice/cbc:PriceTypeCode" es 02 (Valor referencial en operaciones no onerosa),  el Tag UBL es mayor a 0 (cero)  ERROR 2425 -->
      <!-- Valor referencial unitario por ítem en operaciones no onerosas -->
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'2425'"/>
        <xsl:with-param name="node" select="cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cbc:TaxExemptionReasonCode"/>
        <xsl:with-param name="expresion" select="cac:PricingReference/cac:AlternativeConditionPrice[cbc:PriceTypeCode ='02']/cbc:PriceAmount &gt; 0 and cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cbc:TaxExemptionReasonCode[text() = '10' or text() = '20' or text() = '30']"/>
        <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
      </xsl:call-template>
    </xsl:if>
    <!-- cac:InvoiceLine/cac:PricingReference/cac:AlternativeConditionPrice/cbc:PriceAmount No existe el Tag UBL o es vacío  ERROR 2028 -->
    <!-- El formato del Tag UBL es diferente de decimal de 12 enteros y hasta 10 decimales  ERROR 2367 -->
    <xsl:call-template name="existAndValidateValueTenDecimal">
      <xsl:with-param name="errorCodeNotExist" select="'2028'"/>
      <xsl:with-param name="errorCodeValidate" select="'2367'"/>
      <xsl:with-param name="node" select="cac:PricingReference/cac:AlternativeConditionPrice/cbc:PriceAmount"/>
      <xsl:with-param name="isGreaterCero" select="false()"/>
      <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
    </xsl:call-template>
    <!--  "El valor del Tag UBL no debe repertirse en el cac:InvoiceLine/cac:PricingReference/cac:AlternativeConditionPrice"  ERROR 2409 -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2409'"/>
      <xsl:with-param name="node" select="cac:PricingReference/cac:AlternativeConditionPrice/cbc:PriceTypeCode"/>
      <xsl:with-param name="expresion" select="(count(cac:PricingReference/cac:AlternativeConditionPrice/cbc:PriceTypeCode[text()='01'])&gt;1 or count(cac:PricingReference/cac:AlternativeConditionPrice/cbc:PriceTypeCode[text()='02'])&gt;1)"/>
      <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
    </xsl:call-template>
    <!--  cac:InvoiceLine/cac:TaxTotal/cbc:TaxAmount Si  Si "Código de tributo por línea" es 1000 (IGV), "Tipo de operación" es 07 (IVAP), el valor del Tag UBL es igual a 0 (cero)  ERROR 2643 -->
    <xsl:if test="$tipoOperacion='07'">
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'2643'"/>
        <xsl:with-param name="node" select="cac:TaxTotal[cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID='1000']/cbc:TaxAmount"/>
        <xsl:with-param name="expresion" select="cac:TaxTotal[cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID='1000']/cbc:TaxAmount &lt;= 0"/>
        <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
      </xsl:call-template>
    </xsl:if>
    <!--  cac:InvoiceLine/cac:Item/cbc:Description Si "Tipo de operación" es 04 (Anticipo), no existe el Tag UBL  ERROR 2500 -->
    <xsl:if test="$tipoOperacion='04'">
      <xsl:call-template name="existElement">
        <xsl:with-param name="errorCodeNotExist" select="'2500'"/>
        <xsl:with-param name="node" select="cac:Item/cbc:Description"/>
        <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
      </xsl:call-template>
      <!-- cbc:LineExtensionAmount     Si "Tipo de operación" es 04 (Anticipo), el Tag UBL es menor igual a 0 (cero)    ERROR   2501 -->
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'2501'"/>
        <xsl:with-param name="node" select="cbc:LineExtensionAmount"/>
        <xsl:with-param name="expresion" select="cbc:LineExtensionAmount &lt;= 0"/>
        <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
      </xsl:call-template>
    </xsl:if>
    <!--  cac:InvoiceLine/cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID Debe existir en el cac:InvoiceLine un bloque con ID = 1000 ERROR 2042 -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2042'"/>
      <xsl:with-param name="node" select="cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID[text()='1000']"/>
      <xsl:with-param name="expresion" select="not(cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID[text()='1000'])"/>
      <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
      <!-- Ini PAS20171U210300071 -->
      <xsl:with-param name="isError" select="false()"/>
      <!-- Fin PAS20171U210300071 -->
    </xsl:call-template>
    <!-- Valor de venta por línea -->
    <!-- cac:InvoiceLine/cbc:LineExtensionAmount El formato del Tag UBL es diferente de decimal (positivo o negativo) de 12 enteros y hasta 2 decimales ERROR 2370 -->
    <xsl:call-template name="existAndValidateValueTwoDecimal">
      <xsl:with-param name="errorCodeNotExist" select="'2370'"/>
      <xsl:with-param name="errorCodeValidate" select="'2370'"/>
      <xsl:with-param name="node" select="cbc:LineExtensionAmount"/>
      <xsl:with-param name="isGreaterCero" select="false()"/>
      <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
    </xsl:call-template>
    <!-- Tributos por linea de detalle -->
    <xsl:apply-templates select="cac:TaxTotal" mode="linea">
      <xsl:with-param name="nroLinea" select="$nroLinea"/>
      <xsl:with-param name="root" select="$root"/>
    </xsl:apply-templates>
    <!-- Tributos duplicados por linea -->
    <xsl:apply-templates select="cac:TaxTotal/cac:TaxSubtotal" mode="linea">
      <xsl:with-param name="nroLinea" select="$nroLinea"/>
    </xsl:apply-templates>
  </xsl:template>
  <!--  ===========================================================================================================================================  =========================================== fin Template cac:InvoiceLine ===========================================   =========================================================================================================================================== -->
  <!--  ===========================================================================================================================================  =========================================== Template cac:TaxTotal/cac:TaxSubtotal ===========================================   =========================================================================================================================================== -->
  <xsl:template match="cac:TaxTotal/cac:TaxSubtotal" mode="linea">
    <xsl:param name="nroLinea"/>
    <!--  cac:InvoiceLine/cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID El valor del Tag UBL no debe repetirse en el cac:InvoiceLine ERROR 2355 -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2355'"/>
      <xsl:with-param name="node" select="cac:TaxCategory/cac:TaxScheme/cbc:ID"/>
      <xsl:with-param name="expresion" select="count(key('by-tributos-in-line', concat(cac:TaxCategory/cac:TaxScheme/cbc:ID,'-', $nroLinea))) &gt; 1"/>
      <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
    </xsl:call-template>
  </xsl:template>
  <!--  ===========================================================================================================================================  =========================================== fin Template cac:TaxTotal/cac:TaxSubtotal ===========================================   =========================================================================================================================================== -->
  <!--  ===========================================================================================================================================  =========================================== Template cac:TaxTotal ===========================================   =========================================================================================================================================== -->
  <xsl:template match="cac:TaxTotal" mode="linea">
    <xsl:param name="nroLinea"/>
    <xsl:param name="root"/>
    <xsl:variable name="tipoOperacion" select="$root/ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:SUNATTransaction/cbc:ID/text()"/>
    <!-- cac:InvoiceLine/cac:TaxTotal/cac:TaxSubtotal/cbc:TaxAmount No existe el Tag UBL o es diferente al Tag anterior  ERROR 2372 -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2372'"/>
      <xsl:with-param name="node" select="cbc:TaxAmount"/>
      <xsl:with-param name="expresion" select="number(cac:TaxSubtotal/cbc:TaxAmount) != number(cbc:TaxAmount)"/>
      <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
    </xsl:call-template>
    <!-- El formato del Tag UBL es diferente de decimal de 12 enteros y hasta 2 decimales   ERROR   2033 -->
    <xsl:call-template name="existAndValidateValueTwoDecimal">
      <xsl:with-param name="errorCodeNotExist" select="'2033'"/>
      <xsl:with-param name="errorCodeValidate" select="'2033'"/>
      <xsl:with-param name="node" select="cbc:TaxAmount"/>
      <xsl:with-param name="isGreaterCero" select="false()"/>
      <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
    </xsl:call-template>
    <xsl:choose>
      <xsl:when test="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID = '1000'">
        <!-- cac:InvoiceLine/cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cbc:TaxExemptionReasonCode Si "Código de tributo por línea" es 1000 (IGV), no existe el Tag UBL  ERROR 2371 -->
        <xsl:call-template name="existElement">
          <xsl:with-param name="errorCodeNotExist" select="'2371'"/>
          <xsl:with-param name="node" select="cac:TaxSubtotal/cac:TaxCategory/cbc:TaxExemptionReasonCode"/>
          <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
        </xsl:call-template>
        <!--  Si "Código de tributo por línea" es 1000 (IGV), el valor del Tag UBL es diferente al listado  ERROR 2040 -->
        <xsl:call-template name="findElementInCatalog">
          <xsl:with-param name="catalogo" select="'07'"/>
          <xsl:with-param name="idCatalogo" select="cac:TaxSubtotal/cac:TaxCategory/cbc:TaxExemptionReasonCode"/>
          <xsl:with-param name="errorCodeValidate" select="'2040'"/>
          <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
        </xsl:call-template>
        <!--  cac:InvoiceLine/cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cbc:TaxExemptionReasonCode Si "Código de tributo por línea" es 1000 (IGV) y "Leyendas" es 1002 (Transferencia gratuita), el valor del Tag UBL es 10 (Gravado), 20 (Exonerado), 30 (Inafecto) o 40 (Exportación)  OBSERV 4025 -->
        <xsl:variable name="leyendaTransferenciaGratuita" select="$root/ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalProperty/cbc:ID[text()=1002]"/>
        <xsl:call-template name="isTrueExpresion">
          <xsl:with-param name="errorCodeValidate" select="'4025'"/>
          <xsl:with-param name="node" select="cac:TaxSubtotal/cac:TaxCategory/cbc:TaxExemptionReasonCode"/>
          <xsl:with-param name="expresion" select="$leyendaTransferenciaGratuita  and cac:TaxSubtotal/cac:TaxCategory/cbc:TaxExemptionReasonCode[text()='10' or text()='20' or text()='30' or text()='40']"/>
          <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
          <xsl:with-param name="isError" select="false()"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID = '2000'">
        <!-- cac:InvoiceLine/cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cbc:TierRange Si "Código de tributo por línea" es 2000 (ISC), no existe el Tag UBL  ERROR 2373 -->
        <xsl:call-template name="existElement">
          <xsl:with-param name="errorCodeNotExist" select="'2373'"/>
          <xsl:with-param name="node" select="cac:TaxSubtotal/cac:TaxCategory/cbc:TierRange"/>
          <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
        </xsl:call-template>
        <!--  Si "Código de tributo por línea" es 2000 (ISC), el valor del Tag UBL es diferente al listado  ERROR 2041 -->
        <xsl:call-template name="findElementInCatalog">
          <xsl:with-param name="catalogo" select="'08'"/>
          <xsl:with-param name="idCatalogo" select="cac:TaxSubtotal/cac:TaxCategory/cbc:TierRange"/>
          <xsl:with-param name="errorCodeValidate" select="'2041'"/>
          <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
        </xsl:call-template>
      </xsl:when>
    </xsl:choose>
    <!-- Validaciones exportacion -->
    <xsl:if test="$tipoOperacion='02'">
      <!-- /Invoice/cac:InvoiceLine/cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID   Si "Tipo de operación" es 02 (Exportación), el valor del Tag UBL es diferente de 1000 (IGV)  ERROR 2654 -->
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'2654'"/>
        <xsl:with-param name="node" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID"/>
        <xsl:with-param name="expresion" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID != '1000'"/>
        <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
      </xsl:call-template>
      <!--   Si "Código de tributo por línea" es 1000 (IGV) y "Tipo de operación" es 02 (Exportación), el valor del Tag UBL es diferente a 40 (Exportación)  ERROR 2642 -->
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'2642'"/>
        <xsl:with-param name="node" select="cac:TaxSubtotal/cac:TaxCategory[cac:TaxScheme/cbc:ID='1000']/cbc:TaxExemptionReasonCode"/>
        <xsl:with-param name="expresion" select="cac:TaxSubtotal/cac:TaxCategory[cac:TaxScheme/cbc:ID='1000']/cbc:TaxExemptionReasonCode != '40'"/>
        <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
      </xsl:call-template>
    </xsl:if>
    <!-- cac:InvoiceLine/cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:Name No existe el Tag UBL  ERROR 2038 -->
    <xsl:call-template name="existElement">
      <xsl:with-param name="errorCodeNotExist" select="'2038'"/>
      <xsl:with-param name="node" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:Name"/>
      <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
    </xsl:call-template>
    <!-- Validaciones de IVAP -->
    <xsl:choose>
      <xsl:when test="$tipoOperacion='07'">
        <!--  Si "Tipo de operación" es 07 (IVAP), el valor del Tag UBL es diferente a 17  ERROR 2644 -->
        <xsl:call-template name="isTrueExpresion">
          <xsl:with-param name="errorCodeValidate" select="'2644'"/>
          <xsl:with-param name="node" select="cac:TaxSubtotal/cac:TaxCategory/cbc:TaxExemptionReasonCode"/>
          <xsl:with-param name="expresion" select="cac:TaxSubtotal/cac:TaxCategory/cbc:TaxExemptionReasonCode != '17'"/>
          <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
        </xsl:call-template>
        <!--  Si "Tipo de operación" es 07 (IVAP), el valor del Tag UBL es diferente de 1000 (IGV)  ERROR 2645 -->
        <xsl:call-template name="isTrueExpresion">
          <xsl:with-param name="errorCodeValidate" select="'2645'"/>
          <xsl:with-param name="node" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID"/>
          <xsl:with-param name="expresion" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID != '1000'"/>
          <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
        </xsl:call-template>
        <!--  Si "Código de tributo por línea" es 1000 (IGV) y "Tipo de operación" es 07 (IVAP), el valor del Tag UBL es "IVAP"  ERROR 2646 -->
        <xsl:call-template name="isTrueExpresion">
          <xsl:with-param name="errorCodeValidate" select="'2646'"/>
          <xsl:with-param name="node" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:Name"/>
          <xsl:with-param name="expresion" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme[cbc:ID = '1000']/cbc:Name != 'IVAP'"/>
          <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
        </xsl:call-template>
        <xsl:if test="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID = '1000'">
          <xsl:call-template name="existElement">
            <xsl:with-param name="errorCodeNotExist" select="'2646'"/>
            <xsl:with-param name="node" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme[cbc:ID = '1000']/cbc:Name"/>
            <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
          </xsl:call-template>
        </xsl:if>
      </xsl:when>
      <xsl:otherwise>
        <!--  Si "Código de tributo por línea" es 1000 (IGV) y "Tipo de operación" es diferente 07 (IVAP), el valor del Tag UBL es diferente de "IGV"  ERROR 2377 -->
        <xsl:call-template name="isTrueExpresion">
          <xsl:with-param name="errorCodeValidate" select="'2377'"/>
          <xsl:with-param name="node" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID"/>
          <xsl:with-param name="expresion" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme[cbc:ID = '1000']/cbc:Name != 'IGV'"/>
          <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea, '. cbc:Name debe de ser IGV')"/>
          <!-- Ini PAS20171U210300071 -->
          <xsl:with-param name="isError" select="false()"/>
          <!-- Fin PAS20171U210300071 -->
        </xsl:call-template>
        <xsl:if test="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID = '1000'">
          <xsl:call-template name="existElement">
            <xsl:with-param name="errorCodeNotExist" select="'2377'"/>
            <xsl:with-param name="node" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme[cbc:ID = '1000']/cbc:Name"/>
            <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
            <!-- Ini PAS20171U210300071 -->
            <xsl:with-param name="isError" select="false()"/>
            <!-- Fin PAS20171U210300071 -->
          </xsl:call-template>
        </xsl:if>
      </xsl:otherwise>
    </xsl:choose>
    <!-- cac:InvoiceLine/cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID No existe el Tag UBL  ERROR 2037 -->
    <xsl:call-template name="existElement">
      <xsl:with-param name="errorCodeNotExist" select="'2037'"/>
      <xsl:with-param name="node" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID"/>
      <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
    </xsl:call-template>
    <!--  El valor del Tag UBL es diferente al listado  ERROR 2036 -->
    <xsl:call-template name="findElementInCatalog">
      <xsl:with-param name="catalogo" select="'05'"/>
      <xsl:with-param name="idCatalogo" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID"/>
      <xsl:with-param name="errorCodeValidate" select="'2036'"/>
      <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
    </xsl:call-template>
    <!-- cac:InvoiceLine/cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:TaxTypeCode Si "Código de tributo por línea" es 1000, el valor del Tag UBL es diferente al código internacional del listado para el "Código de tributo por línea"   ERROR 2377 -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2377'"/>
      <xsl:with-param name="node" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID"/>
      <xsl:with-param name="expresion" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme[cbc:ID = '1000']/cbc:TaxTypeCode != 'VAT'"/>
      <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea, '. cbc:TaxTypeCode debe ser VAT')"/>
      <!-- Ini PAS20171U210300071 -->
      <xsl:with-param name="isError" select="false()"/>
      <!-- Fin PAS20171U210300071 -->
    </xsl:call-template>
    <xsl:if test="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID = '1000'">
      <xsl:call-template name="existElement">
        <xsl:with-param name="errorCodeNotExist" select="'2377'"/>
        <xsl:with-param name="node" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme[cbc:ID = '1000']/cbc:TaxTypeCode"/>
        <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
        <!-- Ini PAS20171U210300071 -->
        <xsl:with-param name="isError" select="false()"/>
        <!-- Fin PAS20171U210300071 -->
      </xsl:call-template>
    </xsl:if>
    <!-- cac:InvoiceLine/cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:Name Si "Código de tributo por línea" es 2000 (ISC), el valor del Tag UBL es diferente de "ISC"  ERROR 2378 -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2378'"/>
      <xsl:with-param name="node" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID"/>
      <xsl:with-param name="expresion" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme[cbc:ID = '2000']/cbc:Name != 'ISC'"/>
      <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
    </xsl:call-template>
    <!-- cac:InvoiceLine/cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:TaxTypeCode Si "Código de tributo por línea" es 2000, el valor del Tag UBL es diferente al código internacional del listado para el "Código de tributo por línea"   ERROR 2378 -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2378'"/>
      <xsl:with-param name="node" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID"/>
      <xsl:with-param name="expresion" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme[cbc:ID = '2000']/cbc:TaxTypeCode != 'EXC'"/>
      <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
    </xsl:call-template>
    <xsl:if test="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID = '2000'">
      <xsl:call-template name="existElement">
        <xsl:with-param name="errorCodeNotExist" select="'2378'"/>
        <xsl:with-param name="node" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme[cbc:ID = '2000']/cbc:Name"/>
        <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
      </xsl:call-template>
      <xsl:call-template name="existElement">
        <xsl:with-param name="errorCodeNotExist" select="'2378'"/>
        <xsl:with-param name="node" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme[cbc:ID = '2000']/cbc:TaxTypeCode"/>
        <xsl:with-param name="descripcion" select="concat('Error en la linea: ', $nroLinea)"/>
      </xsl:call-template>
    </xsl:if>
  </xsl:template>
  <!--  ===========================================================================================================================================  =========================================== fin Template cac:TaxTotal ===========================================   =========================================================================================================================================== -->
  <!--  ===========================================================================================================================================  ========== Template ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal =========  =========================================================================================================================================== -->
  <xsl:template match="ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal">
    <xsl:param name="root"/>
    <xsl:variable name="tipoOperacion" select="$root/ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:SUNATTransaction/cbc:ID/text()"/>
    <!-- ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal/cbc:ID El valor del Tag UBL no debe repetirse en el /Invoice  ERROR 2406 -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2406'"/>
      <xsl:with-param name="node" select="cbc:ID"/>
      <xsl:with-param name="expresion" select="count(key('by-AdditionalMonetaryTotal', cbc:ID)) &gt; 1"/>
    </xsl:call-template>
    <!-- ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal/cbc:PayableAmount El formato del Tag UBL es diferente de decimal de 12 enteros y hasta 2 decimales  ERROR 2043 -->
    <xsl:call-template name="existAndValidateValueTwoDecimal">
      <xsl:with-param name="errorCodeNotExist" select="'2043'"/>
      <xsl:with-param name="errorCodeValidate" select="'2043'"/>
      <xsl:with-param name="node" select="cbc:PayableAmount"/>
      <xsl:with-param name="isGreaterCero" select="false()"/>
    </xsl:call-template>
    <xsl:if test="$tipoOperacion ='07'">
      <!-- ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal/cbc:PayableAmount Si "Tipo de operación" es 07 (IVAP) y "Código de tipo de monto" es diferente a 1001 (Gravado), el Tag UBL es mayor a 0 (cero)  ERROR 2648 -->
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'2648'"/>
        <xsl:with-param name="node" select="cbc:PayableAmount"/>
        <xsl:with-param name="expresion" select="cbc:ID !='1001' and cbc:PayableAmount &gt; 0"/>
      </xsl:call-template>
      <!-- ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal/cbc:PayableAmount Si "Tipo de operación" es 07 (IVAP) y "Código de tipo de monto" es 1001 (Gravado), el Tag UBL es menor igual a 0 (cero)  ERROR 2649 -->
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'2649'"/>
        <xsl:with-param name="node" select="cbc:PayableAmount"/>
        <xsl:with-param name="expresion" select="cbc:ID ='1001' and cbc:PayableAmount &lt;= 0"/>
      </xsl:call-template>
    </xsl:if>
    <xsl:variable name="afectacionIgv" select="$root/cac:InvoiceLine/cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory[cac:TaxScheme/cbc:ID ='1000']/cbc:TaxExemptionReasonCode"/>
    <!-- ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal/cbc:PayableAmount  Si existe alguna línea con "Afectación a IGV por la línea" igual a "10" (Gravado), el Tag UBL es menor igual a 0 (cero)  OBSERV 4016 -->
    <xsl:if test="$afectacionIgv[text() = '10']">
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'4016'"/>
        <xsl:with-param name="node" select="cbc:PayableAmount"/>
        <xsl:with-param name="expresion" select="cbc:PayableAmount &lt;= 0 and cbc:ID = '1001'"/>
        <xsl:with-param name="isError" select="false()"/>
      </xsl:call-template>
    </xsl:if>
    <!-- ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal/cbc:PayableAmount Si "Código de tipo de monto" es 1001 (Gravado) y no existe alguna línea con "Afectación a IGV por la línea" igual a "10" (Gravado) OBSERV  4016 -->
    <xsl:if test="not($afectacionIgv[text() = '10'])">
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'4016'"/>
        <xsl:with-param name="node" select="cbc:ID"/>
        <xsl:with-param name="expresion" select="cbc:ID = '1001'"/>
        <xsl:with-param name="isError" select="false()"/>
      </xsl:call-template>
    </xsl:if>
    <!-- ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal/cbc:PayableAmount  Si "Código de tipo de monto" es 1002 (Inafecta) y existe alguna línea con "Afectación a IGV por la línea" igual a "30" (Inafecta) o "40" (Exportación), el Tag UBL es igual a 0 (cero)  OBSERV 4017 -->
    <xsl:if test="$afectacionIgv[text() = '30' or text() = '40']">
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'4017'"/>
        <xsl:with-param name="node" select="cbc:PayableAmount"/>
        <xsl:with-param name="expresion" select="cbc:PayableAmount &lt;= 0 and cbc:ID = '1002'"/>
        <xsl:with-param name="isError" select="false()"/>
      </xsl:call-template>
    </xsl:if>
    <!-- ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal/cbc:ID  Si "Código de tipo de monto" es 1003 (Exonerada) y existe alguna línea con "Afectación a IGV por la línea" igual a "20" (Exonerada), el Tag UBL es igual a 0 (cero)  OBSERV 4018 -->
    <xsl:if test="$afectacionIgv[text() = '20']">
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'4018'"/>
        <xsl:with-param name="node" select="cbc:PayableAmount"/>
        <xsl:with-param name="expresion" select="cbc:PayableAmount &lt;= 0 and cbc:ID = '1003'"/>
        <xsl:with-param name="isError" select="false()"/>
      </xsl:call-template>
    </xsl:if>
    <!-- ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal/cbc:PayableAmount Si "Código de leyenda" es 2001, el valor del Tab UBL es menor igual a 0 (cero)  OBSERV 4022 -->
    <xsl:if test="$root/ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalProperty/cbc:ID[text()='2001']">
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'4022'"/>
        <xsl:with-param name="node" select="cbc:PayableAmount"/>
        <xsl:with-param name="expresion" select=".[cbc:ID='1003']/cbc:PayableAmount &lt;= 0"/>
        <xsl:with-param name="isError" select="false()"/>
      </xsl:call-template>
    </xsl:if>
    <!-- ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal/cbc:PayableAmount Si "Código de precio" es 02 (Valor referencial no onerosa) y "Código de tipo de monto" es 1004 (Gratuita), no existe el Tag UBL o es menor o igual a 0 (cero)  ERROR 2641 -->
    <xsl:if test="cbc:ID ='1004'  and $root/cac:InvoiceLine/cac:PricingReference/cac:AlternativeConditionPrice/cbc:PriceTypeCode[text()='02']">
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'2641'"/>
        <xsl:with-param name="node" select="cbc:PayableAmount"/>
        <xsl:with-param name="expresion" select="cbc:PayableAmount &lt;= 0"/>
        <!-- Ini PAS20171U210300071 -->
        <xsl:with-param name="isError" select="false()"/>
        <!-- Fin PAS20171U210300071 -->
      </xsl:call-template>
    </xsl:if>
    <!-- ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal/cbc:PayableAmount Si "Código de leyenda" es 1002, el valor del Tag UBL es menor igual a 0 (cero)  ERROR 2416 -->
    <xsl:if test="cbc:ID ='1004'  and $root/ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalProperty/cbc:ID[text()='1002']">
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'2416'"/>
        <xsl:with-param name="node" select="cbc:PayableAmount"/>
        <xsl:with-param name="expresion" select="cbc:PayableAmount &lt;= 0"/>
      </xsl:call-template>
    </xsl:if>
    <!-- Ini PAS20171U210300055 factura-percepcion -->
    <xsl:if test="cbc:ID ='2001'">
      <!-- sac:AdditionalMonetaryTotal/cbc:ID/@schemeID -->
      <!-- Regimen de percepcion, debe existir en cbc:ID/@schemeID -->
      <xsl:call-template name="existElement">
        <xsl:with-param name="errorCodeNotExist" select="'2784'"/>
        <xsl:with-param name="node" select="cbc:ID/@schemeID"/>
        <xsl:with-param name="descripcion" select="concat('Error Expr Regular Factura (codigo: 2784)')"/>
      </xsl:call-template>
      <!-- Regimen de percepcion, debe de pertenecer al catalogo 22-->
      <xsl:call-template name="findElementInCatalog">
        <xsl:with-param name="catalogo" select="'22'"/>
        <xsl:with-param name="idCatalogo" select="cbc:ID/@schemeID"/>
        <xsl:with-param name="errorCodeValidate" select="'2602'"/>
      </xsl:call-template>
      <!-- sac:AdditionalMonetaryTotal/sac:ReferenceAmount -->
      <!-- Base imponible percepcion, tiene que ser mayor que cero -->
      <xsl:call-template name="existAndRegexpValidateElement">
        <xsl:with-param name="errorCodeNotExist" select="'2785'"/>
        <xsl:with-param name="errorCodeValidate" select="'2786'"/>
        <xsl:with-param name="node" select="sac:ReferenceAmount"/>
        <xsl:with-param name="regexp" select="'(?!(^0+(\.0+)?$))(^\d{1,12}(\.\d{1,2})?$)'"/>
      </xsl:call-template>
      <!-- Base imponible percepcion (moneda), debe existir en sac:ReferenceAmount/@schemeID -->
      <!-- <xsl:call-template name="existElement"><xsl:with-param name="errorCodeNotExist" select="'2787'"/><xsl:with-param name="node" select="sac:ReferenceAmount/@currencyID"/><xsl:with-param name="descripcion" select="concat('Error Expr Regular Factura (codigo: 2787)')"/></xsl:call-template>-->
      <!-- Base imponible percepcion (moneda), el Tag UBL debe ser "PEN" -->
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'2788'"/>
        <xsl:with-param name="node" select="sac:ReferenceAmount/@currencyID"/>
        <xsl:with-param name="expresion" select="sac:ReferenceAmount/@currencyID != 'PEN'"/>
      </xsl:call-template>
      <!-- Base imponible percepcion, Si tipo de moneda es PEN, el valor del Tag UBL debe ser menor o igual a Importe total -->
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'2797'"/>
        <xsl:with-param name="node" select="sac:ReferenceAmount"/>
        <xsl:with-param name="expresion" select="$root/cbc:DocumentCurrencyCode[text() = 'PEN'] and number(sac:ReferenceAmount) &gt; number($root/cac:LegalMonetaryTotal/cbc:PayableAmount)"/>
      </xsl:call-template>
      <!-- sac:AdditionalMonetaryTotal/cbc:PayableAmount -->
      <!-- Monto de la percepcion, tiene que ser mayor que cero -->
      <xsl:call-template name="existAndRegexpValidateElement">
        <xsl:with-param name="errorCodeNotExist" select="'2789'"/>
        <xsl:with-param name="errorCodeValidate" select="'2790'"/>
        <xsl:with-param name="node" select="cbc:PayableAmount"/>
        <xsl:with-param name="regexp" select="'(?!(^0+(\.0+)?$))(^\d{1,12}(\.\d{1,2})?$)'"/>
      </xsl:call-template>
      <!-- Monto de la percepcion (moneda), el Tag UBL debe ser "PEN" -->
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'2792'"/>
        <xsl:with-param name="node" select="cbc:PayableAmount/@currencyID"/>
        <xsl:with-param name="expresion" select="cbc:PayableAmount/@currencyID != 'PEN'"/>
      </xsl:call-template>
      <!-- Obtener la tasa del catalogo 22 -->
      <xsl:variable name="porTasa">
        <xsl:call-template name="getValueInCatalogProperty">
          <xsl:with-param name="idCatalogo" select="cbc:ID/@schemeID"/>
          <xsl:with-param name="catalogo" select="'22'"/>
          <xsl:with-param name="propiedad" select="'tasa'"/>
        </xsl:call-template>
      </xsl:variable>
      <!-- Monto de la percepcion, es igual a ( "Base imponible percepción" * ( (Tasa del listado del "Código de régimen de percepción") / 100 ) ) redondeado a 2 decimales -->
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'2798'"/>
        <xsl:with-param name="node" select="cbc:PayableAmount"/>
        <xsl:with-param name="expresion" select="(round((number($porTasa div 100) * number(sac:ReferenceAmount)) * 100) div 100) != number(cbc:PayableAmount)"/>
      </xsl:call-template>
      <!-- sac:AdditionalMonetaryTotal/sac:TotalAmount -->
      <!-- Monto total incluido la percepcion, tiene que ser mayor que cero -->
      <xsl:call-template name="existAndRegexpValidateElement">
        <xsl:with-param name="errorCodeNotExist" select="'2793'"/>
        <xsl:with-param name="errorCodeValidate" select="'2794'"/>
        <xsl:with-param name="node" select="sac:TotalAmount"/>
        <xsl:with-param name="regexp" select="'(?!(^0+(\.0+)?$))(^\d{1,12}(\.\d{1,2})?$)'"/>
      </xsl:call-template>
      <!-- Monto total incluido la percepcion (moneda), el Tag UBL debe ser "PEN" -->
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'2796'"/>
        <xsl:with-param name="node" select="sac:TotalAmount/@currencyID"/>
        <xsl:with-param name="expresion" select="sac:TotalAmount/@currencyID != 'PEN'"/>
      </xsl:call-template>
      <!-- Monto total incluido la percepcion, es igual a ( "Base imponible percepción" * ( (Tasa del listado del "Código de régimen de percepción") / 100 ) ) redondeado a 2 decimales -->
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'2799'"/>
        <xsl:with-param name="node" select="sac:TotalAmount"/>
        <xsl:with-param name="expresion" select="(((number(sac:ReferenceAmount) + number(cbc:PayableAmount)) * 100) div 100) != number(sac:TotalAmount)"/>
      </xsl:call-template>
    </xsl:if>
    <!-- Fin PAS20171U210300055 factura-percepcion -->
    <!-- ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal/cbc:ID El valor del Tag UBL es diferente al listado  ERROR 2045 -->
    <xsl:call-template name="findElementInCatalog">
      <xsl:with-param name="catalogo" select="'14'"/>
      <xsl:with-param name="idCatalogo" select="cbc:ID"/>
      <xsl:with-param name="errorCodeValidate" select="'2045'"/>
    </xsl:call-template>
  </xsl:template>
  <!--  ===========================================================================================================================================  ======== Fin Template ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal =========  =========================================================================================================================================== -->
  <!--  ===========================================================================================================================================  =========================================== Template cac:TaxTotal/cac:TaxSubtotal ===========================================   =========================================================================================================================================== -->
  <xsl:template match="cac:TaxTotal/cac:TaxSubtotal" mode="cabecera">
    <!-- cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID El valor del Tag UBL no debe repetirse en el /Invoice  ERROR 2352 -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2352'"/>
      <xsl:with-param name="node" select="cac:TaxCategory/cac:TaxScheme/cbc:ID"/>
      <xsl:with-param name="expresion" select="count(key('by-tributos-in-root', cac:TaxCategory/cac:TaxScheme/cbc:ID)) &gt; 1"/>
    </xsl:call-template>
  </xsl:template>
  <!--  ===========================================================================================================================================  =========================================== fin Template cac:TaxTotal/cac:TaxSubtotal ===========================================   =========================================================================================================================================== -->
  <!--  ===========================================================================================================================================  =========================================== Template cac:TaxTotal ===========================================   =========================================================================================================================================== -->
  <xsl:template match="cac:TaxTotal" mode="cabecera">
    <xsl:param name="root"/>
    <xsl:variable name="tipoOperacion" select="$root/ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:SUNATTransaction/cbc:ID/text()"/>
    <!-- cac:TaxTotal/cbc:TaxAmount El formato del Tag UBL es diferente de decimal de 12 enteros y hasta 2 decimales  ERROR 2048 -->
    <xsl:call-template name="validateValueTwoDecimalIfExist">
      <xsl:with-param name="errorCodeValidate" select="'2048'"/>
      <xsl:with-param name="node" select="cbc:TaxAmount"/>
      <xsl:with-param name="isGreaterCero" select="false()"/>
    </xsl:call-template>
    <xsl:choose>
      <xsl:when test="$tipoOperacion='07'">
        <!-- cac:TaxTotal/cbc:TaxAmount Si "Tipo de operación" es 07 (IVAP) y "Código de tributo" es 2000 (ISC), el Tag UBL es mayor a 0 (cero)  ERROR 2650 -->
        <xsl:call-template name="isTrueExpresion">
          <xsl:with-param name="errorCodeValidate" select="'2650'"/>
          <xsl:with-param name="node" select="cbc:TaxAmount"/>
          <xsl:with-param name="expresion" select="cbc:TaxAmount &gt; 0 and cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID = '2000'"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$tipoOperacion='04'">
        <!-- cac:TaxTotal/cbc:TaxAmount  Si "Código de tributo" es 1000 (IGV), "Tipo de operación" es 04 (Anticipo) y "Código de tipo de monto" es 1001 (Gravado), el Tag UBL es igual a 0 (cero)  ERROR 2502 -->
        <xsl:call-template name="isTrueExpresion">
          <xsl:with-param name="errorCodeValidate" select="'2502'"/>
          <xsl:with-param name="node" select="cbc:TaxAmount"/>
          <xsl:with-param name="expresion" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme[cbc:ID = '1000'] and cbc:TaxAmount &lt;= 0 and $root/ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalMonetaryTotal/cbc:ID = '1001'"/>
        </xsl:call-template>
      </xsl:when>
    </xsl:choose>
    <!-- cac:TaxTotal/cac:TaxSubtotal/cbc:TaxAmount No existe el Tag UBL o es diferente al Tag anterior  ERROR 2061 -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2061'"/>
      <xsl:with-param name="node" select="cbc:TaxAmount"/>
      <xsl:with-param name="expresion" select="number(cac:TaxSubtotal/cbc:TaxAmount) != number(cbc:TaxAmount)"/>
    </xsl:call-template>
    <!-- cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID No existe el Tag UBL  ERROR 2052 -->
    <xsl:call-template name="existElement">
      <xsl:with-param name="errorCodeNotExist" select="'2052'"/>
      <xsl:with-param name="node" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID"/>
    </xsl:call-template>
    <!-- cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID El valor del Tag UBL es diferente al listado  ERROR 2051 -->
    <xsl:call-template name="findElementInCatalog">
      <xsl:with-param name="catalogo" select="'05'"/>
      <xsl:with-param name="idCatalogo" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID"/>
      <xsl:with-param name="errorCodeValidate" select="'2051'"/>
    </xsl:call-template>
    <!-- cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:Name No existe el Tag UBL  ERROR 2054 -->
    <xsl:call-template name="existElement">
      <xsl:with-param name="errorCodeNotExist" select="'2054'"/>
      <xsl:with-param name="node" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:Name"/>
    </xsl:call-template>
    <!-- cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:Name Si "Código de tributo" es 1000 (IGV), el valor del Tag UBL es diferente de "IGV" o "IVAP"  ERROR 2057 -->
    <!-- cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:TaxTypeCode Si "Código de tributo" es 1000 (IGV), el valor del Tag UBL es diferente al código internacional del listado para el "Código de tributo"  ERROR 2057 -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2057'"/>
      <xsl:with-param name="node" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID"/>
      <xsl:with-param name="expresion" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme[cbc:ID = '1000']/cbc:Name[text() != 'IVAP' and text() != 'IGV'] or cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme[cbc:ID = '1000']/cbc:TaxTypeCode[text() != 'VAT']"/>
    </xsl:call-template>
    <xsl:variable name="detalleIscGreaterCero" select="$root/cac:InvoiceLine/cac:TaxTotal[cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID='2000']/cbc:TaxAmount[text() &gt; 0] "/>
    <!-- cac:InvoiceLine/cac:TaxTotal/cbc:TaxAmount Si existe "Sumatoria ISC" y es mayor a cero, el valor del Tag UBL es menor igual a 0  OBSERV 4201 -->
    <xsl:if test="cbc:TaxAmount[../cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID='2000' and text() &gt; 0]">
      <xsl:call-template name="isTrueExpresion">
        <xsl:with-param name="errorCodeValidate" select="'4201'"/>
        <xsl:with-param name="node" select="cbc:TaxAmount[../cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID='2000']"/>
        <xsl:with-param name="expresion" select="not($detalleIscGreaterCero)"/>
        <xsl:with-param name="isError" select="false()"/>
      </xsl:call-template>
    </xsl:if>
    <!-- cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:Name Si "Código de tributo" es 2000 (ISC), el valor del Tag UBL es diferente de "ISC"  ERROR 2058 -->
    <!-- cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:TaxTypeCode Si "Código de tributo" es 2000 (ISC), el valor del Tag UBL es diferente al código internacional del listado para el "Código de tributo"  ERROR 2058 -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2058'"/>
      <xsl:with-param name="node" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID"/>
      <xsl:with-param name="expresion" select="cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:ID = '2000' and not(cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:Name = 'ISC' and cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:TaxTypeCode[text() = 'EXC'])"/>
    </xsl:call-template>
  </xsl:template>
  <!--  ===========================================================================================================================================  =========================================== fin Template cac:TaxTotal ===========================================   =========================================================================================================================================== -->
  <!--  ===========================================================================================================================================  =========================================== cac:PrepaidPayment ===========================================   =========================================================================================================================================== -->
  <xsl:template match="cac:PrepaidPayment" mode="cabecera">
    <!-- /Invoice/cac:PrepaidPayment/cbc:ID Si "Monto anticipado" existe y no existe el Tag UBL  OBSERV 2504 -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2504'"/>
      <xsl:with-param name="node" select="cbc:ID"/>
      <xsl:with-param name="expresion" select="cbc:PaidAmount and not(string(cbc:ID))"/>
      <xsl:with-param name="isError" select="false()"/>
    </xsl:call-template>
    <xsl:choose>
      <!-- /Invoice/cac:PrepaidPayment/cbc:ID  Si "Tipo de documento del emisor del anticipo" existe y "Tipo de comprobante que se realizo el anticipo" es 02 (Factura), el formato del Tag UBL  es diferente a: [F][A-Z0-9]{3}-[0-9]{1,8} E001-[0-9]{1,8} [0-9]{1,4}-[0-9]{1,8}  OBSERV 2521 -->
      <xsl:when test="cbc:ID/@schemeID ='02'">
        <xsl:call-template name="regexpValidateElementIfExist">
          <xsl:with-param name="errorCodeValidate" select="'2521'"/>
          <xsl:with-param name="node" select="cbc:ID"/>
          <xsl:with-param name="regexp" select="'^(([F][A-Z0-9]{3}-[0-9]{1,8})|((E001)-[0-9]{1,8})|([0-9]{1,4}-[0-9]{1,8}))$'"/>
          <xsl:with-param name="isError" select="false()"/>
          <xsl:with-param name="descripcion" select="concat('Documento de anticipo numero: ', position(), ', Tipo comprobante: ', cbc:ID/@schemeID,'. ')"/>
        </xsl:call-template>
      </xsl:when>
      <!-- /Invoice/cac:PrepaidPayment/cbc:ID  Si "Tipo de documento del emisor del anticipo" existe y "Tipo de comprobante que se realizo el anticipo" es 03 (Boleta), el formato del Tag UBL  es diferente a: [B][A-Z0-9]{3}-[0-9]{1,8} [F][A-Z0-9]{3}-[0-9]{1,8} E001-[0-9]{1,8} EB01-[0-9]{1,8} [0-9]{1,4}-[0-9]{1,8} OBSERV 2521 -->
      <xsl:when test="cbc:ID/@schemeID ='03'">
        <xsl:call-template name="regexpValidateElementIfExist">
          <xsl:with-param name="errorCodeValidate" select="'2521'"/>
          <xsl:with-param name="node" select="cbc:ID"/>
          <xsl:with-param name="regexp" select="'^(([B][A-Z0-9]{3}-[0-9]{1,8})|([F][A-Z0-9]{3}-[0-9]{1,8})|(E001-[0-9]{1,8})|(EB01-[0-9]{1,8})|([0-9]{1,4}-[0-9]{1,8}))$'"/>
          <xsl:with-param name="isError" select="false()"/>
          <xsl:with-param name="descripcion" select="concat('Documento de anticipo numero: ', position(), ', Tipo comprobante: ', cbc:ID/@schemeID,'. ')"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:otherwise>
        <!-- /Invoice/cac:PrepaidPayment/cbc:ID/@schemeID Si el atributo del Tag UBL existe y es diferente a 02 (Factura) y 03 (Boleta)  OBSERV 2505 -->
        <xsl:call-template name="isTrueExpresion">
          <xsl:with-param name="errorCodeValidate" select="'2505'"/>
          <xsl:with-param name="node" select="cbc:ID/@schemeID"/>
          <xsl:with-param name="expresion" select="true()"/>
          <xsl:with-param name="isError" select="false()"/>
          <xsl:with-param name="descripcion" select="concat('Documento de anticipo numero: ', position())"/>
        </xsl:call-template>
      </xsl:otherwise>
    </xsl:choose>
    <!-- /Invoice/cac:PrepaidPayment/cbc:PaidAmount Si el Tag UBL existe y es menor o igual a 0 (cero)  OBSERV 2503 -->
    <xsl:call-template name="isTrueExpresion">
      <xsl:with-param name="errorCodeValidate" select="'2503'"/>
      <xsl:with-param name="node" select="cbc:PaidAmount"/>
      <xsl:with-param name="expresion" select="cbc:PaidAmount and cbc:PaidAmount &lt;= 0"/>
      <xsl:with-param name="descripcion" select="concat('Documento de anticipo numero: ', position())"/>
      <xsl:with-param name="isError" select="false()"/>
    </xsl:call-template>
    <!-- /Invoice/cac:PrepaidPayment/cbc:InstructionID Si "Tipo de documento del emisor del anticipo" existe y el formato del Tag UBL es diferente a númerico de 11 dígitos  OBSERV 2529 -->
    <xsl:call-template name="regexpValidateElementIfExist">
      <xsl:with-param name="errorCodeValidate" select="'2529'"/>
      <xsl:with-param name="node" select="cbc:InstructionID"/>
      <xsl:with-param name="regexp" select="'^(?!(0)0+$)[0-9]{11}$'"/>
      <!-- numero decimal de 11 enteros. No acepta solo cero -->
      <xsl:with-param name="isError" select="false()"/>
      <xsl:with-param name="descripcion" select="concat('Documento de anticipo numero: ', position(), '. Ruc del emisor del anticipo.')"/>
    </xsl:call-template>
    <!-- /Invoice/cac:PrepaidPayment/cbc:InstructionID/@schemeID Si el atributo del Tag UBL existe y es diferente a 6 (RUC)  OBSERV 2520 -->
    <xsl:call-template name="existAndRegexpValidateElement">
      <xsl:with-param name="errorCodeNotExist" select="'2520'"/>
      <xsl:with-param name="errorCodeValidate" select="'2520'"/>
      <xsl:with-param name="node" select="cbc:InstructionID/@schemeID"/>
      <xsl:with-param name="regexp" select="'^[6]$'"/>
      <xsl:with-param name="isError" select="false()"/>
      <xsl:with-param name="descripcion" select="concat('Documento de anticipo numero: ', position())"/>
    </xsl:call-template>
  </xsl:template>
  <!--  ===========================================================================================================================================  =========================================== fin cac:PrepaidPayment ===========================================   =========================================================================================================================================== -->
  <!--  ===========================================================================================================================================  =============== ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalProperty ==============   =========================================================================================================================================== -->
  <xsl:template match="ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalProperty">
    <xsl:param name="tipoOPeracion"/>
    <!-- /Invoice/ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalProperty/cbc:ID Si existe el Tag UBL y el formato del Tag UBL es diferente a numérico de 4 dígitos  ERROR 2366 -->
    <xsl:call-template name="existAndRegexpValidateElement">
      <xsl:with-param name="errorCodeNotExist" select="'2366'"/>
      <xsl:with-param name="errorCodeValidate" select="'2366'"/>
      <xsl:with-param name="node" select="cbc:ID"/>
      <xsl:with-param name="regexp" select="'^\d{4}$'"/>
      <!-- Ini PAS20171U210300071 -->
      <xsl:with-param name="isError" select="false()"/>
      <!-- Fin PAS20171U210300071 -->
    </xsl:call-template>
    <!-- /Invoice/ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalProperty/cbc:Value Si existe el Tag UBL y el formato del Tag UBL es diferente a alfanumérico de hasta 100 caractéres  ERROR 2066 -->
    <xsl:call-template name="existAndRegexpValidateElement">
      <xsl:with-param name="errorCodeNotExist" select="'2066'"/>
      <xsl:with-param name="errorCodeValidate" select="'2066'"/>
      <xsl:with-param name="node" select="cbc:Value"/>
      <xsl:with-param name="regexp" select="'^(?!\s*$)[^\s].{0,99}$'"/>
    </xsl:call-template>
  </xsl:template>
  <!--  ===========================================================================================================================================  =============== fin ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sac:AdditionalInformation/sac:AdditionalProperty ==============   =========================================================================================================================================== -->
</xsl:stylesheet>
