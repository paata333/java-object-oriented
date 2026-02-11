import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
public class workshop {
    public static void main(String[] args) {
        int[] firstArray = {23, 45, 12, 67, 89, 34, 56, 78};
        int[] secondArray = firstArray.clone();
        int[] thirdArray = new int[firstArray.length];
        for (int i = 0; i < firstArray.length; i++) {
            thirdArray[i] = firstArray[i];
        }
        System.out.println("first array is: " + Arrays.toString(firstArray));
        System.out.println("second array is:" + Arrays.toString(secondArray));
        System.out.println("third array is: " + Arrays.toString(thirdArray));
      task();

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
        List<Integer> List4 = new ArrayList<>();
        for (int i = 0; i < indexList.size(); i++) {
            int index = indexList.get(i);
            if (index < list1.size()) {
                List4.add(list1.get(index));
            }
            System.out.println("first list is: " + list1);
            System.out.println("fourth list is: " + List4);


        }

    }
}