package fontConverter;

public class Chanakya_To_Unicode {
	private static String array_one[] = {

			"¤", "U",

			// correct anusvAr+ekAr, ekAr+nuqta to the reverse order
			"¢ð", "´ð", "ð¸",

			"¥æò", "¸",
			"¸•", "¸¹", "¸»", "¸Á", "¸Ç", "¸É", "¸È", "¸Ø", "¸Ú", "¸Ù",

			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
			"N", "O", "P", "Q", "R", "T", "V", "W", "X", "Y",

			"`", "a", "b", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "u",

			"žæ", "ž", "#", "%", "@",
			"„", "¦", "¨", "¯", "µ", "º",

			"Cþ", "q", "Ê", "u", "g",
			"Ÿæ", "Åþ", "Çþ", "Éþ",
			"^", "h", "Ð", "ý", "þ",

			"¥ô", "¥æð", "¥õ", "¥æñ", "¥æ", "¥", "§Z", "§ü", "§", "©", "ª", "«", "¬", "­", "°ð", "°",

			"€", "•", "", "¹", "‚", "»", "ƒæ", "ƒ", "¾",
			"“", "‘", "¿", "À", "”", "…", "’", "Á", "Ûæ", "Û", "†æ", "†",

			"Å", "Æ", "Ç", "É", "‡æ", "‡",
			"ˆ", "Ì", "‰", "Í", "Î", "¼", "Š", "Ï", "óæ", "ó", "‹æ", "Ù", "‹",

			"Œ", "Â", "", "È", "Ž", "Õ", "", "Ö", "", "×",
			"Ä", "Ø", "Ú", "Ë", "Ü", "¶", "Ý", "Ã", "ß",
			"àæ", "³æ", "o", "³", "à", "c", "á", "S", "â", "ã", "±",
			"ÿæ", "ÿ", "˜æ", "˜", "™æ", "™", "üð´",

			"æò", "æñ", "æ", "è", "é", "ê", "ä", "å", "ë", "ì", "í", "Ô", "ñ", "ô", "õ",
			"¢", "´", "¡", "Ñ", "¸", "ò", "ù", "÷", "ð",
			"Z",

			"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"®", "v", "w", "x", "y", "z", "{", "|", "}", "~",
			"्ो", "्ौ", "्ाे", "्ाा", "ाे", "ाे", "ाै", "्ा", "ंु", "ओे", "ोे", "ाे", "ईंं" };

	private static String array_two[] = {

			"", "",

			// correct anusvAr+ekAr, ekAr+nuqta to the reverse order
			"ð¢", "ð´", "¸ð",

			"ऑ", "फ़्",
			"क़", "ख़", "ग़", "ज़", "ड़", "ढ़", "फ़", "य़", "ऱ", "ऩ",

			"्र", "क्च", "ष्ट", "ष्ठ", "श्व", "स्न", "त्र", "॥", "ढ्ढ", "छ्व", "्य", "रु", "रू",
			"हृ", "ह्र", "क्क", "क्त", "क्र", "ञ्ज", "ङ्क", "ङ्ख", "ङ्ग", "ङ्घ",
			"क्व", "ड्ड", "ड्ढ", "स्र", "द्ग", "द्घ", "द्द", "द्ध", "द्ब", "द्भ", "द्म", "द्य", "द्व", "ठ्ठ", "श्च",
			"ह्न", "ह्म्", "ह्य", "ह्ल", "ह्व",

			"त्त", "त्त्", "प्त", "त्न", "ञ्च",
			"ल्ल", "ष्ट्व", "ङ्क्ष", "ख्न", "द्ब्र", "ख्र",

			"ष्ट्र", "ह्न", "ज़्", "ह्व", "द्द",
			"श्र", "ट्र", "ड्र", "ढ्र",
			"ट्ट", "द्ध", "।", "्र", "्र",

			"ओ", "ओ", "औ", "औ", "आ", "अ", "ईं", "ई", "इ", "उ", "ऊ", "ऋ", "ॠ", "ऌ", "ऐ", "ए",

			"क्", "क", "ख्", "ख", "ग्", "ग", "घ", "घ्", "ङ",
			"च्च्", "च्", "च", "छ", "ज्ज्", "ज्", "ज्", "ज", "झ", "झ्", "ञ", "ञ्",

			"ट", "ठ", "ड", "ढ", "ण", "ण्",
			"त्", "त", "थ्", "थ", "द", "द", "ध्", "ध", "न्न", "न्न्", "न", "न", "न्",

			"प्", "प", "फ्", "फ", "ब्", "ब", "भ्", "भ", "म्", "म",
			"य्", "य", "र", "ल्", "ल", "ल", "ळ", "व्", "व",
			"श", "श", "श", "श्", "श्", "ष्", "ष", "स्", "स", "ह", "ह्",
			"क्ष", "क्ष्", "त्र", "त्र्", "ज्ञ", "ज्ञ्", "ðZ",

			"ॉ", "ौ", "ा", "ी", "ु", "ू", "ु", "ू", "ृ", "ॄ", "ॢ", "े", "ै", "ो", "ौ",
			"ं", "ं", "ँ", ":", "़", "ॅ", "ऽ", "्", "े",
			"ंü",

			"०", "१", "२", "३", "४", "५", "६", "७", "८", "९",
			"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",

			"े", "ै", "े", "ा", "ो", "ो", "ौ", "", "ुं", "ओ", "ो", "ो", "ईं" };

	private static int array_one_length = array_one.length;

	public static String convertToUnicode(String inputText) {
		if (inputText == null || inputText.isEmpty()) {
			return "";
		}

		String outputText = "";
		String modified_substring = inputText;
		String processed_text = ""; // blank

		modified_substring = Replace_Symbols(modified_substring);

		processed_text = modified_substring;

		outputText = processed_text;
		return outputText;

		// --------------------------------------------------

	}

	private static String Replace_Symbols(String modified_substring) {

		// substitute array_two elements in place of corresponding array_one
		// elements

		if (modified_substring != "") // if stringto be converted is non-blank
								// then no need of any processing.
		{
			for (int input_symbol_idx = 0; input_symbol_idx < array_one_length; input_symbol_idx++)

			{

				int idx = 0; // index of the symbol being searched for
							// replacement

				while (idx != -1) // while-00
				{

					modified_substring = modified_substring.replace(array_one[input_symbol_idx],
							array_two[input_symbol_idx]);
					idx = modified_substring.indexOf(array_one[input_symbol_idx]);

				} // end of while-00 loop
			} // end of for loop

			// *******************************************************

		} // end of IF statement meant to supress processing of blank string.
		modified_substring = shiftMathrasPosition(modified_substring);

		modified_substring = modified_substring.replace("/Ò/g", "‘");
		modified_substring = modified_substring.replace("/Ó/g", "’");
		return modified_substring;

	} // end of the function Replace_Symbols

	private static String shiftMathrasPosition(String modified_substring) {
		int position_of_i = modified_substring.indexOf("ç");

		while (position_of_i != -1) // while-02
		{
			char charecter_next_to_i = modified_substring.charAt(position_of_i + 1);
			String charecter_to_be_replaced = "ç" + charecter_next_to_i;
			modified_substring = modified_substring
					.replace(charecter_to_be_replaced, charecter_next_to_i + "ि");
			position_of_i = modified_substring.indexOf("/ç/"); // search
														// for
														// i
														// ahead
														// of
														// the
														// current
														// position.

		} // end of while-02 loop

		// following loop to eliminate 'chhotee ee kee maatraa' on
		// half-letters as a result of above transformation.

		int position_of_wrong_ee = modified_substring.indexOf("ि्");

		while (position_of_wrong_ee != -1) // while-03

		{
			char consonent_next_to_wrong_ee = modified_substring.charAt(position_of_wrong_ee + 2);
			String charecter_to_be_replaced = "ि्" + consonent_next_to_wrong_ee;
			modified_substring = modified_substring.replace(charecter_to_be_replaced, "्"
					+ consonent_next_to_wrong_ee + "ि");
			int nextPosition = modified_substring.substring(position_of_wrong_ee).indexOf("ि्");
			// if (nextPosition == 0) {
			// position_of_wrong_ee = -1;
			// } else if (nextPosition != -1) {
			if (nextPosition != -1) {
				position_of_wrong_ee += nextPosition;
			} else {
				position_of_wrong_ee = nextPosition;
			}// search
				// for
				// 'wrong
				// ee'
				// ahead
				// of
				// the
				// current
				// position.

		} // end of while-03 loop

		// following loop to eliminate 'chhotee ee kee maatraa' on
		// half-letters as a result of above transformation.

		position_of_wrong_ee = modified_substring.indexOf("िं्");

		while (position_of_wrong_ee != -1) // while-03

		{
			char consonent_next_to_wrong_ee = modified_substring.charAt(position_of_wrong_ee + 3);
			String charecter_to_be_replaced = "िं्" + consonent_next_to_wrong_ee;
			modified_substring = modified_substring.replace(charecter_to_be_replaced, "्"
					+ consonent_next_to_wrong_ee + "िं");
			position_of_wrong_ee = modified_substring.indexOf("/िं्/"); // search
															// for
															// 'wrong
															// ee'
															// ahead
															// of
															// the
															// current
															// position.

		} // end of while-03 loop

		// Eliminating reph "Ô" and putting 'half - r' at proper position
		// for this.
		String set_of_matras = "ा ि ी ु ू ृ े ै ो ौ ं : ँ ॅ";
		int position_of_R = modified_substring.indexOf("ü");

		while (position_of_R > 0) // while-04
		{
			int probable_position_of_half_r = position_of_R - 1;
			char charecter_at_probable_position_of_half_r = modified_substring
					.charAt(probable_position_of_half_r);

			// trying to find non-maatra position left to current O (ie,
			// half -r).

			while (set_of_matras.indexOf(charecter_at_probable_position_of_half_r) != -1) // while-05

			{
				probable_position_of_half_r = probable_position_of_half_r - 1;
				charecter_at_probable_position_of_half_r = modified_substring
						.charAt(probable_position_of_half_r);

			} // end of while-05

			String charecter_to_be_replaced = modified_substring.substring(probable_position_of_half_r,
					(position_of_R - probable_position_of_half_r));
			String new_replacement_string = "र्" + charecter_to_be_replaced;
			charecter_to_be_replaced = charecter_to_be_replaced + "ü";
			modified_substring = modified_substring.replace(charecter_to_be_replaced, new_replacement_string);
			position_of_R = modified_substring.indexOf("ü");

		} // end of while-04

		return modified_substring;
	}

}
