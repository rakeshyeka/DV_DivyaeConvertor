package fontConverter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	
	public static String jsReplace(String text, String targetText, String replaceText){
		Pattern p = Pattern.compile(targetText);
		Matcher m = p.matcher(text);
		if (m.find()) {
			text = m.replaceFirst(replaceText);
		}
		return text;
	}
}
