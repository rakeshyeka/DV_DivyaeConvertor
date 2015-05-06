package xmlParser;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomParser {
	private Document dom;
	private List<Page> pages;

	public DomParser(String file) {
		// get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			// Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parse using builder to get DOM representation of the XML file
			dom = db.parse(file);
			NodeList pdf2xml = dom.getElementsByTagName("pdf2xml");
			pages = Page.buildPageFromNodeList(((Element) pdf2xml.item(0)).getElementsByTagName(Constants.PAGE_TAG));

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public String getHindiPages() {
		String text = "";
		for (int i = 0; i < pages.size(); i++) {
			if (pages.get(i).isHindi()) {
				text = String.format(Constants.NEWLINE_JOIN_TEMPLATE, text, Constants.PAGE_DECORATION_BOUNDARY);
				text = String.format(Constants.NEWLINE_JOIN_TEMPLATE, text, pages.get(i).getText());
				text = String.format(Constants.NEWLINE_JOIN_TEMPLATE, text, Constants.PAGE_DECORATION_BOUNDARY);
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
				text = String.format(Constants.NEWLINE_JOIN_TEMPLATE, text, Constants.PAGE_DECORATION_BOUNDARY);
				text = String.format(Constants.NEWLINE_JOIN_TEMPLATE, text, "");
			}
		}
		return text;
	}
}
