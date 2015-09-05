package htmlParserGandhi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DomParser {
	private Document dom;
	private List<Text> texts = new ArrayList<Text>();

	public DomParser(String file) {

		try {
			File input = new File(file);
			this.dom = Jsoup.parse(input, "UTF-8", "");

			Elements elements = this.dom.getElementsByTag(Constants.PAGE_CONTAINER_TAG);
			Element element = elements.first();
			Text.canIgnore = true;
			for (Element page : element.getElementsByClass("calibre")) {
				for (Element child : page.getAllElements()) {
					Text text = new Text(child);
					this.texts.add(text);
				}
			}
		} catch (IOException ioe) {

		}

	}

	public String getPages() {
		String data = "";
		for (int i = 0; i < texts.size(); i++) {
			Text text = texts.get(i);
			if (text.containsBold()) {
				data = String.format(Constants.NEWLINE_JOIN_TEMPLATE, data, Constants.PAGE_DECORATION_BOUNDARY);
			}
			if (!text.canIgnore()) {
				data = String.format(Constants.NEWLINE_JOIN_TEMPLATE, data, text.getData());
			}
		}
		return data;
	}
}
