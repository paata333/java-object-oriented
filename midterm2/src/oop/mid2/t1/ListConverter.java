package oop.mid2.t1;

import java.util.*;

/**
 * ListConverter - A class that processes two lists using 2n-1 logic.
 *
 * This class processes a list of integers (list1) and a list of strings (list2).
 * It takes each number 'n' from list1, calculates 2n-1, and selects the string
 * from list2 at that calculated index position.
 *
 * @author Paata Shvelidze
 * @version 1.0
 * @since 2025-05-15
 */
public class ListConverter {

    /**
     * Processes two lists using 2n-1 logic.
     * Takes each number from list1 as 'n', calculates 2n-1, and selects
     * the corresponding element from list2 at that index.
     *
     * @param list1 a list of integers where each integer 'n' is used in the 2n-1 formula
     * @param list2 a list of strings from which elements are selected based on calculated indices
     * @return a list of strings containing elements from list2 at positions calculated by 2n-1
     */
    public static List<String> processLists(List<Integer> list1, List<String> list2) {
        List<String> result = new ArrayList<>();

        // Process each number from list1
        for (Integer n : list1) {
            // Calculate the index using 2n-1 formula
            int index = 2 * n - 1;

            // Check if the calculated index is valid for list2
            if (index >= 0 && index < list2.size()) {
                result.add(list2.get(index));
                System.out.println("n=" + n + " -> index=" + index + " -> \"" + list2.get(index) + "\"");
            } else {
                System.out.println("n=" + n + " -> index=" + index + " -> OUT OF BOUNDS (list2 size: " + list2.size() + ")");
            }
        }

        return result;
    }

    /**
     * Processes the result list by removing elements at increasing indices (0, 1, 2, ...)
     * and renewing indexes after each removal until only 5 elements remain.
     *
     * @param resultList the list to process
     * @return the processed list with only 5 elements remaining
     */
    public static List<String> processRemoval(List<String> resultList) {
        List<String> workingList = new ArrayList<>(resultList);
        int removalIndex = 0;

        System.out.println("\nStarting removal process:");
        System.out.println("Initial list: " + workingList + " (size: " + workingList.size() + ")");

        while (workingList.size() > 6) {
            if (removalIndex < workingList.size()) {
                String removedElement = workingList.remove(removalIndex);
                System.out.println("Removed element at index " + removalIndex + ": \"" + removedElement + "\"");
                System.out.println("List after removal: " + workingList + " (size: " + workingList.size() + ")");

                // If we still need to remove more elements, move to next index
                if (workingList.size() > 6) {
                    removalIndex++;
                    // Reset to 0 if we exceed the list size
                    if (removalIndex >= workingList.size()) {
                        removalIndex = 0;
                        System.out.println("Resetting removal index to 0");
                    }
                }
            } else {
                removalIndex = 0;
            }
        }

        System.out.println("\nFinal list after removal process: " + workingList);
        return workingList;
    }
    /**
     * Main method to demonstrate the list processing functionality.
     * Creates list1 and list2 with predefined values and processes them using 2n-1 logic.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Creating list1 and list2 with predefined values
        List<Integer> list1 = Arrays.asList(5, 8, 8, 5, 4, 9, 4, 2, 7, 5, 9, 4, 2);
        List<String> list2 = new ArrayList<>(Arrays.asList(
                "UcQvp", "87NYZ", "j6AZr", "rbK1H", "ktKj", "kfYr",
                "90T0B", "h1d", "ZeYbt", "u1J6T", "cPBG", "Jv1",
                "OYSh7", "9cQj", "qSq", "FUGu", "o2PjN", "8w6"
        ));

        System.out.println("List1 (numbers): " + list1);
        System.out.println("List2 (strings): " + list2);
        System.out.println("List2 size: " + list2.size());
        System.out.println("\nProcessing using 2n-1 formula:");
        System.out.println("=================================");

        List<String> resultList = processLists(list1, list2);

        System.out.println("\nResult after 2n-1 processing: " + resultList);
        System.out.println("Result size: " + resultList.size());

        // Apply the removal process
        List<String> finalResult = processRemoval(resultList);

        System.out.println("\n=================================");
        System.out.println("FINAL RESULT: " + finalResult);
        System.out.println("Final size: " + finalResult.size());
    }
}