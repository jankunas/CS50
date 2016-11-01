package lt.jankunas.shop.utils;

public class InputValidator {
	
	public static boolean isSelectionValid(String input){
		if(RegexChecker.isFullNumber(input))
			return true;
		else
			return false;
	}
	
	public static boolean isNameQuantityPriceValid(String name, String quantity, String price){
		if(RegexChecker.isWord(name) && RegexChecker.isFullNumber(quantity) && (RegexChecker.isDecimalNumber(price) || RegexChecker.isFullNumber(price)))
			return true;
		else
			return false;
	}
	
	public static boolean isNameValid(String name){
		if(RegexChecker.isWord(name))
			return true;
		else
			return false;
	}
	
	public static boolean isNameQuantityValid(String name, String quantity){
		if(RegexChecker.isWord(name) && RegexChecker.isFullNumber(quantity))
			return true;
		else 
			return false;
	}
	
	public static boolean isQuantityValid(String quantity){
        if(RegexChecker.isFullNumber(quantity))
            return true;
        else 
            return false;
    }
	
	
}
