package satellite;

import java.io.*;

public class Satellite {

    static int[][] oldImage;
    static int[][] newImage;
    static int noOfRows, noOfCols;

    public static void main(String[] args) {
        String inputFile = "src/main/resources/input4.txt"; // default
        System.out.println("Running test: " + inputFile);

        // Allow specifying input file as command line argument
        if (args.length > 0) {
            inputFile = args[0];
        }
        
        try {
            int[] boundaries = processImages(inputFile);
            printResult(boundaries[0], boundaries[1], boundaries[2], boundaries[3]);
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }
    }

    // SINGLE METHOD FOR READING BOTH IMAGES AND PROCESSING
    private static int[] processImages(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        

        noOfRows = Integer.parseInt(reader.readLine().trim());
        noOfCols = Integer.parseInt(reader.readLine().trim());
        

        oldImage = new int[noOfRows][noOfCols];
        newImage = new int[noOfRows][noOfCols];
        

        readImageData(reader, oldImage);
        readImageData(reader, newImage);
        
        reader.close();
        
        // SINGLE METHOD TO DETERMINE ALL BOUNDARIES AT ONCE
        return findBoundaries();
    }
    
    private static void readImageData(BufferedReader reader, int[][] image) throws IOException {
        for (int row = 0; row < noOfRows; row++) {
            String[] parts = reader.readLine().trim().split("\\s+");
            for (int col = 0; col < noOfCols; col++) {
                image[row][col] = Integer.parseInt(parts[col]);
            }
        }
    }

    // SINGLE METHOD TO FIND ALL FOUR BOUNDARIES (x1, x2, y1, y2)
    private static int[] findBoundaries() {
        int x1 = 0, x2 = noOfRows - 1, y1 = 0, y2 = noOfCols - 1;
        

        while (x1 < noOfRows && rowsEqual(x1)) x1++;
        

        while (y1 < noOfCols && colsEqual(y1)) y1++;
        

        while (x2 >= 0 && rowsEqual(x2)) x2--;
        

        while (y2 >= 0 && colsEqual(y2)) y2--;
        
        return new int[]{x1, y1, x2, y2};
    }
    
    // Helper methods for checking equality
    private static boolean rowsEqual(int row) {
        for (int col = 0; col < noOfCols; col++) {
            if (oldImage[row][col] != newImage[row][col]) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean colsEqual(int col) {
        for (int row = 0; row < noOfRows; row++) {
            if (oldImage[row][col] != newImage[row][col]) {
                return false;
            }
        }
        return true;
    }

    //OUTPUT

    private static void printResult(int x1, int y1, int x2, int y2) {
        if (x1 > x2 || y1 > y2) {
            System.out.println("The two images are the same");
        } else {
            // convert to 1-based indexing
            System.out.println((x1 + 1) + " " + (y1 + 1) + " " + (x2 + 1) + " " + (y2 + 1));
        }
    }
}
