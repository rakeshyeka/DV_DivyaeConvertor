package htmlParser;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import fontConverter.DV_To_Unicode;

public class Text {
	private static final String CONVERSION_ERROR = "Error while converting text : %s";
	private boolean isBold;
	private boolean isHindi;
	private boolean isFooterBoundary;
	private boolean containsBold;
	private List<Text> children = null;

	private String data = "";

	private String font;

	public Text(String data, boolean isHindi, boolean isBold, String font) {
		this.data = data;
		this.isHindi = isHindi;
		this.isBold = isBold;
		this.font = font;
		this.processNodeValue();
		if (this.isBold) {
			this.containsBold = this.isBold;
		}
	}

	public Text(Element textEl, List<String> hindiFontClasses, List<String> boldFontClasses, String font) {

		updateAttributes(textEl, hindiFontClasses, boldFontClasses, font);

		if (textEl.childNodeSize() > 0) {
			this.children = new ArrayList<Text>();
		}
		for (Node child : textEl.childNodes()) {
			if (child.nodeName().equals(Constants.RAW_TEXT_CHILD_TAG)) {
				Text rawText = new Text(child.toString(), this.isHindi, this.isBold, this.font);
				this.children.add(rawText);
			} else {
				Text childNode = new Text((Element) child, hindiFontClasses, boldFontClasses, this.font);
				if (childNode.hasChildren()) {
					this.children.addAll(childNode.getChildren());
				}
			}
		}

		if (textEl.tagName().equals("div")) {
			String finalText = "";
			this.data = "";
			for (Text child : this.children) {
				if (!child.isHindi()) {
					finalText = convertToUnicode(finalText);
					this.addToData(finalText);
					finalText = "";
					if (!Util.isNullOrEmptyOrWhiteSpace(child.getData())) {
						this.addToData(child.getData());
					}
				} else {
					if (!Util.isNullOrEmptyOrWhiteSpace(child.getData())) {
						finalText += child.getData();
					}
				}
				this.containsBold = this.containsBold | child.containsBold();
			}
			if (!Util.isNullOrEmptyOrWhiteSpace(finalText)) {
				finalText = convertToUnicode(finalText);
				this.addToData(finalText);
			}
			if (Util.isNumber(this.data)) {
				this.data = "";
			}
		}

		this.setFooterBoundary();
	}

	private void processNodeValue() {
		this.normalizeData();
		if (this.isBold) {
			// nodeValue = decorateBoldText(nodeValue);
		}
	}

	private String convertToUnicode(String text) {
		try {
			text = DV_To_Unicode.convertToUnicode(text);
		} catch (Exception e) {
			Util.logMessage(Level.SEVERE,
					String.format(CONVERSION_ERROR, text));
		}
		return text;
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
			this.isBold = true;
		} else if (fontClass != null && !boldFontClasses.contains(fontClass)) {
			this.isBold = false;
		} else if (fontClass == null && boldFontClasses.contains(font)) {
			this.isBold = true;
		} else {
			this.isBold = false;
		}

	}

	public boolean hasChildren() {
		return this.children != null;
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

	public List<Text> getChildren() {
		return this.children;
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

	private String normalizeData() {
		data = data.replace('—', '-');
		data = data.replace('–', '-');
		data = data.replace('“', '"');
		data = data.replace('”', '"');
		// data = data.replace('é', 'e');
		data = data.replace("‘", "'");
		data = data.replace("’", "'");
		data = data.replace("…", "...");
		data = data.replace("∗", "");

		if (this.isHindi) {
			data = data.replace("&lt;", "<");
			data = data.replace("&amp;", "&");
		} else {
			// data = data.replace("[", "");
			// data = data.replace("]", "");
		}
		return data;
	}

	public boolean isFooterBoundary() {
		return isFooterBoundary;
	}

	public void setFooterBoundary() {
		this.isFooterBoundary = data.equals(Constants.FOOTER_BOUNDARY);
	}

	public boolean containsBold() {
		return containsBold;
	}

	public void setContainsBold(boolean containsBold) {
		this.containsBold = containsBold;
	}
}
