//import java.util.Arrays;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //enter size
        System.out.print("Enter size: ");
        int size = sc.nextInt();

//creating array
        int[] arr = new int[size];
        System.out.print("Entering elements in array: ");

        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.print("Original array: ");

        arrayPrinter(arr);
        System.out.println();
        int[] arr_2 = arr.clone();
        System.out.print("Copied array: ");

        arrayPrinter(arr_2);
        System.out.println();
        arr_2[0] = 3;
        System.out.print("Updated Original array: ");

        arrayPrinter(arr);
        System.out.println();

        System.out.print("Updated Copied array: ");

        arrayPrinter(arr_2);

    }

    public static void arrayPrinter(int[] array) {
        for (int j : array) {
            System.out.print(j + " ");
        }
    }
}
