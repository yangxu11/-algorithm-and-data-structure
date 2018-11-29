package learn.sort;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] a = {50, 3, 542, 745, 2014, 154, 63, 616};
        bubbleSort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void bubbleSort(int[] a) {
        for(int i=0 ; i<a.length ; i++) {
            for(int j=i+1 ; j<a.length ; j++) {
                if(a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
}
