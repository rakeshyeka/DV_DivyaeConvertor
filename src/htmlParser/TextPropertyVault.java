package htmlParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextPropertyVault {
	private static TextPropertyVault vault = new TextPropertyVault();
	private static Map<String, String> hindiFontClasses;
	private static List<String> boldFontClasses;
	private static Map<String, Boolean> colouredClasses;

	private TextPropertyVault() {
		TextPropertyVault.setHindiFontClasses(new HashMap<String, String>());
		TextPropertyVault.setBoldFontClasses(new ArrayList<String>());
		TextPropertyVault.setColouredClasses(new HashMap<String, Boolean>());
	}

	public static TextPropertyVault getVault() {
		return vault;
	}

	public static void setVault(TextPropertyVault vault) {
		TextPropertyVault.vault = vault;
	}

	public Map<String, String> getHindiFontClasses() {
		return hindiFontClasses;
	}

	public static void setHindiFontClasses(Map<String, String> hindiFontClasses) {
		TextPropertyVault.hindiFontClasses = hindiFontClasses;
	}

	public List<String> getBoldFontClasses() {
		return boldFontClasses;
	}

	public static void setBoldFontClasses(List<String> boldFontClasses) {
		TextPropertyVault.boldFontClasses = boldFontClasses;
	}

	public Map<String, Boolean> getColouredClasses() {
		return colouredClasses;
	}

	public static void setColouredClasses(Map<String, Boolean> colouredClasses) {
		TextPropertyVault.colouredClasses = colouredClasses;
	}

}
