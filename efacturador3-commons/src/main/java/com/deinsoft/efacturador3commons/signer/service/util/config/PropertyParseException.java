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
/*    */ public class PropertyParseException
/*    */   extends InvalidPropertyException
/*    */ {
/*    */   private static final long serialVersionUID = 7679881550223970073L;
/*    */   private String parseMessage;
/*    */   /*    */   
/*    */   public PropertyParseException(String parseMessage) {
/* 24 */     if (parseMessage.endsWith(".")) {
/* 25 */       parseMessage = parseMessage.substring(0, parseMessage.length() - 1);
/*    */     }
/* 27 */     this.parseMessage = parseMessage;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 31 */     return getClass().getName() + ": couldn't parse " + this.propertyname + ", reason: " + this.parseMessage + ". Value was: '" + this.value + "', expected type: " + this.expectedtype;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValue(String value) {
/* 36 */     this.value = value;
/*    */   }
/*    */   
/*    */   public void setExpectedtype(String expectedtype) {
/* 40 */     this.expectedtype = expectedtype;
/*    */   }
/*    */ }


/* Location:              D:\Proyectos\facturacion\EFacturador\java\SFS_v1.3.2\facturadorApp-1.3.2.jar!\document\signer\servic\\util\config\PropertyParseException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */