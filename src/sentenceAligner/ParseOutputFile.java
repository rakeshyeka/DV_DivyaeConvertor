package sentenceAligner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import xmlParser.Constants;
import xmlParser.Util;

public class ParseOutputFile {

	private List<Page> pages = new ArrayList<Page>();

	public void parsePages(String inputFile) {
		Page pg = null;
		BufferedReader br = null;
		String block = "";

		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(inputFile));
			while ((sCurrentLine = br.readLine()) != null) {
				if (sCurrentLine.equals(Constants.PAGE_DECORATION_BOUNDARY)) {
					if (pg != null) {
						pages.add(pg);
					}
					pg = new Page();
				} else if (sCurrentLine.equals(Constants.BLOCK_DECORATION_BOUNDARY)) {
					if (!Util.isNullOrEmptyOrWhiteSpace(block)) {
						pg.addBlock(block);
					}
					block = "";
				} else {
					block = String.format(Constants.NEWLINE_JOIN_TEMPLATE, block, sCurrentLine);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public List<Page> getPages() {
		return this.pages;
	}
}
