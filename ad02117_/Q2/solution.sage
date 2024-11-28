# solution.sage
from sage.all import Integer

def rsa_decrypt_small_e3(c):
    
    # Compute the integer cube root and check for exactness
    m, exact = Integer(c).nth_root(3, truncate_mode=True)

    if not exact:
        print("ERROR: The cube root is not exact. The ciphertext might not follow m^3 < N.")
        exit(1)

    # Convert to string and decode as UTF-8
    try:
        plaintext = bytes.fromhex(hex(m)[2:]).decode('utf-8')
        print(f"Decrypted message: {plaintext}")
    except UnicodeDecodeError:
        print("Failed to decode the message as UTF-8.")


if __name__ == "__main__":
    # Input values
    c = Integer(input("Enter ciphertext c: "))
    
    # Decrypt the ciphertext
    rsa_decrypt_small_e3(c)
