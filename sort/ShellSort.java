package learn.sort;

import java.util.Arrays;
//先分片，再对每片进行插入排序
public class ShellSort {

    public static void main(String[] args) {
        int[] a = new int[10];
        for(int i=0 ; i<10 ; i++) {
            a[i] = 10-i;
        }

        for(int gap = a.length/2 ; gap>0 ; gap /= 2) {
           for(int i=gap ; i<a.length ; i++) {
               int temp = a[i];
               int k =i;
               for(int j=i ; j>=gap ; j-=gap) {
                   if(a[j-gap] > temp) {
                       a[j] = a[j-gap];
                       k=j-gap;
                   }
               }
               a[k]=temp;
           }
        }
        System.out.println(Arrays.toString(a));
    }

    public void shellSort1(int[] a) {
        for(int gap = a.length/2 ; gap>0 ; gap /= 2) {
            for(int i=gap ; i<a.length ; i++) {
                int temp = a[i];
                for(int j=i%gap ; j<i ; j+=gap) {
                    if(temp <= a[j]) {
                        for(int k=i ; k>j ; k-=gap) {
                            a[k] = a[k-gap];
                        }
                        a[j] = temp;
                        break;
                    }
                }
            }
        }
    }

    public void shellSort2(int[] a) {
        for(int gap = a.length/2 ; gap>0 ; gap /= 2) {
            for(int i=gap ; i<a.length ; i++) {
                int temp = a[i];
                int k =i;
                for(int j=i ; j>=gap ; j-=gap) {
                    if(a[j-gap] > temp) {
                        a[j] = a[j-gap];
                        k=j-gap;
                    }
                }
                a[k]=temp;
            }
        }
    }

}
