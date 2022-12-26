package encryption;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import encryption.Base64;

public class Base64Test {
	
	@Test
	void base64EncodedTextMethodShouldReturnCorrectString1() {
		
		//given
		String toEncode = "hello";
		String result;
		
		//when
		result = Base64.encodeString(toEncode);
		
		//then
		assertEquals("aGVsbG8=", result);
	}
	
	@Test
	void base64EncodedTextMethodShouldReturnCorrectString2() {
		
		//given
		String toEncode = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
		String result;
		
		//when
		result = Base64.encodeString(toEncode);
		
		//then
		assertEquals("TG9yZW0gaXBzdW0gZG9sb3Igc2l0IGFtZXQsIGNvbnNlY3RldHVyIGFkaXBpc2NpbmcgZWxpdC4=", result);
	}
	

}
