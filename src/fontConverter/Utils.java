package fontConverter;


public class Utils {

	public static String jsReplace(String text, String targetText, String replaceText) {
		text = text.replaceAll(targetText, replaceText);
		return text;
	}
}
