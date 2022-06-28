package DadesHash;

public class HashNode <K,T> implements Comparable<HashNode<K, T>> {
    private K key;
    private T object;
    private HashNode <K,T> next;

    public HashNode(K key,T object){
        this.key = key;
        this.object = object;
        next = null;
    }



    @Override
    public String toString() {
        return "HashNode{" +
                "key=" + key +
                ", object=" + object +
                ", next=" + next +
                '}';
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public HashNode<K, T> getNext() {
        return next;
    }

    public void setNext(HashNode<K, T> next) {
        this.next = next;
    }

    @Override
    public int compareTo(HashNode<K, T> o) {
        return 0;
    }
}
