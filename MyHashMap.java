package learn.dataStructure;

import java.util.*;

class MyHashMapTest{
    public static void main(String[] args) {
        MyHashMap<Integer,Integer> map = new MyHashMap();




        for(int i=0 ; i<30 ; i++) {
            map.put(i,i);
        }

        for(int i=0 ; i<30 ; i++) {
            System.out.println(map.get(i));
        }
        Set set = map.entrySet();

        System.out.println(set);


    }
}

public class MyHashMap<K,V> {
    //Node节点类
    class Node<K,V> implements Map.Entry<K,V> {
        final K key;
        V value;
        final int hash;
        Node<K,V> next;

        public Node(K key, V value, int hash, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public int getHash() {
            return hash;
        }

        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }

        @Override
        public int hashCode() {
            return key.hashCode()^value.hashCode();
        }

        public boolean equals(Object o) {
            if(o == this) return true;
            if(o instanceof Map.Entry) {
                if(((Map.Entry) o).getKey().equals(key)
                && ((Map.Entry) o).getValue().equals(value)) {
                    return true;
                }
            }

            return false;
        }
    }
    //node[] table
    //Defult_cap
    //threshold
    //load_factor
    Node<K,V>[] table;
    final int DEFULT_CAPICITY = 1<<4;
    int CAPICITY;
    int threshold;
    final float LOAD_FACTOR;
    int size;
    Set<Map.Entry<K,V>> entrySet;

    //构造方法
    //无参构造器
    public MyHashMap() {
        table = new Node[DEFULT_CAPICITY];
        CAPICITY = DEFULT_CAPICITY;
        LOAD_FACTOR = 0.75f;
        threshold = (int)(DEFULT_CAPICITY * LOAD_FACTOR);
        size=0;
    }

    //参数为map的构造器




    //hash函数
    private int hash(K key){
        int h;
        return key==null ? 0 : ((h=key.hashCode()) ^ (h>>>16));
    }

    //get

    public V get(K key) {
        Node<K,V> node;
        return (node=getNode(hash(key),key,table))==null ? null : node.value;
    }

    private Node<K,V> getNode(int hash, K key,Node[] table) {
        Node<K,V>[] tab = table;
        int len = tab.length;
        Node head,e;
        if(tab!=null && len !=0 && (head=tab[(len-1)&hash]) != null){
            if(head.hash==hash && (head.key == key)) {
                return head;
            }
            while((e= head.next) != null) {
                if(e.hash==hash && e.key==key) {
                    return e;
                }
            }
        }
        return null;
    }
    //containsKey
    public boolean containsKey(K key){
        return getNode(hash(key),key,table) !=null ;
    }

    //put
    public void put(K key,V value) {
        putVal(hash(key),key,value,table);
    }

    private void putVal(int hash, K key, V value,Node[] table) {
        Node<K,V>[] tab = table;
        Node node;
        int len = table.length;
        if(size > threshold) {
            resize();
        }
        if(tab[(len-1) & hash] == null)
            tab[(len-1) & hash] = new Node(key,value,hash,null);
        else {
            Node head,e=null;
            head = tab[(len-1) & hash];
            if(head.hash == hash && head.key ==key)
                e = head;
            while ((head=head.next) != null) {
                if(head.hash == hash && head.key ==key){
                    e = head;
                    break;
                }
                if(head.next == null) {
                    head.next = new Node(key,value,hash,null);
                    break;
                }
            }
            if(e != null) {
                e.setValue(value);
            }
        }
        if(++size > threshold) {
            resize();
        }
    }

    //putAll
    public void putAll(MyHashMap<K,V> map) {
        Set<Map.Entry<K,V>> set = map.entrySet();
        for(Map.Entry entry : set) {
            put((K)entry.getKey(),(V)entry.getValue());
        }

    }

    //resize
    private void resize() {
        int oldCap = CAPICITY;
        int oldThr = threshold;
        CAPICITY = CAPICITY<<1;
        threshold = (int)(CAPICITY * LOAD_FACTOR);
        Node[] oldTab = table;
        Node[] newTab = new Node[CAPICITY];


        for(int i=0 ; i<oldTab.length ; i++) {
            Node node = oldTab[i];
            if(node != null) {
                int hash = node.hash;
                K key = (K)node.getKey();
                V value = (V)node.getValue();
                putVal(hash,key,value,newTab);
                while((node=node.next) != null) {
                    putVal(node.hash,(K)node.getKey(),(V)node.getValue(),newTab);
                }
            }
        }
        table = newTab;

    }



    //entrySet
    public Set<Map.Entry<K,V>> entrySet() {
        Set<Map.Entry<K,V>> es;
        return new EntrySet();
    }

    class HashMapIterator{
        int index;
        Node<K,V> next;
        Node<K,V> current;
        public boolean hasNext() {
            return next!=null;
        }

        //abstract Node<K,V> next();

        public Map.Entry<K, V> nextNode() {
            Node<K,V>[] t;
            Node<K,V> e = next;
            if (e == null)
                throw new NoSuchElementException();
            if ((next = (current = e).next) == null && (t = table) != null) {
                do {} while (index < t.length && (next = t[index++]) == null);
            }
            return e;
        }

        public int size() {
            return size;
        }
    }
    final class EntrySetIterator extends HashMapIterator implements Iterator<Map.Entry<K,V>> {
        @Override
        public Map.Entry<K,V> next() {
            return nextNode();
        }
    }
     final class EntrySet extends AbstractSet<Map.Entry<K,V>>{
         @Override
         public Iterator<Map.Entry<K, V>> iterator() {
             return new EntrySetIterator();
         }

         @Override
         public int size() {
             return size;
         }
     }
     //keySet
    final class KeySetIterator extends HashMapIterator implements Iterator<K> {
         @Override
         public final K next() {
             return nextNode().getKey();
         }
     }
     final class KeySet extends AbstractSet<K> {
         @Override
         public Iterator<K> iterator() {
             return new KeySetIterator();
         }

         @Override
         public int size() {
             return size;
         }
     }
     final Set<K> keySet(){
        return new KeySet();
    }

    public String toString() {
        Iterator<Map.Entry<K,V>> i = entrySet().iterator();
        if (! i.hasNext())
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (;;) {
            Map.Entry<K,V> e = i.next();
            K key = e.getKey();
            V value = e.getValue();
            sb.append(key   == this ? "(this Map)" : key);
            sb.append('=');
            sb.append(value == this ? "(this Map)" : value);
            if (! i.hasNext())
                return sb.append('}').toString();
            sb.append(',').append(' ');
        }
    }
}
