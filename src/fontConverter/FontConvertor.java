package fontConverter;

public class FontConvertor {

	public static void main(String[] args) {
		String output = DV_To_Unicode
				.convertToUnicode("+ÉÆiÉMÉÇiÉ, ªÉÖr ªÉÉ ¤ÉÉÿªÉ +ÉÉµÉEàÉhÉ ªÉÉ ");
		System.out.println(output);
		output = DV_To_Unicode
				.convertToUnicode("+ÉÉÆBÉE1⁄2ä");
		System.out.println(output);

	}

}