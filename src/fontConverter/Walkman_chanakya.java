package fontConverter;

public class Walkman_chanakya {
	// This is useful for converting PDF books uploaded by NCERT on internet.
	// last upadate 16-Dec-2011

	private static String array_one[] = { "ñ", "॰", "Q+Z", "QZ+", "sas", "sa",
			"aa", "a", "¼Z", "र्द्ध", "ZZ", "Z",

			"å", "०", "ƒ", "१", "„", "२", "…", "३", "†", "४", "‡", "५", "ˆ",
			"६", "‰", "७", "Š", "८", "‹", "९", "¶+", "फ़्", "d+", "क़", "[+k",
			"ख़", "[+", "ख़्", "x+", "ग़", "T+", "ज़्", "t+", "ज़", "M+", "ड़",
			"<+", "ढ़", "Q+", "फ़", ";+", "य़", "j+", "ऱ", "u+", "ऩ", "Ùk", "त्त",
			"Ù", "त्त्", "ä", "क्त", "–", "दृ", "—", "कृ", "é", "न्न", "™",
			"न्न्", "=kk", "=k", "f=k", "f=", "à", "ह्न", "á", "ह्य", "â",
			"हृ", "ã", "ह्म", "ºz", "ह्र", "º", "ह्", "í", "द्द", "{k", "क्ष",
			"{", "क्ष्", "f=", "त्रि", "=k", "त्र", "«", "त्र्", "Nî", "छ्य",
			"Vî", "ट्य", "Bî", "ठ्य", "Mî", "ड्य", "<î", "ढ्य", "|", "द्य",
			"K", "ज्ञ", "}", "द्व", "J", "श्र", "Vª", "ट्र", "Mª", "ड्र", ">ª",
			"ढ्र", "Nª", "छ्र", "Ø", "क्र", "Ý", "फ्र", "nzZ", "र्द्र", "æ",
			"द्र", "ç", "प्र", "Á", "प्र", "xz", "ग्र", "#", "रु", ":", "रू",
			"v‚", "ऑ", "vks", "ओ", "vkS", "औ", "vk", "आ", "v", "अ", "b±", "ईं",
			"Ã", "ई", "bZ", "ई", "b", "इ", "mQ", "ऊ", "m", "उ", "Å", "ऊ", ",s",
			"ऐ", ",", "ए", "½", "ऋ", "ô", "क्क", "d", "क", "Dk", "क", "D",
			"क्", "£", "र्f", "[k", "ख", "[", "ख्", "x", "ग", "Xk", "ग", "X",
			"ग्", "Ä", "घ", "?k", "घ", "?", "घ्", "³", "ङ", "p", "च", "Pk",
			"च", "P", "च्",

			"N", "छ",

			"”k", "ज", "”", "ज्",

			"t", "ज", "Tk", "ज", "T", "ज्", ">", "झ", "÷", "झ्", "¥", "ञ", "ê",
			"ट्ट", "ë", "ट्ठ", "V", "ट", "B", "ठ", "ì", "ड्ड", "ï", "ड्ढ",
			"M+", "ड़", "<+", "ढ़", "M", "ड", "<", "ढ", ".k", "ण", ".", "ण्",
			"r", "त", "Rk", "त", "R", "त्", "Fk", "थ", "F", "थ्", "n", "द",
			"/", "ध", "èk", "ध", "è", "ध्", "Ë  ", "ध्", "u", "न", "Uk", "न",
			"U", "न्", "iQ", "फ", "i", "प", "Ik", "प", "I", "प्", "¶", "फ्",
			"c", "ब", "Ck", "ब", "C", "ब्", "Hk", "भ", "H", "भ्", "e", "म",
			"Ek", "म", "E", "म्", ";", "य", "¸", "य्", "j", "र", "y", "ल",
			"Yk", "ल", "Y", "ल्", "G", "ळ", "oQ", "क", "o", "व", "Ok", "व",
			"O", "व्",

			"'k", "श", "'", "श्",

			"Ük", "श", "Ü", "श्",

			"\"k", "ष", "\"", "ष्", "l", "स", "Lk", "स", "L", "स्", "g", "ह",
			"È", "ीं", "z", "्र", "Ì", "द्द", "Í", "ट्ट", "Î", "ट्ठ", "Ï",
			"ड्ड", "Ñ", "कृ", "Ò", "भ", "Ó", "्य", "Ô", "ड्ढ", "Ö", "झ्", "Ø",
			"क्र", "Ù", "त्त्", "¼", "द्ध",
			"Ú",
			"फ्र",
			"É",
			"ह्न",

			// following block added on 19-3-2011
			"Ů", "त्त्", "Ľ", "द्ध", "˝", "ऋ", "Ř", "क्र", "Ń", "कृ", "Q",
			"फ़", "č", "ध्", "Ş", "्र",

			"‚", "ॉ", "¨", "ो", "ks", "ो", "©", "ौ", "kS", "ौ", "k", "ा", "h",
			"ी", "q", "ु", "w", "ू", "`", "ृ", "s", "े", "¢", "े", "S", "ै",
			"a", "ं", "¡", "ँ", "ˇ", "ँ", "%", "ः", "W", "ॅ", "•", "ऽ", "·",
			"ऽ", "∙", "ऽ", "·", "ऽ", "+", "़", "\\", "?",

			"‘", "\"", "’", "\"", "“", "\'",
			// "\”" , "\'" ,

			"^", "‘", "*", "’", "Þ", "“", "ß", "”", "¾", "=", "&", "-", "μ",
			"-", "¿", "{", "À", "}", "A", "।",
			// "-" , "." ,
			"Œ", "॰", "]", ",", "@", "/",

			" ः", ":", "~", "्", "्ा", "", "ाे", "ो", "ाॅ", "ॉ",

			"अौ", "औ", "अो", "ओ", "आॅ", "ऑ" };

	// **********************************************

	private static int array_one_length = array_one.length;

	public static String convertToUnicode(String inputText) {
		if (inputText == null || inputText.isEmpty()) {
			return "";
		}

		String modified_substring = inputText;
		String outputText = "";

		// ****************************************************
		// Break the long text into small bunches of chunk_size characters each.
		// ****************************************************
		int text_size = modified_substring.length();

		String processed_text = ""; // blank

		int sthiti1 = 0;
		int sthiti2 = 0;
		int chale_chalo = 1;

		int chunk_size = 6000; // this charecter long text will be processed in
								// one go.

		while (chale_chalo == 1) {
			sthiti1 = sthiti2;

			if (sthiti2 < (text_size - chunk_size)) {
				sthiti2 += chunk_size;
			} else {
				sthiti2 = text_size;
				chale_chalo = 0;
			}

			modified_substring = inputText.substring(sthiti1, sthiti2);

			modified_substring = Replace_Symbols(modified_substring);

			processed_text += modified_substring;

			outputText = processed_text;

		}

		return outputText;

	}
	
	private static String Replace_Symbols(String modified_substring) {
		// substitute array_two elements in place of corresponding array_one
		// elements

		if (modified_substring != "") // if stringto be converted is non-blank
										// then no need of any processing.
		{

			modified_substring = Utils.jsReplace(modified_substring,"([ZzsSqwa¡`]+)Q",
					"Q$1");

			for (int input_symbol_idx = 0; input_symbol_idx < array_one_length - 1; input_symbol_idx = input_symbol_idx + 2){
				// ******************************************************
				int idx = 0; // index of the symbol being searched for
								// replacement

				while (idx != -1) // while-00
				{
					modified_substring = modified_substring.replace(
							array_one[input_symbol_idx],
							array_one[input_symbol_idx + 1]);
					idx = modified_substring
							.indexOf(array_one[input_symbol_idx]);
				} // end of while-00 loop

			} // end of for loop

			modified_substring = Utils.jsReplace(modified_substring, "([ेैुूं]+)्र",
					"्र$1");
			modified_substring = Utils.jsReplace(modified_substring, "ं([ाेैुू]+)",
					"$1ं");

			modified_substring = Utils.jsReplace(modified_substring, "([ \n])ा", "$1श");

			modified_substring = Utils.jsReplace(modified_substring, "¯", "f");
			modified_substring = Utils.jsReplace(modified_substring, "Ł", "र्f");

			modified_substring = Utils.jsReplace(modified_substring, 
					"([fŻ])([कखगघङचछजझञटठडड़ढढ़णतथदधनपफबभमयरलवशषसहक्ष])", "$2$1");

			modified_substring = Utils.jsReplace(modified_substring, 
					"([fŻ])(्)([कखगघङचछजझञटठडड़ढढ़णतथदधनपफबभमयरलवशषसहक्ष])",
					"$2$3$1");

			modified_substring = Utils.jsReplace(modified_substring, 
					"([fŻ])(्)([कखगघङचछजझञटठडड़ढढ़णतथदधनपफबभमयरलवशषसहक्ष])",
					"$2$3$1");

			modified_substring = Utils.jsReplace(modified_substring, "f", "ि");
			modified_substring = Utils.jsReplace(modified_substring, "Ż", "िं");

			// following three statement for adjusting position of reph ie, half
			// r .
			modified_substring = Utils.jsReplace(modified_substring, "±", "Zं");

			modified_substring = modified_substring
					.replace(
							"([कखगघचछजझटठडड़ढढ़णतथदधनपफबभमयरलळवशषसहक्षज्ञ])([ािीुूृेैोौंँ]*)([Z])",
							"$3$1$2");

			modified_substring = Utils.jsReplace(modified_substring, 
					"([कखगघचछजझटठडड़ढढ़णतथदधनपफबभमयरलळवशषसहक्षज्ञ])([्])([Z])",
					"$3$1$2");

			modified_substring = Utils.jsReplace(modified_substring, "Z", "र्");

		} // end of IF statement meant to supress processing of blank string.

		return modified_substring;
	} // end of the function Replace_Symbols

}
