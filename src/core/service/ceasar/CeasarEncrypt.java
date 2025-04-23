package core.service.ceasar;

import core.service.IEncryptService;

import static core.Constants.ALLOWED_CHARACTERS;
import static core.Constants.ALLOWED_SPECIAL;

public class CeasarEncrypt implements IEncryptService {
    String text;
    int key;
    public CeasarEncrypt(String text, int key) {
        this.text = text;
        this.key = key;
    }
    public String encrypt() {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            String symbol = String.valueOf(c);
            boolean isCharacter = ALLOWED_CHARACTERS.contains(symbol);
            boolean isSpecial = ALLOWED_SPECIAL.contains(symbol);

            if (!isCharacter && !isSpecial) {
                result.append(symbol);
                continue;
            }

            String targetAllowed = isCharacter ? ALLOWED_CHARACTERS : ALLOWED_SPECIAL;
            int oldCharIdx = targetAllowed.indexOf(c);
            char newChar = getEncryptedChar(oldCharIdx, key, targetAllowed);
            result.append(newChar);
        }

        return result.toString();
    }

    private char getEncryptedChar(int oldCharIdx, int key, String symbolsSource) {
        int newCharIdx = oldCharIdx + key;
        int length = symbolsSource.length();

        while (newCharIdx >= length) {
            newCharIdx -= length;
        }

        while (newCharIdx < 0) {
            newCharIdx += length;
        }

        return symbolsSource.charAt(newCharIdx);
    }
}
