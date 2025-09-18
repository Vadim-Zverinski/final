package by.it_academy.util;

import by.it_academy.util.api.IAuthCode;

public class AuthCode implements IAuthCode {

    private static final String UPPER_LETTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_LETTER = "abcdefghijklmnopqrstuvwxyz";

    @Override
    public String generateCode() {
        String res = "";
        double random = Math.random();
        int y = (int) (random * UPPER_LETTER.length());
        char res2 = UPPER_LETTER.charAt(y);
        for (int i = 0; i < 5; i++) {
            double random2 = Math.random();
            int x = (int) (random2 * LOWER_LETTER.length());
            res += LOWER_LETTER.charAt(x);
        }
        return res2 + res;
    }
}
