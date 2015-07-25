package htmlParserGandhi;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import fontConverter.DV_To_Unicode;

public class Text {
	public static boolean canIgnore = false;

	private static final String CONVERSION_ERROR = "Error while converting text : %s";
	private boolean isBold;
	private boolean isHindi;
	private boolean isFooterBoundary;
	private boolean containsBold;
	private boolean ignoreText;
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

	public Text(Element textEl) {

		updateAttributes(textEl);

		if (textEl.childNodeSize() > 0) {
			this.children = new ArrayList<Text>();
		}
		for (Node child : textEl.childNodes()) {
			if (child.nodeName().equals(Constants.RAW_TEXT_CHILD_TAG)) {
				Text rawText = new Text(child.toString(), this.isHindi, this.isBold, this.font);
				this.children.add(rawText);
			} else {
				Text childNode = new Text((Element) child);
				if (childNode.hasChildren()) {
					this.children.addAll(childNode.getChildren());
				}
			}
		}

		if (this.children != null
				&& (textEl.tagName().equals("p") || Util.substringRegex(textEl.tagName(), "h[0-9]+") != null)) {
			String finalText = "";
			this.data = "";
			for (Text child : this.children) {
				if (!Util.isNullOrEmptyOrWhiteSpace(child.getData())) {
					finalText += child.getData();
				}
				this.containsBold = this.containsBold | child.containsBold() | child.isBold() | this.isBold;
			}
			if (!Util.isNullOrEmptyOrWhiteSpace(finalText)) {
				this.addToData(finalText);
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

	private void updateAttributes(Element child) {
		String classValue = child.attr("class");
		String fontClass = Util.substringRegex(classValue, "block[0-9]+");

		// Verification for boldness
		if (Util.substringRegex(child.tagName(), "h[0-9]+") != null) {
			this.isBold = true;
		}

		// Verification for text ignore
		if (Text.canIgnore && !Util.isNullOrEmptyOrWhiteSpace(fontClass) && fontClass.equals("block10")) {
			this.ignoreText = true;
		}
	}

	public boolean hasChildren() {
		return this.children != null;
	}

	public boolean isBold() {
		return isBold;
	}

	public boolean canIgnore() {
		return this.ignoreText;
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
		data = data.replace("&nbsp;", " ");
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
