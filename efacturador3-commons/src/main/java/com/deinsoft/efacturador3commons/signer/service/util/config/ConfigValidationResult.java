package com.deinsoft.efacturador3commons.signer.service.util.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConfigValidationResult
{
  private String sourceDescription;
  private final List<String> errors = new ArrayList<>();



  
  private Set<String> unusedProperties = new HashSet<>();



  
  private Set<PropertyDisplayItem> usedProperties = new HashSet<>();







  
  public void addError(String methodName, Throwable e) {
    if (e instanceof java.lang.reflect.InvocationTargetException && e.getCause() != null) {
      e = e.getCause();
    }
    
    if (e instanceof MissingPropertyException) {
      MissingPropertyException mpe = (MissingPropertyException)e;
      String missingPropertyDescription = "Missing property " + mpe.getPropertyname();
      
      if (mpe.getExpectedType() != null)
      {
        missingPropertyDescription = missingPropertyDescription + " which must be " + getAorAn(mpe.getExpectedType()) + " " + mpe.getExpectedType() + " value";
      }
      
      missingPropertyDescription = missingPropertyDescription + ".";
      this.errors.add(missingPropertyDescription);
      
      return;
    } 
    
    if (e instanceof InvalidPropertyException) {
      InvalidPropertyException ipe = (InvalidPropertyException)e;
      this.errors.add("Property " + ipe.getPropertyname() + " had value '" + ipe.getValue() + "' which is not " + 
          getAorAn(ipe.getExpectedtype()) + " " + ipe.getExpectedtype() + " value.");
      
      return;
    } 
    
    this.errors.add("Couldn't call " + methodName + ": " + e);
  }
  
  private String getAorAn(String word) {
    if (word == null) return ""; 
    if (word.startsWith("i") || word.startsWith("e") || word.startsWith("o") || word.startsWith("a")) {
      return "an";
    }
    return "a";
  }







  
  public boolean thereAreErrors() {
    return !this.errors.isEmpty();
  }







  
  public boolean thereAreUnusedProperties() {
    return !this.unusedProperties.isEmpty();
  }





  
  public List<String> getErrors() {
    return Collections.unmodifiableList(this.errors);
  }







  
  public Set<String> getUnusedProperties() {
    return Collections.unmodifiableSet(this.unusedProperties);
  }
  
  public String toString() {
    String toReturn = "";
    if (thereAreErrors()) {
      toReturn = toReturn + "Configuration errors: " + this.errors + ".";
    } else {
      
      toReturn = toReturn + "No configuration errors. Unused properties: " + this.unusedProperties.size() + ", used properties: " + this.usedProperties.size() + ".";
    } 
    if (this.sourceDescription != null) {
      toReturn = toReturn + " Source description: " + this.sourceDescription + ".";
    }
    return toReturn;
  }







  
  public void setUnusedProperties(Set<String> set) {
    if (set == null) {
      throw new NullPointerException("set cannot be null here.");
    }
    this.unusedProperties = set;
  }








  
  public void setUsedProperties(Set<PropertyDisplayItem> usedProperties) {
    if (usedProperties == null) {
      throw new NullPointerException("usedProperties cannot be null here.");
    }
    this.usedProperties = usedProperties;
  }






  
  public Set<PropertyDisplayItem> getUsedProperties() {
    return this.usedProperties;
  }





  
  public String getSourceDescription() {
    return this.sourceDescription;
  }





  
  public void setSourceDescription(String sourceDescription) {
    this.sourceDescription = sourceDescription;
  }
}