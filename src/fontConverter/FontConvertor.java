package fontConverter;

public class FontConvertor {

	public static void main(String[] args) {
		String output = DV_To_Unicode
				.convertToUnicode("+É´ÉvÉÉÉË®iÉ");
		System.out.println(output);
		output = DV_To_Unicode
				.convertToUnicode("ÉËcnÉÒ");
		System.out.println(output);

	}

}