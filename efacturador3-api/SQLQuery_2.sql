SELECT TOP (1000) [idempresa]
      ,[cert_name]
      ,[cert_pass]
      ,[clavesol]
      ,[estado]
      ,[numdoc]
      ,[razon_social]
      ,[serie]
      ,[tipodoc]
      ,[token]
      ,[usuariosol] from empresa
eyJhbGciOiJIUzUxMiJ9.eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2NDkzNjExODQsImlzcyI6IkRFSU5TT0ZUIiwianRpIjoiREVGQUNULUpXVCIsInN1YiI6IjIwNTE4OTY0NzU2L0hFUk9FUyBERUwgUEFDSUZJQ08iLCJudW1Eb2MiOiIyMDUxODk2NDc1NiIsInJhem9uU29jaWFsIjoiSEVST0VTIERFTCBQQUNJRklDTyIsInVzdWFyaW9Tb2wiOiJNT0REQVRPUyIsImV4cCI6MTY1MTE2MTE4NH0.jqX1NeB4MZ3O_wpiZIK_B2MgUsRacAjWG-2rPzXJQd1p1D-jXt4ihznBKC4ymp53O7K3wXCEikZiO2IIOT-AzQ.ulu9iCbXzudfgmAenRHtSgmjLANTOM907MOzwVdSQvj06-D2C9zQ2xBPDHgTKsUKCT4Tmbk_jjin8Q3v45ZQuA
  update empresa set usuariosol = 'EBOLETAS',clavesol = 'Eboletas123' where idempresa = 14
  FROM [dbo].[empresa]
  update factura_electronica set ind_situacion = '04' where m_id in (24,25)
select * from factura_electronica where empresa_id = 4
select * from factura_electronica_Det
select * from factura_electronica_tax


--update factura_electronica set ind_situacion = '02' where m_id = 39
  --delete from empresa where idempresa = 4
  --delete from factura_electronica where empresa_id = 4
  --delete from factura_electronica where m_id <=25
  --delete from factura_electronica_tax where factura_electronica_id <=25
  -- delete from factura_electronica_det where m_id <=25
   -- delete from factura_electronica_cuota where factura_electronica_id <=25

update factura_electronica_det set 
afectacion_igv = round((precio_venta_unitario* cantidad)-((precio_venta_unitario*cantidad)/1.18),2),
valor_unitario = round((precio_venta_unitario/1.18),2),
valor_venta_item = round((precio_venta_unitario/1.18),2)
where valor_unitario <= 0

update factura_electronica_tax
set taxtotal = (select sum(afectacion_igv) from factura_electronica_det where m_id = factura_electronica_tax.factura_electronica_id)
where taxtotal = 0

update factura_electronica
set sumatoriaigv = (select sum(afectacion_igv) from factura_electronica_det where m_id = factura_electronica.m_id)
where sumatoriaigv = 0



select sumatoriaigv,(select sum(afectacion_igv) from factura_electronica_det where m_id = t.factura_electronica_id)
from factura_electronica t

select * from factura_electronica_tax t

ALTER TABLE factura_electronica
drop column ticket_operacion;

ALTER TABLE factura_electronica
add ticket_operacion bigint;

ALTER TABLE factura_electronica_Det
add codigo varchar(100);

ALTER TABLE empresa
add nombre_comercial varchar(300);

ALTER TABLE empresa
add direccion varchar(300);

ALTER TABLE factura_electronica
add ticket_sunat varchar(100);


ALTER TABLE factura_electronica
drop COLUMN observacion_envio


ALTER TABLE factura_electronica
ALTER COLUMN observacion_envio VARCHAR(1000);

select afectacion_igv,precio_venta_unitario,cantidad,

from factura_electronica_det det
inner join factura_electronica fe on fe.m_id =  det.m_id
where fe.empresa_id = 14

