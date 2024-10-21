#!/usr/bin/env python3
import sys

# DO NOT ADD ANY import STATEMENTS

# DO NOT CHANGE THIS LINE NOR ANYTHING ABOVE THIS LINE

# You can add functions here to support your implementation below

def breakcipher(block): # This function must return a string of 4 decimal digits
    plaintext = "0000" 

    # YOUR IMPLEMENTATION HERE
    
    return plaintext

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
