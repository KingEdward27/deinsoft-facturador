<?xml version="1.0" encoding="iso-8859-1"?>
<Perception xmlns="urn:sunat:names:specification:ubl:peru:schema:xsd:Perception-1"
		xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2"
		xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2"
		xmlns:ccts="urn:un:unece:uncefact:documentation:2"
		xmlns:ds="http://www.w3.org/2000/09/xmldsig#"
		xmlns:ext="urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2"
		xmlns:qdt="urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2"
		xmlns:sac="urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1"
		xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<ext:UBLExtensions>
	</ext:UBLExtensions>
	<cbc:UBLVersionID>${ublVersionIdSwf}</cbc:UBLVersionID>
	<cbc:CustomizationID>${CustomizationIdSwf}</cbc:CustomizationID>	
	<cac:Signature>
	<cbc:ID>${nroRucEmisorSwf}</cbc:ID>
	<cbc:Note>${identificadorFacturadorSwf}</cbc:Note>
	<cac:SignatoryParty>
      <cac:PartyIdentification>
        <cbc:ID>${nroRucEmisorSwf}</cbc:ID>
      </cac:PartyIdentification>
      <cac:PartyName>
        <cbc:Name><![CDATA[${nombreComercialSwf}]]></cbc:Name>
      </cac:PartyName>
      <cac:AgentParty>
        <cac:PartyIdentification>
          <cbc:ID>${nroRucEmisorSwf}</cbc:ID>
        </cac:PartyIdentification>
        <cac:PartyName>
          <cbc:Name><![CDATA[${razonSocialSwf}]]></cbc:Name>
        </cac:PartyName>
        <cac:PartyLegalEntity>
          <cbc:RegistrationName><![CDATA[${razonSocialSwf}]]></cbc:RegistrationName>
        </cac:PartyLegalEntity>
      </cac:AgentParty>
    </cac:SignatoryParty>
    <cac:DigitalSignatureAttachment>
      <cac:ExternalReference>
        <cbc:URI>${identificadorFirmaSwf}</cbc:URI>
      </cac:ExternalReference>
    </cac:DigitalSignatureAttachment>
	</cac:Signature>
	
	<!-- Cabecera de CRE -->
	<cbc:ID>${nroCdpSwf}</cbc:ID>
	<cbc:IssueDate>${fecEmision}</cbc:IssueDate>
	<cac:AgentParty>
		<cac:PartyIdentification>
			<cbc:ID schemeID="${tipDocuEmisorSwf}">${nroRucEmisorSwf}</cbc:ID>
		</cac:PartyIdentification>
		<cac:PostalAddress>
         <cbc:ID>${ubigeoDomFiscalSwf}</cbc:ID>
         <cbc:StreetName><![CDATA[${direccionDomFiscalSwf}]]></cbc:StreetName>		 
		 <cbc:CitySubdivisionName>${urbanizaSwf}</cbc:CitySubdivisionName>
         <cbc:CityName><![CDATA[${provinSwf}]]></cbc:CityName>
         <cbc:CountrySubentity><![CDATA[${deparSwf}]]></cbc:CountrySubentity>
         <cbc:District><![CDATA[${distrSwf}}]]></cbc:District>		
         <cac:Country>
          <cbc:IdentificationCode>${paisDomFiscalSwf}</cbc:IdentificationCode>      
         </cac:Country>
        </cac:PostalAddress>
		<cac:PartyLegalEntity>
			<cbc:RegistrationName><![CDATA[${nombreComercialSwf}]]></cbc:RegistrationName>
		</cac:PartyLegalEntity>
	</cac:AgentParty>
	<cac:ReceiverParty>
		<cac:PartyIdentification>
			<cbc:ID schemeID="${tipDocIdeReceptor}">${nroDocIdeReceptor}</cbc:ID>
		</cac:PartyIdentification>
		<cac:PostalAddress>
         <cbc:ID>${desUbiReceptor}</cbc:ID>
         <cbc:StreetName><![CDATA[${desDirReceptor}]]></cbc:StreetName>		 
		 <cbc:CitySubdivisionName><![CDATA[${desUrbReceptor}]]></cbc:CitySubdivisionName>
         <cbc:CityName><![CDATA[${desProReceptor}]]></cbc:CityName>
         <cbc:CountrySubentity><![CDATA[${desDepReceptor}]]></cbc:CountrySubentity>
         <cbc:District><![CDATA[${desDisReceptor}]]></cbc:District>		
         <cac:Country>
          <cbc:IdentificationCode>${codPaisReceptor}</cbc:IdentificationCode>      
         </cac:Country>
        </cac:PostalAddress>
		<cac:PartyLegalEntity>
			<cbc:RegistrationName><![CDATA[${desNomComReceptor}]]></cbc:RegistrationName>
		</cac:PartyLegalEntity>
	</cac:ReceiverParty>
	<sac:SUNATPerceptionSystemCode>${codRegPercepcion}</sac:SUNATPerceptionSystemCode>
	<sac:SUNATPerceptionPercent>${tasPercepcion}</sac:SUNATPerceptionPercent>
	<cbc:Note><![CDATA[${desObsPercepcion}]]></cbc:Note>
	<cbc:TotalInvoiceAmount currencyID="${tipMonPercepcion}">${mtoTotPercepcion}</cbc:TotalInvoiceAmount>
	<sac:SUNATTotalCashed currencyID="${tipMonImpTotPagPercepcion}">${mtoImpTotPagPercepcion}</sac:SUNATTotalCashed>	
	<!-- Fin de Cabecera de CRE -->
	
	<!-- Documentos Relacionados -->
	<#list listaDetalle as detalle>
	<sac:SUNATPerceptionDocumentReference>
		<cbc:ID schemeID="${detalle.tipDocRelacionado}">${detalle.nroDocRelacionado}</cbc:ID>
		<cbc:IssueDate>${detalle.fecEmiDocRelacionado}</cbc:IssueDate>
		<cbc:TotalInvoiceAmount currencyID="${detalle.tipMonDocRelacionado}">${detalle.mtoImpTotDocRelacionado}</cbc:TotalInvoiceAmount>
		
		<#if detalle.tipDocRelacionado != "07">
		<!-- Datos de Pago -->
		<cac:Payment>
			<cbc:ID>${detalle.nroPagDocRelacionado}</cbc:ID>
			<cbc:PaidAmount currencyID="${detalle.tipMonPagDocRelacionado}">${detalle.mtoPagDocRelacionado}</cbc:PaidAmount>
			<cbc:PaidDate>${detalle.fecPagDocRelacionado}</cbc:PaidDate>
		</cac:Payment>
		
		<!-- Datos de la Percención del Pago -->	
		<sac:SUNATPerceptionInformation>
			<sac:SUNATPerceptionAmount currencyID="${detalle.tipMonPerDocRelacionado}">${detalle.mtoPerDocRelacionado}</sac:SUNATPerceptionAmount>
			<sac:SUNATPerceptionDate>${detalle.fecPerDocRelacionado}</sac:SUNATPerceptionDate>
			<sac:SUNATNetTotalCashed currencyID="${detalle.tipMonTotPagNetoDocRelacionado}">${detalle.mtoTotPagNetoDocRelacionado}</sac:SUNATNetTotalCashed>
			
			<!-- Datos del tipo de cambio-->
			<#if detalle.tipMonRefTipCambio != 'PEN' && detalle.tipMonRefTipCambio != '-' && detalle.tipMonRefTipCambio != ''>
			<cac:ExchangeRate>
				<cbc:SourceCurrencyCode>${detalle.tipMonRefTipCambio}</cbc:SourceCurrencyCode>
				<cbc:TargetCurrencyCode>${detalle.tipMonObjTipCambio}</cbc:TargetCurrencyCode>
				<cbc:CalculationRate>${detalle.facTipCambio}</cbc:CalculationRate>
				<cbc:Date>${detalle.fecTipCambio}</cbc:Date>		
			</cac:ExchangeRate>	  
			</#if>
		</sac:SUNATPerceptionInformation>
		</#if>
	</sac:SUNATPerceptionDocumentReference>
	<!-- Fin de Documentos Relacionados --> 

	</#list>
</Perception>