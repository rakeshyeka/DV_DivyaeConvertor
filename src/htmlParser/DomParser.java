package htmlParser;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.css.CSSStyleSheet;

import com.steadystate.css.parser.CSSOMParser;
import com.steadystate.css.parser.SACParserCSS3;

public class DomParser {
	private static final String BOLD = "Bold";
	private static final String BASE64_PATTERN = "(?!base64)[^,]+(?=\\) format\\(\"truetype\"\\))";
	private static final String FONT_FAMILY_PATTERN = "(?!font-family: )ff[0-9a-fA-F]+(?=;)";
	private static final String FONT_FACE = "@font-face";
	private Document dom;
	private List<Page> pages;
	private Map<String, String> hindiFontClasses = new HashMap<String, String>();
	private List<String> boldFontClasses = new ArrayList<String>();

	public DomParser(String file) {

		try {
			File input = new File(file);
			this.dom = Jsoup.parse(input, "UTF-8", "");

			getFontClasses(this.dom, hindiFontClasses, boldFontClasses);

			pages = Page.buildPageFromNodeList(this.dom.getElementById(Constants.PAGE_CONTAINER_TAG),
					hindiFontClasses, boldFontClasses);
		} catch (IOException ioe) {

		}

	}

	public String getHindiPages() {
		String text = "";
		for (int i = 0; i < pages.size(); i++) {
			if (pages.get(i).isHindi()) {
				text = String.format(Constants.NEWLINE_JOIN_TEMPLATE, text, Constants.PAGE_DECORATION_BOUNDARY);
				text = String.format(Constants.NEWLINE_JOIN_TEMPLATE, text, pages.get(i).getText());
				text = String.format(Constants.NEWLINE_JOIN_TEMPLATE, text, "");
			}
		}
		return text;
	}

	public String getEnglishPages() {
		String text = "";
		for (int i = 0; i < pages.size(); i++) {
			if (pages.get(i).isEnglish()) {
				text = String.format(Constants.NEWLINE_JOIN_TEMPLATE, text, Constants.PAGE_DECORATION_BOUNDARY);
				text = String.format(Constants.NEWLINE_JOIN_TEMPLATE, text, pages.get(i).getText());
				text = String.format(Constants.NEWLINE_JOIN_TEMPLATE, text, "");
			}
		}
		return text;
	}

	public String getPages() {
		String text = "";
		for (int i = 0; i < pages.size(); i++) {
			text = String.format(Constants.NEWLINE_JOIN_TEMPLATE, text, Constants.PAGE_DECORATION_BOUNDARY);
			text = String.format(Constants.NEWLINE_JOIN_TEMPLATE, text, pages.get(i).getText());
			text = String.format(Constants.NEWLINE_JOIN_TEMPLATE, text, "");
		}
		return text;
	}

	private static Map<String, String> getFontClasses(Document dom, Map<String, String> hindiFontClasses,
			List<String> boldFontClasses) {
		for (Element style : dom.getElementsByTag(Constants.STYLE_TAG)) {
			String data = style.data();
			if (data.contains(FONT_FACE)) {
				InputSource source = new InputSource(new StringReader(data));
				CSSOMParser parser = new CSSOMParser(new SACParserCSS3());
				CSSStyleSheet sheet;
				try {
					sheet = parser.parseStyleSheet(source, null, null);
					CSSRuleList cssRules = sheet.getCssRules();
					for (int i = 0; i < cssRules.getLength(); i++) {
						CSSRule cssRule = cssRules.item(i);
						String cssText = cssRule.getCssText();
						String fontFamily = Util.substringRegex(cssText, FONT_FAMILY_PATTERN);
						String fontData;
						if (fontFamily != null) {
							String fontDataEncoded = Util.substringRegex(cssText, BASE64_PATTERN);
							if (fontDataEncoded != null) {
								fontData = Util.decode(fontDataEncoded);
								String convertorClass = Config.getHindiConvertorClass(fontData);
								if (convertorClass != null
										&& !hindiFontClasses.containsKey(fontFamily)) {
									hindiFontClasses.put(fontFamily, convertorClass);
								}
								if (fontData.contains(BOLD) && !boldFontClasses.contains(fontFamily)) {
									boldFontClasses.add(fontFamily);
								}
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return hindiFontClasses;
	}
}
