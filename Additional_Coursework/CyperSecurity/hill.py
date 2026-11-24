def multiply_matrix(a, b, mod):
    """Multiply two matrices a and b under modulo."""
    n = len(a)
    result = [[0] * n for _ in range(n)]
    for i in range(n):
        for j in range(n):
            for k in range(n):
                result[i][j] += a[i][k] * b[k][j]
                result[i][j] %= mod
    return result

def matrix_determinant(a, mod):
    """Calculate the determinant of matrix a under modulo."""
    n = len(a)
    det = 1
    for i in range(n):
        max_row = i
        for j in range(i+1, n):
            if abs(a[j][i]) > abs(a[max_row][i]):
                max_row = j
        a[i], a[max_row] = a[max_row], a[i]
        det *= a[i][i]
        sign = -1 ** (max_row - i)
        det %= mod
    for i in range(i+1, n):
        det = (det * (mod - abs(a[i][i]))) % mod
    return det

def inverse_matrix(matrix, mod):
    """Calculate the modular inverse of a matrix."""
    determinant = matrix_determinant(matrix, mod)
    if determinant == 0:
        raise ValueError("Determinant is zero; no inverse exists.")

    # Find modular inverse of determinant
    def extended_gcd(a, b):
        if b == 0:
            return (a, 1, 0)
        else:
            g, x, y = extended_gcd(b, a % b)
            return (g, y, x - (a // b) * y)

    inverse_det, x, y = extended_gcd(determinant, mod)
    if inverse_det != 1:
        raise ValueError("Determinant is not invertible modulo {}".format(mod))

    # Find adjugate matrix
    n = len(matrix)
    adjugate = [[0]*n for _ in range(n)]
    for i in range(n):
        for j in range(n):
            if i == j:
                adjugate[i][j] = determinant * pow(determinant, mod-2, mod) % mod
            else:
                adjugate[i][j] = (-matrix[j][i]) % mod

    inverse = [[(adjugate[i][j] * inverse_det) % mod for j in range(n)]
               for i in range(n)]
    return inverse

def hill_encrypt(message, key_matrix, mod=26):
    """Encrypt a message using the Hill Cipher."""
    # Convert message to numbers
    message_nums = [ord(c.upper()) - ord('A') for c in message]

    n = len(key_matrix)
    m_len = len(message_nums)

    if (m_len % n) != 0:
        padding = (n - (m_len % n)) * 'Z'
        message_nums += [ord(p) - ord('A') for p in padding]

    # Split into blocks
    block_size = n
    encrypted_blocks = []

    for i in range(0, len(message_nums), block_size):
        block = message_nums[i:i+block_size]
        pad = block[-1] * ((block_size - (len(block))) % block_size)
        padded_block = block + [pad] * ((block_size - (len(block))) % block_size)

        # Convert to matrix
        plain_matrix = [[padded_block[j + i] for j in range(block_size)]
                       for i in range(block_size)]

        # Multiply with key matrix
        cipher_matrix = multiply_matrix(plain_matrix, key_matrix, mod)

        encrypted_blocks += [num % mod for num in flatten(cipher_matrix)]

    # Convert back to string
    ciphertext = ''.join([chr(num + ord('A')) for num in encrypted_blocks])
    return ciphertext

def hill_decrypt(ciphertext, key_matrix, mod=26):
    """Decrypt a message using the Hill Cipher."""
    try:
        inv_key_matrix = inverse_matrix(key_matrix, mod)
    except ValueError as e:
        raise e

    # Convert ciphertext to numbers
    ciphertext_nums = [ord(c.upper()) - ord('A') for c in ciphertext]

    n = len(key_matrix)
    m_len = len(ciphertext_nums)

    if (m_len % n) != 0:
        raise ValueError("Ciphertext length must be divisible by key matrix size.")

    # Split into blocks
    decrypted_blocks = []

    for i in range(0, len(ciphertext_nums), n):
        block = ciphertext_nums[i:i+n]
        # Convert to matrix
        cipher_matrix = [[block[j + i] for j in range(n)]
                        for i in range(n)]

        # Multiply with inverse key matrix
        plain_matrix = multiply_matrix(cipher_matrix, inv_key_matrix, mod)

        decrypted_block = [num % mod for num in flatten(plain_matrix)]

        decrypted_blocks += decrypted_block

    # Convert back to string
    plaintext = ''.join([chr(num + ord('A')) for num in decrypted_blocks])
    return plaintext

def flatten(matrix):
    """Flatten a matrix into a single list."""
    return [element for row in matrix for element in row]

# Example usage:
key_matrix = [[5, 1], [3, 2]]
message = "HELLO WORLD"
ciphertext = hill_encrypt(message, key_matrix)
print("Ciphertext:", ciphertext)

try:
    decrypted_message = hill_decrypt(ciphertext, inverse_matrix(key_matrix))
    print("Decrypted message:", decrypted_message)
except ValueError as e:
    print(e)