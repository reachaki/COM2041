#!/usr/bin/env python3
import sys

# DO NOT ADD ANY import STATEMENTS

# DO NOT CHANGE THIS LINE NOR ANYTHING ABOVE THIS LINE

# You can implement additional functions here for your implementation below.

def encipher(block,key):
    cipher = "0000"  # This is the expected output format: 4 consecutive digits

    # YOUR IMPLEMENTATION HERE
    
    return cipher  # This function must return a string of 4 consecutive digits

# DO NOT CHANGE THIS LINE NOR ANYTHING BELOW THIS LINE

def non_digits(s):
    return len(s.strip("0123456789")) > 0

if __name__ == "__main__":
    if len(sys.argv) != 2 or len(sys.argv[1]) != 8 or non_digits(sys.argv[1]):
        print('''Usage: encipher.py <key>
        
        <key> is a string of 8 decimal digits used as the encryption key.

        Call this program with the 8-digit key as a command line argument.
        Then enter blocks of 4 decimal digits to be encrypted under that key
        by (your) implementation (see the source code of this file).
        ''')
        sys.exit(1)

    key = sys.argv[1]
        
    print("Go ahead, type in your plaintext consisting of blocks of 4 decimal digits.")
    print("Do NOT input any brackets [ ] or commas")
    for line in sys.stdin:
        line = line.strip().replace(" ","")
        if non_digits(line):
            print("Error: Input contains non-decimal digit characters.")
            sys.exit(1)
        if len(line) % 4 != 0:
            print("Error: Length of input should be, but is not, a multiple of the cipher's block size (which is 4)")
            sys.exit(1)
        while line!="":
            print(encipher(line[0:4],key))
            line=line[4:]
