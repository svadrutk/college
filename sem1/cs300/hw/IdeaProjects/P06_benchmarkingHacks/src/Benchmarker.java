public class Benchmarker {

    public static long timeBruteForce(PasswordHacker ph) {
        long a = System.currentTimeMillis();
        ph.bruteForce();
        long b = System.currentTimeMillis();
        return(b - a);
    }
    public static long timeHack(PasswordHacker ph) {
        long a = System.currentTimeMillis();
        ph.hack();
        long b = System.currentTimeMillis();
        return(b - a);
    }
    public static String race(int passwordLength, int numRuns) {
        long bruteForceTime = 0;
        for(int i = 0; i < numRuns; i++) {
            PasswordHacker a = new PasswordHacker(passwordLength);
            bruteForceTime += timeBruteForce(a);
        }
        bruteForceTime = bruteForceTime / numRuns;
        long hackTime = 0;
        for(int i = 0; i < numRuns; i++) {
            PasswordHacker a = new PasswordHacker(passwordLength);
            hackTime += timeHack(a);
        }
        hackTime = hackTime / numRuns;
        return "Brute force " + passwordLength + " " + bruteForceTime + "\nHack " + passwordLength
            + " " + hackTime;
    }
    public static void main(String[] args) {
        System.out.println(race(5, 5));
    }
}
