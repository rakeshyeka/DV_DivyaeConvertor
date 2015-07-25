package fontConverter;

public class DV_To_Unicode {

	private static String array_one[] = { "$", "%", "&", "*", "£Ã", "ÉË", "ÉÊ", "BÉD", "BÉ", "BÉÖ",

			"+ÉÉä", "+ÉÉè", "+ÉÉ", "+É", "<È", "<Ç", "<", ">", "=", "Aä", "A", "@", "Bä", "‹ä", "B", "‹", "आå",
			"आé",

			"C", "D", "F", "µÉE", "H", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
			"Y", "Z",
			"\\", "]", "^", "_",

			"`", "a", "b", "c", "d", "e", "f", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
			"v", "w", "x", "y", "z", "{É", "{", "|", "}", "~",

			"¢", "¤", "¥", "£", "§", "à", "©", "ª", "«", "®", "¯", "¬", "â", "°", "ã", "²", "³", "´", "µ", "¶", "•",
			"·", "g", "−", "º", "»",

			"À", "Á", "Ø", "¿", "¼", "Â", "Ã", "Ä", "Æ", "Å", "ÿ", "½",

			"ä", "è", "Éä", "Éè", "ÉÒ", "É", "Ö", "×", "Ù", "Ú", "Ý", "Þ", "ß", "्ा", "å", "é", "ì",

			"ð", "ñ", "ò", "ó", "ô", "õ", "ö", "÷", "ø", "ù", "ú", "û", "ü", "ý", "þ", "ाे", "ाै", "आॅ", "ाॅ", "\'",
			"$", "Ω", "E" };

	private static String array_two[] = { "ॐ", "ऽ", "ः", "।", "फ़्र", "Ω", "$", "क्", "क", "कु",

			"ओ", "औ", "आ", "अ", "ईं", "ई", "इ", "ऊ", "उ", "ॠ", "ए", "ऐं", "ऐ", "ऐ", "व्", "ए", "ओं", "औं",

			"क़्", "क्", "क्ष्", "क्र", "क्त", "ख्", "ख़्", "ख्र्", "ग्", "ग़्", "ग्र्", "घ्", "घ्र्", "ङ", "च्",
			"च्र्", "छ",
			"ज्", "ज़्", "ज्र्", "ज्ञ्", "झ्", "ञ्", "ट", "ट्ट", "ट्ठ",

			"ठ", "ठ्ठ", "ड", "ह", "ड्ड", "ड्ढ", "ढ", "ण्", "त्", "त्र्", "त्त्", "थ्", "थ्र्", "द", "दृ", "द्र",
			"द्द", "द्ध", "द्म", "द्य्", "द्व", "ध्", "ध्र्", "न्", "न्र्", "न्न्", "प", "प्", "प्र्", "फ्", "ठ",

			"फ़", "ब्", "ब्र्", "भ्", "भ्र्", "म्", "म्र्", "य्", "य्र्", "र", "", "्य", "रु", "रू", "ल्", "ळ्",
			"ळ", "व्", "व्र्", "श्", "श्व्", "श्व्", "श्र्", "ष्", "स्", "स्र्",

			"ह्म्", "ह्य", "हृ", "ह्र", "ह्", "्", "़", "ँ", "ं", "्र", "ह्", "ड़",

			"े", "ै", "ो", "ौ", "ी", "ा", "ु", "ु", "ु", "ू", "ू", "ृ", "ॄ", "", "ें", "ैं", "ॅ",

			"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "ो", "ौ", "ऑ", "ॉ", "\"", "Ê", "Ë", "" };

	private static int array_one_length = array_one.length;

	public static String convertToUnicode(String inputText) {

		if (inputText == null || inputText.isEmpty()) {
			return "";
		}

		String outputText="";

		String modified_substring = inputText;

		int text_size = inputText.length();

		String processed_text = ""; // blank

		int sthiti1 = 0;
		int sthiti2 = 0;
		int chale_chalo = 1;

		int max_text_size = 6000;

		while (chale_chalo == 1) {
			sthiti1 = sthiti2;

			if (sthiti2 < (text_size - max_text_size)) {
				sthiti2 += max_text_size;
				while (inputText.charAt(sthiti2) != ' ') {
					sthiti2--;
				}
			} else {
				sthiti2 = text_size;
				chale_chalo = 0;
			}

			// alert(" sthiti 1 = "+sthiti1); alert(" sthit 2 = "+sthiti2)

			modified_substring = inputText.substring(sthiti1, sthiti2);

			modified_substring = Replace_Symbols(modified_substring);

			processed_text += modified_substring;

			outputText = processed_text;

		} // chalechalo while loop ends

		// ***********************************************************************
		// -------- code for keeping English words (which are within small
		// bracket) unchanged----------------

		return outputText;

	}

	// **********************************************************************************************

	private static String Replace_Symbols(String modified_substring) {

		// substitute array_two elements in place of corresponding array_one
		// elements

		if (modified_substring != "") // if stringto be converted is non-blank
		// then no need of any processing.
		{
			modified_substring = processMatras(modified_substring);

			for (int input_symbol_idx = 0; input_symbol_idx < array_one_length; input_symbol_idx++) {

				int idx = 0; // index of the symbol being searched for
				// replacement

				while (idx != -1) // while-00
				{

					modified_substring = modified_substring.replace(array_one[input_symbol_idx],
							array_two[input_symbol_idx]);
					idx = modified_substring.indexOf(array_one[input_symbol_idx]);

				} // end of while-00 loop
			} // end of for loop

		} // end of IF statement meant to supress processing of blank string.

		modified_substring = shiftMatraPosition(modified_substring);

		modified_substring = modified_substring.replace("ाे", "ो");
		modified_substring = modified_substring.replace("ाै", "ौ");

		return modified_substring;

	} // end of the function Replace_Symbols

	private static String processMatras(String modified_substring) {
		// alert(" modified substring = "+modified_substring)

		// **********************************************************************************
		// Code for Replacing five Special glyphs
		// **********************************************************************************
		// "æ","ç","ê","ë",
		// "र्ä","र्å","र्è","र्é",

		// **********************************************************************************
		// Code for Glyph1 : È (reph+anusString)
		// **********************************************************************************
		modified_substring = modified_substring.replace("È", "Çं"); // at
		// some
		// places
		// ì is
		// used
		// eg
		// in
		// "कर्कंधु,पूर्णांक".

		// **********************************************************************************
		// Code for Glyph2 : ç (ekAr+reph+anusString)
		// **********************************************************************************
		modified_substring = modified_substring.replace("ç", "Çें");

		// **********************************************************************************
		// Code for Glyph3 : æ (ekAr+reph)
		// **********************************************************************************
		modified_substring = modified_substring.replace("æ", "Çे");

		// **********************************************************************************
		// Code for Glyph4 : ë (aikAr+reph+anusString)
		// **********************************************************************************
		modified_substring = modified_substring.replace("ë", "Çैं");

		// **********************************************************************************
		// Code for Glyph5 : ê (aikAr+reph)
		// **********************************************************************************
		modified_substring = modified_substring.replace("ê", "Çै");

		// **********************************************************************************
		// Code for Glyph6 : Ô ( ाÔ = reph + ी )
		// **********************************************************************************
		modified_substring = modified_substring.replace("ाÔ", "Çी");

		// **********************************************************************************
		// Code for Glyph7 : Ó ( ी + anusString )
		// **********************************************************************************
		modified_substring = modified_substring.replace("Ó", "ीं");

		// **********************************************************************************
		// Code for Glyph8 : Õ (ाÕ = reph + ी + anusString )
		// **********************************************************************************
		modified_substring = modified_substring.replace("ाÕ", "Çीं");

		// **********************************************************************************
		// Code for Glyph09 : Ì ( reph + ि )
		// Code for Glyph10 : Î (ि) [ikAr mAtrA before a संयुक्ताक्षर as
		// in
		// क्लिष्ट, स्थित etc)
		// replace "Ê" and "Î" with "ि" and correcting its position
		// too(moving it one position forward)
		// **********************************************************************************

		modified_substring = modified_substring.replace("ÉÎ", "EÊ");
		modified_substring = modified_substring.replace("Ì", "र्Ê"); // at
		// some
		// places
		// Ì is
		// used
		// eg
		// in
		// "धार्मिक".
		modified_substring = modified_substring.replace("Î", "Ê");
		return modified_substring;
	}

	private static String shiftMatraPosition(String modified_substring) {
		int position_of_i = modified_substring.indexOf("Ê");

		while (position_of_i != -1) // while-02
		{
			char charecter_next_to_i = modified_substring.charAt(position_of_i + 1);
			String charecter_to_be_replaced = "Ê" + charecter_next_to_i;
			modified_substring = modified_substring.replace(charecter_to_be_replaced, charecter_next_to_i + "ि");
			position_of_i = modified_substring.indexOf("Ê");// Search
			// for
			// i
			// ahead
			// of
			// the
			// current
			// position.

		} // end of while-02 loop

		// **********************************************************************************
		// Code for Glyph8 : Ë ("िं")
		// Code for Glyph9 : Í ("र्िं")
		// replace Ë with "िं" and correcting its position too(moving it
		// two
		// positions forward)
		// **********************************************************************************
		modified_substring = modified_substring.replace("Í", "र्Ë"); // at
		// some
		// places
		// Í is
		// used
		// eg
		// in
		// "शर्मिंदा"
		modified_substring = modified_substring.replace("Ë", "ÊÆ"); // at
		// some
		// places
		// Ë is
		// used
		// eg
		// in
		// "किंकर".

		position_of_i = modified_substring.indexOf("ÊÆ");

		while (position_of_i != -1) // while-02
		{
			char charecter_next_to_ip2 = modified_substring.charAt(position_of_i + 2);
			String charecter_to_be_replaced = "ÊÆ" + charecter_next_to_ip2;
			modified_substring = modified_substring.replace(charecter_to_be_replaced, charecter_next_to_ip2 + "िं");
			position_of_i = modified_substring.substring(position_of_i + 2).indexOf("ÊÆ");// search
			// for
			// i
			// ahead
			// of
			// the
			// current
			// position.

		} // end of while-02 loop

		// **********************************************************************************
		// End of Code for Replacing four Special glyphs
		// **********************************************************************************

		// following loop to eliminate 'chhotee ee kee maatraa' on
		// half-letters as a result of above transformation.

		int position_of_wrong_ee = modified_substring.indexOf("ि्");

		while (position_of_wrong_ee != -1) { // while-03

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

		// Eliminating reph "Ç" and putting 'half - r' at proper position
		// for this.
		String set_of_matras = "ा ि ी ु ू ृ े ै ो ौ ं : ँ ॅ";
		int position_of_R = modified_substring.indexOf("Ç");

		while (position_of_R > 0) // while-04
		{
			int probable_position_of_half_r = position_of_R - 1;
			char character_at_probable_position_of_half_r = modified_substring.charAt(probable_position_of_half_r);

			// trying to find non-maatra position left to current O (ie,
			// half -r).

			while (set_of_matras.indexOf(character_at_probable_position_of_half_r) != -1) { // while-05
				probable_position_of_half_r = probable_position_of_half_r - 1;
				character_at_probable_position_of_half_r = modified_substring.charAt(probable_position_of_half_r);

			} // end of while-05

			String charecter_to_be_replaced = modified_substring.substring(probable_position_of_half_r,
					position_of_R);
			String new_replacement_string = "र्" + charecter_to_be_replaced;
			charecter_to_be_replaced = charecter_to_be_replaced + "Ç";
			modified_substring = modified_substring.replace(charecter_to_be_replaced, new_replacement_string);
			position_of_R = modified_substring.indexOf("Ç");

		} // end of while-04
		return modified_substring;
	}
}
