package learn.sort;

import java.util.Arrays;

public class SelectSort {

    public static void main(String[] args) {
        int[] a = {50, 3, 542, 745, 2014, 154, 63, 616};
        selectSort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void selectSort(int[] a){
        for(int i=0 ; i<a.length ; i++) {
            int k=i;
            for(int j=i+1 ; j<a.length ; j++) {
                if(a[j]<a[k]) {
                    k = j;
                }
            }
            int temp = a[i];
            a[i] = a[k];
            a[k] = temp;
        }
    }

}
