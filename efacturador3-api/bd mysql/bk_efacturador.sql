-- MySQL dump 10.17  Distrib 10.3.13-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: efacturador
-- ------------------------------------------------------
-- Server version	10.3.13-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration` (
  `configuracion_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `folderspath` varchar(900) NOT NULL,
  `usernamepath` varchar(90) DEFAULT NULL,
  `passpath` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`configuracion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration`
--

LOCK TABLES `configuration` WRITE;
/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
INSERT INTO `configuration` VALUES (1,'D://efacturador_folders','','');
/*!40000 ALTER TABLE `configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documento`
--

DROP TABLE IF EXISTS `documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documento` (
  `num_docu` varchar(255) NOT NULL,
  `num_ruc` varchar(255) NOT NULL,
  `tip_docu` varchar(255) NOT NULL,
  `des_obse` varchar(255) DEFAULT NULL,
  `fec_carg` varchar(255) DEFAULT NULL,
  `fec_envi` varchar(255) DEFAULT NULL,
  `fec_gene` varchar(255) DEFAULT NULL,
  `firm_digital` varchar(255) DEFAULT NULL,
  `ind_situ` varchar(255) DEFAULT NULL,
  `nom_arch` varchar(255) DEFAULT NULL,
  `tip_arch` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`num_docu`,`num_ruc`,`tip_docu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documento`
--

LOCK TABLES `documento` WRITE;
/*!40000 ALTER TABLE `documento` DISABLE KEYS */;
/*!40000 ALTER TABLE `documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empresa` (
  `idempresa` int(11) NOT NULL AUTO_INCREMENT,
  `razon_social` varchar(300) DEFAULT NULL,
  `tipodoc` int(11) DEFAULT NULL,
  `numdoc` varchar(13) DEFAULT NULL,
  `serie` varchar(4) DEFAULT NULL,
  `estado` char(1) DEFAULT NULL,
  `usuariosol` varchar(30) NOT NULL,
  `clavesol` varchar(30) NOT NULL,
  `cert_name` varchar(300) NOT NULL,
  `cert_pass` varchar(30) NOT NULL,
  `token` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idempresa`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (6,'DEINSOFT',6,'20534999616','','1','MODDATOS','MODDATOS','LLAMA-PE-CERTIFICADO-DEMO-20534999616.pfx','123456',''),(7,'HEROES DEL PACIFICO',6,'20518964756','','1','MODDATOS','MODDATOS','LLAMA-PE-CERTIFICADO-DEMO-20518964756.pfx','123456',''),(8,'HEROES DEL PACIFICO',6,'20518964756','','1','admin','admin','LLAMA-PE-CERTIFICADO-DEMO-20518964756.pfx','123456',''),(9,'HEROES DEL PACIFICO',6,'20518964756','','1','admin','admin','LLAMA-PE-CERTIFICADO-DEMO-20518964756.pfx','KW3BL19V9nc=','');
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `error`
--

DROP TABLE IF EXISTS `error`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `error` (
  `cod_cataerro` varchar(255) NOT NULL,
  `ind_estado` varchar(255) DEFAULT NULL,
  `nom_cataerro` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cod_cataerro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `error`
--

LOCK TABLES `error` WRITE;
/*!40000 ALTER TABLE `error` DISABLE KEYS */;
/*!40000 ALTER TABLE `error` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fact_nota_credito`
--

DROP TABLE IF EXISTS `fact_nota_credito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fact_nota_credito` (
  `id_nc` int(11) NOT NULL AUTO_INCREMENT,
  `id_factura_electronica` int(11) NOT NULL,
  `id_tipo_nc` char(2) DEFAULT NULL,
  `estado` char(1) DEFAULT NULL,
  `estado_envio` char(1) DEFAULT NULL,
  `num_nc` varchar(13) NOT NULL,
  `flag_gen_xml_nc` char(1) DEFAULT '0',
  `fechareg` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`id_nc`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fact_nota_credito`
--

LOCK TABLES `fact_nota_credito` WRITE;
/*!40000 ALTER TABLE `fact_nota_credito` DISABLE KEYS */;
INSERT INTO `fact_nota_credito` VALUES (1,102,'01','1','0','E001-1','0','2020-04-18 23:57:22'),(2,102,'01','1','0','E001-2','0','2020-04-18 23:57:22'),(3,102,'01','1','0','E001-3','0','2020-04-18 23:57:22'),(4,102,'01','1','0','E001-4','0','2020-04-18 23:57:22'),(5,102,'01','1','0','E001-5','0','2020-04-18 23:57:22'),(6,102,'01','1','0','E001-6','0','2020-04-18 23:57:49'),(7,102,'01','1','0','E001-7','0','2020-04-19 00:15:17'),(8,102,'01','1','0','E001-8','0','2020-04-19 00:17:05'),(9,102,'01','1','0','E001-9','0','2020-04-19 00:25:06'),(10,102,'01','1','0','E001-11','0','2020-04-19 00:39:25'),(11,102,'01','1','0','E001-12','0','2020-04-19 12:31:48'),(12,102,'01','1','0','E001-13','0','2020-04-19 12:39:25'),(13,102,'01','1','0','E001-14','0','2020-04-19 13:02:45'),(14,102,'01','1','0','B002-15','0','2020-04-19 13:11:53'),(15,102,'01','1','0','B002-16','0','2020-04-19 16:54:08'),(16,102,'01','1','0','B002-17','0','2020-04-19 17:53:14'),(17,102,'01','1','0','B002-18','0','2020-04-19 18:10:49'),(18,102,'01','1','0','F002-19','0','2020-04-19 18:11:58'),(19,102,'01','1','0','F002-20','0','2020-04-19 18:19:02'),(20,102,'01','1','0','F002-21','0','2020-04-19 22:37:30'),(21,102,'01','1','0','F002-22','0','2020-04-26 13:28:32');
/*!40000 ALTER TABLE `fact_nota_credito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fact_parametros`
--

DROP TABLE IF EXISTS `fact_parametros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fact_parametros` (
  `idParametro` int(11) NOT NULL AUTO_INCREMENT,
  `cod_pais` varchar(10) DEFAULT NULL,
  `id_firma_digital` varchar(100) DEFAULT NULL,
  `customization_id_boleta` varchar(10) DEFAULT NULL,
  `customization_id_resumen` varchar(10) DEFAULT NULL,
  `tasa_igv` decimal(5,2) DEFAULT NULL,
  `serieBoleta` char(4) DEFAULT NULL,
  `serieNotaCredito` char(4) DEFAULT NULL,
  `autorizacion` varchar(1000) DEFAULT NULL,
  `leyenda_boleta` varchar(1000) DEFAULT NULL,
  `fecini_proceso_fe` date DEFAULT NULL COMMENT 'Fecha de inicio de proceso de facturacion electronica',
  `ruta_pse` varchar(200) DEFAULT '',
  `correo_emisor` varchar(150) DEFAULT NULL,
  `pass_correo_emisor` varchar(40) DEFAULT NULL,
  `titulo_correo` varchar(800) DEFAULT NULL,
  `asunto_correo` varchar(800) DEFAULT NULL,
  `cuerpo_correo` varchar(2000) DEFAULT NULL,
  `url_pag_web` varchar(200) DEFAULT NULL,
  `ruta_firma` varchar(200) DEFAULT NULL,
  `pass_firma` varchar(200) DEFAULT NULL,
  `directorio_archivos` varchar(200) DEFAULT NULL,
  `flag_tipoenvio_sunat` char(1) DEFAULT '1',
  `flag_envioweb_cliente` char(1) DEFAULT '1',
  `flag_enviocorreo_cliente` char(1) DEFAULT '1',
  `razonsocial` varchar(200) DEFAULT NULL,
  `direccion` varchar(400) DEFAULT NULL,
  `ruc` varchar(20) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `version_ubl` varchar(10) NOT NULL,
  `id_parametro` int(11) NOT NULL,
  `serie_boleta` varchar(4) DEFAULT NULL,
  `serie_nota_credito` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`idParametro`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fact_parametros`
--

LOCK TABLES `fact_parametros` WRITE;
/*!40000 ALTER TABLE `fact_parametros` DISABLE KEYS */;
INSERT INTO `fact_parametros` VALUES (1,'PE','ID_FIRMA1','2.0','1.1',0.18,'','','','','2020-04-01','','edward21.sistemas@gmail.com','TomaTuPutaClaveComplicada123','EMPRESA X: Comprobante elctrónico',NULL,'Estimado Padre de familia, \r\n\r\nInformamos a usted que el documento \" + listAdj[0].NombreArchivo + @\" ya se encuentra disponible. \r\n\r\nTipo	:	BOLETA DE VENTA ELECTRONICA\r\nNúmero	:	<numerodoc>\r\nMonto	:	S/ <monto>\r\nFecha Emisión	:	<fechaemision>\r\n\r\nSaluda atentamente, \r\n\r\nCOLEGIO HEROES DEL PACIFICO',NULL,'D:\\Proyectos\\Colegio_Heroes_pacifico_Lima\\ServicioFacturacion(poner en ruta c)\\LLAMA-PE-CERTIFICADO-DEMO-20518964756 (1).pfx','123456','C:\\Users\\EDWARD-PC\\Documents','1','1','1','DESARROLLO INTEGRAL DE SOFTWARE SRL','AV PANAMERICANA SUR 1232','20534999616','12345678','2.1',0,NULL,NULL);
/*!40000 ALTER TABLE `fact_parametros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fact_resumen_diario`
--

DROP TABLE IF EXISTS `fact_resumen_diario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fact_resumen_diario` (
  `idResumen` int(11) NOT NULL AUTO_INCREMENT,
  `id_respuesta` int(11) DEFAULT NULL,
  `nro_doc` varchar(30) DEFAULT NULL,
  `fecha_emision` date DEFAULT NULL,
  `fechaRegistro` datetime DEFAULT NULL,
  `flag_envio` char(1) DEFAULT NULL,
  `flag_estado` char(1) DEFAULT '1',
  `id_resumen` int(11) NOT NULL,
  `fecha_registro` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`idResumen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fact_resumen_diario`
--

LOCK TABLES `fact_resumen_diario` WRITE;
/*!40000 ALTER TABLE `fact_resumen_diario` DISABLE KEYS */;
/*!40000 ALTER TABLE `fact_resumen_diario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fact_resumen_diario_det`
--

DROP TABLE IF EXISTS `fact_resumen_diario_det`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fact_resumen_diario_det` (
  `idResumen` int(11) DEFAULT NULL,
  `id_factura_boleta` int(11) DEFAULT NULL,
  KEY `idx_idfactura` (`id_factura_boleta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fact_resumen_diario_det`
--

LOCK TABLES `fact_resumen_diario_det` WRITE;
/*!40000 ALTER TABLE `fact_resumen_diario_det` DISABLE KEYS */;
/*!40000 ALTER TABLE `fact_resumen_diario_det` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fact_tipo_nc`
--

DROP TABLE IF EXISTS `fact_tipo_nc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fact_tipo_nc` (
  `id_tipo_nc` char(2) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `estado` char(1) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_nc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fact_tipo_nc`
--

LOCK TABLES `fact_tipo_nc` WRITE;
/*!40000 ALTER TABLE `fact_tipo_nc` DISABLE KEYS */;
INSERT INTO `fact_tipo_nc` VALUES ('01','Anulación de la operación','1'),('02','Anulación por error en el RUC','1'),('03','Corrección por error en la descripción','1'),('04','Descuento global','1'),('05','Decuento por item','1'),('06','Devolución total','1'),('07','Devolución por item','1'),('08','Bonificación','1'),('09','Disminución en el valor','1');
/*!40000 ALTER TABLE `fact_tipo_nc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fact_tipos_doc`
--

DROP TABLE IF EXISTS `fact_tipos_doc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fact_tipos_doc` (
  `codigo` int(11) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fact_tipos_doc`
--

LOCK TABLES `fact_tipos_doc` WRITE;
/*!40000 ALTER TABLE `fact_tipos_doc` DISABLE KEYS */;
INSERT INTO `fact_tipos_doc` VALUES (1,'FACTURA ELECTRONICA'),(3,'BOLETA DE VENTA ELECTRÓNICA'),(7,'NOTA DE CRÉDITO ELECTRÓNICA'),(8,'NOTA DE DÉBITO ELECTRÓNICA');
/*!40000 ALTER TABLE `fact_tipos_doc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fact_tipos_doc_id`
--

DROP TABLE IF EXISTS `fact_tipos_doc_id`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fact_tipos_doc_id` (
  `codigo` char(1) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `abreviatura` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fact_tipos_doc_id`
--

LOCK TABLES `fact_tipos_doc_id` WRITE;
/*!40000 ALTER TABLE `fact_tipos_doc_id` DISABLE KEYS */;
INSERT INTO `fact_tipos_doc_id` VALUES ('0','DOC.TRIB.NO.DOM.SIN.RUC','-'),('1','DOC. NACIONAL DE IDENTIDAD','DNI'),('4','CARNET DE EXTRANJERIA','-'),('6','REG. UNICO DE CONTRIBUYENTES','RUC'),('7','PASAPORTE','-'),('A','CED. DIPLOMATICA DE IDENTIDAD','-');
/*!40000 ALTER TABLE `fact_tipos_doc_id` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fact_tipos_igv`
--

DROP TABLE IF EXISTS `fact_tipos_igv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fact_tipos_igv` (
  `codigo` int(11) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `codigo_subtipo` char(2) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fact_tipos_igv`
--

LOCK TABLES `fact_tipos_igv` WRITE;
/*!40000 ALTER TABLE `fact_tipos_igv` DISABLE KEYS */;
INSERT INTO `fact_tipos_igv` VALUES (10,'Gravado - Operación Onerosa','01'),(11,'Gravado – Retiro por premio','01'),(12,'Gravado – Retiro por donación','01'),(13,'Gravado – Retiro','01'),(14,'Gravado – Retiro por publicidad','01'),(15,'Gravado – Bonificaciones','01'),(16,'Gravado – Retiro por entrega a trabajadores','01'),(17,'Gravado – IVAP','01'),(20,'Exonerado - Operación Onerosa','02'),(21,'Exonerado – Transferencia Gratuita','02'),(30,'Inafecto - Operación Onerosa','03'),(31,'Inafecto – Retiro por Bonificación','05'),(32,'Inafecto – Retiro','03'),(33,'Inafecto – Retiro por Muestras Médicas','03'),(34,'Inafecto - Retiro por Convenio Colectivo','03'),(35,'Inafecto – Retiro por premio','03'),(36,'Inafecto - Retiro por publicidad','03'),(40,'Exportación','04');
/*!40000 ALTER TABLE `fact_tipos_igv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura_electronica`
--

DROP TABLE IF EXISTS `factura_electronica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factura_electronica` (
  `m_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha_gen_xml` datetime(6) DEFAULT NULL,
  `cliente_direccion` varchar(255) DEFAULT NULL,
  `cliente_documento` varchar(255) DEFAULT NULL,
  `cliente_email` varchar(255) DEFAULT NULL,
  `cliente_nombre` varchar(255) DEFAULT NULL,
  `cliente_telefono` varchar(255) DEFAULT NULL,
  `cliente_tipo` varchar(255) DEFAULT NULL,
  `customization_id` varchar(255) DEFAULT NULL,
  `descuento_global_porcentaje` varchar(255) DEFAULT NULL,
  `descuentos_globales` decimal(19,2) DEFAULT NULL,
  `fecha_emision` datetime(6) DEFAULT NULL,
  `fecha_vencimiento` varchar(255) DEFAULT NULL,
  `guia_remision` varchar(255) DEFAULT NULL,
  `empresa_id` int(11) DEFAULT NULL,
  `importe_total` decimal(19,2) DEFAULT NULL,
  `incluir_pdf` varchar(255) DEFAULT NULL,
  `incluir_xml` varchar(255) DEFAULT NULL,
  `ind_situacion` varchar(255) DEFAULT NULL,
  `leyenda1` varchar(255) DEFAULT NULL,
  `leyenda2` varchar(255) DEFAULT NULL,
  `leyenda3` varchar(255) DEFAULT NULL,
  `moneda` varchar(255) DEFAULT NULL,
  `nota_motivo` varchar(255) DEFAULT NULL,
  `nota_referencia_numero` varchar(255) DEFAULT NULL,
  `nota_referencia_serie` varchar(255) DEFAULT NULL,
  `nota_referencia_tipo` varchar(255) DEFAULT NULL,
  `nota_tipo` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `orden_compra` varchar(255) DEFAULT NULL,
  `placa_vehiculo` varchar(255) DEFAULT NULL,
  `serie` varchar(255) DEFAULT NULL,
  `sumatoriaigv` decimal(19,2) DEFAULT NULL,
  `sumatoriaisc` decimal(19,2) DEFAULT NULL,
  `sumatoria_otros_cargos` decimal(19,2) DEFAULT NULL,
  `sumatoria_otros_tributos` decimal(19,2) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `tipo_operacion` varchar(255) DEFAULT NULL,
  `total_valor_venta` decimal(19,2) DEFAULT NULL,
  `total_valor_ventas_exoneradas` decimal(19,2) DEFAULT NULL,
  `total_valor_ventas_gravadas` decimal(19,2) DEFAULT NULL,
  `total_valor_ventas_inafectas` decimal(19,2) DEFAULT NULL,
  `vendedor_nombre` varchar(255) DEFAULT NULL,
  `xml_hash` varchar(255) DEFAULT NULL,
  `fechaEnvio` datetime DEFAULT NULL,
  `fecha_envio` datetime DEFAULT NULL,
  `observacion_envio` varchar(900) DEFAULT NULL,
  `cod_local` varchar(100) DEFAULT NULL,
  `forma_pago` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura_electronica`
--

LOCK TABLES `factura_electronica` WRITE;
/*!40000 ALTER TABLE `factura_electronica` DISABLE KEYS */;
INSERT INTO `factura_electronica` VALUES (1,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false',NULL,NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'01','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(2,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false',NULL,NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(5,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false',NULL,NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(6,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false',NULL,NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(7,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false',NULL,NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(8,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false',NULL,NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(9,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false',NULL,NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(10,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false',NULL,NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(11,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(12,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(13,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(14,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(15,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(16,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(17,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(18,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(19,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(20,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(21,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(22,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(23,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(24,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(25,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(26,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(27,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(28,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(29,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(30,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(31,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(32,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(33,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(34,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(35,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(36,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(37,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(38,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(39,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(40,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(41,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(42,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(43,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(44,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(45,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(46,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(47,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(48,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(49,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(50,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'42',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(51,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'43',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(52,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'43',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(53,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'43',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(54,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'43',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(55,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'43',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(56,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'43',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(57,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'43',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',100.00,0.00,100.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(58,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'43',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(59,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'43',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(60,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'43',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(61,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,0,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'43',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(62,'2022-02-22 19:24:16.000000','','00000000','','PUBLICO EN GENERAL',NULL,'1',NULL,'0.00',NULL,'2020-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','02',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'43',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(63,'2022-02-23 11:16:11.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','02',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'44',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(64,'2022-02-23 11:19:51.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','02',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'44',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(65,'2022-02-23 11:20:23.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','02',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'44',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(66,'2022-02-23 11:22:52.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','02',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'44',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(67,'2022-02-23 11:40:46.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','06',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'45',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,'2022-02-23 11:49:25','Hubo un problema al invocar servicio SUNAT: Could not load extension class org.apache.cxf.ws.policy.AssertionBuilderRegistryImpl.',NULL,NULL),(68,'2022-02-23 20:37:20.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','05',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'50',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,'2022-02-24 00:22:16','El documento electrónico ingresado ha sido alterado - Detalle: Incorrect reference digest value',NULL,NULL),(69,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'51',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(70,'2022-02-24 00:34:20.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','02',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'51',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(71,'2022-02-24 00:47:19.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','05',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'52',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,'2022-02-24 00:49:38','El documento electrónico ingresado ha sido alterado - Detalle: Incorrect reference digest value',NULL,NULL),(72,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'53',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(73,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'53',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(74,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'53',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(75,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'53',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(76,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'53',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(77,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'53',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(78,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'53',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(79,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'53',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(80,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'53',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(81,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'53',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(82,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'53',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(83,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'54',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(84,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'54',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(85,'2022-02-24 03:02:12.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,6,NULL,'false','false','05',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'54',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,'2022-02-24 11:13:28','El documento electrónico ingresado ha sido alterado - Detalle: Incorrect reference digest value',NULL,NULL),(86,'2022-02-24 11:30:41.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,7,NULL,'false','false','05',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'1',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,'2022-02-24 11:31:34','El documento electrónico ingresado ha sido alterado - Detalle: Incorrect reference digest value',NULL,NULL),(87,'2022-02-24 11:40:22.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,7,NULL,'false','false','02',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'1',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(88,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,8,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'2',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(89,'2022-02-24 11:58:28.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,8,NULL,'false','false','05',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'2',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,'2022-02-24 11:59:00','El documento electrónico ingresado ha sido alterado - Detalle: Incorrect reference digest value',NULL,NULL),(90,'2022-02-24 12:04:33.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','05',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'3',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,'2022-02-24 12:12:22','El documento electrónico ingresado ha sido alterado - Detalle: Incorrect reference digest value',NULL,NULL),(91,'2022-02-24 12:29:44.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','02',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'4',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(92,'2022-02-24 14:53:24.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','02',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'5',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(93,'2022-02-24 18:41:37.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','02',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'6',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(94,'2022-02-24 18:42:52.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','02',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'6',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(95,'2022-02-24 19:32:13.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','02',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'7',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(96,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'8',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(97,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'9',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(98,'2022-02-24 19:36:52.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','02',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'9',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(99,'2022-02-24 19:43:16.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','02',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'10',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(100,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'10',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(101,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'10',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(102,'2022-02-24 19:59:04.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','10',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'10',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,'2022-02-24 23:22:39','El archivo ZIP esta vacio - Detalle: xxx.xxx.xxx value=\'ticket:  error: Validation empty File error (codigo: 0155)\'',NULL,NULL),(103,'2022-02-24 23:18:59.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','02',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'11',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(104,'2022-02-24 23:36:11.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','05',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'12',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,'2022-02-24 23:36:34','El XML debe contener al menos un tributo por linea de afectacion por IGV - Detalle: xxx.xxx.xxx value=\'ticket: 1645763770524 error: Error en la linea: 100: 3105 (nodo: \"/\" valor: \"\")\'',NULL,NULL),(105,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'12',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(106,'2022-02-24 23:51:23.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','05',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'12',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,'2022-02-24 23:52:39','Si tiene operaciones de un tributo en alguna línea, debe consignar el tag del total del tributo  - Detalle: xxx.xxx.xxx value=\'ticket: 1645764735612 error: INFO : 2638 (nodo: \"/\" valor: \"\")\'',NULL,NULL),(107,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'13',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(108,'2022-02-25 00:31:08.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','02',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'13',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(109,'2022-02-25 00:36:05.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','02',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'13',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(110,'2022-02-25 00:37:20.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','02',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'13',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(111,'2022-02-25 00:38:23.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','02',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'13',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(112,'2022-02-25 00:43:30.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','02',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'13',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(113,NULL,'','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','01',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'13',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,NULL,NULL),(114,'2022-02-25 00:47:32.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','04',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'13',NULL,NULL,NULL,'BB01',18.00,0.00,0.00,0.00,'03','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,'2022-02-25 00:47:59','-',NULL,NULL),(115,'2022-02-25 01:12:03.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','05',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'1',NULL,NULL,NULL,'FF01',18.00,0.00,0.00,0.00,'01','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,'2022-02-25 01:12:20','El XML no contiene el tag o no existe información del código de local anexo del emisor - Detalle: xxx.xxx.xxx value=\'ticket: 1645769515181 error: INFO: 3030 (nodo: \"cac:RegistrationAddress/cbc:AddressTypeCode\" valor: \"\")\'',NULL,NULL),(116,'2022-02-25 10:26:46.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','05',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'2',NULL,NULL,NULL,'FF01',18.00,0.00,0.00,0.00,'01','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,'2022-02-25 10:27:04','El dato ingresado en el tipo de documento de identidad del receptor no esta permitido. - Detalle: xxx.xxx.xxx value=\'ticket: 1645802796710 error: INFO : 2800 (nodo: \"cbc:ID/schemeID\" valor: \"01\")\'','0000',NULL),(117,'2022-02-25 10:36:00.000000','','00000000','','PUBLICO EN GENERAL',NULL,'01',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','02',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'3',NULL,NULL,NULL,'FF01',18.00,0.00,0.00,0.00,'01','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,NULL,NULL,'0000',NULL),(118,'2022-02-25 10:39:52.000000','','12345678909','','PUBLICO EN GENERAL',NULL,'06',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','05',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'4',NULL,NULL,NULL,'FF01',18.00,0.00,0.00,0.00,'01','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,'2022-02-25 10:40:28','El dato ingresado en el tipo de documento de identidad del receptor no esta permitido. - Detalle: xxx.xxx.xxx value=\'ticket: 1645803601542 error: INFO : 2800 (nodo: \"cbc:ID/schemeID\" valor: \"06\")\'','0000',NULL),(119,'2022-02-25 10:47:12.000000','','12345678909','','PUBLICO EN GENERAL',NULL,'06',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','05',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'5',NULL,NULL,NULL,'FF01',18.00,0.00,0.00,0.00,'01','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,'2022-02-25 10:47:32','El dato ingresado en el tipo de documento de identidad del receptor no esta permitido. - Detalle: xxx.xxx.xxx value=\'ticket: 1645804025856 error: INFO : 2800 (nodo: \"cbc:ID/schemeID\" valor: \"06\")\'','0000',NULL),(120,'2022-02-25 11:35:40.000000','','12345678909','','PUBLICO EN GENERAL',NULL,'6',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','05',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'6',NULL,NULL,NULL,'FF01',18.00,0.00,0.00,0.00,'01','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,'2022-02-25 11:36:13','La base imponible a nivel de línea difiere de la información consignada en el comprobante - Detalle: xxx.xxx.xxx value=\'ticket: 1645806945865 error: Error en la linea: 116: 3272 (nodo: \"cac:TaxSubtotal/cbc:TaxableAmount\" valor: \"100\")\'','0000',NULL),(121,'2022-02-25 12:49:41.000000','','12345678909','','PUBLICO EN GENERAL',NULL,'6',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','05',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'7',NULL,NULL,NULL,'FF01',18.00,0.00,0.00,0.00,'01','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,'2022-02-25 12:49:54','Debe consignar la informacion del tipo de transaccion del comprobante - Detalle: xxx.xxx.xxx value=\'ticket: 1645811368273 error: INFO : 3244 (nodo: \"/\" valor: \"\")\'','0000',NULL),(122,'2022-02-25 15:45:47.000000','','12345678909','','PUBLICO EN GENERAL',NULL,'6',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','05',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'8',NULL,NULL,NULL,'FF01',18.00,0.00,0.00,0.00,'01','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,'2022-02-25 15:46:03','Debe consignar la informacion del tipo de transaccion del comprobante - Detalle: xxx.xxx.xxx value=\'ticket: 1645821936375 error: INFO : 3244 (nodo: \"/\" valor: \"\")\'','0000','contado'),(123,'2022-02-25 15:53:22.000000','','12345678909','','PUBLICO EN GENERAL',NULL,'6',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','05',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'9',NULL,NULL,NULL,'FF01',18.00,0.00,0.00,0.00,'01','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,'2022-02-25 15:54:23','El tipo de transaccion o el identificador de la cuota no cumple con el formato esperado - Detalle: xxx.xxx.xxx value=\'ticket: 1645822436564 error: INFO: 3246 (nodo: \"cac:PaymentTerms/cbc:PaymentMeansID\" valor: \"contado\")\'','0000','contado'),(124,'2022-02-25 15:55:02.000000','','12345678909','','PUBLICO EN GENERAL',NULL,'6',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','04',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'10',NULL,NULL,NULL,'FF01',18.00,0.00,0.00,0.00,'01','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,'2022-02-25 15:55:13','-','0000','Contado'),(125,'2022-02-25 16:43:49.000000','','12345678909','','PUBLICO EN GENERAL',NULL,'6',NULL,'0.00',NULL,'2021-09-04 00:00:00.000000',NULL,NULL,9,NULL,'false','false','05',NULL,NULL,NULL,'PEN',NULL,NULL,NULL,NULL,NULL,'11',NULL,NULL,NULL,'FF01',18.00,0.00,0.00,0.00,'01','0101',118.00,0.00,118.00,0.00,'ADMIN',NULL,NULL,'2022-02-25 16:44:00','Si el tipo de transaccion es al Credito debe existir al menos información de una cuota de pago - Detalle: xxx.xxx.xxx value=\'ticket: 1645825414369 error: INFO : 3249 (nodo: \"/\" valor: \"\")\'','0000','Credito');
/*!40000 ALTER TABLE `factura_electronica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura_electronica_det`
--

DROP TABLE IF EXISTS `factura_electronica_det`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factura_electronica_det` (
  `factura_electronica_det_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `afectacion_igvcode` varchar(255) DEFAULT NULL,
  `afectacion_igv` decimal(19,2) DEFAULT NULL,
  `cantidad` decimal(19,2) DEFAULT NULL,
  `cod_tributo_igv` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `descuento` decimal(19,2) DEFAULT NULL,
  `precio_code` varchar(255) DEFAULT NULL,
  `precio_venta_unitario` decimal(19,2) DEFAULT NULL,
  `recargo` decimal(19,2) DEFAULT NULL,
  `unidad_medida` varchar(255) DEFAULT NULL,
  `valor_unitario` decimal(19,2) DEFAULT NULL,
  `valor_venta_item` decimal(19,2) DEFAULT NULL,
  `m_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`factura_electronica_det_id`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura_electronica_det`
--

LOCK TABLES `factura_electronica_det` WRITE;
/*!40000 ALTER TABLE `factura_electronica_det` DISABLE KEYS */;
INSERT INTO `factura_electronica_det` VALUES (1,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,NULL,NULL,NULL,5),(2,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,NULL,NULL,NULL,6),(3,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,NULL,NULL,NULL,7),(4,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,NULL,NULL,NULL,8),(5,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,NULL,NULL,NULL,9),(6,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,NULL,NULL,NULL,10),(7,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,NULL,NULL,NULL,11),(8,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,NULL,NULL,NULL,12),(9,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,NULL,NULL,NULL,13),(10,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,NULL,NULL,NULL,14),(11,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,NULL,NULL,NULL,15),(12,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,NULL,NULL,NULL,16),(13,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,NULL,NULL,NULL,17),(14,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,NULL,NULL,NULL,18),(15,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,NULL,NULL,NULL,19),(16,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,NULL,NULL,NULL,20),(17,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,NULL,NULL,NULL,21),(18,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,NULL,NULL,NULL,22),(19,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,NULL,NULL,NULL,23),(20,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',NULL,NULL,24),(21,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,25),(22,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,26),(23,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,27),(24,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,28),(25,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,29),(26,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,30),(27,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,31),(28,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,32),(29,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,33),(30,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,34),(31,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,35),(32,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,36),(33,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,37),(34,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,38),(35,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,39),(36,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,40),(37,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,41),(38,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,42),(39,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,43),(40,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,44),(41,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,45),(42,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,46),(43,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,47),(44,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,48),(45,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,49),(46,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,50),(47,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,51),(48,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,52),(49,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,53),(50,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,54),(51,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,55),(52,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,56),(53,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,100.00,NULL,'ZZ',100.00,100.00,57),(54,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,58),(55,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,59),(56,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,60),(57,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,61),(58,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,62),(59,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,63),(60,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,64),(61,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,65),(62,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,66),(63,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,67),(64,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,68),(65,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,69),(66,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,70),(67,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,71),(68,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,72),(69,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,73),(70,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,74),(71,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,75),(72,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,76),(73,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,77),(74,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,78),(75,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,79),(76,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,80),(77,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,81),(78,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,82),(79,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,83),(80,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,84),(81,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,85),(82,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,86),(83,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,87),(84,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,88),(85,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,89),(86,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,90),(87,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,91),(88,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,92),(89,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,93),(90,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,94),(91,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,95),(92,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,96),(93,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,97),(94,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,98),(95,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,99),(96,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,100),(97,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,101),(98,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,102),(99,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,103),(100,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,104),(101,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,105),(102,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,106),(103,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,107),(104,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,108),(105,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,109),(106,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,110),(107,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,111),(108,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,112),(109,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,113),(110,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,114),(111,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,115),(112,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,116),(113,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,117),(114,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,118),(115,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,119),(116,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',118.00,118.00,120),(117,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',100.00,100.00,121),(118,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',100.00,100.00,122),(119,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',100.00,100.00,123),(120,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',100.00,100.00,124),(121,'10',18.00,1.00,NULL,'ASDSA',0.00,NULL,118.00,NULL,'ZZ',100.00,100.00,125);
/*!40000 ALTER TABLE `factura_electronica_det` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura_electronica_tax`
--

DROP TABLE IF EXISTS `factura_electronica_tax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factura_electronica_tax` (
  `factura_electronica_tax_id` int(11) NOT NULL AUTO_INCREMENT,
  `factura_electronica_id` int(11) NOT NULL,
  `tax_id` int(11) DEFAULT NULL,
  `baseamt` decimal(12,4) DEFAULT NULL,
  `taxtotal` decimal(12,4) DEFAULT NULL,
  PRIMARY KEY (`factura_electronica_tax_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura_electronica_tax`
--

LOCK TABLES `factura_electronica_tax` WRITE;
/*!40000 ALTER TABLE `factura_electronica_tax` DISABLE KEYS */;
INSERT INTO `factura_electronica_tax` VALUES (1,107,1000,100.0000,18.0000),(2,108,1000,100.0000,18.0000),(3,109,1000,100.0000,18.0000),(4,110,1000,100.0000,18.0000),(5,111,1000,100.0000,18.0000),(6,112,1000,100.0000,18.0000),(7,113,1000,100.0000,18.0000),(8,114,1000,100.0000,18.0000),(9,115,1000,100.0000,18.0000),(10,116,1000,100.0000,18.0000),(11,117,1000,100.0000,18.0000),(12,118,1000,100.0000,18.0000),(13,119,1000,100.0000,18.0000),(14,120,1000,100.0000,18.0000),(15,121,1000,100.0000,18.0000),(16,122,1000,100.0000,18.0000),(17,123,1000,100.0000,18.0000),(18,124,1000,100.0000,18.0000),(19,125,1000,100.0000,18.0000);
/*!40000 ALTER TABLE `factura_electronica_tax` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parametro`
--

DROP TABLE IF EXISTS `parametro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parametro` (
  `id_para` varchar(255) NOT NULL,
  `cod_para` varchar(255) DEFAULT NULL,
  `ind_esta_para` varchar(255) DEFAULT NULL,
  `nom_para` varchar(255) DEFAULT NULL,
  `tip_para` varchar(255) DEFAULT NULL,
  `val_para` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_para`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametro`
--

LOCK TABLES `parametro` WRITE;
/*!40000 ALTER TABLE `parametro` DISABLE KEYS */;
/*!40000 ALTER TABLE `parametro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `respuesta_sunat`
--

DROP TABLE IF EXISTS `respuesta_sunat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `respuesta_sunat` (
  `m_id` int(11) NOT NULL,
  `fecha_hora` datetime NOT NULL,
  `nombre_archivo` varchar(300) NOT NULL,
  `codigo` varchar(20) NOT NULL,
  `descripcion` varchar(2000) NOT NULL,
  `estado` char(1) DEFAULT '1',
  `idresumen` int(11) DEFAULT NULL,
  `ticketRespuestaResumen` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respuesta_sunat`
--

LOCK TABLES `respuesta_sunat` WRITE;
/*!40000 ALTER TABLE `respuesta_sunat` DISABLE KEYS */;
INSERT INTO `respuesta_sunat` VALUES (60,'2020-04-13 18:56:38','20534999616-03-BB01-103','0','La Boleta numero BB01-103, ha sido aceptada','1',0,NULL),(68,'2020-04-13 22:21:06','20534999616-03-BB01-111','0','La Boleta numero BB01-111, ha sido aceptada','1',0,NULL),(69,'2020-04-13 22:32:36','20534999616-03-BB01-112','0','La Boleta numero BB01-112, ha sido aceptada','1',0,NULL),(70,'2020-04-14 12:38:04','20534999616-03-BB01-113','0','La Boleta numero BB01-113, ha sido aceptada','1',0,NULL),(71,'2020-04-15 15:40:02','20534999616-03-BB01-115','0','La Boleta numero BB01-115, ha sido aceptada','1',0,NULL),(73,'2020-04-15 15:55:10','20534999616-03-BB01-117','0','La Boleta numero BB01-117, ha sido aceptada','1',0,NULL),(74,'2020-04-15 15:57:57','20534999616-03-BB01-118','0','La Boleta numero BB01-118, ha sido aceptada','1',0,NULL),(77,'2020-04-15 20:44:45','20534999616-03-BB01-121','0','La Boleta numero BB01-121, ha sido aceptada','1',0,NULL),(79,'2020-04-15 20:57:01','20534999616-03-BB01-123','0','La Boleta numero BB01-123, ha sido aceptada','1',0,NULL),(86,'2020-04-15 21:31:40','20534999616-03-BB01-130','0','La Boleta numero BB01-130, ha sido aceptada','1',0,NULL),(89,'2020-04-15 22:45:34','20534999616-03-BB01-133','0','La Boleta numero BB01-133, ha sido aceptada','1',0,NULL),(95,'2020-04-16 19:37:46','20534999616-01-FF01-6','0','La Factura numero FF01-6, ha sido aceptada','1',0,NULL),(99,'2020-04-16 22:45:40','20534999616-01-FF01-10','0','La Factura numero FF01-10, ha sido aceptada','1',0,NULL),(100,'2020-04-16 22:47:04','20534999616-01-FF01-11','0','La Factura numero FF01-11, ha sido aceptada','1',0,NULL),(101,'2020-04-16 23:15:19','20534999616-01-FF01-12','0','La Factura numero FF01-12, ha sido aceptada','1',0,NULL),(102,'2020-04-17 22:22:24','20534999616-01-FF01-13','0','La Factura numero FF01-13, ha sido aceptada','1',0,NULL),(103,'2020-04-19 13:10:19','20534999616-01-FF01-14','0','La Factura numero FF01-14, ha sido aceptada','1',0,NULL);
/*!40000 ALTER TABLE `respuesta_sunat` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-28 23:22:05

-- agregado 02/03/2022

alter table factura_electronica
add tipo_moneda_monto_neto_pendiente varchar(3);

alter table factura_electronica
add monto_neto_pendiente decimal(18,3);

alter table factura_electronica
add porcentaje_igv decimal(5,2);

create table factura_electronica_cuota
(
	factura_electronica_cuota_id int auto_increment primary key,
    factura_electronica_id int,
    monto_cuota_pago decimal(18,3) ,
    tipo_moneda_cuota_pago varchar(3),
    fecha_cuota_pago date
)engine=innodb;

ALTER TABLE `efacturador`.`empresa` 
CHANGE COLUMN `token` `token` VARCHAR(900) NULL DEFAULT NULL ;

ALTER TABLE `efacturador`.`factura_electronica` 
drop column fechaEnvio;

ALTER TABLE `efacturador`.`empresa` 
add nombre_comercial varchar(300);

ALTER TABLE `efacturador`.`empresa` 
add direccion varchar(300);

ALTER TABLE `efacturador`.`factura_electronica` 
add ticket_operacion bigint;

ALTER TABLE `efacturador`.`factura_electronica_Det` 
add codigo varchar(100);

select * from empresa
select * from EFACTURADOR.factura_electronica_det
update EFACTURADOR.factura_electronica set ind_situacion = '06'

create table resumen_baja


create table seg_usuario(
	seg_usuario_id int auto_increment primary key,
    empresa_id int,
    nombre varchar(20) not null,
    clave varchar(400) not null,
    correo varchar(200),
    fec_expira_clave date,
    estado char(1)
)engine=innodb;

create table seg_rol(
	seg_rol_id int auto_increment primary key,
    nombre varchar(20) not null,
    estado char(1) default '1'
)engine=innodb;

create table seg_usuario_rol(
	seg_usuario_rol_id int auto_increment primary key,
    seg_usuario_id int,
    seg_rol_id int,
    estado char(1) default '1'
)engine=innodb;

create table seg_opcion(
	seg_opcion_id int auto_increment primary key,
    nombre varchar(20) not null,
    url varchar(200) not null,
    seg_opcion_parent int,
    estado char(1) default '1'
)engine=innodb;

create table seg_opcion_rol(
	seg_opcion_rol_id int,
    seg_opcion_id int,
    seg_rol_id int,
    estado char(1) default '1'
)engine=innodb;

create table seg_action(
	seg_action_id int,
    nombre int,
    estado char(1) default '1'
)engine=innodb;

create table seg_action_rol(
	seg_action_rol_id int,
    seg_rol_id int,
    seg_action_id int,
    estado char(1) default '1'
)engine=innodb;

create table seg_acceso
(
	id_acceso int auto_increment primary key,
    id_usuario int,
    fecha_acceso datetime,
    ip varchar(90),
    navegador varchar(500),
    descripcion_sesion varchar(200),
    fecha_registro datetime,
    mac varchar(100)
)engine=innodb;

ALTER TABLE `efacturador`.`factura_electronica` 
add ticket_sunat varchar(100);

ALTER TABLE factura_electronica
CHANGE COLUMN observacion_envio observacion_envio VARCHAR(900) NULL DEFAULT NULL ;
select * from empresa
select * from factura_electronica