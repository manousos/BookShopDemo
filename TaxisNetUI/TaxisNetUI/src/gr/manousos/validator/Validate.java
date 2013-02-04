package gr.manousos.validator;

public class Validate {

	public static boolean isValidAFM(String AFM) {

		int AFM9_Total = 0, AFM9_var, AFM9_res;
		int[] afmDigits = new int[9];
		AFM9_var = 512;

		if (!(AFM.length() == 10 && AFM != "000000000"))
			return false;

		for (int i = 0; i < AFM.length() - 1; i++) {
			char d = AFM.charAt(i);
			if (!Character.isDigit(d))
				return false;

			AFM9_var = AFM9_var / 2;
			AFM9_Total = AFM9_var + (Integer.valueOf(d) * AFM9_var);
		}
		
		AFM9_res = AFM9_Total % 11;

		if (AFM9_res > 9)
			AFM9_res = 0;
		if (afmDigits[9] != AFM9_res)
			return false;

		return true;
	}
}
