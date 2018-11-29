package learn.sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] a = {50, 3, 542, 745, 2014, 154, 63, 616};
        radixSort(a);
        System.out.println(Arrays.toString(a));
    }

    private static int getMax(int[] a) {
        int max= 0;
        for(int i=0 ; i<a.length ; i++) {
            if(a[i]>max) {
                max= a[i];
            }
        }
        return max;
    }

    public static void radixSort(int[] a) {
        int max = getMax(a);
        for(int i=1 ; max/i > 0 ; i*=10) {
            radixSort(a,i);
        }
    }

    public static void radixSort(int[] a,int rate){
        int[] bucket = new int[10];
        int[] temp = new int[a.length];

        for(int i=0 ; i<a.length ; i++) {
            bucket[(a[i]/rate)%10]++;
        }
        for(int i=1 ; i<10 ; i++) {
            bucket[i] += bucket[i-1];
        }//表示我是第几个

        for(int i=a.length-1 ; i>=0 ; i--) {
            temp[bucket[(a[i]/rate)%10]-1] = a[i];//bucket数组中的值表示该数字排第几，放到数组中应该-1
            bucket[(a[i]/rate)%10]--;
        }
        for(int i=0 ; i<a.length ; i++) {
            a[i] = temp[i];
        }
        bucket=null;
        temp=null;
    }

}
