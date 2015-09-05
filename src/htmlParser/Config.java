package htmlParser;

import java.util.HashMap;
import java.util.Map;

public class Config {
	private static Map<String, String> hindiFontClasses;
	static
	{
		hindiFontClasses = new HashMap<String, String>();
		hindiFontClasses.put("DV_Divyae", "DV_To_Unicode");
		hindiFontClasses.put("UntitledTTF", "DV_To_Unicode");
		hindiFontClasses.put("Walkman-chanakya", "Walkman_chanakya");
	}
	private boolean isMixedLanguage = false;
	private String inputFolder;
	private String outputFolder;

	public Config() {
	}

	public Config(boolean isMixedLanguage) {
		this.isMixedLanguage = isMixedLanguage;
	}

	public boolean isMixedLanguage() {
		return this.isMixedLanguage;
	}

	public static String getHindiConvertorClass(String fontData) {
		for (String fontKey : hindiFontClasses.keySet()) {
			if (fontData.contains(fontKey)) {
				return getFontClass(fontKey);
			}
		}
		return null;
	}

	public String getInputFolder() {
		return inputFolder;
	}

	public void setInputFolder(String inputFolder) {
		this.inputFolder = inputFolder;
	}

	public String getOutputFolder() {
		return outputFolder;
	}

	public void setOutputFolder(String outputFolder) {
		this.outputFolder = outputFolder;
	}

	private static String getFontClass(String font) {
		return hindiFontClasses.get(font);
	}

}
