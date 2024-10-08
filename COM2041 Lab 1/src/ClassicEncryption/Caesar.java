package ClassicEncryption;

public class Caesar {

	static char rot(char c, int shift) {
		if (Character.isLowerCase(c)) {
			return (char) ((c - 'a' + shift) % 26 + 'a');
		} else if (Character.isUpperCase(c)) {
			return (char) ((c - 'A' + shift) % 26 + 'A');
		} else {
			return c; // Non-alphabet characters remain unchanged
		}
	}

	static char charAdd(char c, char k) {
		if (Character.isLetter(k)) {
			int shift = Character.toLowerCase(k) - 'a'; // Convert key character to a shift amount
			return rot(c, shift);
		}
		return c; // Non-letter key characters remain unchanged
	}

	static String caesarEncrypt(String str, int shift) {
		StringBuilder encrypted = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			encrypted.append(rot(str.charAt(i), shift));
		}
		return encrypted.toString();
	}

	static String caesarDecrypt(String str, int shift) {
		return caesarEncrypt(str, 26 - (shift % 26));
	}

	static String vigenereEncrypt(String str, String key) {
		StringBuilder encrypted = new StringBuilder();
		int keyIndex = 0; // To track the position in the key

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			// Only process alphabetic characters
			if (Character.isLetter(c)) {
				char k = key.charAt(keyIndex % key.length()); // Get corresponding key character
				encrypted.append(charAdd(c, k));
				keyIndex++; // Move to the next key character
			} else {
				encrypted.append(c); // Append non-alphabet characters without change
			}
		}

		return encrypted.toString();
	}

	static String vigenereDecrypt(String str, String key) {
		StringBuilder decrypted = new StringBuilder();
		int keyIndex = 0; // To track the position in the key

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			// Only process alphabetic characters
			if (Character.isLetter(c)) {
				char k = key.charAt(keyIndex % key.length()); // Get corresponding key character
				// Inverse the character using negative shift
				int shift = Character.toLowerCase(k) - 'a';
				decrypted.append(rot(c, 26 - shift)); // Decrypt character
				keyIndex++; // Move to the next key character
			} else {
				decrypted.append(c); // Append non-alphabet characters without change
			}
		}

		return decrypted.toString();
	}

	static char inverseChar(char c) {
		if (Character.isLowerCase(c)) {
			return (char) ((26 - (c - 'a')) % 26 + 'a');
		} else if (Character.isUpperCase(c)) {
			return (char) ((26 - (c - 'A')) % 26 + 'A');
		} else {
			return c; // Non-letter characters remain unchanged
		}
	}

}
