package htmlParserGandhi;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;

public class Page {
	private List<Text> content = new ArrayList<Text>();
	private int pageNumber;

	public Page(Element el, List<String> hindiFontClasses, List<String> boldFontClasses) {
		if (el != null && el.childNodeSize() > 0) {
			for (Element child : el.getAllElements()) {
				Text textEntity = new Text(child);
				content.add(textEntity);
			}
		}
	}

	public static List<Page> buildPageFromNodeList(Element element, List<String> hindiFontClasses,
			List<String> boldFontClasses) {
		List<Page> pageList = new ArrayList<Page>();
		for (Element child : element.getElementsByClass("calibre")) {
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
}
