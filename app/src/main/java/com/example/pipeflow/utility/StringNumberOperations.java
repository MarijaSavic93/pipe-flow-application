package com.example.pipeflow.utility;

public class StringNumberOperations {

    public boolean isEmpty(String string){ return string == null || string.trim().isEmpty(); }

    public boolean isDot(String string){ return string.equals("."); }
    public boolean startsWithDot(String string){ return string.length() > 1 && string.charAt(0) == '.'; }
    public boolean endsWithDot(String string){ return string.length() > 1 && string.charAt(string.length() - 1) == '.'; }
    public boolean hasLeadingZeros(String string){ return string.length() > 1 && string.charAt(0) == '0' && string.charAt(1) != '.'; }
    public String addZeroStart(String string){ return "0" + string; }
    public String addZeroEnd(String string){ return string + "0"; }
    public String removeLeadingZeros(String string){ return string.replaceFirst("^0+(?!$)", ""); }

    public String formatNumber(String string){
        if(!isEmpty(string)){
            String trimmedString = string.trim();

            if(isDot(trimmedString)){ return "0"; }
            if(startsWithDot(trimmedString)){ return addZeroStart(trimmedString); }
            if(endsWithDot(trimmedString)){ return addZeroEnd(trimmedString); }
            if(hasLeadingZeros(trimmedString)){ return removeLeadingZeros(trimmedString); }

            return trimmedString;
        }
        return "";
    }

    public String setDecimalPlaces(double value, int numberOfDecimalPlaces){
        if(numberOfDecimalPlaces < 0){ return Double.toString(value); }

        String format = "%." + numberOfDecimalPlaces + "f";

        return String.format(format, value);
    }
    public double returnDoubleFromText(String string){
        String formattedString = formatNumber(string);
        return isEmpty(formattedString) ? 0 : Double.parseDouble(formattedString);
    }
}
