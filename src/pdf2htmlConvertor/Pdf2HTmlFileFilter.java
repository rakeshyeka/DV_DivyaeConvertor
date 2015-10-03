package pdf2htmlConvertor;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pdf2HTmlFileFilter {

	private static final String FOLDER_PARSE_FINISH = "Finished parsing Input Folder: %s";
	private static final String FOLDER_PARSE_START = "Started parsing Input Folder: %s";
	private static final String CURRENT_FILE_INFO_TEMPLATE = "Processing file %s";

	public static void main(String[] args) throws IOException {
		execute();
	}

	private static void execute() throws IOException {
		// String inputFolder =
		// "/home/rakesh/Copy/Constitution/Consttn/bilingual-constitution/tempPDF";
		String inputFolder = "/home/rakesh/Copy/NCERT/PDF/Current/temp";
		String outputFolder = "/home/rakesh/Copy/NCERT/html/Current/temp";
		parseFileInFolder(inputFolder, outputFolder);
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
		String outputFolder = getOutputNodePath(currentNode, inputNode, outputNode);
		createOutputFolder(outputFolder);
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
				Pdf2HtmlEx.convertToHtml(inputFile, outputFolder);
			}
		}
	}

	private static String getOutputNodePath(File currentNode, File inputNode, File outputNode) {
		String outputFolder = "";
		String inputPath = currentNode.getAbsolutePath();
		outputFolder = Util.pathJoin(outputNode.getAbsolutePath()
				, inputPath.replace(inputNode.getAbsolutePath(), ""));
		return outputFolder;
	}

	private static void createOutputFolder(String outputFolder) {
		String command = "mkdir -p " + outputFolder;
		try {
			Util.runSystemCommand(command);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
