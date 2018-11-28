package learn.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] a = new int[100];
        int[] b = new int[10000];
        for(int i=0 ; i<100 ; i++) {
            a[i] = 100-i;
        }
        for(int i=0 ; i<10000 ; i++) {
            b[i] = i;
        }
        double time1 = insertSort1(a);
        double time2 = insertSort2(b);
        System.out.println(Arrays.toString(a));
        //System.out.println(time1);
        //System.out.println(time2);
    }

    //从头部插入
    public static double insertSort1(int[] nums) {
        double start = System.currentTimeMillis();
        for(int i=0 ; i<nums.length ; i++) {
            int temp = nums[i];
            for(int j=0 ; j<=i ; j++) {
                if(nums[j] > temp) {
                    for(int k=i ; k>j ; k--) {
                        nums[k]=nums[k-1];
                    }
                    nums[j]=temp;
                    break;
                }
            }
        }
        return System.currentTimeMillis()-start;
    }
//从末尾插入
    public static double insertSort2(int[] nums) {
        double start = System.currentTimeMillis();
        for(int i=1 ; i<nums.length ; i++) {
            int temp = nums[i];
            int k=i;
            for(int j=i-1 ; j>=0 ; j--) {
                if(nums[j]>temp) {
                    nums[k] = nums[j];
                    nums[j] = temp;
                    k=j;
                } else {
                    break;
                }
            }
        }
        return System.currentTimeMillis()-start;
    }
}
