package xmlParser;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.management.RuntimeErrorException;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Util {

	private static final String FILE_SEPERATOR = "/";
	private static Logger LOGGER = Logger.getAnonymousLogger();
	private static boolean isFileHandlerSet = false;

	public static String getTextVal(Node el) {
		String textval = null;
		if (el.getNodeName().equals(Constants.RAW_TEXT_CHILD_TAG)) {
			textval = el.getNodeValue();
		} else {
			textval = el.getFirstChild().getNodeValue();
		}
		return textval;
	}

	public static String getStringAttribute(Element el, String attribute) {
		return el.getAttribute(attribute);
	}

	public static int getIntegerAttribute(Element el, String attribute) {
		String attributeValue = el.getAttribute(attribute);
		try {
			return Integer.parseInt(attributeValue);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public static String pathJoin(String prePath, String postPath) {
		prePath = StringUtils.stripEnd(prePath, FILE_SEPERATOR);
		postPath = StringUtils.strip(postPath, FILE_SEPERATOR);
		return String.format("%s/%s", prePath, postPath);
	}

	public static boolean isNullOrEmptyOrWhiteSpace(String data) {
		return (data == null || data.isEmpty() || data.trim().length() <= 0);
	}

	public static void logMessage(Level level, String message) {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		if (!isFileHandlerSet) {
			FileHandler logFileHandler = null;
			try {
				logFileHandler = new FileHandler(Constants.logFile);
			} catch (IOException e) {
				throw new RuntimeErrorException(new Error(e.getMessage()));
			}
			logFileHandler.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(logFileHandler);
			isFileHandlerSet = true;
		}
		LOGGER.logp(level, stackTraceElements[2].getClassName(), stackTraceElements[2].getMethodName(), message);
	}

	public static void closeLogger() {
		for (Handler handler : LOGGER.getHandlers()) {
			handler.close();
		}
	}
}
