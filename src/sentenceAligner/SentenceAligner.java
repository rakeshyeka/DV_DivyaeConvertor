package sentenceAligner;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.tuple.Pair;

public class SentenceAligner {

	private static int getMaxBlockCountIndex(int[] blockCount, int size) {
		int max = 0;
		int maxIndex = -1;
		for(int i=0;i < size;i++){
			if(blockCount[i] > max){
				max = blockCount[i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	private static void updateBlockCounts(List<String> engNumbers,
			List<String> hinBlocks, int[] blockCount) {
		for (String number : engNumbers) {
			for (int i = 0; i < hinBlocks.size(); i++) {
				if (hinBlocks.get(i).indexOf(number) != -1) {
					blockCount[i]++;
				}
			}
		}
	}

	private static int[] initializeArray(int[] blockCount, int size) {
		for (int i = 0; i < size; i++) {
			blockCount[i] = 0;
		}
		return blockCount;
	}

	private static List<Pair<Integer, Integer>> getNumberIndices(String block) {
		List<Pair<Integer, Integer>> indices = new ArrayList<Pair<Integer, Integer>>();
		Pattern numberPattern = Pattern.compile("\\d+");
		Matcher matcher = numberPattern.matcher(block);
		Pair<Integer, Integer> startEnd = null;
		while (matcher.find()) {
			startEnd = Pair.of(matcher.start(), matcher.end());
			indices.add(startEnd);
		}
		return indices;
	}

	private static List<String> getNumberMatches(String block) {
		List<String> indices = new ArrayList<String>();
		Pattern numberPattern = Pattern.compile("\\d+");
		Matcher matcher = numberPattern.matcher(block);
		while (matcher.find()) {
			indices.add(matcher.group());
		}
		return indices;
	}

	public static void blockAligner(Page engPage, Page hinPage) {
		List<String> engBlocks = engPage.getBlocks();
		List<String> hinBlocks = hinPage.getBlocks();
		int[] blockCount = new int[hinBlocks.size()];
		int prevHinBlock = -1;
		for (int i=0; i < engBlocks.size(); i++) {
			initializeArray(blockCount, hinBlocks.size());

			List<String> engNumbers = getNumberMatches(engBlocks.get(i));
			updateBlockCounts(engNumbers, hinBlocks, blockCount);
			int maxBlockIndex = getMaxBlockCountIndex(blockCount, hinBlocks.size());
			if(maxBlockIndex==-1){
				prevHinBlock++;
				System.out.println(i + " - " + prevHinBlock);
			} else{
				System.out.println(i + " - " + maxBlockIndex);
				prevHinBlock = maxBlockIndex;
			}
		}
	}
	
	public static void pageAligner(List<Page> engPages, List<Page> hinPage){
		
	}
}
