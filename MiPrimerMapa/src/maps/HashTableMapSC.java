package maps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Separate chaining table implementation of hash tables. Note that all
 * "matching" is based on the equals method.
 *
 * @author A. Duarte, J. Vélez, J. Sánchez-Oro
 * @param <K> The key
 * @param <V> The stored value
 */
public class HashTableMapSC<K, V> implements Map<K, V> {

    ArrayList<LinkedList<HashEntry<K,V>>> mapa;
    int primo = -1;
    int a;
    int b;
    int size;

    private class HashEntry<T, U> implements Entry<T, U> {
        private T t;
        private U u;

        public HashEntry(T k, U v) {
            this.t=k;
            this.u=v;
        }
        @Override
        public U getValue() { return u;}

        @Override
        public T getKey() {return t;}

        public U setValue(U val) {
            u=val;
            return u;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            HashEntry obj = (HashEntry) o;
            return this.t.equals(obj) && this.u.equals(obj);
        }

        @Override
        public String toString() {
            return "("+t.toString()+","+u.toString()+")";
        }
    }

    private class HashTableMapIterator<T, U> implements Iterator<Entry<T, U>> {

        //Ejercicio 2.2
        public HashTableMapIterator(ArrayList<HashEntry<T, U>>[] map, int numElems) {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        private void goToNextElement() {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public Entry<T, U> next() {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public void remove() {
            // NO HAY QUE IMPLEMENTARLO
            throw new UnsupportedOperationException("Not implemented.");
        }
    }

    private class HashTableMapKeyIterator<T, U> implements Iterator<T> {

        public HashTableMapKeyIterator(HashTableMapIterator<T, U> it) {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public void remove() {
            // NO HAY QUE IMPLEMENTARLO
            throw new UnsupportedOperationException("Not implemented.");
        }
    }

    private class HashTableMapValueIterator<T, U> implements Iterator<U> {

        public HashTableMapValueIterator(HashTableMapIterator<T, U> it) {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public U next() {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not implemented.");
        }
    }

    /**
     * Creates a hash table
     */
    public HashTableMapSC() {
        this.mapa = new ArrayList<>(101);
        primo = 103;
        for (List<HashEntry<K, V>> l: mapa) {
            l = new LinkedList<>();
        }
        a = (int) (Math.random() * (primo-1));
        b = (int) (Math.random() * (primo-1));
    }

    private boolean esPrimo(int n){
        boolean primo = true;
        int aux=2;
        while(primo){
            primo = !(n % aux == 0);
            n++;
        }
        return primo;
    }
    /**
     * Creates a hash table.
     *
     * @param cap initial capacity
     */
    public HashTableMapSC(int cap) {
        this.mapa = new ArrayList<>(cap);
        //Vamos a buscar el siguiente primo
        int p = cap +1;
        while(!esPrimo(p)){
            p++;
        }

        for (List<HashEntry<K, V>> l: mapa) {
            l = new LinkedList<>();
        }
        a = (int) (Math.random() * (primo-1));
        b = (int) (Math.random() * (primo-1));
    }

    /**
     * Creates a hash table with the given prime factor and capacity.
     *
     * @param p prime number
     * @param cap initial capacity
     */
    public HashTableMapSC(int p, int cap) {
        this.primo = p;
        this.mapa = new ArrayList<>(cap);
        for (List<HashEntry<K, V>> l: mapa) {
            l = new LinkedList<>();
        }
        a = (int) (Math.random() * (primo-1));
        b = (int) (Math.random() * (primo-1));
    }

    /**
     * Hash function applying MAD method to default hash code.
     *
     * @param key Key
     * @return
     */
    protected int hashValue(K key) {
        return ((a* key.hashCode()+b) % primo) % mapa.size();
    }

    /**
     * Returns the number of entries in the hash table.
     *
     * @return the size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns whether or not the table is empty.
     *
     * @return true if the size is 0
     */
    @Override
    public boolean isEmpty() {
        return mapa.isEmpty();
    }

    /**
     * Returns the value associated with a key.
     *
     * @param key
     * @return value
     */
    @Override
    public V get(K key) throws IllegalStateException {
        if(key == null)
            throw new IllegalStateException("Clave no valida");
        int hv = hashValue(key);
        LinkedList<HashEntry<K,V>> list = this.mapa.get(hv);
        for(HashEntry<K,V> he:list){
            if(key.equals(he.getKey())){
                return he.getValue();
            }
        }
        return null;
    }

    /**
     * Put a key-value pair in the map, replacing previous one if it exists.
     *
     * @param key
     * @param value
     * @return value
     */
    @Override
    public V put(K key, V value) throws IllegalStateException {
        if(key == null)
            throw new IllegalStateException("Clave no valida");
        int hv = hashValue(key);
        LinkedList<HashEntry<K,V>> list = this.mapa.get(hv);
        for(HashEntry<K,V> he:list) {
            if (key.equals(he.getKey())) {
                V aux = he.getValue();
                he.setValue(value);
                return aux;
            }
        }
        list.add(new HashEntry<K,V>(key,value));
        size++;
        return null;
    }

    /**
     * Removes the key-value pair with a specified key.
     *
     * @param key
     * @return
     */
    @Override
    public V remove(K key) throws IllegalStateException {
        if(key == null)
            throw new IllegalStateException("Clave no valida");
        int hv = hashValue(key);
        LinkedList<HashEntry<K,V>> list = this.mapa.get(hv);
        for(HashEntry<K,V> he:list) {
            if (key.equals(he.getKey())) {
                V aux = he.getValue();
                list.remove(he);
                return aux;
            }
        }
        return null;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns an iterable object containing all of the keys.
     *
     * @return
     */
    @Override
    public Iterable<K> keys() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns an iterable object containing all of the values.
     *
     * @return
     */
    @Override
    public Iterable<V> values() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns an iterable object containing all of the entries.
     *
     * @return
     */
    @Override
    public Iterable<Entry<K, V>> entries() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Determines whether a key is valid.
     *
     * @param k Key
     */
    protected void checkKey(K k) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Increase/reduce the size of the hash table and rehashes all the entries.
     * @param newCap
     */
    protected void rehash(int newCap) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
