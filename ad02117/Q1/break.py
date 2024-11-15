#!/usr/bin/env python3
import sys

# DO NOT ADD ANY import STATEMENTS

# DO NOT CHANGE THIS LINE NOR ANYTHING ABOVE THIS LINE

def reverse_permutation(block):
    return [block[3], block[1], block[0], block[2]]

def encipher(block, key):
    block = [int(digit) for digit in block]
    key1 = [int(k) for k in key[:4]]
    key2 = [int(k) for k in key[4:]]

    permuted1 = [block[2], block[1], block[3], block[0]]
    added1 = [(permuted1[i] + key1[i]) % 10 for i in range(4)]
    permuted2 = [added1[2], added1[1], added1[3], added1[0]]
    cipher = [(permuted2[i] + key2[i]) % 10 for i in range(4)]

    return ''.join(map(str, cipher))

def decrypt_message(ciphertext, key):
    ciphertext = [int(digit) for digit in ciphertext]
    key1 = [int(k) for k in key[:4]]
    key2 = [int(k) for k in key[4:]]

    subtracted2 = [(ciphertext[i] - key2[i]) % 10 for i in range(4)]
    permuted2 = reverse_permutation(subtracted2)

    subtracted1 = [(permuted2[i] - key1[i]) % 10 for i in range(4)]
    decrypted = reverse_permutation(subtracted1)

    return ''.join(map(str, decrypted))

def breakcipher(ciphertext):
    plaintext = "0123"  # Known plaintext 
    known_ciphertext = "7468"  # Known ciphertext 

    for k in range(1000000000):  
        key = f"{k:08d}"  
        if encipher(plaintext, key) == known_ciphertext:
            print(f"Found key: {key}")  # Debugging output
            return decrypt_message(ciphertext, key)  # Return decrypted message using the found key

    return "No key found"


# DO NOT CHANGE THIS LINE NOR ANYTHING BELOW THIS LINE

def non_digits(s):
    return len(s.strip("0123456789")) > 0

if __name__ == "__main__":
    if len(sys.argv) != 1:
        print('''Usage: break.py

        Call this program without any command line arguments. Then enter
        blocks of 4 decimal digits to be decrypted by (your) implementation.
        ''')
        sys.exit(1)

    print("Go ahead, type in your ciphertext consisting of blocks of 4 decimal digits.")
    print("Do NOT input any brackets [ ] or commas")
    for line in sys.stdin:
        line = line.strip().replace(" ","")
        if non_digits(line):
            print("Error: Input contains non-decimal digit characters.")
            sys.exit(1)
        if len(line) % 4 != 0:
            print("Error: The length of your input should be, but is not, a multiple of\n      the cipher's block size (which is 4).")
            sys.exit(1)
        while line!="":
            print(breakcipher(line[0:4]))
            line=line[4:]
