package htmlParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileFilter {

	private static final String FOLDER_PARSE_FINISH = "Finished parsing Input Folder: %s";
	private static final String FOLDER_PARSE_START = "Started parsing Input Folder: %s";
	private static final String CURRENT_FILE_INFO_TEMPLATE = "Processing file %s";
	private static final String HINDI = "Hindi";
	private static final String PDF_POSTFIX = ".pdf";
	private static final String TXT_POSTFIX = ".txt";
	private static final String XML_POSTFIX = ".xml";
	private static final String HTML_POSTFIX = ".html";
	private static final String ENGLISH = "English";

	public static void main(String[] args) throws IOException {
		execute();
	}

	private static void execute() throws IOException {
		// String inputFolder =
		// "/home/rakesh/Copy/Constitution/Consttn/bilingual-constitution/tempXmlFiles";
		String inputFolder = "/home/rakesh/Copy/Constitution/Consttn/bilingual-constitution/tempPDF";
		String outputFolder = "/home/rakesh/Copy/Constitution/Consttn/bilingual-constitution/tempPDF";
		parseFileInFolder(inputFolder, outputFolder);
	}

	private static void parseFile(String inputFile, String engFile, String hinFile) {
		DomParser dom = new DomParser(inputFile);
		PrintWriter out;
		String englishPages = dom.getEnglishPages();
		try {
			out = new PrintWriter(engFile);
			out.println(englishPages);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String hindiPages = dom.getHindiPages();
		try {
			out = new PrintWriter(hinFile);
			out.println(hindiPages);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void parseFileInFolder(String inputFolder, String outputFolder) {
		File inputDirectory = new File(inputFolder);
		File outputDirectory = new File(outputFolder);
		if (inputDirectory.isDirectory()) {
			Util.logMessage(Level.INFO, String.format(FOLDER_PARSE_START, inputFolder));
			recurseDirectory(inputDirectory, inputDirectory, outputDirectory);
			Util.logMessage(Level.INFO, String.format(FOLDER_PARSE_FINISH, inputFolder));
		}
		Util.closeLogger();
	}

	private static void recurseDirectory(File currentNode, File inputNode, File outputNode) {
		String nodePath = currentNode.getAbsolutePath();
		List<String> fileList = Arrays.asList(currentNode.list());
		Collections.sort(fileList);
		Pattern xmlRegex = Pattern.compile(".pdf$");
		for (String subNodeName : fileList) {
			File subNode = new File(nodePath + "/" + subNodeName);
			Matcher m = xmlRegex.matcher(subNodeName);
			if (subNode.isDirectory()) {
				recurseDirectory(subNode, inputNode, outputNode);
			} else if (m.find()) {
				String inputFile = Util.pathJoin(nodePath, subNodeName);
				Util.logMessage(Level.INFO, String.format(CURRENT_FILE_INFO_TEMPLATE, inputFile));
				// convertToHtml(inputFile, outputNode.getAbsolutePath());
				String inputHtmlFile = getHtmlInputFile(nodePath, subNodeName, inputNode, outputNode);
				String engFile = getEnglishFile(nodePath, subNodeName, inputNode, outputNode);
				String hinFile = getHindiFile(nodePath, subNodeName, inputNode, outputNode);
				parseFile(inputHtmlFile, engFile, hinFile);
			}
		}
	}

	private static String getEnglishFile(String inputDirectory, String inputFile, File inputNode, File outputNode) {
		return getOutputFilePath(inputDirectory, inputFile, inputNode, outputNode, ENGLISH);

	}

	private static String getHindiFile(String inputDirectory, String inputFile, File inputNode, File outputNode) {
		return getOutputFilePath(inputDirectory, inputFile, inputNode, outputNode, HINDI);

	}

	private static String getHtmlInputFile(String inputDirectory, String inputFile, File inputNode, File outputNode) {
		String inputFolderPath = inputNode.getAbsolutePath();
		String inputFilePath = inputDirectory.replace(inputFolderPath, "");
		String outputFilePath = Util.pathJoin(outputNode.getAbsolutePath(), "");
		outputFilePath = Util.pathJoin(outputFilePath, inputFilePath);

		String outputTxtFile = inputFile.replace(PDF_POSTFIX, HTML_POSTFIX);

		File outputFile = new File(outputFilePath);
		outputFile.mkdirs();
		return Util.pathJoin(outputFile.getAbsolutePath(), outputTxtFile);
	}

	private static String getOutputFilePath(String inputDirectory, String inputFile, File inputNode, File outputNode,
			String language) {
		String inputFolderPath = inputNode.getAbsolutePath();
		String inputFilePath = inputDirectory.replace(inputFolderPath, "");
		String outputFilePath = Util.pathJoin(outputNode.getAbsolutePath(), language);
		outputFilePath = Util.pathJoin(outputFilePath, inputFilePath);

		String outputTxtFile = inputFile.replace(PDF_POSTFIX, TXT_POSTFIX);

		File outputFile = new File(outputFilePath);
		outputFile.mkdirs();
		return Util.pathJoin(outputFile.getAbsolutePath(), outputTxtFile);
	}

	private static void convertToHtml(String inputFile, String outputFolder) {
		String pdf2htmlx = "%s --font-format ttf --dest-dir %s %s";
		String command = String.format(pdf2htmlx, Constants.PDF2HTML_COMMAND, outputFolder, inputFile);
		try {
			runSystemCommand(command);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static String runSystemCommand(String command) throws IOException, InterruptedException {
		Runtime r = Runtime.getRuntime();
		Process p;
		String output = "";
		p = r.exec(command);
		p.waitFor();
		BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = "";

		while ((line = b.readLine()) != null) {
			output += line + "\n";
		}

		b.close();
		return output;
	}
}