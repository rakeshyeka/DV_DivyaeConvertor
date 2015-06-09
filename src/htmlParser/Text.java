package htmlParser;

import java.util.List;
import java.util.logging.Level;

import org.jsoup.nodes.Element;

import com.google.common.base.CharMatcher;

import fontConverter.DV_To_Unicode;

public class Text {
	private static final String CONVERSION_ERROR = "Error while converting text : %s";
	private boolean isBold;
	private boolean isHindi;
	private boolean isFooterBoundary;

	private String data = "";

	private String font;

	public Text(Element textEl, List<String> hindiFontClasses, List<String> boldFontClasses, String font) {

		updateAttributes(textEl, hindiFontClasses, boldFontClasses, font);
		String ownText = textEl.ownText();
		if (!Util.isNullOrEmptyOrWhiteSpace(ownText)) {
			ownText = processNodeValue(ownText);
			if (this.isHindi && textEl.tagName().equals("div")) {
				ownText = convertToUnicode(ownText);
			}
			this.addToData(ownText);
		}

		String childText = "";
		for (Element child : textEl.children()) {
			Text childNode = new Text(child, hindiFontClasses, boldFontClasses, this.font);
			if (childNode.isHindi()) {
				if (!Util.isNullOrEmptyOrWhiteSpace(childNode.getData())) {
					childText += childNode.getData();
				}
			} else {
				childText = convertToUnicode(childText);
				this.addToData(childText);
				childText = "";

				if (!Util.isNullOrEmptyOrWhiteSpace(childNode.getData())) {
					this.addToData(childNode.getData());
				}
			}
		}
		if (!Util.isNullOrEmptyOrWhiteSpace(childText)) {
			// All non-Hindi text is added directly to data, hence childText
			// only contains Hindi text
			childText = convertToUnicode(childText);
			this.addToData(childText);
		}

		this.setFooterBoundary();
	}

	private String processNodeValue(String nodeValue) {
		nodeValue = this.normalizeData(nodeValue);
		if (this.isBold) {
			nodeValue = decorateBoldText(nodeValue);
		}
		return nodeValue;
	}

	private String convertToUnicode(String nodeValue) {
		try {
			nodeValue = DV_To_Unicode.convertToUnicode(nodeValue);
		} catch (Exception e) {
			Util.logMessage(Level.SEVERE,
					String.format(CONVERSION_ERROR, nodeValue));
		}
		return nodeValue;
	}

	private void updateAttributes(Element child, List<String> hindiFontClasses, List<String> boldFontClasses,
			String font) {
		String classValue = child.attr("class");
		String fontClass = Util.substringRegex(classValue, "ff[0-9]+");
		// verification for Hindi text
		if (fontClass != null && hindiFontClasses.contains(fontClass)) {
			this.font = fontClass;
			this.isHindi = true;
		} else if (fontClass != null && !hindiFontClasses.contains(fontClass)) {
			this.font = fontClass;
			this.isHindi = false;
		} else if (fontClass == null && hindiFontClasses.contains(font)) {
			this.font = font;
			this.isHindi = true;
		} else {
			this.font = null;
			this.isHindi = false;
		}

		// Verification for boldness
		if (fontClass != null && boldFontClasses.contains(fontClass)) {
			this.font = fontClass;
			this.isBold = true;
		} else if (fontClass != null && !boldFontClasses.contains(fontClass)) {
			this.font = fontClass;
			this.isBold = false;
		} else if (fontClass == null && boldFontClasses.contains(font)) {
			this.font = font;
			this.isBold = true;
		} else {
			this.font = null;
			this.isBold = false;
		}

	}

	public boolean isBold() {
		return isBold;
	}

	public void setBold(boolean isBold) {
		this.isBold = isBold;
	}

	public String getData() {
		return data;
	}

	public void addToData(String data) {
		if (!Util.isNullOrEmptyOrWhiteSpace(data)) {
			this.data = String.format("%s%s", this.data, data);
		}
	}

	public void setData(String data) {
		if (!Util.isNullOrEmptyOrWhiteSpace(data)) {
			this.data = data;
		}
	}

	public boolean isHindi() {
		return isHindi;
	}

	private String decorateBoldText(String text) {
		return String.format(Constants.BOLD_TEMPLATE, text);
	}

	private String normalizeData(String data) {
		data = data.replace('—', '-');
		data = data.replace('–', '-');
		data = data.replace('“', '"');
		data = data.replace('”', '"');
		// data = data.replace('é', 'e');
		data = data.replace("‘", "'");
		data = data.replace("’", "'");
		data = data.replace("…", "...");
		data = data.replace("∗", "*");

		// data = data.replace("[", "");
		// data = data.replace("]", "");

		if (isHindiText(data)) {
			data = data.replace("&lt;", "<");
		}
		return data;
	}

	public boolean isFooterBoundary() {
		return isFooterBoundary;
	}

	public void setFooterBoundary() {
		this.isFooterBoundary = data.equals(Constants.FOOTER_BOUNDARY);
	}

	private boolean isHindiText(String data) {
		if (!Util.isNullOrEmptyOrWhiteSpace(this.font)) {
			return isHindiText();
		}
		String[] words = data.split(" ");
		int hindiWordCount = 0;
		int wordsLength = words.length;
		for (int i = 0; i < words.length; i++) {
			if (Util.isNullOrEmptyOrWhiteSpace(words[i])) {
				wordsLength--;
			} else if (words[i].equals("(:##)")) {
				wordsLength--;
			} else {
				if (!isAscii(words[i])) {
					hindiWordCount++;
				}
			}
		}
		float wordProbability = ((float) hindiWordCount) / wordsLength;
		float textProability = (float) (isAscii(data) ? 0 : 1.0);
		float finalProbability = ((wordsLength) * wordProbability + textProability)
				/ (wordsLength + 1);
		return finalProbability >= Constants.EXPECTED_PROBABILITY;
	}

	private static boolean isAscii(String text) {
		return CharMatcher.ASCII.matchesAllOf(text);
	}

	private boolean isHindiText() {
		return this.font.contains(Constants.DV_DIVYAE);
	}

}
