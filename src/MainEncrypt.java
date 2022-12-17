import java.math.BigInteger;
import java.sql.SQLOutput;
import java.util.Scanner;

public class MainEncrypt {


    public static void main(String[] args) {

        // The following initializes the delta value, and sum.
        // The Left and Right blocks of our plaintext, along with each 32 bit block of our key are declared here.             //16557142
        int delta = 0x9e3779b9;
        int sum  = 0;
        int L, R;
        int k1, k2, k3, k4;

        // Creating a scanner to take user input. The user types in the plaintext to be encrypted, emitting the "0x".
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter plaintext to be encrypted");
        String plaintext = scan.nextLine();

        // The plaintext entered by the user is split using substring into 2 blocks, and then converted to integer.
        L = (int) Long.parseLong(plaintext.substring(0, 8), 16);
        R = (int) Long.parseLong(plaintext.substring(8), 16);

        System.out.println(L);
        System.out.println(R);

        System.out.println(Integer.toHexString(L));
        System.out.println(Integer.toHexString(R));

        // The user types in they 128bit key to be used for encryption.
        // The key entered by the user is split using substring into 4 blocks, and then converted to integer.
        System.out.println("Enter the key");
        String key = scan.nextLine();
        k1 = (int) Long.parseLong(key.substring(0, 8), 16);
        k2 = (int) Long.parseLong(key.substring(8, 16), 16);
        k3 = (int) Long.parseLong(key.substring(16, 24), 16);
        k4 = (int) Long.parseLong(key.substring(24, 32), 16);

        // For loop that iterates 32 rounds of encryption. The bits are manipulated and shifted left and right, and xor'ed. (right shift is unsigned from the >>>).
        for (int i = 0; i < 32; i++) {
            sum += delta;
            L += ((R << 4) + k1) ^ (R + sum) ^ ((R >>> 5) + k2);
            R += ((L << 4) + k3) ^ (L + sum) ^ ((L >>> 5) + k4);
        }

        System.out.println("The encrypted ciphertext is: " + Integer.toHexString(L) + Integer.toHexString(R));


        // For loop that iterates 32 rounds of decryption to return the original plaintext.
        for (int i = 0; i < 32; i++) {
            R -= ((L << 4) + k3) ^ (L + sum) ^ ((L >>> 5) + k4);
            L -= ((R << 4) + k1) ^ (R + sum) ^ ((R >>> 5) + k2);
            sum -= delta;
        }

        System.out.println("The decrypted ciphertext is: " + Integer.toHexString(L) + Integer.toHexString(R));
    }
}
