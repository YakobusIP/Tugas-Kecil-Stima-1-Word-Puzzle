import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class wordPuzzle {
    // DECLARING COLOR SPACES
    public static final String ANSI_RESET = "\033[0m";
    public static final String ANSI_MAGENTA = "\033[95m";
    public static final String ANSI_RED = "\033[31m";
    public static final String ANSI_GREEN = "\033[32m";
    public static final String ANSI_YELLOW = "\033[33m";
    public static final String ANSI_BLUE = "\033[34m";
    public static final String ANSI_PURPLE = "\033[35m";
    public static final String ANSI_CYAN = "\033[36m";
    public static final String ANSI_BRIGHT_RED = "\033[91m";

    public static boolean isWithinRange(int index, int lowerBound, int upperBound) {
        if ((index >= lowerBound) && (index < upperBound)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main (String[] args) {
        int i, j, k, x, y;
        int letterCounter, charIndexX, charIndexY, stepsTaken;
        int ansX, ansY;
        String testCase, chars, path1, path2, fullPath;
        Character currentChar;
        boolean markFound = false;
        boolean checkFlag;
        boolean up, rightup, right, rightdown, down, leftdown, left, leftup;
        boolean finalup, finalrightup, finalright, finalrightdown, finaldown, finalleftdown, finalleft, finalleftup;
        long startComp, endComp, compDur, startProg, endProg, progDur;
        float elapsedTime, fileDuration;

        // INITIATE BOOLEANS
        up = false;
        rightup = false;
        right = false;
        rightdown = false;
        down = false;
        leftdown = false;
        left = false;
        leftup = false;
        finalup = false;
        finalrightup = false;
        finalright = false;
        finalrightdown = false;
        finaldown = false;
        finalleftdown = false;
        finalleft = false;
        finalleftup = false;

        System.out.println("Please input your file name in the test folder:");
        path1 = "../test/";
        Scanner input = new Scanner(System.in);
        path2 = input.nextLine();
        fullPath = path1 + path2;
        startProg = System.nanoTime();
        input.close();

        try {
            List<String> fullFile = Files.readAllLines(Paths.get(fullPath));
            ArrayList<ArrayList<Character>> game = new ArrayList<ArrayList<Character>>();

            // READING MATRIX
            for (String line : fullFile) {
                if (line.trim().isEmpty()) {
                    break;
                } else {
                    Scanner scanElmt = new Scanner (line);
                    ArrayList<Character> col = new ArrayList<Character>();
                    while (scanElmt.hasNext()) {
                        chars = scanElmt.next().trim();
                        col.add(chars.charAt(0));
                    }
                    game.add(col);
                    scanElmt.close();
                }
            }

            // CREATE COLOR MATRIX
            String[][] color_codes = new String[game.size()][game.get(0).size()];
            for (i=0;i<game.size();i++) {
                for(j=0;j<game.get(i).size();j++) {
                    color_codes[i][j] = ANSI_RESET;
                }
            }

            // CREATING ANSWER MATRIX
            String[][] answer = new String[game.size()][game.get(0).size()];
            for (i=0;i<game.size();i++) {
                for (j=0;j<game.get(i).size();j++) {
                    answer[i][j] = "-";
                }
            }

            // READ TEST CASES AND CALCULATING
            for (String line : fullFile) {
                stepsTaken = 0;
                if (line.trim().isEmpty()) {
                    markFound = true;
                    continue;
                }
                if (markFound) {
                    startComp = System.nanoTime();
                    Scanner readCase = new Scanner(line);
                    testCase = readCase.next();
                    
                    for (i=0;i<game.size();i++) {
                        for (j=0;j<game.get(0).size();j++) {
                            stepsTaken++;
                            if (game.get(i).get(j) == testCase.charAt(0)) {
                                k = 0;
                                x = j; // Letak baris
                                //System.out.println("Currently reading:");
                                //System.out.println("X" + x);
                                y = i; // Letak kolom
                                //System.out.println("Y" + y);
                                // CHECK EVERY DIRECTION
                                // IF CURRENTLY SCANNING TOP LEFT CORNER
                                if ((i == 0) && (j == 0)) {
                                    // Arah kanan
                                    if (testCase.charAt(k+1) == game.get(y).get(x+1)) {
                                        stepsTaken++;
                                        right = true;
                                    }
                                    // Arah kanan bawah
                                    if (testCase.charAt(k+1) == game.get(y+1).get(x+1)) {
                                        stepsTaken++;
                                        rightdown = true;
                                    }
                                    // Arah bawah
                                    if (testCase.charAt(k+1) == game.get(y+1).get(x)) {
                                        stepsTaken++;
                                        down = true;
                                    }
                                // IF CURRENTLY SCANNING TOP RIGHT CORNER
                                } else if ((i == 0) && (j == game.get(i).size()-1)) {
                                    // Arah bawah
                                    if (testCase.charAt(k+1) == game.get(y+1).get(x)) {
                                        stepsTaken++;
                                        down = true;
                                    }
                                    // Arah kiri bawah
                                    if (testCase.charAt(k+1) == game.get(y+1).get(x-1)) {
                                        stepsTaken++;
                                        leftdown = true;
                                    }
                                    // Arah kiri
                                    if (testCase.charAt(k+1) == game.get(y).get(x-1)) {
                                        stepsTaken++;
                                        left = true;
                                    }
                                // IF CURRENTLY SCANNING BOTTOM LEFT CORNER
                                 
                                } else if ((i == game.size()-1) && (j == 0)) {
                                    // Arah atas
                                    if (testCase.charAt(k+1) == game.get(y-1).get(x)) {
                                        stepsTaken++;
                                        up = true;
                                    }
                                    // Arah kanan atas
                                    if (testCase.charAt(k+1) == game.get(y-1).get(x+1)) {
                                        stepsTaken++;
                                        rightup = true;
                                    }
                                    // Arah kanan
                                    if (testCase.charAt(k+1) == game.get(y).get(x+1)) {
                                        stepsTaken++;
                                        right = true;
                                    }
                                // IF CURRENTLY SCANNING BOTTOM RIGHT CORNER
                                } else if ((i == game.size()-1) && (j == game.get(i).size()-1)) {
                                    // Arah atas
                                    if (testCase.charAt(k+1) == game.get(y-1).get(x)) {
                                        stepsTaken++;
                                        up = true;
                                    }
                                    // Arah kiri atas
                                    if (testCase.charAt(k+1) == game.get(y-1).get(x-1)) {
                                        stepsTaken++;
                                        leftup = true;
                                    }
                                    // Arah kiri
                                    if (testCase.charAt(k+1) == game.get(y).get(x-1)) {
                                        stepsTaken++;
                                        left = true;
                                    }
                                // IF CURRENTLY SCANNING TOP ROW   
                                } else if ((i == 0) && (j != 0)) {
                                    // Arah kanan
                                    if (testCase.charAt(k+1) == game.get(y).get(x+1)) {
                                        stepsTaken++;
                                        right = true;
                                    }
                                    // Arah kanan bawah
                                    if (testCase.charAt(k+1) == game.get(y+1).get(x+1)) {
                                        stepsTaken++;
                                        rightdown = true;
                                    }
                                    // Arah bawah
                                    if (testCase.charAt(k+1) == game.get(y+1).get(x)) {
                                        stepsTaken++;
                                        down = true;
                                    }
                                    // Arah kiri bawah
                                    if (testCase.charAt(k+1) == game.get(y+1).get(x-1)) {
                                        stepsTaken++;
                                        leftdown = true;
                                    }
                                    // Arah kiri
                                    if (testCase.charAt(k+1) == game.get(y).get(x-1)) {
                                        stepsTaken++;
                                        left = true;
                                    }
                                // IF CURRENTLY SCANNING LEFT COLUMN
                                }  else if ((i != 0) && (j == 0)) {
                                    // Arah atas
                                    if (testCase.charAt(k+1) == game.get(y-1).get(x)) {
                                        stepsTaken++;
                                        up = true;
                                    }
                                    // Arah kanan atas
                                    if (testCase.charAt(k+1) == game.get(y-1).get(x+1)) {
                                        stepsTaken++;
                                        rightup = true;
                                    }
                                    // Arah kanan
                                    if (testCase.charAt(k+1) == game.get(y).get(x+1)) {
                                        stepsTaken++;
                                        right = true;
                                    }
                                    // Arah kanan bawah
                                    if (testCase.charAt(k+1) == game.get(y+1).get(x+1)) {
                                        stepsTaken++;
                                        rightdown = true;
                                    }
                                    // Arah bawah
                                    if (testCase.charAt(k+1) == game.get(y+1).get(x)) {
                                        stepsTaken++;
                                        down = true;
                                    }
                                // IF CURRENTLY SCANNING BOTTOM ROW
                                } else if ((i == game.size()-1) && (j != 0)) {
                                    // Arah kiri
                                    if (testCase.charAt(k+1) == game.get(y).get(x-1)) {
                                        stepsTaken++;
                                        left = true;
                                    }
                                    // Arah kiri atas
                                    if (testCase.charAt(k+1) == game.get(y-1).get(x-1)) {
                                        stepsTaken++;
                                        leftup = true;
                                    }
                                    // Arah atas
                                    if (testCase.charAt(k+1) == game.get(y-1).get(x)) {
                                        stepsTaken++;
                                        up = true;
                                    }
                                    // Arah kanan atas
                                    if (testCase.charAt(k+1) == game.get(y-1).get(x+1)) {
                                        stepsTaken++;
                                        rightup = true;
                                    }
                                    // Arah kanan
                                    if (testCase.charAt(k+1) == game.get(y).get(x+1)) {
                                        stepsTaken++;
                                        right = true;
                                    }
                                // IF CURRENTLY SCANNING RIGHT COLUMN
                                } else if ((i != 0) && (j == game.get(i).size()-1)) {
                                    // Arah atas
                                    if (testCase.charAt(k+1) == game.get(y-1).get(x)) {
                                        stepsTaken++;
                                        up = true;
                                    }
                                    // Arah kiri atas
                                    if (testCase.charAt(k+1) == game.get(y-1).get(x-1)) {
                                        stepsTaken++;
                                        leftup = true;
                                    }
                                    // Arah kiri
                                    if (testCase.charAt(k+1) == game.get(y).get(x-1)) {
                                        stepsTaken++;
                                        left = true;
                                    }
                                    // Arah kiri bawah
                                    if (testCase.charAt(k+1) == game.get(y+1).get(x-1)) {
                                        stepsTaken++;
                                        leftdown = true;
                                    }
                                    // Arah bawah
                                    if (testCase.charAt(k+1) == game.get(y+1).get(x)) {
                                        stepsTaken++;
                                        down = true;
                                    }
                                // IF CURRENTLY SCANNING THE MIDDLE OF THE MATRIX
                                } else {
                                    // Arah atas
                                    if (testCase.charAt(k+1) == game.get(y-1).get(x)) {
                                        stepsTaken++;
                                        up = true;
                                    }
                                    // Arah kanan atas
                                    if (testCase.charAt(k+1) == game.get(y-1).get(x+1)) {
                                        stepsTaken++;
                                        rightup = true;
                                    }
                                    // Arah kanan
                                    if (testCase.charAt(k+1) == game.get(y).get(x+1)) {
                                        stepsTaken++;
                                        right = true;
                                    }
                                    // Arah kanan bawah
                                    if (testCase.charAt(k+1) == game.get(y+1).get(x+1)) {
                                        stepsTaken++;
                                        rightdown = true;
                                    }
                                    // Arah bawah
                                    if (testCase.charAt(k+1) == game.get(y+1).get(x)) {
                                        stepsTaken++;
                                        down = true;
                                    }
                                    // Arah kiri bawah
                                    if (testCase.charAt(k+1) == game.get(y+1).get(x-1)) {
                                        stepsTaken++;
                                        leftdown = true;
                                    }
                                    // Arah kiri
                                    if (testCase.charAt(k+1) == game.get(y).get(x-1)) {
                                        stepsTaken++;
                                        left = true;
                                    }
                                    // Arah kiri atas
                                    if (testCase.charAt(k+1) == game.get(y-1).get(x-1)) {
                                        stepsTaken++;
                                        leftup = true;
                                    }
                                }
                                /*
                                System.out.println("Up:" + up);
                                System.out.println("Rightup:" + rightup);
                                System.out.println("Right:" + right);
                                System.out.println("Rightdown:" + rightdown);
                                System.out.println("Down:" + down);
                                System.out.println("Leftdown:" + leftdown); 
                                System.out.println("Left:" + left);
                                System.out.println("Leftup:" + leftup);*/

                                // ITERATE THE REST OF CHARACTERS BASED ON FLAG
                                checkFlag = true;
                                k = 1;
                                letterCounter = 1;
                                charIndexX = 0;
                                charIndexY = 0;
                                while ((k < testCase.length()) && (checkFlag)) {
                                    //System.out.println("Letter counter:" + letterCounter);
                                    if (up) {
                                        charIndexX = x;
                                        charIndexY = y-1;
                                        if ((isWithinRange(charIndexX, 0, game.get(i).size())) && (isWithinRange(charIndexY, 0, game.size()))) {
                                            if ((game.get(y-1).get(x) == testCase.charAt(k)) && (letterCounter != testCase.length())) {
                                                stepsTaken++;
                                                letterCounter++;
                                                y--;
                                                if (letterCounter == testCase.length()) {
                                                    finalup = true;
                                                    up = false;
                                                }
                                            } else {
                                                x = j;
                                                y = i;
                                                k = 0;
                                                letterCounter = 1;
                                                up = false;
                                            }  
                                        } else {
                                            x = j;
                                            y = i;
                                            k = 0;
                                            letterCounter = 1;
                                            up = false;
                                        }    
                                    }
                                    else if (rightup) {
                                        charIndexX = x+1;
                                        charIndexY = y-1;
                                        if ((isWithinRange(charIndexX, 0, game.get(i).size())) && (isWithinRange(charIndexY, 0, game.size()))) {
                                            if ((game.get(y-1).get(x+1) == testCase.charAt(k)) && (letterCounter != testCase.length())) {
                                                stepsTaken++;
                                                letterCounter++;
                                                x++;
                                                y--;
                                                if (letterCounter == testCase.length()) {
                                                    finalrightup = true;
                                                    rightup = false;
                                                }
                                            } else {
                                                x = j;
                                                y = i;
                                                k = 0;
                                                letterCounter = 1;
                                                rightup = false;
                                            }
                                        } else {
                                            x = j;
                                            y = i;
                                            k = 0;
                                            letterCounter = 1;
                                            rightup = false;
                                        }
                                    }
                                    else if (right) {
                                        charIndexX = x+1;
                                        charIndexY = y;
                                        if ((isWithinRange(charIndexX, 0, game.get(i).size())) && (isWithinRange(charIndexY, 0, game.size()))) {
                                            if ((game.get(y).get(x+1) == testCase.charAt(k)) && (letterCounter != testCase.length())) {
                                                stepsTaken++;
                                                letterCounter++;
                                                x++;
                                                if (letterCounter == testCase.length()) {
                                                    finalright = true;
                                                    right = false;
                                                }
                                            } else {
                                                x = j;
                                                y = i;
                                                k = 0;
                                                letterCounter = 1;
                                                right = false;
                                            }
                                        } else {    
                                            x = j;
                                            y = i;
                                            k = 0;    
                                            letterCounter = 1;
                                            right = false;
                                        }
                                    }
                                    else if (rightdown) {
                                        
                                        charIndexX = x+1;
                                        charIndexY = y+1;
                                        if ((isWithinRange(charIndexX, 0, game.get(i).size())) && (isWithinRange(charIndexY, 0, game.size()))) {
                                            if ((game.get(y+1).get(x+1) == testCase.charAt(k)) && (letterCounter != testCase.length())) {
                                                stepsTaken++;
                                                letterCounter++;
                                                x++;
                                                y++;
                                                if (letterCounter == testCase.length()) {
                                                    finalrightdown = true;
                                                    rightdown = false;
                                                }
                                            } else {
                                                x = j;
                                                y = i;
                                                k = 0;
                                                letterCounter = 1;
                                                rightdown = false;
                                            }
                                        } else {
                                            x = j;
                                            y = i;
                                            k = 0;
                                            letterCounter = 1;
                                            rightdown = false;
                                        }
                                    }
                                    else if (down) {
                                        charIndexX = x;
                                        charIndexY = y+1;
                                        if ((isWithinRange(charIndexX, 0, game.get(i).size())) && (isWithinRange(charIndexY, 0, game.size()))) {
                                            if ((game.get(y+1).get(x) == testCase.charAt(k)) && (letterCounter != testCase.length())) {
                                                stepsTaken++;
                                                letterCounter++;
                                                y++;
                                                if (letterCounter == testCase.length()) {
                                                    finaldown = true;
                                                    down = false;
                                                }
                                            } else {
                                                x = j;
                                                y = i;
                                                k = 0;
                                                letterCounter = 1;
                                                down = false;
                                            }
                                        } else {
                                            x = j;
                                            y = i;
                                            k = 0;
                                            letterCounter = 1;
                                            down = false;
                                        }
                                    }
                                    else if (leftdown) {
                                        charIndexX = x-1;
                                        charIndexY = y+1;
                                        if ((isWithinRange(charIndexX, 0, game.get(i).size())) && (isWithinRange(charIndexY, 0, game.size()))) {
                                            if ((game.get(y+1).get(x-1) == testCase.charAt(k)) && (letterCounter != testCase.length())) {
                                                stepsTaken++;
                                                letterCounter++;
                                                x--;
                                                y++;
                                                if (letterCounter == testCase.length()) {
                                                    finalleftdown = true;
                                                    leftdown = false;
                                                }
                                            } else {
                                                x = j;
                                                y = i;
                                                k = 0;
                                                letterCounter = 1;
                                                leftdown = false;
                                            }
                                        } else {
                                            x = j;
                                            y = i;
                                            k = 0;
                                            letterCounter = 1;
                                            leftdown = false;
                                        }
                                    }
                                    else if (left) {
                                        charIndexX = x-1;
                                        charIndexY = y;
                                        if ((isWithinRange(charIndexX, 0, game.get(i).size())) && (isWithinRange(charIndexY, 0, game.size()))) {
                                            if ((game.get(y).get(x-1) == testCase.charAt(k)) && (letterCounter != testCase.length())) {
                                                stepsTaken++;
                                                letterCounter++;
                                                x--;
                                                if (letterCounter == testCase.length()) {
                                                    finalleft = true;
                                                    left = false;
                                                }
                                            } else {
                                                x = j;
                                                y = i;
                                                k = 0;
                                                letterCounter = 1;
                                                left = false;
                                            }
                                        } else {
                                            x = j;
                                            y = i;
                                            k = 0;
                                            letterCounter = 1;
                                            left = false;
                                        }
                                    }
                                    
                                    else if (leftup) {
                                        charIndexX = x-1;
                                        charIndexY = y-1;
                                        if ((isWithinRange(charIndexX, 0, game.get(i).size())) && (isWithinRange(charIndexY, 0, game.size()))) {
                                            if ((game.get(y-1).get(x-1) == testCase.charAt(k)) && (letterCounter != testCase.length())) {
                                                stepsTaken++;
                                                letterCounter++;
                                                x--;
                                                y--;
                                                if (letterCounter == testCase.length()) {
                                                    finalleftup = true;
                                                    leftup = false;
                                                }
                                            } else {
                                                x = j;
                                                y = i;
                                                k = 0;
                                                letterCounter = 1;
                                                leftup = false;
                                            }
                                        } else {
                                            x = j;
                                            y = i;
                                            k = 0;
                                            letterCounter = 1;
                                            leftup = false;
                                        }
                                    }
                                    
                                    if (letterCounter == testCase.length()) {
                                        endComp = System.nanoTime();
                                        compDur = endComp - startComp;
                                        elapsedTime = (float)compDur / (float)1000000;
                                        /*
                                        System.out.println("Up:" + finalup);
                                        System.out.println("Rightup:" + finalrightup);
                                        System.out.println("Right:" + finalright);
                                        System.out.println("Rightdown:" + finalrightdown);
                                        System.out.println("Down:" + finaldown);
                                        System.out.println("Leftdown:" + finalleftdown); 
                                        System.out.println("Left:" + finalleft);
                                        System.out.println("Leftup:" + finalleftup); */

                                        System.out.println("========== Word Found! ==========");
                                        System.out.println("Found " + testCase + " at X:" + (j+1) + " and Y:" + (i+1));
                                        System.out.println("Program took " + stepsTaken + " step(s) to find the word");
                                        System.out.print("Exited in ");
                                        System.out.printf("%.5f", elapsedTime);
                                        System.out.println(" ms");
                                        
                                        ansX = j;
                                        ansY = i;
                                        //System.out.println(ansX);
                                        //System.out.println(ansY);
                                        for (i=0;i<testCase.length();i++) {
                                            if (finalup) {
                                                currentChar = testCase.charAt(i);
                                                answer[ansY][ansX] = String.valueOf(currentChar);
                                                color_codes[ansY][ansX] = ANSI_BLUE;
                                                ansY--;
                                            } else if (finalrightup) {
                                                currentChar = testCase.charAt(i);
                                                answer[ansY][ansX] = String.valueOf(currentChar);
                                                color_codes[ansY][ansX] = ANSI_CYAN;
                                                ansX++;
                                                ansY--;
                                            } else if (finalright) {
                                                currentChar = testCase.charAt(i);
                                                answer[ansY][ansX] = String.valueOf(currentChar);
                                                color_codes[ansY][ansX] = ANSI_GREEN;
                                                ansX++;
                                            } else if (finalrightdown) {
                                                currentChar = testCase.charAt(i);
                                                answer[ansY][ansX] = String.valueOf(currentChar);
                                                color_codes[ansY][ansX] = ANSI_MAGENTA;
                                                ansX++;
                                                ansY++;
                                            } else if (finaldown) {
                                                currentChar = testCase.charAt(i);
                                                answer[ansY][ansX] = String.valueOf(currentChar);
                                                color_codes[ansY][ansX] = ANSI_PURPLE;
                                                ansY++;
                                            } else if (finalleftdown) {
                                                currentChar = testCase.charAt(i);
                                                answer[ansY][ansX] = String.valueOf(currentChar);
                                                color_codes[ansY][ansX] = ANSI_RED;
                                                ansX--;
                                                ansY++;
                                            } else if (finalleft) {
                                                currentChar = testCase.charAt(i);
                                                answer[ansY][ansX] = String.valueOf(currentChar);
                                                color_codes[ansY][ansX] = ANSI_BRIGHT_RED;
                                                ansX--;
                                            } else if (finalleftup) {
                                                currentChar = testCase.charAt(i);
                                                answer[ansY][ansX] = String.valueOf(currentChar);
                                                color_codes[ansY][ansX] = ANSI_YELLOW;
                                                ansX--;
                                                ansY--;
                                            }
                                        }

                                        for (i=0;i<game.size();i++) {
                                            for (j=0;j<game.get(i).size();j++) {
                                                System.out.print(color_codes[i][j] + answer[i][j] + ANSI_RESET);
                                                System.out.print(" ");
                                            }
                                            System.out.println();
                                        }

                                        finalup = false;
                                        finalrightup = false;
                                        finalright = false;
                                        finalrightdown = false;
                                        finaldown = false;
                                        finalleftdown = false;
                                        finalleft = false;
                                        finalleftup = false;
                                        checkFlag = false;
                                    }
                                    k++;
                                }
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("File not found!");
        }
        endProg = System.nanoTime();
        progDur = endProg - startProg;
        fileDuration = (float)progDur/(float)1000000;
        System.out.println("Program fully exited in " + fileDuration + " ms"); 
    }
}
