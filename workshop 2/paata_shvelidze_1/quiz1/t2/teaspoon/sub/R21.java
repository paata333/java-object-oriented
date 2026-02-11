package paata_shvelidze_1.quiz1.t2.teaspoon.sub;

import paata_shvelidze_1.quiz1.t2.teaspoon.A21;
import paata_shvelidze_1.quiz1.t2.cream.makeup.A22;

public class R21 {
    public static void main(String[] args) {
        // Create objects of A21 and A22
        A21 rectangle = new A21();
        A22 monthInfo = new A22();

        // Calculate the area of the rectangle
        int area = rectangle.getWidth() * rectangle.getHeight();
        System.out.println("Rectangle Area: " + area);

        // Get the month number and determine the name
        int monthNumber = monthInfo.getMonth();
        String monthName = getMonthName(monthNumber);

        // Print the month name
        System.out.println("Month Name: " + monthName);
    }

    // Method to convert month number to name with validation
    private static String getMonthName(int month) {
        String[] months = {
                "Invalid Month", "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };

        if (month < 1 || month > 12) {
            return "Invalid Month";
        }
        return months[month];
    }
}
