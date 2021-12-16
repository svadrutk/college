
public class PasswordHacker {
    private LockBox toPick;
    private int passwordLength;

    /** Complexity: O(1) **/
    public PasswordHacker(int passwordLength) {
        this.passwordLength = passwordLength;
        toPick = new LockBox(this.passwordLength);
    }
    /** Complexity: O(1) **/
    public void hack() {
        toPick.reset();
        String a = toPick.hackMe();
        toPick.authenticate(a);
    }
    /** Complexity: O(n) **/
    private int power() {
        int c = 1;
        for(int i = 1; i <= passwordLength; i++) {
            c *= 10;
        }
        return c;
    }
    /** Complexity: O(n) **/
    public void bruteForce() {
        toPick.reset();
        for(int i = 0; i < power(); i++) {
            String g = generateGuess(i);
            toPick.authenticate(g);
            if(toPick.isOpen()) {
                break;
            }
        }
    }
    /** Complexity: O(1) **/
    public String generateGuess(int count) {
        String guess = count + "";

        if (guess.length() > passwordLength)
            guess = guess.substring(guess.length()-passwordLength);

        else {
            for (int i=guess.length(); i<passwordLength; i++) {
                guess = "0" + guess;
            }
        }
        return guess;
    }
}

