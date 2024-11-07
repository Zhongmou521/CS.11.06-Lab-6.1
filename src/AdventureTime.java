import java.io.*;
import java.util.Scanner;

public class AdventureTime {

    /** This is the main method and it is where you will test your implementations for challengeOne, challengeTwo, etc.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

            System.out.println(challengeOne("inputOneTwo.txt"));
            System.out.println(challengeTwo("inputOneTwo.txt"));
            System.out.println(challengeThree("inputThreeFour.txt"));
            System.out.println(challengeFour("inputThreeFour.txt"));
    }

    /** TODO 1
     *
     * Challenge 1
     *
     * @param fileName
     * @return Answer to Challenge 1
     * @throws IOException
     */
    public static int challengeOne(String fileName) throws IOException {
        int[] depths = readFile(fileName);
        int count = 0;
        for (int i = 1; i < depths.length; i++) {
            if (depths[i] > depths[i - 1]) {
                count++;
            }
        }
        return count;
    }

    /** TODO 2
     *
     * Challenge 2
     *
     * @param fileName
     * @return Answer to Challenge 2
     * @throws FileNotFoundException
     */
    public static int challengeTwo(String fileName) throws FileNotFoundException {
        int[] depths = readFile(fileName);
        int count = 0;
        for (int i = 3; i < depths.length; i++) {
            int currentSum = depths[i] + depths[i - 1] + depths[i - 2];
            int previousSum = depths[i - 1] + depths[i - 2] + depths[i - 3];
            if (currentSum > previousSum) {
                count++;
            }
        }
        return count;
    }

    /** TODO 3
     *
     * Challenge 3
     *
     * @param fileName
     * @return Answer to Challenge 3
     * @throws FileNotFoundException
     */
    public static int challengeThree(String fileName) throws FileNotFoundException {
        return calculatePositionAndDepth(fileName, false);
    }

    /** TODO 4
     *
     * Challenge 4
     *
     * @param filename
     * @return Answer to Challenge 4
     * @throws FileNotFoundException
     */
    public static int challengeFour(String filename) throws FileNotFoundException {
        return calculatePositionAndDepth(filename, true);
    }

    public static int calculatePositionAndDepth(String fileName, boolean hasAim) throws FileNotFoundException{
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        int horizontalPosition = 0;
        int depth = 0;
        int aim = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String command = parts[0];
            int value = Integer.parseInt(parts[1]);

            if (command.equals("forward")) {
                horizontalPosition += value;
                if (hasAim) {
                    depth += aim * value;
                }
            } else if (command.equals("down")) {
                if (hasAim) {
                    aim += value;
                } else {
                    depth += value;
                }
            } else if (command.equals("up")) {
                if (hasAim) {
                    aim -= value;
                } else {
                    depth -= value;
                }
            }
        }
        scanner.close();
        return horizontalPosition * depth;
    }

    /** This method will write the values passed as challengeOne, challengeTwo, challengeThree, and challengeFour to a text file.
     * Do not edit this method.
     */
    private static void writeFileAllAnswers(String outPutFilename, int challengeOne, int challengeTwo, int challengeThree, int challengeFour) throws IOException {
        File file = new File(outPutFilename);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("Challenge 1: " + "\t" + challengeOne + "\n");
        bufferedWriter.write("Challenge 2: " + "\t" + challengeTwo + "\n");
        bufferedWriter.write("Challenge 3: " + "\t" + challengeThree + "\n");
        bufferedWriter.write("Challenge 4: " + "\t" + challengeFour + "\n");
        bufferedWriter.close();
    }

    /** This method will read the values in inputFilename into an array of integers, which it will return.
     * Do not edit this method.
     */
    private static int[] readFile(String inputFilename) throws FileNotFoundException {
        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);
        int numberOfLinesInFile = countLinesInFile(inputFilename);
        int[] data = new int[numberOfLinesInFile];
        int index = 0;
        while (scanner.hasNextLine()) {
            data[index++] = scanner.nextInt();
        }
        scanner.close();
        return data;
    }

    /** This method will count the number of lines in a text file, which it will return.
     * Do not edit this method.
     */
    private static int countLinesInFile(String inputFilename) throws FileNotFoundException {
        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);
        int lineCount = 0;
        while (scanner.hasNextLine()) {
            lineCount++;
            scanner.nextLine();
        }
        scanner.close();
        return lineCount;
    }

}
