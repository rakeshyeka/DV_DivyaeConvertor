package sentenceAligner;

import java.util.ArrayList;
import java.util.List;

public class Page {
	private List<String> blocks;

	public Page() {
		blocks = new ArrayList<String>();
	}

	public List<String> getBlocks() {
		return this.blocks;
	}

	public void addBlock(String block) {
		this.blocks.add(block);
	}
}
