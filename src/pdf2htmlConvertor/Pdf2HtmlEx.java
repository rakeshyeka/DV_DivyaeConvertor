package pdf2htmlConvertor;

import java.io.IOException;
import java.util.logging.Level;

public class Pdf2HtmlEx {

	public static void convertToHtml(String inputFile, String outputFolder) {
		String pdf2htmlx = "%s --font-format ttf --embed-image 0 --dest-dir %s %s";
		String command = String.format(pdf2htmlx, Constants.PDF2HTML_COMMAND, outputFolder, inputFile);
		try {
			Util.logMessage(Level.INFO, String.format(Constants.EXECUTING_COMMAND_TEMPLATE, command));
			Util.runSystemCommand(command);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
