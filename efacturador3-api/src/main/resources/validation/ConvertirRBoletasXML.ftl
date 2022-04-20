<?xml version="1.0" encoding="windows-1250"?>
<SummaryDocuments xmlns="urn:sunat:names:specification:ubl:peru:schema:xsd:SummaryDocuments-1" 
                xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" 
                xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" 
                xmlns:ext="urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2" 
                xmlns:sac="urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1" 
                xmlns:ds="http://www.w3.org/2000/09/xmldsig#" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
                xsi:schemaLocation="urn:sunat:names:specification:ubl:peru:schema:xsd:SummaryDocuments-1 UBLPE-SummaryDocuments-1.0.xsd">                 
<ext:UBLExtensions>
</ext:UBLExtensions>        
 <cbc:UBLVersionID>${ublVersionIdSwf}</cbc:UBLVersionID>
 <cbc:CustomizationID>${CustomizationIdSwf}</cbc:CustomizationID>
 <cbc:ID>${idResumen}</cbc:ID>
 <cbc:ReferenceDate>${fechaEmision}</cbc:ReferenceDate>
 <cbc:IssueDate>${fechaResumen}</cbc:IssueDate>
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
 <cac:AccountingSupplierParty>
  <cbc:CustomerAssignedAccountID>${nroRucEmisorSwf}</cbc:CustomerAssignedAccountID>
  <cbc:AdditionalAccountID>${tipDocuEmisorSwf}</cbc:AdditionalAccountID>
  <cac:Party>
    <cac:PartyLegalEntity>
      <cbc:RegistrationName><![CDATA[${razonSocialSwf}]]></cbc:RegistrationName>
    </cac:PartyLegalEntity>
  </cac:Party>
  </cac:AccountingSupplierParty>
  <#list listaResumen as resumen>
  <sac:SummaryDocumentsLine>
    <cbc:LineID>${resumen.linea}</cbc:LineID>
    <cbc:DocumentTypeCode>${resumen.tipDocResumen}</cbc:DocumentTypeCode>
    <cbc:ID>${resumen.idDocResumen}</cbc:ID>
    <cac:AccountingCustomerParty>
      <cbc:CustomerAssignedAccountID>${resumen.numDocUsuario}</cbc:CustomerAssignedAccountID>
      <cbc:AdditionalAccountID>${resumen.tipDocUsuario}</cbc:AdditionalAccountID>
    </cac:AccountingCustomerParty>
    <!--  Documento que modifica -->
	<#if resumen.serDocModifico != "">
    <cac:BillingReference>
      <cac:InvoiceDocumentReference>
        <cbc:ID>${resumen.serDocModifico}-${resumen.numDocModifico}</cbc:ID>
        <cbc:DocumentTypeCode>${resumen.tipDocModifico}</cbc:DocumentTypeCode>
      </cac:InvoiceDocumentReference>
    </cac:BillingReference>
	</#if>
	
    <!--  Datos de Percepcion - PER -->
	<#if resumen.tipRegPercepcion != "">
		<sac:SUNATPerceptionSummaryDocumentReference>
			<sac:SUNATPerceptionSystemCode>${resumen.tipRegPercepcion}</sac:SUNATPerceptionSystemCode>
			<sac:SUNATPerceptionPercent>${resumen.porPercepcion}</sac:SUNATPerceptionPercent>
			<cbc:TotalInvoiceAmount currencyID="PEN">${resumen.monPercepcion}</cbc:TotalInvoiceAmount>
			<sac:SUNATTotalCashed currencyID="PEN">${resumen.monTotIncPercepcion}</sac:SUNATTotalCashed>
			<cbc:TaxableAmount currencyID="PEN">${resumen.monBasePercepcion}</cbc:TaxableAmount>
		</sac:SUNATPerceptionSummaryDocumentReference>	
	</#if>
	<!-- PER -->
	
    <cac:Status>
      <cbc:ConditionCode>${resumen.tipEstado}</cbc:ConditionCode>
    </cac:Status>
    <!--Total Importe Total-->
    <sac:TotalAmount currencyID="${resumen.moneda}">${resumen.totImpCpe}</sac:TotalAmount>
	
    <!--Total Venta Operaciones Gravadas - 01 -->
	<#if resumen.totValGrabado != ''>
	<#if resumen.totValGrabado?number gt 0>
    <sac:BillingPayment>
      <cbc:PaidAmount currencyID="${resumen.moneda}">${resumen.totValGrabado}</cbc:PaidAmount>
      <cbc:InstructionID>01</cbc:InstructionID>
    </sac:BillingPayment>
	</#if>	
	</#if>
	<!-- fin 01 -->
	
    <!--Total Venta Operaciones Exoneradas - 02 -->
	<#if resumen.totValExoneado != ''>
	<#if resumen.totValExoneado?number gt 0>
    <sac:BillingPayment>
      <cbc:PaidAmount currencyID="${resumen.moneda}">${resumen.totValExoneado}</cbc:PaidAmount>
      <cbc:InstructionID>02</cbc:InstructionID>
    </sac:BillingPayment>
	</#if>	
	</#if>
	<!-- fin 02 -->
		
    <!--Total Venta Operaciones Inafectas - 03 -->
	<#if resumen.totValInafecto != ''>
	<#if resumen.totValInafecto?number gt 0>
    <sac:BillingPayment>
      <cbc:PaidAmount currencyID="${resumen.moneda}">${resumen.totValInafecto}</cbc:PaidAmount>
      <cbc:InstructionID>03</cbc:InstructionID>
    </sac:BillingPayment>
	</#if>	
	</#if>	
	<!-- fin 03 -->
	
	<!--Total Venta Operaciones Exportaciones - 04 -->
	<#if resumen.totValExportado != ''>
	<#if resumen.totValExportado?number gt 0>
    <sac:BillingPayment>
      <cbc:PaidAmount currencyID="${resumen.moneda}">${resumen.totValExportado}</cbc:PaidAmount>
      <cbc:InstructionID>04</cbc:InstructionID>
    </sac:BillingPayment>
	</#if>	
	</#if>	
	<!-- fin 04 -->
	
    <!--Total Venta Operaciones Gratuitas - 05 -->
	<#if resumen.totValGratuito != ''>
	<#if resumen.totValGratuito?number gt 0>
    <sac:BillingPayment>
      <cbc:PaidAmount currencyID="${resumen.moneda}">${resumen.totValGratuito}</cbc:PaidAmount>
      <cbc:InstructionID>05</cbc:InstructionID>
    </sac:BillingPayment>
	</#if>	
	</#if>	
	
	<!-- fin 05 -->
	
    <!--Total SUMATORIO OTROS CARGOS - Cargos-->
	<#if resumen.totOtroCargo != ''>
	<#if resumen.totOtroCargo?number gt 0>
    <cac:AllowanceCharge>
      <cbc:ChargeIndicator>true</cbc:ChargeIndicator>
      <cbc:Amount currencyID="${resumen.moneda}">${resumen.totOtroCargo}</cbc:Amount>
    </cac:AllowanceCharge>
	</#if>	
	</#if>	
	<!-- fin Cargos -->
	
   <#list listaTributosDocResumen as detalleTributosDocResumen>

   <#if detalleTributosDocResumen.idLineaRd??>
   <#if detalleTributosDocResumen.idLineaRd?number = resumen.linea?number>
    <cac:TaxTotal>
      <cbc:TaxAmount currencyID="${resumen.moneda}">${detalleTributosDocResumen.mtoTributoRd}</cbc:TaxAmount>
      <cac:TaxSubtotal>
        <cbc:TaxAmount currencyID="${resumen.moneda}">${detalleTributosDocResumen.mtoTributoRd}</cbc:TaxAmount>
        <cac:TaxCategory>
          <cac:TaxScheme>
            <cbc:ID>${detalleTributosDocResumen.ideTributoRd}</cbc:ID>
            <cbc:Name>${detalleTributosDocResumen.nomTributoRd}</cbc:Name>
            <cbc:TaxTypeCode>${detalleTributosDocResumen.codTipTributoRd}</cbc:TaxTypeCode>
          </cac:TaxScheme>
        </cac:TaxCategory>
      </cac:TaxSubtotal>
    </cac:TaxTotal>
   </#if>
   </#if>

   </#list>
  </sac:SummaryDocumentsLine>
  </#list>
</SummaryDocuments>