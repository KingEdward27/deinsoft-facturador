package com.deinsoft.efacturador3commons.signer.service.util.config;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
















class DurationParser
{
  public long getDurationInMs(String key, String value) throws ConfigurationException {
    if (value == null) {
      throw new NullPointerException("value cannot be null here.");
    }



    
    value = value.replaceAll(",", "");
    value = value.replaceAll("and", "");
    
    if (value.endsWith(".")) {
      value = value.substring(0, value.length() - 1);
    }



    
    try {
      List<Object> tokens = parseTokens(value);



      
      List<Object> realValues = convertToRealNumbers(tokens);



      
      double intermediateResult = calculateDuration(realValues);
      
      return (long)intermediateResult;
    }
    catch (InvalidPropertyException e) {
      
      e.setExpectedtype("duration");
      e.setValue(value);
      e.setPropertyname(key);
      throw e;
    } 
  }
  
  private List<Object> parseTokens(String stringValue) {
    StringTokenizer st = new StringTokenizer(stringValue);
    List<Object> tokens = new ArrayList();
    
    while (st.hasMoreTokens()) {
      String currentToken = st.nextToken();
      currentToken = currentToken.trim();

      
      ParsePosition pp = new ParsePosition(0);
      
      Number number = NumberFormat.getInstance(Locale.US).parse(currentToken, pp);
      String leftOver = currentToken.substring(pp.getIndex());
      
      if (number != null) {
        tokens.add(number);
      }
      
      if (leftOver != null && !leftOver.trim().equals("")) {
        tokens.add(leftOver);
      }
    } 
    
    return tokens;
  }








  
  private List<Object> convertToRealNumbers(List<Object> tokens) {
    List<Object> realValues = new ArrayList();
    
    for (Iterator<Object> iter = tokens.iterator(); iter.hasNext(); ) {
      Object element = iter.next();
      
      if (element instanceof String) {
        Number currentNumberValue = getNumberValue((String)element);
        
        if (currentNumberValue != null) {
          realValues.add(currentNumberValue);
          continue;
        } 
        realValues.add(element);
        
        continue;
      } 
      realValues.add(element);
    } 

    
    return realValues;
  }
  
  private double calculateDuration(List<Object> realValues) {
    boolean expectingNumber = true;
    double finalResult = 0.0D;
    
    Number currentNumberValue = null;
    
    if (realValues.size() == 0) {
      throw new PropertyParseException("Nothing to parse.");
    }
    
    for (Iterator<Object> iter = realValues.iterator(); iter.hasNext(); ) {
      Object element = iter.next();
      
      if (element instanceof Number) {
        
        if (!expectingNumber) {
          throw new PropertyParseException("Unexpected token '" + element + "'. Didn't expect a number.");
        }
        
        currentNumberValue = (Number)element;
        
        expectingNumber = false;
        
        continue;
      } 
      if (expectingNumber && 
        currentNumberValue == null) {
        throw new PropertyParseException("Unexpected token '" + element + "'. Expected a number.");
      }

      
      if (currentNumberValue instanceof Double && (
        (Double)currentNumberValue).isInfinite()) {
        throw new PropertyParseException("Overflow");
      }

      
      double currentDoubleValue = currentNumberValue.doubleValue();
      
      if (currentDoubleValue < 0.0D) {
        throw new PropertyParseException("Negative number '" + currentDoubleValue + "' not allowed.");
      }
      
      long currentLongValue = currentNumberValue.longValue();
      
      if (currentLongValue < currentDoubleValue - 1.0D) {
        throw new PropertyParseException("Overflow");
      }
      
      long multiplier = getMultiplierForTimeString((String)element, (currentLongValue == 1L));
      double intermediateResult = multiplier * currentDoubleValue;
      
      finalResult += intermediateResult;
      
      long finalResultTest = (long)finalResult;
      
      if (finalResultTest < finalResult - 1.0D) {
        throw new PropertyParseException("Overflow");
      }

      
      expectingNumber = true;
      currentNumberValue = null;
    } 

    
    if (!expectingNumber)
    {
      throw new PropertyParseException("Expected a unit but reached end of input.");
    }
    
    return finalResult;
  }
  
  private Number getNumberValue(String string) {
    string = string.toLowerCase();
    
    if (string.equals("zero")) {
      return Integer.valueOf("0");
    }
    
    if (string.equals("a")) {
      return Integer.valueOf("1");
    }
    
    if (string.equals("one")) {
      return Integer.valueOf("1");
    }
    
    if (string.equals("two")) {
      return Integer.valueOf("2");
    }
    
    if (string.equals("three")) {
      return Integer.valueOf("3");
    }
    
    if (string.equals("four")) {
      return Integer.valueOf("4");
    }
    
    if (string.equals("five")) {
      return Integer.valueOf("5");
    }
    
    if (string.equals("six")) {
      return Integer.valueOf("6");
    }
    
    if (string.equals("seven")) {
      return Integer.valueOf("7");
    }
    
    if (string.equals("eight")) {
      return Integer.valueOf("8");
    }
    
    if (string.equals("nine")) {
      return Integer.valueOf("9");
    }
    
    if (string.equals("ten")) {
      return Integer.valueOf("10");
    }
    
    return null;
  }
  
  private long getMultiplierForTimeString(String string, boolean singleWord) {
    string = string.toLowerCase();
    
    boolean addS = !singleWord;
    
    if (string.equals("week" + (addS ? "s" : ""))) {
      return 604800000L;
    }
    
    if (string.equals("day" + (addS ? "s" : ""))) {
      return 86400000L;
    }
    
    if (string.equals("hour" + (addS ? "s" : ""))) {
      return 3600000L;
    }
    
    if (string.equals("minute" + (addS ? "s" : ""))) {
      return 60000L;
    }
    
    if (string.equals("second" + (addS ? "s" : ""))) {
      return 1000L;
    }
    
    if (string.equals("ms")) {
      return 1L;
    }
    
    if (string.equals("s")) {
      return 1000L;
    }
    
    throw new PropertyParseException("Unknown unit: '" + string + "'");
  }
}