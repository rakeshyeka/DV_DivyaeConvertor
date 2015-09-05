package htmlParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;

public class Page {
	private boolean isHindi;
	private List<Text> content = new ArrayList<Text>();
	private int pageNumber;

	public Page(Element el, Map<String, String> hindiFontClasses, List<String> boldFontClasses) {
		if (el != null && el.childNodeSize() > 0) {
			for (Element child : el.getElementsByClass("t")) {
				Text textEntity = new Text(child, hindiFontClasses, boldFontClasses, null);
				content.add(textEntity);
			}
		}
		this.setHindi();
	}

	public static List<Page> buildPageFromNodeList(Element element, Map<String, String> hindiFontClasses,
			List<String> boldFontClasses) {
		List<Page> pageList = new ArrayList<Page>();
		for (Element child : element.getElementsByClass("pf")) {
			Page page = new Page(child, hindiFontClasses, boldFontClasses);
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
		int contentSize = 0;
		for (int i = 0; i < this.content.size(); i++) {
			if (this.content.get(i).isHindi()) {
				hindiCount++;
			} else if (!content.get(i).getData().equals("\n")) {
				contentSize++;
			}
		}
		float probability = ((float) hindiCount) / contentSize;
		if (probability >= Constants.EXPECTED_PROBABILITY) {
			this.isHindi = true;
		}
	}

	public String getText() {
		String text = "";
		text = processTextEntities(text, this.content);
		return text;
	}

	private String processTextEntities(String text, List<Text> content) {
		int prevBold = -2;
		for (int i = 0; i < content.size(); i++) {
			if (content.get(i).containsBold() && i - prevBold > 1) {
				text = String.format(Constants.NEWLINE_JOIN_TEMPLATE, text,
						Constants.BLOCK_DECORATION_BOUNDARY);
				prevBold = i;
			}
			text = String.format(Constants.NEWLINE_JOIN_TEMPLATE, text, content.get(i).getData());
		}
		return text;
	}

	public boolean isEnglish() {
		return !this.isHindi();
	}
}
