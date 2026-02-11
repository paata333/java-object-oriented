package t2l.sub;

import t2l.A21;
import t2l.A22;

public class R21 {
    public static void main(String[] args) {
        int area = A21.width * A21.height;
        System.out.println("Area of rectangle: " + area);

        String[] months = {
                "Invalid", "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };

        if (A22.monthNumber >= 1 && A22.monthNumber <= 12) {
            System.out.println("Month: " + months[A22.monthNumber]);
        } else {
            System.out.println("Invalid month number");
        }
    }
}
