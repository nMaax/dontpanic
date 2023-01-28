package dontpanic;

/**
 * A collection of functions that you can copy and paste in your code where needed.
 * */
public class Utils {
	
	/**
	 * Given an integer to format and a second integer indicating in how many digits it should be formatted, returns a string with the formatted number composed of as many zeros in front as are missing.
	 * <br><br>
	 * <mark>Alternativa nativa di Java 5: String.format()</mark> <i>(guarda il commento dentro il metodo per approfondire)</i>
	 * <hr>
	 * e.g.
	 * <br><br>
	 * <p style="font-family:'Courier New'">num = 3, digits = 3 --> "003"</p>
	 * <p style="font-family:'Courier New'">num = 100, digits = 5 --> "00100"</p>
	 * <p style="font-family:'Courier New'">num = 42, digits = 2 --> "42"</p>
	 * <p style="font-family:'Courier New'">num = 654, digits = 1 --> "654"</p>
	 * @param num The number to be formatted
	 * @param digits The number of digits required
	 * @return The String rappresenting the formatted number
	 * */
	public String numberZerosFormatter(int num, int digits) {
		
		/*
		 * Alternativa: 
		 * 
		 * Java fornisce il metodo String.format() per formattare i numeri
		 * che fa esattamente la stessa identica cosa
		 *	
		 *	e.g.
		 *		
		 *		String.format("%04d", 42) --> 0042 
		 *		String.format("%03d", 42) -->  042
		 *		String.format("%02d", 42) -->   42
		 *		String.format("%01d", 42) -->   42
		 * 
		 * */
		
		String output = "" + num;
		while (output.length() < digits) {
			output = "0" + output;
		}
		return output;
	}
	
	/**
	 * Returns a boolean indicating whether the given string contains any of the characters in the given charset.
	 * @param s The string to search in.
	 * @param charset The set of characters to search for.
	 * @return true if the string contains any of the characters in the charset, false otherwise.
	 * */
	public static boolean containsCharset(String s, String charset) {
		boolean output = false;
		char[] uppercaseLetters = charset.toCharArray();
		for (char c : uppercaseLetters) {
			if (s.indexOf(c) != -1) {
				output = true;
			}
		}
		return output;
	}
	
}
