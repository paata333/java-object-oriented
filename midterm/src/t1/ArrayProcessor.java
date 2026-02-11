package t1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class processes two lists as per the specifications shown in
 * the animation at max.ge/oop_midterm/t1/t1_paata_shvelidze_1_37439627.html.
 * <p>
 * The program initializes an Integer list (list1) with 9 elements and a String list (list2)
 * with 11 elements. It then selects 9 random elements from list2 to create a result list.
 * </p>
 *
 * @author Paata Shvelidze
 * @version 1.0
 */
public class ArrayProcessor {

    /**
     * Main method that demonstrates the list processing operations.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Initialize the first list with 9 integer values
        List<Integer> list1 = new ArrayList<>();
        list1.add(17);
        list1.add(22);
        list1.add(43);
        list1.add(84);
        list1.add(95);
        list1.add(106);
        list1.add(31);
        list1.add(52);
        list1.add(78);

        // Initialize the second list with 11 string values
        List<String> list2 = new ArrayList<>();
        list2.add("cat");
        list2.add("elephant");
        list2.add("dog");
        list2.add("fish");
        list2.add("ant");
        list2.add("lion");
        list2.add("tiger");
        list2.add("bear");
        list2.add("wolf");
        list2.add("fox");
        list2.add("owl");

        // Process the lists and display results
        processLists(list1, list2);
    }

    /**
     * Processes two lists according to specified rules and displays the results.
     * Prints both lists in full.
     * Creates a result list with 9 randomly selected elements from list2.
     *
     * @param list1 A list of 9 integers
     * @param list2 A list of 11 strings
     */
    public static void processLists(List<Integer> list1, List<String> list2) {
        // Display the original lists completely
        System.out.println("List1: " + list1);
        System.out.println("List2: " + list2);

        // Select 9 random elements from list2
        List<String> resultList = new ArrayList<>();
        Random random = new Random();

        // Create a copy of list2 to avoid modifying the original
        List<String> tempList = new ArrayList<>(list2);

        // Select 9 random elements
        for (int i = 0; i < 9; i++) {
            if (tempList.isEmpty()) {
                break; // Safety check in case list2 has fewer than 9 elements
            }

            int randomIndex = random.nextInt(tempList.size());
            resultList.add(tempList.get(randomIndex));
            tempList.remove(randomIndex); // Remove to avoid duplicates
        }

        // Display the final result list
        System.out.println("Result (9 random elements from List2): " + resultList);
    }
}