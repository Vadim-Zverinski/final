package by.it_academy.util;

import by.it_academy.util.api.IVerifyCodeGeneration;
import org.springframework.stereotype.Service;

@Service
public class VerifyCodeGeneration implements IVerifyCodeGeneration {

    private static final String UPPER_LETTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_LETTER = "abcdefghijklmnopqrstuvwxyz";
    private static final int COUNT = 5;

    @Override
    public String generateCode() {
        String res = "";
        double random = Math.random();
        int y = (int) (random * UPPER_LETTER.length());
        char res2 = UPPER_LETTER.charAt(y);
        for (int i = 0; i < COUNT; i++) {
            double random2 = Math.random();
            int x = (int) (random2 * LOWER_LETTER.length());
            res += LOWER_LETTER.charAt(x);
        }
        return res2 + res;
    }
}
