package htmlParser;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class Text {
	private static final String CONVERSION_ERROR = "Error while converting text : %s";
	private boolean isBold;
	private boolean isHindi;
	private boolean isFooterBoundary;
	private boolean containsBold;
	private List<Text> children = null;

	private String data = "";

	private String fontConvertor;

	public Text(String data, boolean isHindi, boolean isBold, String fontConvertor) {
		this.data = data;
		this.isHindi = isHindi;
		this.isBold = isBold;
		this.fontConvertor = fontConvertor;
		this.processNodeValue();
		if (this.isBold) {
			this.containsBold = this.isBold;
		}
	}

	public Text(Element textEl, Map<String, String> hindiFontClasses, List<String> boldFontClasses,
			String fontConvertor) {

		updateAttributes(textEl, hindiFontClasses, boldFontClasses, fontConvertor);

		if (textEl.childNodeSize() > 0) {
			this.children = new ArrayList<Text>();
		}
		for (Node child : textEl.childNodes()) {
			if (child.nodeName().equals(Constants.RAW_TEXT_CHILD_TAG)) {
				Text rawText = new Text(child.toString(), this.isHindi, this.isBold, this.fontConvertor);
				this.children.add(rawText);
			} else {
				Text childNode = new Text((Element) child, hindiFontClasses, boldFontClasses, this.fontConvertor);
				if (childNode.hasChildren()) {
					this.children.addAll(childNode.getChildren());
				}
			}
		}

		if (textEl.tagName().equals("div")) {
			String finalText = "";
			this.data = "";
			String oldFontConvertor = null;
			for (Text child : this.children) {
				if (!Util.stringsEqual(oldFontConvertor, child.getFontConvertor())) {
					finalText = convertToUnicode(finalText, oldFontConvertor);
					this.addToData(finalText);
					finalText = "";
					if (!Util.isNullOrEmptyOrWhiteSpace(child.getData())) {
						finalText += child.getData();
					}
					oldFontConvertor = child.getFontConvertor();
				} else {
					if (!Util.isNullOrEmptyOrWhiteSpace(child.getData())) {
						finalText += child.getData();
					}
				}
				this.containsBold = this.containsBold | child.containsBold() | child.isBold();
			}
			if (!Util.isNullOrEmptyOrWhiteSpace(finalText)) {
				finalText = convertToUnicode(finalText, oldFontConvertor);
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

	private String convertToUnicode(String text, String fontConvertor) {
		try {
			if (!Util.isNullOrEmptyOrWhiteSpace(fontConvertor)) {
				Class<?> clazz = Class.forName("fontConverter." + fontConvertor);
				Method method = clazz.getMethod("convertToUnicode", String.class);
				// text = DV_To_Unicode.convertToUnicode(text);
				text = (String) method.invoke(null, text);
			}
		} catch (Exception e) {
			Util.logMessage(Level.SEVERE,
					String.format(CONVERSION_ERROR, text));
		}
		return text;
	}

	private void updateAttributes(Element child, Map<String, String> hindiFontClasses, List<String> boldFontClasses,
			String parentFontConvertor) {
		String classValue = child.attr("class");
		String fontClass = Util.substringRegex(classValue, "ff[0-9]+");
		String localFontConvertor = hindiFontClasses.get(fontClass);
		// verification for Hindi text
		if (localFontConvertor != null) {
			this.fontConvertor = localFontConvertor;
			this.isHindi = true;
		} else if (localFontConvertor == null && parentFontConvertor != null) {
			this.fontConvertor = parentFontConvertor;
			this.isHindi = true;
		} else {
			this.fontConvertor = null;
			this.isHindi = false;
		}

		// Verification for boldness
		if (fontClass != null && boldFontClasses.contains(fontClass)) {
			this.isBold = true;
		} else if (fontClass != null && !boldFontClasses.contains(fontClass)) {
			this.isBold = false;
		} else if (fontClass == null && boldFontClasses.contains(parentFontConvertor)) {
			this.isBold = true;
		} else {
			this.isBold = false;
		}

		// String marginShiftClass = Util.substringRegex(classValue,
		// "_[0-9]+");
		// if (marginShiftClass != null) {
		// this.isBold = true;
		// }

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

	public String getFontConvertor() {
		return this.fontConvertor;
	}
}
