import java.util.Random;
import java.util.concurrent.locks.Lock;

public class LockBox {
    protected static Random randGen;
    private String password;
    private boolean isOpen;

    /**
     * Complexity: O(n)
     * @param passwordLength
     * @throws IllegalArgumentException
     */
    public LockBox(int passwordLength) throws IllegalArgumentException {
        if(randGen == null) {
            randGen = new Random();
        }
        password = "";
        if(passwordLength <= 0) {
            throw new IllegalArgumentException("invalid password length");
        }
        else {
            for(int i = 1; i <= passwordLength; i++) {
                int num = randGen.nextInt(10);
                password = password.concat(String.valueOf(num));
            }
        }
    }
    /** Complexity: O(1) **/
    public void authenticate(String guess) {
        if(guess.equals(password)) {
            isOpen = true;
        }
    }
    /** Complexity: O(1) **/
    public String hackMe() {
        return password;
    }
    /** Complexity: O(1) **/
    public boolean isOpen() {
        return isOpen;
    }
    /** Complexity: O(1) **/
    public void reset() {
        isOpen = false;
    }
}
