

public class Check {

    public static void main(String[] args) {

        int[] marks = {50, 30, 20, 10, 40, 80, 600, 90, 70, 100};
        //  System.out.println(marks.length);
        //  int key = 20;
       /* if (linearSearch(marks, key) == -1) {
            System.out.println("Not found");
        } else {
            System.out.println(key + " found st index: " + linearSearch(marks, key));
        }*/

        //maxNum(marks);
        //minNum(marks);

        int[] arr = {
                10, 20, 30, 40, 50, 60, 70, 80, 90, 100
        };
        int key = 90;
        /*if (binarySearch(arr, key) == -1) {
            System.out.println("NOT FOUND");
        } else {
            System.out.println(key + " found at index: " + binarySearch(arr, key));
        }*/

        triPatter(5);


    }

    public static void triPatter(int n) {
        //1st
        for (int i = n; i >= 1; i--) {
            //space
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            //star
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("  ");
            }
            System.out.println();

        }

    }

    public static int binarySearch(int[] arr, int key) {
        int start = 0, end = arr.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }


        }
        return -1;

    }

    public static void maxNum(int[] marks) {
        int max = marks[0];
        int index = 0;

        for (int i = 1; i < marks.length; i++) {

            if (max < marks[i]) {
                max = marks[i];
                index = i;
            }
        }
        System.out.println("largest number is: " + max + " at index: " + index);
    }

    public static void minNum(int[] marks) {
        int min = marks[0];
        int index = 0;

        for (int i = 1; i < marks.length; i++) {

            if (min > marks[i]) {
                min = marks[i];
                index = i;
            }
        }
        System.out.println("Smallest number is: " + min + " at index: " + index);
    }


    public static int linearSearch(int[] marks, int key) {

        for (int i = 0; i < marks.length; i++) {

            if (marks[i] == key) {
                return i;
            }
        }
        return -1;
    }


}

