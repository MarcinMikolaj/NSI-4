package encryption;

public class Base64 {
	
	private final static String base64Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	
	static String encodeString(String s) {
		
		String r = ""; //encoded string
		String p = ""; //padding string
		
		int c = s.length() % 3;
		
		if(c > 0) {
			for(; c < 3; c++) {
				p += "=";
				s += "\0"; //called a NUL in ASCII
			}	
		}
		
		for(int b = 0; b < s.length(); b += 3) {
			
			if(b > 0 &&  (b / 3 * 4) % 76 == 0) {
				r += "\r\n"; //Przejście do nowego wiersza (Line feed - LF)	, Powrót karetki
			}
			
			
			// these three 8-bit (ASCII) characters become one 24-bit number
			// << Przesunięcie bitowe w lewo 
			int n = (s.charAt(b) << 16) + (s.charAt(b+1) << 8) + (s.charAt(b+2));
			
			// this 24-bit number gets separated into four 6-bit numbers
			int n1 = (n >> 18) & 63;
			int n2 = (n >> 12) & 63;
			int n3 = (n >> 6) & 63;
			int n4 = n & 63;
				
			r +=  String.valueOf(base64Chars.charAt(n1)) +  String.valueOf(base64Chars.charAt(n2)) 
					+  String.valueOf(base64Chars.charAt(n3)) +  String.valueOf(base64Chars.charAt(n4));
		}

		return r.substring(0, r.length() - p.length()) + p;
	}
	
}
