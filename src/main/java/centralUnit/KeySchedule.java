package centralUnit;

public class KeySchedule {
    private final int[][] firstPermutionMatrix = {
            {57, 49, 41, 33, 25, 17, 9, 1},
            {58, 50, 42, 34, 26, 18, 10, 2},
            {59, 51, 43, 35, 27, 19, 11, 3},
            {60, 52, 44, 36, 63, 55, 47, 39},
            {31, 23, 15, 7, 62, 54, 46, 38},
            {30, 22, 14, 6, 61, 53, 45, 37},
            {29, 21, 13, 5, 28, 20, 12, 4}
    };

    private final int[][] secondPermutionMatrix = {
            {14, 17, 11, 24, 1, 5, 3, 28},
            {15, 6, 21, 10, 23, 19, 12, 4},
            {26, 8, 16, 7, 27, 20, 13, 2},
            {41, 52, 31, 37, 47, 55, 30, 40},
            {51, 45, 33, 48, 44, 49, 39, 56},
            {34, 53, 46, 42, 50, 36, 29, 32}
    };


    public String firstPerm(String input) {
        char[] result = new char[56];

        for (int i = 0; i < result.length; i++) {
            result[i] = input.charAt(firstPermutionMatrix[i / 8][i % 8] - 1);
        }

        return new String(result);
    }

    public String secondPerm(String input) {
        char[] result = new char[48];

        for (int i = 0; i < 48; i++) {
            result[i] = input.charAt(secondPermutionMatrix[i / 8][i % 8] - 1);
        }

        return new String(result);
    }

    public String[] schedule(String input, Boolean isEncryption) {

        String[] result = new String[16];
        String permuted = firstPerm(input);

        String c = permuted.substring(0, 28);
        String d = permuted.substring(28);

        for (int i = 1; i <= result.length; i++) {
            if (isEncryption) {
                if (i == 1 || i == 2 || i == 9 || i == 16) {
                    c = c.substring(1) + c.charAt(0);
                    d = d.substring(0, 27) + d.charAt(0);
                } else {
                    c = d.substring(2) + c.substring(0, 2);
                    d = d.substring(2) + d.substring(0, 2);
                }
            } else {
                if (i == 2 || i == 9 || i == 16) {
                    c = c.charAt(27) + c.substring(0, 27);
                    d = d.charAt(27) + d.substring(0, 27);
                } else {
                    c = c.substring(26, 28) + c.substring(0, 26);
                    d = d.substring(26, 28) + d.substring(0, 26);
                }
            }
            result[i - 1] = secondPerm(c + d);
        }


        return result;
    }
}
