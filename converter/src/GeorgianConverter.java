import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GeorgianConverter {

    private static final Map<Character, Character> georgianToLatin = new HashMap<>();
    private static final Map<Character, Character> latinToGeorgian = new HashMap<>();

    static {
        // Initialize conversion maps based on the correspondence table
        initMappings();
    }

    private static void initMappings() {
        // Georgian Unicode to Latin mappings
        addMapping('ა', 'a');
        addMapping('ბ', 'b');
        addMapping('გ', 'g');
        addMapping('დ', 'd');
        addMapping('ე', 'e');
        addMapping('ვ', 'v');
        addMapping('ზ', 'z');
        addMapping('თ', 'T');
        addMapping('ი', 'i');
        addMapping('კ', 'k');
        addMapping('ლ', 'l');
        addMapping('მ', 'm');
        addMapping('ნ', 'n');
        addMapping('ო', 'o');
        addMapping('პ', 'p');
        addMapping('ჟ', 'J');
        addMapping('რ', 'r');
        addMapping('ს', 's');
        addMapping('ტ', 't');
        addMapping('უ', 'u');
        addMapping('ფ', 'f');
        addMapping('ქ', 'q');
        addMapping('ღ', 'R');
        addMapping('ყ', 'y');
        addMapping('შ', 'S');
        addMapping('ჩ', 'C');
        addMapping('ც', 'c');
        addMapping('ძ', 'Z');
        addMapping('წ', 'w');
        addMapping('ჭ', 'W');
        addMapping('ხ', 'x');
        addMapping('ჯ', 'j');
        addMapping('ჰ', 'h');
    }

    private static void addMapping(char georgian, char latin) {
        georgianToLatin.put(georgian, latin);
        latinToGeorgian.put(latin, georgian);
    }

    public static String convertGeorgianToLatin(String georgianText) {
        StringBuilder result = new StringBuilder();

        for (char c : georgianText.toCharArray()) {
            if (georgianToLatin.containsKey(c)) {
                result.append(georgianToLatin.get(c));
            } else {
                result.append(c); // Keep non-Georgian characters as is
            }
        }

        return result.toString();
    }

    public static String convertLatinToGeorgian(String latinText) {
        StringBuilder result = new StringBuilder();

        for (char c : latinText.toCharArray()) {
            if (latinToGeorgian.containsKey(c)) {
                result.append(latinToGeorgian.get(c));
            } else {
                result.append(c); // Keep non-Latin characters as is
            }
        }

        return result.toString();
    }

    public static void convertGeorgianFileToLatin(String inputFilePath, String outputFilePath) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(inputFilePath), StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                     new FileOutputStream(outputFilePath), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String convertedLine = convertGeorgianToLatin(line);
                System.out.println(convertedLine);
                writer.write(convertedLine);
                writer.newLine();
            }

            System.out.println("\nConversion complete! Result saved to: " + outputFilePath);

        } catch (IOException e) {
            System.err.println("Error during file conversion: " + e.getMessage());
        }
    }

    public static void convertLatinFileToGeorgian(String inputFilePath, String outputFilePath) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(inputFilePath), StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                     new FileOutputStream(outputFilePath), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String convertedLine = convertLatinToGeorgian(line);
                System.out.println(convertedLine);
                writer.write(convertedLine);
                writer.newLine();
            }

            System.out.println("\nConversion complete! Result saved to: " + outputFilePath);

        } catch (IOException e) {
            System.err.println("Error during file conversion: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Georgian-Latin Converter");
        System.out.println("1. Convert Georgian text to Latin");
        System.out.println("2. Convert Latin text to Georgian");
        System.out.print("Choose an option (1 or 2): ");

        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (option == 1) {
            // Task 1: Georgian to Latin
            System.out.print("Enter the input file path with Georgian text: ");
            String inputFile = scanner.nextLine();

            System.out.print("Enter the output file path for Latin text: ");
            String outputFile = scanner.nextLine();

            convertGeorgianFileToLatin(inputFile, outputFile);

        } else if (option == 2) {
            // Task 2: Latin to Georgian
            System.out.print("Enter the input file path with Latin text: ");
            String inputFile = scanner.nextLine();

            System.out.print("Enter the output file path for Georgian text: ");
            String outputFile = scanner.nextLine();

            convertLatinFileToGeorgian(inputFile, outputFile);

        } else {
            System.out.println("Invalid option selected.");
        }

        scanner.close();
    }
}