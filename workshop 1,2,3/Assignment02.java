package workshop.w1.paata_shvelidze_1;


/**
 * Find two packages inside this package.
 * Create classes A and B inside first and the second package accordingly
 * 1 point
*/

public class Assignment02 {
    public static void main(String[] args) {
        A classA = new A();
        B classB = new B();

        // Call methods from class A and class B
        classA.displayA();
        classB.displayB();

        System.out.println("This text is just for example");
    }

    public static class B {
        public void displayB() {
            System.out.println("This is class B in the 'second' package.");
        }
    }

    public static class A {
        public void displayA() {
            System.out.println("This is class A in the 'first' package.");
        }
    }
}
