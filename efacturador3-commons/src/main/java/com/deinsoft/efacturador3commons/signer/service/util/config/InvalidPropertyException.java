/*    */ package com.deinsoft.efacturador3commons.signer.service.util.config;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InvalidPropertyException
/*    */   extends ConfigurationException
/*    */ {
/*    */   protected String expectedtype;
/*    */   protected String propertyname;
/*    */   protected String value;
/*    */   /*    */   
/*    */   public InvalidPropertyException() {}
/*    */   /*    */   
/*    */   public InvalidPropertyException(String propertyname) {
/* 39 */     this.propertyname = propertyname;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   /*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InvalidPropertyException(String propertyname, String value, String expectedType) {
/* 50 */     this.propertyname = propertyname;
/* 51 */     this.value = value;
/* 52 */     this.expectedtype = expectedType;
/*    */   }
/*    */   
/*    */   public String getPropertyname() {
/* 56 */     return this.propertyname;
/*    */   }
/*    */   
/*    */   public String getValue() {
/* 60 */     return this.value;
/*    */   }
/*    */   
/*    */   public String getExpectedtype() {
/* 64 */     return this.expectedtype;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 68 */     return getClass().getName() + ": " + this.propertyname + ", " + this.value + ", " + this.expectedtype;
/*    */   }
/*    */   
/*    */   public void setValue(String value) {
/* 72 */     this.value = value;
/*    */   }
/*    */   
/*    */   public void setExpectedtype(String expectedtype) {
/* 76 */     this.expectedtype = expectedtype;
/*    */   }
/*    */   
/*    */   public void setPropertyname(String propertyname) {
/* 80 */     this.propertyname = propertyname;
/*    */   }
/*    */ }


/* Location:              D:\Proyectos\facturacion\EFacturador\java\SFS_v1.3.2\facturadorApp-1.3.2.jar!\document\signer\servic\\util\config\InvalidPropertyException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */