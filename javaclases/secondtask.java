import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class secondtask {
    public static void main(String[] args) {
        // --- ARRAY COPY TASK ---
        int[] firstArray = {23, 45, 12, 67, 89, 34, 56, 78};
        int[] secondArray = firstArray.clone();
        int[] thirdArray = new int[firstArray.length];
        for (int i = 0; i < firstArray.length; i++) {
            thirdArray[i] = firstArray[i];
        }

        System.out.println("first array is: " + Arrays.toString(firstArray));
        System.out.println("second array is: " + Arrays.toString(secondArray));
        System.out.println("third array is: " + Arrays.toString(thirdArray));

        // --- INDEX FILTERING TASK ---
        task();

        // --- ELIMINATION GAME TASK ---
        eliminationGame();
    }

    public static void task() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(23);
        list1.add(45);
        list1.add(12);
        list1.add(67);
        list1.add(89);
        list1.add(34);
        list1.add(56);
        list1.add(78);

        List<Integer> indexList = new ArrayList<>();
        indexList.add(1);
        indexList.add(3);
        indexList.add(5);
        indexList.add(7);

        List<Integer> list4 = new ArrayList<>();
        for (int i = 0; i < indexList.size(); i++) {
            int index = indexList.get(i);
            if (index < list1.size()) {
                list4.add(list1.get(index));
            }
        }

        System.out.println("first list is: " + list1);
        System.out.println("fourth list is: " + list4);
    }

    public static void eliminationGame() {
        List<Integer> list8 = new ArrayList<>();
        list8.add(23);
        list8.add(45);
        list8.add(12);
        list8.add(67);
        list8.add(89);
        list8.add(34);
        list8.add(56);
        list8.add(78);
        list8.add(69);
        list8.add(10);

        System.out.println("Initial list: " + list8);

        // First turn
        for (int i = list8.size() - 1; i >= 0; i--) {
            if (i % 2 == 0) {
                list8.remove(i);
            }
        }
        System.out.println("After first turn: " + list8);

        // Second turn
        for (int i = list8.size() - 1; i >= 0; i--) {
            if (i % 2 == 0) {
                list8.remove(i);
            }
        }
        System.out.println("After second turn: " + list8);

        // Third turn: remove until one remains
        while (list8.size() > 1) {
            list8.remove(1);
            System.out.println("Current list: " + list8);
        }

        System.out.println("Final element remaining: " + list8.get(0));
    }
}
