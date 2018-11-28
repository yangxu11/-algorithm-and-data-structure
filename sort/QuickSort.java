package learn.sort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        QuickSort q = new QuickSort();
        int[] a = {12,32,8,4,29,23,12,43,76,98,32,30};

        q.quickSort(a);
        System.out.println(Arrays.toString(a));
    }

    public void quickSort(int[] a) {
        quickSort2(a,0,a.length-1);
    }

    private void quickSort1(int[] a, int left, int right) {
        if(left>=right) return;

        int i=left;
        int j=right;
        int key =getMid(a,left,right);;


        while(j > i) {
            while(j>i && a[j]>=key) {
                j--;
            }
            a[i] = a[j];
            while(j>i && a[i]<=key) {
                i++;
            }
            a[j] = a[i];
        }
        a[i] = key;

        quickSort1(a,left,i-1);
        quickSort1(a,i+1,right);

    }

    private void quickSort2(int[] a , int left , int right) {
        if(left>=right) return;

        int key = getMid(a,left,right);

        int i=left;
        int j=right;

        while(j>i){
            while(j>i && a[j]>=key) {
                j--;
            }
            while(j>i && a[i]<=key) {
                i++;
            }
            swap(a,i,j);
        }

        if(a[i] >= key && i>left) {
            swap(a,left,i-1);
        } else {
            swap(a,left,i);
        }
        quickSort2(a,left,i-1);
        quickSort2(a,i+1,right);

    }

    private void swap(int[] a,int left, int right) {
        int p = a[left];
        a[left] = a[right];
        a[right] = p;
    }

    private int getMid(int[] a ,int left,int right) {
        int center = (left+right)/2;
        if(a[center] < a[left]) {
            swap(a,center,left);
        }
        if(a[right] < a[left]) {
            swap(a,right,left);
        }
        if(a[right] < a[center]) {
            swap(a,center,right);
        }
        swap(a,center,left);
        return a[left];
    }

}
