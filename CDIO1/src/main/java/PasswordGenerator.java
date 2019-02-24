import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PasswordGenerator {
    // This class generates a random password with capitals, lower case letters,
    // digits and the symbols . - _ ! ? ' , =
    private Random random = new Random();

    // Array of all valid symbols
    private Character[] validSymbols = {
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            '0','1','2','3','4','5','6','7','8','9',
            '.','-','_','+','!','?','\'',',','='};

    private String generate() {

        ArrayList<Character> passwordCharacters = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        // Set random length: 6 to 50.
        int passwordLength = (random.nextInt(45) + 6); // Bound not inclusive

        // Add at least one of the following:
        passwordCharacters.add(validSymbols[random.nextInt(26)]); // capitals
        passwordCharacters.add(validSymbols[random.nextInt(26) + 26]); // lower case
        passwordCharacters.add(validSymbols[random.nextInt(10) + 52]); // digits
        passwordCharacters.add(validSymbols[random.nextInt(9) + 62]); // special symbols

        // Make rest of the password:
        for (int i = 4; i < passwordLength; i++){
            passwordCharacters.add(validSymbols[random.nextInt(71)]);
        }

        // Randomize order of characters
        Collections.shuffle(passwordCharacters);

        // Build a string
        for (int i = 0; i < passwordLength; i++){
            stringBuilder.append(passwordCharacters.get(i));
        }

        return stringBuilder.toString();
    }

    public String getPassword(){
        return generate();
    }

}
