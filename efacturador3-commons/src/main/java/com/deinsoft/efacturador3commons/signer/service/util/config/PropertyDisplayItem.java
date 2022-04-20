/*     */ package com.deinsoft.efacturador3commons.signer.service.util.config;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PropertyDisplayItem
/*     */   implements Comparable<Object>
/*     */ {
/*     */   private final String methodName;
/*     */   private final String originalValue;
/*     */   private final String parseValue;
/*     */   private final String propertyName;
/*     */   /*     */   
/*     */   public PropertyDisplayItem(String methodName, String propertyName, String originalValue, String parseValue) {
/*  31 */     if (methodName == null) {
/*  32 */       throw new NullPointerException("methodName cannot be null here.");
/*     */     }
/*     */     
/*  35 */     this.methodName = methodName;
/*  36 */     this.propertyName = propertyName;
/*  37 */     this.originalValue = originalValue;
/*  38 */     this.parseValue = parseValue;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  42 */     String result = this.methodName + " = " + this.parseValue;
/*     */     
/*  44 */     if (this.propertyName == null) {
/*  45 */       result = result + " (hard-coded)";
/*     */     } else {
/*  47 */       result = result + ", from property " + this.propertyName + " = " + ((this.originalValue == null) ? "<null>" : ("'" + this.originalValue + "'"));
/*     */     } 
/*     */ 
/*     */     
/*  51 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMethodName() {
/*  60 */     return this.methodName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOriginalValue() {
/*  69 */     return this.originalValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getParseValue() {
/*  78 */     return this.parseValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/*  87 */     return this.propertyName;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  91 */     return this.methodName.hashCode();
/*     */   }
/*     */   /*     */   
/*     */   public boolean equals(Object o) {
/*  95 */     if (o == this) {
/*  96 */       return true;
/*     */     }
/*     */     
/*  99 */     if (o == null) {
/* 100 */       return false;
/*     */     }
/*     */     /*     */     
/* 103 */     if (o instanceof PropertyDisplayItem) {
/* 104 */       PropertyDisplayItem other = (PropertyDisplayItem)o;
/*     */       
/* 106 */       return (other.methodName.equals(this.methodName) && areEquals(this.propertyName, other.propertyName) && 
/* 107 */         areEquals(this.originalValue, other.originalValue) && areEquals(this.parseValue, other.parseValue));
/*     */     } 
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */   /*     */ 
/*     */   
/*     */   public int compareTo(Object o) {
/* 114 */     PropertyDisplayItem rhs = (PropertyDisplayItem)o;
/* 115 */     int result = compareStrings(this.methodName, rhs.methodName);
/* 116 */     if (result != 0) return result; 
/* 117 */     result = compareStrings(this.propertyName, rhs.propertyName);
/* 118 */     if (result != 0) return result; 
/* 119 */     result = compareStrings(this.originalValue, rhs.originalValue);
/* 120 */     if (result != 0) return result; 
/* 121 */     result = compareStrings(this.parseValue, rhs.parseValue);
/* 122 */     return result;
/*     */   }
/*     */   
/*     */   private boolean areEquals(String string1, String string2) {
/* 126 */     if (string1 == null) {
/* 127 */       if (string2 == null) {
/* 128 */         return true;
/*     */       }
/* 130 */       return false;
/*     */     } 
/*     */     
/* 133 */     return string1.equals(string2);
/*     */   }
/*     */   
/*     */   private int compareStrings(String string1, String string2) {
/* 137 */     if (string1 == string2) return 0; 
/* 138 */     if (string1 == null) return -1; 
/* 139 */     if (string2 == null) return 1; 
/* 140 */     return string1.compareTo(string2);
/*     */   }
/*     */ }


/* Location:              D:\Proyectos\facturacion\EFacturador\java\SFS_v1.3.2\facturadorApp-1.3.2.jar!\document\signer\servic\\util\config\PropertyDisplayItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */