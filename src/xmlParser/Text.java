package xmlParser;

import java.util.Map;
import java.util.logging.Level;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.google.common.base.CharMatcher;

import fontConverter.DV_To_Unicode;

public class Text {
	private static final String CONVERSION_ERROR = "Error while converting text : %s";
	private boolean isBold;
	private boolean isHindi;
	private boolean isFooterBoundary;

	private String data = "";

	private int top;
	private int height;
	private String font;

	public Text(Node node, Map<String, String> fontIndex, String font) {

		if (node.getNodeType() == Node.ELEMENT_NODE) {
			updateAttributes((Element) node, fontIndex, font);
		}

		Node nd = node.getFirstChild();
		while (nd != null) {
			String nodeValue = Util.getTextVal(nd);
			if (nd.getNodeName().equals(Constants.BOLD_TAG)) {
				this.setBold(true);
				nodeValue = decorateBoldText(nodeValue);
			}
			if (!Util.isNullOrEmptyOrWhiteSpace(nodeValue)) {
				nodeValue = this.normalizeData(nodeValue);
				if (isHindiText(nodeValue)) {
					try {
						nodeValue = DV_To_Unicode.convertToUnicode(nodeValue);
						nodeValue = postProcess(nodeValue);
					} catch (Exception e) {
						Util.logMessage(Level.SEVERE,
								String.format(CONVERSION_ERROR, nodeValue));
					}
				}
				this.addToData(nodeValue);
			}

			Text childText = new Text(nd, fontIndex, this.font);
			if (Util.isNullOrEmptyOrWhiteSpace(childText.getData())) {
				this.addToData(childText.getData());
			}
			nd = nd.getNextSibling();
		}

		if (!this.shouldIgnore()) {
			this.setHindi();
		}
		this.setFooterBoundary();
	}

	private void updateAttributes(Element el, Map<String, String> fontIndex, String font) {
		this.top = Util.getIntegerAttribute(el, "top");
		this.height = Util.getIntegerAttribute(el, "height");
		String fontId = Util.getStringAttribute(el, "font");
		if (!Util.isNullOrEmptyOrWhiteSpace(fontId)) {
			this.font = fontIndex.get(fontId);
		} else if (!Util.isNullOrEmptyOrWhiteSpace(font)) {
			this.font = font;
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

	public void setHindi() {
		this.isHindi = isHindiText(this.data);
	}

	public boolean shouldIgnore() {
		if (top <= 55) { // part of header like page number then ignore
			return true;
		}
		if (this.isHindi) {
			if (height == 14) { // if Hindi super notation for footer then
				// ignore
				return true;
			}
		} else if (height == 11) { // if English super notation for footer
								// then ignore
			return true;
		}
		return false;
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

	private String postProcess(String data) {
		data = data.replaceAll("ट$", "]");
		data = data.replaceAll("ट ", "] ");
		data = data.replaceAll("।ट", "।]");
		data = data.replaceAll("्]", "्ट"); // Reverts back any ट converted to
										// ]
										// in case there is a
										// samyukth(joiner) before the
										// bracket
		data = data.replace("रि]", "रिट"); // Correcting a common false
									// positive
									// रिट.

		// Removing close square brackets from the text
		data = data.replace("]", "");

		return data;
	}

	public boolean isFooterBoundary() {
		return isFooterBoundary;
	}

	public void setFooterBoundary() {
		this.isFooterBoundary = data.equals(Constants.FOOTER_BOUNDARY);
	}

	public int getTop() {
		return this.top;
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
