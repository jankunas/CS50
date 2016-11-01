package lt.jankunas.shop.utils;

import java.util.regex.Pattern;

public final class RegexChecker {
    
    public static boolean isWord(String name){
        String wordRegex = "[A-Za-z]{1,8}";
        Pattern checkRegex = Pattern.compile(wordRegex);
        if(checkRegex.matcher(name).matches())
            return true;
        else
            return false;
    }
    
    public static boolean isFullNumber(String quantity){
        String fullNumberRegex = "^[1-9]([0-9]{0,3}$)";
        Pattern checkRegex = Pattern.compile(fullNumberRegex);
        if(checkRegex.matcher(quantity).matches())
            return true;
        else
            return false;
    }
    
    public static boolean isDecimalNumber(String price){
        String decimalNumber = "\\d{1,4}\\.\\d{1,2}";
        Pattern checkRegex = Pattern.compile(decimalNumber);
        if(checkRegex.matcher(price).matches())
            return true;
        else
            return false;
    }
}
