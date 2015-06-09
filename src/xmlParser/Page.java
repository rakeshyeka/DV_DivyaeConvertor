package xmlParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Page {
	private boolean isHindi;
	private int footerTop = 1120;
	private Map<String, String> fontIndex = new HashMap<String, String>();
	private List<Text> content = new ArrayList<Text>();
	private List<Text> footer = new ArrayList<Text>();
	private int pageNumber;

	public Page(Element el) {
		NodeList fontNl = el.getElementsByTagName(Constants.FONT_SPEC_TAG);
		if (fontNl != null && fontNl.getLength() > 0) {
			for (int i = 0; i < fontNl.getLength(); i++) {
				Node node = (Element) fontNl.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					String font = Util.getStringAttribute((Element) node, "family");
					String id = Util.getStringAttribute((Element) node, "id");
					this.fontIndex.put(id, font);
				}
			}
		}
		NodeList nl = el.getElementsByTagName(Constants.TEXT_TAG);
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {
				Text textEntity = new Text((Element) nl.item(i), this.fontIndex, null);
				if (textEntity.isFooterBoundary()) {
					this.footerTop = textEntity.getTop();
				}
				if (!textEntity.shouldIgnore()) {
					if (textEntity.getTop() >= footerTop) {
						footer.add(textEntity);
					} else {
						content.add(textEntity);
					}
				}
			}
		}
		this.setHindi();
	}

	public static List<Page> buildPageFromNodeList(NodeList pages) {
		List<Page> pageList = new ArrayList<Page>();
		for (int i = 0; i < pages.getLength(); i++) {
			Element el = (Element) pages.item(i);
			Page page = new Page(el);
			pageList.add(page);
		}
		return pageList;

	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public boolean isHindi() {
		return isHindi;
	}

	public void setHindi() {
		int hindiCount = 0;
		for (int i = 0; i < this.content.size(); i++) {
			if (this.content.get(i).isHindi()) {
				hindiCount++;
			}
		}
		float probability = ((float) hindiCount) / content.size();
		if (probability >= Constants.EXPECTED_PROBABILITY) {
			this.isHindi = true;
		}
	}

	public String getText() {
		String text = "";
		text = processTextEntities(text, this.content);

		text = String.format(Constants.NEWLINE_JOIN_TEMPLATE, text, Constants.FOOTER_DECORATION_BOUNDARY);
		text = processTextEntities(text, this.footer);
		text = String.format(Constants.NEWLINE_JOIN_TEMPLATE, text, Constants.FOOTER_DECORATION_BOUNDARY);

		return text;
	}

	private String processTextEntities(String text, List<Text> content) {
		int prevBold = -2;
		for (int i = 0; i < content.size(); i++) {
			if (content.get(i).isBold() && i - prevBold > 1) {
				text = String.format(Constants.NEWLINE_JOIN_TEMPLATE, text, Constants.BLOCK_DECORATION_BOUNDARY);
				prevBold = i;
			}
			text = String.format(Constants.NEWLINE_JOIN_TEMPLATE, text, content.get(i).getData());
		}
		if (prevBold != -2) {
			text = String.format(Constants.NEWLINE_JOIN_TEMPLATE, text, Constants.BLOCK_DECORATION_BOUNDARY);
		}
		return text;
	}

	public boolean isEnglish() {
		return !this.isHindi();
	}
}
