package fontConverter;

public class FontConvertor {

	public static void main(String[] args) {

		String output = "";
		// output= DV_To_Unicode
		// .convertToUnicode("{ÉÖ®&ºlÉÉÉÊ{ÉiÉ xÉcÉÓ ÉÊBÉEªÉÉ VÉÉAMÉÉ *");
		// System.out.println(output);
		// output = DV_To_Unicode
		// .convertToUnicode("ÉËcnÉÒ");
		// System.out.println(output);

		// output = Kruti_To_Unicode
		// .convertToUnicode("jk\"Vah; ikB~;p;kZ dh :ijs[kk");

		output = Walkman_chanakya
				.convertToUnicode("foKku lHkh izdkj osQ thou jpuk ,oa tSo  izØeksa dk foKku gSA tho txr dkSrqgy");
		System.out.println(output);

	}
}