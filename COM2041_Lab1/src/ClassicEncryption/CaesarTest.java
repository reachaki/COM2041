package ClassicEncryption;

import static org.junit.Assert.*;

import javax.crypto.EncryptedPrivateKeyInfo;

import org.junit.Test;

public class CaesarTest extends Caesar {

	@Test
	public void rotTest() {
		assertEquals('g', rot('d', 3));
		assertEquals('a', rot('x', 3));
		assertEquals('G', rot('D', 3));
		assertEquals('A', rot('X', 3));
		assertEquals('5', rot('5', 3));
		assertEquals(']', rot(']', 3));
		assertEquals(' ', rot(' ', 3));
	}

	@Test
	public void charAddTest() {
		assertEquals('e', charAdd('d', 'b'));
		assertEquals('!', charAdd('!', 'e'));
		assertEquals(' ', charAdd(' ', 'e'));
		assertEquals('E', charAdd('D', 'b'));
		assertEquals('e', charAdd('d', 'B'));
		assertEquals('E', charAdd('D', 'B'));
		assertEquals('b', charAdd('x', 'e'));
		assertEquals('B', charAdd('X', 'e'));
		assertEquals('b', charAdd('x', 'E'));
		assertEquals('B', charAdd('X', 'E'));

	}

	@Test
	public void inverseCharTest() {
		assertEquals('a', inverseChar('a'));
		assertEquals('p', inverseChar('l'));
		assertEquals('h', inverseChar('t'));
		assertEquals('A', inverseChar('A'));
		assertEquals('P', inverseChar('L'));
		assertEquals('H', inverseChar('T'));
	}

	@Test
	public void caesarEncryptTest() {
		assertEquals("Ufyu", caesarEncrypt("Text", 1));
		assertEquals("Yjcy", caesarEncrypt("Text", 5));

	}

	@Test
	public void vigenereEncryptTest() {
		assertEquals("Ugyv", vigenereEncrypt("Text", "bc"));
		assertEquals("Ffsw ge y fipk poej fccx", vigenereEncrypt("This is a very real test", "MYKEY"));
	}

	@Test
	public void vigenereDecryptTest() {
		assertEquals("Text", vigenereDecrypt("Ugyv", "bc"));
		assertEquals("This is a very real test", vigenereDecrypt("Ffsw ge y fipk poej fccx", "MYKEY"));
	}

}
