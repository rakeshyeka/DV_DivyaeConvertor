package fontConverter;

public class FontConvertor {

	public static void main(String[] args) {
		String output = Modified_DV_To_Unicode
				.convertToUnicode("+ÉÆiÉMÉÇiÉ, ªÉÖr ªÉÉ ¤ÉÉÿªÉ +ÉÉµÉEàÉhÉ ªÉÉ ");
		System.out.println(output);
		output = Modified_DV_To_Unicode
				.convertToUnicode("ÉÊ´ÉµÉEàÉÉÒ");
		System.out.println(output);

	}

}