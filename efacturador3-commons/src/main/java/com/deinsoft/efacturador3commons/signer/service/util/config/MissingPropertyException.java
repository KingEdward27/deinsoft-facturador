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
/*    */ public class MissingPropertyException
/*    */   extends ConfigurationException
/*    */ {
/*    */   private static final long serialVersionUID = -3158177675161179920L;
/*    */   private final String expectedtype;
/*    */   private final String propertyname;
/*    */   /*    */   
/*    */   public MissingPropertyException(String propertyname) {
/* 22 */     this.propertyname = propertyname;
/* 23 */     this.expectedtype = null;
/*    */   }
/*    */   /*    */   
/*    */   public MissingPropertyException(String propertyname, String expectedtype) {
/* 27 */     this.propertyname = propertyname;
/* 28 */     this.expectedtype = expectedtype;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getPropertyname() {
/* 37 */     return this.propertyname;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getExpectedType() {
/* 46 */     return this.expectedtype;
/*    */   }
/*    */   public String toString() {
/* 49 */     return getClass().getName() + ": '" + this.propertyname + "'";
/*    */   }
/*    */ }


/* Location:              D:\Proyectos\facturacion\EFacturador\java\SFS_v1.3.2\facturadorApp-1.3.2.jar!\document\signer\servic\\util\config\MissingPropertyException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */