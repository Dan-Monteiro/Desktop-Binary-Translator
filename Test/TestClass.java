import static org.junit.Assert.*;

import org.junit.Test;

import com.dan.lt.BryTranslt;

public class TestClass {

	@Test
	public void testTranslation() {
		
		BryTranslt c = new BryTranslt(); 
		String actual = c.getBinariesFromText("Dan Çan");
		System.out.println(actual);
		assertEquals("01000100 01100001 01101110 00100000 11000111 01100001 01101110 ", actual);
	}
}
