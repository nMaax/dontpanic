package dontpanic;

import java.time.*;


/**
 * A collection of functions that you can copy and paste in your code when needed.
 * */
public class Utils {
	
	/*
	 
	 *** Cose da sapere utili ***
	1.	Se devi comparare due valori double in un metodo int compareTo() non è necessario castare
		da double a int (anzi, perderesti dei valori decimali che potrebbero falsificare il risultato)
		usa invece la funzione Double.compare(double d1, double d2)
			
			e.g. 
			
				@Override
				public int compare(Prodotto o1, Prodotto o2) {
					NO --> return o1.getPrezzo() - o2.getPrezzo(); //dove getPrezzo() restituisce un double
					SI --> return Double.compare(o1.getPrezzo(), o2.getPrezzo());
				}
				
	2. 	Java fornisce il metodo String.format() per formattare i numeri con gli zero davanti
		https://stackoverflow.com/questions/6431933/how-to-format-strings-in-java
		https://www.javatpoint.com/java-string-format
		
		 	e.g.
		 		
				String.format("%04d", 42) --> 0042 
		 		String.format("%03d", 42) -->  042
		 		String.format("%02d", 42) -->   42
		 		String.format("%01d", 42) -->   42
	 
	 * */
	
	/**
	 * Given two TimestampHandler returns the duration between them using the java.time library.
	 * <br><br>
	 * Edit this method has you need it: you can change toDays() to toYear(), toMonth(), toHour() etc.
	 * <br>
	 * Or you can just return an instance of Duration class and then call on it the methods above reported.
	 * <br>
	 * Another idea is to create a version of this method using strings as parameters...
	 * <br><br>
	 * Note: Import java.time.*; in order to use this method 
	 * <br>
	 * This library has some methods NOT inclueded in java 1.8, so control wich are usable and which not with the javadoc
	 * Thats why i decided to put this fuction here and not as methods of TimestampHandler and TimestampHandlerLite classes,
	 * I wanted those classes to be 100% safe with java 1.8, without importing any external library
	 * @param first The first timestamp
	 * @param second The second timestamp
	 * @return a long variable reporting the days between first and second
	 * */
	public long durationBetween(TimestampHandler first, TimestampHandler second) {
	    LocalDateTime fsTimestamp = LocalDateTime.of(first.getYear(), first.getMonth(), first.getDay(), first.getHour(), first.getMinute(), first.getSecond());
	    LocalDateTime snTimestamp = LocalDateTime.of(second.getYear(), second.getMonth(), second.getDay(), second.getHour(), second.getMinute(), second.getSecond());
	    
	    /*Cambia da toDays a toYears, toMonth, ... per altri tipi di valore*/
	    return Duration.between(fsTimestamp, snTimestamp).toDays();
	}
	
	/**
	 * Given an integer to format and a second integer indicating in how many digits it should be formatted, returns a string with the formatted number composed of as many zeros in front as are missing.
	 * <br><br>
	 * You could also use String.format() for the same purpose, 
	 * it is more readable and less prone to errors:
	 * <br><br>
	 * <code>String.format("%04d", 42) //output: 0042</code>
	 * <hr>
	 * e.g.
	 * <pre><code>
	 * num = 3 	digits = 3 	returns	"003"
	 * num = 100	digits = 5 	returns	"00100"
	 * num = 42 	digits = 2 	returns	"42"
	 * num = 654	digits = 1 	returns	"654"
	 * </code></pre>
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
	 * Sorts an array of integers in ascending order using the bubble sort algorithm.
	 * <mark>This method do not return a new array, it directly modifies the parameter.</mark>
	 * <br><br>
	 * Eventually you can adapt this code to all kinds of objects just by:
	 * <ol> 
	 * 	<li>implementing a compareTo() method</li>
	 * 	<li>modifing the if statement using compareTo()</li>
	 * </ol>
	 * @param arr the array to be sorted
	 * @return A boolean value, true if the array has been modified, false otherwise
	 */
	public boolean bubbleSort(int[] arr) {
		boolean output = false;
	    int n = arr.length;
	    for (int i = 0; i < n-1; i++) {
	        for (int j = 0; j < n-i-1; j++) {
	            if (arr[j] > arr[j+1]) {
	                // swap arr[j] and arr[j+1]
	                int temp = arr[j];
	                arr[j] = arr[j+1];
	                arr[j+1] = temp;
	                output = true;
	            }
	        }
	    }
	    return output;
	}

	
	/**
	 * Modifies an array removing holes, keeping the same order of values.
	 * <br><br>
	 * <mark>This method do not return a new array, it directly modifies the parameter.</mark>
	 * <br><br>
	  Eventually you can adapt this code to primitive types too
	 * @param array The array that needs to modified
	 * @return a boolean value, true if the array has been modified, false otherwise
	 * */
	public boolean shakeArray(Object[] array) {
		int lenght = array.length;
		
		if (lenght == 0) return false;
		
		boolean output = false;
		while(existsHoles(array)) {
			for (int i = 1; i < lenght; i++) {
				if (array[i-1] == null) {
					array[i-1] = array[i];
					array[i] = null;
					output = true;
				}
			}
		}
		
		return output;
	}
	
	/**
	 * Returns true if there is a null before a value different from null in an array
	 * Eventually you can adapt this code to primitive types too
	 * @param array The array that needs to be controlled
	 * @return a boolean value, false if all null values (if they exists) are all placed at the end of the array, true otherwise
	 * */
	public boolean existsHoles(Object[] array) {
		int lenght = array.length;
		boolean output = false;
		
		if (lenght == 0) return output;
		
		for (int i = 1; i < lenght; i++) {
			if (array[i-1] == null && array[i] != null) {
				output = true;
			}
		}
		
		return output; 
	}
	
	/**
	 * Given an array of objects returns true if it contains the other object passed by parameter. 
	 * Note: works only for if the method Object.equals() is defined into the memembers of the array.
	 * <br>
	 * Eventually you can adapt this code to primitive types too
	 * @param objects The array that needs to be controlled
	 * @param object The object that needs to be spotted into the array
	 * @return a boolean value, true if object is contained into the array, false otherwise
	 * */
	public static boolean objArrayContains (Object[] objects, Object object) {
		boolean output = false;
		for (Object objectsItem : objects) {
			if (objectsItem.equals(object)) {
				output = true;
			}
		}
		return output;
	}
	
	/**
	 * The printArray method prints all the elements of an array to the console.
	 * <br><br>
	 * <i>
	 * You could also use Arrays.toString(arr) for the same purpose, 
	 * it is more readable and less prone to errors as it does 
	 * not rely on special characters or loops.
	 * </i>
	 * <br><br>
	 * <code>System.out.println(Arrays.toString(arr));</code>
	 * <br><br>
	 * Eventually you can adapt this code to all kinds of objects
	 * @param arr the array to be printed
	 */
	public static void printArray(int[] arr) {
	    for (int i = 0; i < arr.length; i++) {
	        System.out.print(arr[i] + " ");
	    }
	    System.out.println();
	}

	
	/**
	 * Returns a boolean indicating whether the given string contains any of the characters in the given charset.
	 * <br><br>
	 * Common charsets:<br>
	 * <br>
	 * ALPHABET_UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";<br>
	 * alphabet_lower_case = "abcdefghijklmnopqrstuvwxyz";<br>
	 * DIGITS = "1234567890";<br>
	 * <br>
	 * @param s The string to search in.
	 * @param charset The set of characters to search for.
	 * @return true if the string contains any of the characters in the charset, false otherwise.
	 * */
	public static boolean containsCharset(String s, String charset) {
		boolean output = false;
		for (char c : charset.toCharArray()) {
			if (s.indexOf(c) != -1) {
				output = true;
			}
		}
		return output;
	}
	
	/**
	 * Returns true if the String passed as parameter is an email
	 * <br>
	 * More specifically returns true if and only if the parameter contains the char '@'
	 * @param email The email to be controlled
	 * @return A boolean value, true if it's an email, false otherwise
	 * */
	public static boolean validEmail(String email) {
		boolean output = false;
		if (email.indexOf('@') != -1)
			output = true;
		return output;
	}
	
}