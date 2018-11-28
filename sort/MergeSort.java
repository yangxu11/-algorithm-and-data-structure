package learn.sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] a = new int[10];
        for(int i=0 ; i<10 ; i++) {
            a[i] = 10-i;
        }
        mergeSort(a,0,a.length-1);
        System.out.println(Arrays.toString(a));
    }


    public static void mergeSort(int[] a,int left,int right){
        if(left<right) {
            int center = (left + right) / 2;
            mergeSort(a, left, center);
            mergeSort(a, center + 1, right);
            merge(a, left, center, right);
        }
    }

    public static void merge(int[] a,int leftPos,int center,int rightEnd){

        int rightPos = center+1;
        int leftEnd = center;
        int size = rightEnd - leftPos+1;
        int[] tmp = new int[a.length];
        int tmpPos = leftPos;

        while(leftPos<= leftEnd && rightPos<=rightEnd) {
            if(a[leftPos] < a[rightPos]) {
                tmp[tmpPos++] = a[leftPos++];
            } else {
                tmp[tmpPos++] = a[rightPos++];
            }
        }

        while(leftPos<=leftEnd) {
            tmp[tmpPos++] = a[leftPos++];
        }

        while(rightPos<=rightEnd) {
            tmp[tmpPos++] = a[rightPos++];
        }

        for(int i=0 ; i<size ; i++,rightEnd--) {
            a[rightEnd] = tmp[rightEnd];
        }


    }

}
