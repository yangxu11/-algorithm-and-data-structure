package learn.dataStructure;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

class PriorityTest{
    public static void main(String[] args) {
        MyPriorityQueue<Integer> pq = new MyPriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 > o2 ? 1 : (o1==o2 ? 0: -1);
            }
        });
        Integer[] a = {4,7,2,4,6,8};
        List<Integer> list = Arrays.asList(a);
        pq.addAll(list);
        pq.insert(10);
        int x =pq.size();
        for(int i=0 ; i<x; i++) {
            System.out.println(pq.poll());
        }


    }
}

public class MyPriorityQueue<T> {
    private Object[] queue;
    private int size;
    private Comparator<T> comparator = new Comparator<T>() {
        @Override
        public int compare(T o1, T o2) {
            return 1;
        }
    };

    public int size() {
        return size;
    }
    public static final int DEFULT_SIZE = 11;

    public MyPriorityQueue(Comparator<T> comparator) {
        queue = new Object[DEFULT_SIZE];
        this.comparator = comparator;
    }

    public void insert(T x) {
        if(size==queue.length) {
            enlargeArray();
        }
        int k = size++;
        if(k==0) {
            queue[0] = x;
        } else {
            siftup(k,x);
        }
    }
    //向上筛选，插入元素，先插入末尾，与父结点比较向上筛选，直到它比父节点大
    private void siftup(int i,T x) {
       // Comparable<? extends T> key = (Comparable<? extends T>) x;
        while(i>0) {
            int parent = (i-1)>>>1;
            Object e = queue[parent];
            if(comparator.compare(x,(T)e) >=0)  {
                break;
            }
            queue[i] = e;
            i = parent;
        }
        queue[i] = x;
    }

    private void enlargeArray() {
        Object[] newQueue = new Object[queue.length*2 + 1];
        for(int i=0 ; i<queue.length ; i++) {
            newQueue[i] = queue[i];
        }
        queue = newQueue;
    }

    public T poll(){
        int k = --size;
        T x = (T)queue[k];
        T result = (T)queue[0];
        queue[k] = null;
        if(size==0) {
            queue[0] = null;
        } else {
            siftdown(0,x);
        }
        return result;
    }
//向下筛选，弹出元素，先将最后一个节点拿到开头0位置处，与其较小的子节点比较，直到它比子节点小
    private void siftdown(int i,T x) {
        int half = size>>>1;
        while(i<half) {
            int child = (i<<1) + 1;
            if(child+1 < size && comparator.compare((T)queue[child+1],(T)queue[child]) <=0) {
                child++;
            }//判断是否有右节点，以及是否右节点小
            if(comparator.compare(x,(T)queue[child]) <= 0) {
                break;
            }
            queue[i] = queue[child];
            i = child;
        }
        queue[i] = x;
    }

    public void addAll(Collection<T> c){
        for( T e : c) {
            insert(e);
        }
    }

    public String toString() {
        return Arrays.toString(queue);
    }



}
