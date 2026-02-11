//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
public class Main{
    public static void main(String[]args){
        System.out.println("hello world it is me paata shvelidze");
        int l = 10;
        int b = 20;
        int c = l +b;
        System.out.println("sum of l and b" + c);

        int r= 5;
        float pi =3.14f;
        float area = pi * r *  r;
        System.out.println("area of circle" + area);

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter number of minutes");
        int number = scanner.nextInt();

        int s = number * 60;
        System.out.println( number + "minutes equals to" + s);
        scanner.close();

        String v = "*";
        for ( int i = 1; i < 5; i++) {
            for (int j = 1; j <= i; j++){
             System.out.println(v);
            }
            System.out.println( );
        }


    }

}