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

//		output = Kruti_To_Unicode
//				.convertToUnicode("jk\"Vah; ikB~;p;kZ dh :ijs[kk");

		output = Walkman_chanakya.convertToUnicode("ge lHkh dks] fo'ks\"kdj 'kq\"d ekSle esa] LosVj vFkok laf'y\"V oL=kksa dks 'kjhj ls mrkjrs le;");
		System.out.println(output);

	}
}