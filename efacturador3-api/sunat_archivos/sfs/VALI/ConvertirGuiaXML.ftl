<?xml version="1.0" encoding="ISO-8859-1" standalone="no"?>
<#setting number_format="#########0.########">
<DespatchAdvice xmlns:qdt="urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2"
                xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2"
                xmlns:sac="urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1"
                xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2"
                xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:ccts="urn:un:unece:uncefact:documentation:2"
                xmlns:ext="urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2"
                xmlns:ds="http://www.w3.org/2000/09/xmldsig#"
                xmlns="urn:oasis:names:specification:ubl:schema:xsd:DespatchAdvice-2">
  <ext:UBLExtensions> 
 </ext:UBLExtensions>
  <!-- Cabecera - Versión del UBL-->
  <cbc:UBLVersionID>${ublVersionId}</cbc:UBLVersionID>
  <!-- Cabecera - Versión de la estructura del documento -->
  <cbc:CustomizationID>${customizationId}</cbc:CustomizationID>
  <!-- Cabecera - Numeracion, conformada por serie y numero correlativo -->
  <cbc:ID>${nroCdpSwf}</cbc:ID>
  <!-- Cabecera - Fecha de emisión -->
  <cbc:IssueDate>${fecEmision}</cbc:IssueDate>
  <!-- Cabecera - Tipo de documento (Guia) -->
  <cbc:DespatchAdviceTypeCode>${tipCdpSwf}</cbc:DespatchAdviceTypeCode>
  <!-- Cabecera - Observaciones -->
  <cbc:Note>${obsGuia}</cbc:Note>
  <!-- Cabecera - Guía de Remisión Dada de Baja
		Numero de documento
		Código del tipo de documento
		Tipo de Documento

		Despatch Advice. Order Reference
		A reference to an Order with which this Despatch Advice is associated.	
		
		Se usa para asignar un guía dada de baja - Guía de Remisión dada de Baja - Catálogo N° 01 debe ser = 09 -->
  
  <#if idDocBaja??>
  <cac:OrderReference>
    <cbc:ID>${idDocBaja}</cbc:ID>
    <!-- cbc:SalesOrderID>CON0095678</cbc:SalesOrderID>
    <cbc:UUID>6E09886B-DC6E-439F-82D1-7CCAC7F4E3B1</cbc:UUID>
    <cbc:IssueDate>2005-06-20</cbc:IssueDate-->
    <cbc:OrderTypeCode name="${tipDocBaja}">${codTipDocBaja}</cbc:OrderTypeCode>
  </cac:OrderReference>
  </#if>
  <!-- Cabecera - Documento Adicional Relacionado
		Numero de documento
		Código del tipo de documento 
		
		Despatch Advice. Additional_ Document Reference. Document Reference
        A reference to an additional document associated with this document. 
		
		Catálago N° 23 
		Código 		Descripción
		01 			NUMERACION DAM (solo aplica para motTrasladoDatosEnvio menos importacion y exportacion) 
		02 			NUMERO DE ORDEN DE ENTREGA
		03 			NUMERO SCOP
		04 			NUMERO DE MANIFIESTO DE CARGA
		05 			NUMERO DE CONSTANCIA DE DETRACCION
		06 			OTROS -->
    
  <#list listaDocRelacionado as docRelacionado> 
  <#if docRelacionado.numDocRel??>
  <cac:AdditionalDocumentReference>
    <cbc:ID>${docRelacionado.numDocRel}</cbc:ID>
    <cbc:DocumentTypeCode>${docRelacionado.codTipDocRel}</cbc:DocumentTypeCode>
  </cac:AdditionalDocumentReference>
  </#if>
  </#list>
  <cac:Signature>
    <cbc:ID>${nroRucEmisorSwf}</cbc:ID>
	<cbc:Note>${identificadorFacturadorSwf}</cbc:Note>
    <cac:SignatoryParty>
      <cac:PartyIdentification>
        <cbc:ID>${nroRucEmisorSwf}</cbc:ID>
      </cac:PartyIdentification>
      <cac:PartyName>
        <cbc:Name><![CDATA[${razonSocialSwf}]]></cbc:Name>
      </cac:PartyName>
    </cac:SignatoryParty>
    <cac:DigitalSignatureAttachment>
      <cac:ExternalReference>
        <cbc:URI>${identificadorFirmaSwf}</cbc:URI>
      </cac:ExternalReference>
    </cac:DigitalSignatureAttachment>
  </cac:Signature>
  <!-- Cabecera - Datos del Remitente
		Numero de documento de identidad del remitente
		Tipo de documento de identidad del remitente
		Apellidos y nombres, denominación o razón social del remitente 
		Despatch Advice. Despatch_ Supplier Party. Supplier Party -->
  <cac:DespatchSupplierParty>
    <cbc:CustomerAssignedAccountID schemeID="${tipDocuEmisorSwf}">${nroRucEmisorSwf}</cbc:CustomerAssignedAccountID>
    <cbc:AdditionalAccountID/>
    <cac:Party>
      <cac:PartyName>
        <cbc:Name><![CDATA[${nombreComercialSwf}]]></cbc:Name>
      </cac:PartyName>
      <cac:PartyLegalEntity>
        <cbc:RegistrationName><![CDATA[${razonSocialSwf}]]></cbc:RegistrationName>
      </cac:PartyLegalEntity>
    </cac:Party>
  </cac:DespatchSupplierParty>
  <!-- Cabecera - Datos del Destinatario
		Numero de documento de identidad del destinatario
		Tipo de documento de identidad
		Apellidos y nombres, denominación o razón social del destinatario 
		Despatch Advice. Delivery_ Customer Party. Customer Party -->
  <cac:DeliveryCustomerParty>
    <cbc:CustomerAssignedAccountID schemeID="${tipDocDestinatario}">${numDocDestinatario}</cbc:CustomerAssignedAccountID>
    <cbc:AdditionalAccountID/>
    <cac:Party>
      <cac:PartyName>
        <cbc:Name><![CDATA[${rznSocialDestinatario}]]></cbc:Name>
      </cac:PartyName>
      <cac:PartyLegalEntity>
        <cbc:RegistrationName><![CDATA[${rznSocialDestinatario}]]></cbc:RegistrationName>
      </cac:PartyLegalEntity>
    </cac:Party>
  </cac:DeliveryCustomerParty>
  <!-- Cabecera - Despatch Advice. TIENDA - Seller_ Supplier - Proveedor Remitente - Party. Supplier Party -->
  <cac:SellerSupplierParty>
    <cbc:CustomerAssignedAccountID schemeID="${tipDocProveedor}">${numDocProveedor}</cbc:CustomerAssignedAccountID>
    <cbc:AdditionalAccountID/>
    <cac:Party>
      <cac:PartyName>
        <cbc:Name>${rznSocialProveedor}</cbc:Name>
      </cac:PartyName>
      <cac:PartyLegalEntity>
        <cbc:RegistrationName>${rznSocialProveedor}</cbc:RegistrationName>
      </cac:PartyLegalEntity>
    </cac:Party>
  </cac:SellerSupplierParty>
  <!-- Cabecera - Datos del Envío -->
  <cac:Shipment>
    <cbc:ID>1</cbc:ID>
    <!-- Datos del Envio - Motivo de Traslado -->
    <cbc:HandlingCode>${motTrasladoDatosEnvio}</cbc:HandlingCode>
    <!-- Datos del Envío - Descripcion del motive del traslado -->
    <cbc:Information>${desMotivoTrasladoDatosEnvio}</cbc:Information>
    <!-- Datos del Envío - Peso bruto total de la guía -->
    <cbc:GrossWeightMeasure unitCode="${uniMedidaPesoBrutoDatosEnvio}">${psoBrutoTotalBienesDatosEnvio}</cbc:GrossWeightMeasure>
    <!-- Datos del Envío - Numero de bultos o pallets - Enteros-->
	<#if numBultosDatosEnvio?? && numBultosDatosEnvio != "-" && numBultosDatosEnvio != "" >
    <cbc:TotalTransportHandlingUnitQuantity>${numBultosDatosEnvio}</cbc:TotalTransportHandlingUnitQuantity>
	 </#if>
    <!-- Datos del Envío - Indicador del transbordo programado -->
    <cbc:SplitConsignmentIndicator>${indTransbordoProgDatosEnvio}</cbc:SplitConsignmentIndicator>
    <cac:Consignment>
      <cbc:ID>001</cbc:ID>
    </cac:Consignment>
    <!-- Datos del Envío - Período de embarque -->
    <cac:ShipmentStage>
      <cbc:ID>1</cbc:ID>
      <!-- Datos del Envío - Embarque - Modalidad del traslado -->
      <cbc:TransportModeCode>${modTrasladoDatosEnvio}</cbc:TransportModeCode>
      <!--cbc:TransitDirectionCode>20</cbc:TransitDirectionCode>
      <cbc:PreCarriageIndicator>false</cbc:PreCarriageIndicator>
      <cbc:OnCarriageIndicator>false</cbc:OnCarriageIndicator-->
      <!-- Datos del Envío - Embarque - Fecha Salida -->
      <cac:TransitPeriod>
        <cbc:StartDate>${fecInicioTrasladoDatosEnvio}</cbc:StartDate>
      </cac:TransitPeriod>
      <!-- Datos del Envío - Embarque - Transporte publico-->
      <cac:CarrierParty>
        <cac:PartyIdentification>
          <cbc:ID schemeID="${tipDocTransportista}">${numDocTransportista}</cbc:ID>
        </cac:PartyIdentification>
        <cac:PartyName>
          <cbc:Name>${nomTransportista}</cbc:Name>
        </cac:PartyName>
        <!-- cac:PartyLegalEntity>
          <cbc:RegistrationName>MARVISUR SAC</cbc:RegistrationName>
        </cac:PartyLegalEntity-->
      </cac:CarrierParty>
      <!-- Datos del Envío - Embarque - Medios de Transporte privado-->
      <cac:TransportMeans>
        <cac:RoadTransport>
          <!-- Datos del Envío - Embarque - Medios de Transporte - Placa -->
          <cbc:LicensePlateID>${numPlacaTransPrivado}</cbc:LicensePlateID>
        </cac:RoadTransport>
      </cac:TransportMeans>
      <!-- Datos del Conductor  - Embarque - Medios de Transporte privado-->
      <cac:DriverPerson>
        <cbc:ID schemeID="${tipDocIdeConductorTransPrivado}">${numDocIdeConductorTransPrivado}</cbc:ID>
        <cbc:FirstName>${nomConductorTransPrivado}</cbc:FirstName>
      </cac:DriverPerson>
    </cac:ShipmentStage>
    <!-- Datos del Envio - Entrega -->
    <cac:Delivery>
      <!-- Entrega - Dirección -->
      <cac:DeliveryAddress>
        <cbc:ID>${ubiLlegada}</cbc:ID>
        <cbc:StreetName>${dirLlegada}</cbc:StreetName>
      </cac:DeliveryAddress>
    </cac:Delivery>
    <!-- Datos del Envio - codigo del contenedor -->
    <cac:TransportHandlingUnit>
      <cac:TransportEquipment>
        <cbc:ID>${numContenedor}</cbc:ID>
      </cac:TransportEquipment>
    </cac:TransportHandlingUnit>
    <!-- Datos del Envio - Dirección Origen -->
    <cac:OriginAddress>
      <cbc:ID>${ubiPartida}</cbc:ID>
      <cbc:StreetName>${dirPartida}</cbc:StreetName>
    </cac:OriginAddress>
    <!-- Datos del Envio - codigo del puerto -->
    <cac:FirstArrivalPortLocation>
      <cbc:ID>${codPuerto}</cbc:ID>
    </cac:FirstArrivalPortLocation>
  </cac:Shipment>
  <!-- Item - A Despatch Line associated with a kind of item delivered. -->
  <#list listaDetalle as detalle>
  <cac:DespatchLine>
    <cbc:ID>${detalle.lineaSwf}</cbc:ID>
    <!-- Item - Cantidad -->
    <cbc:DeliveredQuantity unitCode="${detalle.uniMedidaItem}">${detalle.canItem}</cbc:DeliveredQuantity>
    <cac:OrderLineReference>
      <cbc:LineID>1</cbc:LineID>
    </cac:OrderLineReference>
    <cac:Item>
      <cbc:Name>${detalle.desItem}</cbc:Name>
      <cac:SellersItemIdentification>
        <cbc:ID>${detalle.codItem}</cbc:ID>
      </cac:SellersItemIdentification>
    </cac:Item>
  </cac:DespatchLine>
  </#list>
</DespatchAdvice>