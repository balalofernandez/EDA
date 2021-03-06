package maps;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * A hash table with linear probing and the MAD hash function
 */
/**
 * A hash table data structure that uses linear probing to handle collisions.
 * The hash function uses the built-in hashCode method and the
 * multiply-add-and-divide method. The load factor kept less than or equal to
 * 0.5. When the load factor reaches 0.5, the entries are rehashed into a new
 * bucket array with twice the capacity.
 *
 * @author R. Cabido, A. Duarte, and J. Velez
 * @param <K> Key type
 * @param <V> Value type
 */
abstract public class AbstractHashTableMap<K, V> implements Map<K, V> {

    /**
     * @param <T> Key type
     * @param <U> Value type
     *
     */
    private class HashEntry<T, U> implements Entry<T, U> {

        protected T key;
        protected U value;

        public HashEntry(T k, U v) {
            key = k;
            value = v;
        }

        @Override
        public U getValue() {
            return value;
        }

        @Override
        public T getKey() {
            return key;
        }

        public U setValue(U val) {
            U oldValue = value;
            value = val;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {

            if (o.getClass() != this.getClass()) {
                return false;
            }

            HashEntry<T, U> ent;
            try {
                ent = (HashEntry<T, U>) o;
            } catch (ClassCastException ex) {
                return false;
            }
            return (ent.getKey().equals(this.key))
                    && (ent.getValue().equals(this.value));
        }

        /**
         * Entry visualization.
         */
        @Override
        public String toString() {
            return "(" + key + "," + value + ")";
        }
    }

    /**
     * @author Juan David Quintana Perez, Daniel Arroyo Cortes
     */
    private class HashTableMapIterator<T, U> implements Iterator<Entry<T, U>> {

        private int pos;
        private HashEntry<T, U>[] bucket;
        private Entry<T, U> AVAILABLE;

        public HashTableMapIterator(HashEntry<T, U>[] b, Entry<T, U> av, int numElems) {
            this.bucket = b; //anyadir: para que no falle hasNext al comparar pos < bucket.length
            if (numElems == 0) {
                this.pos = bucket.length;
            } else {
                this.pos = 0;
                goToNextElement(0);
                this.AVAILABLE = av;
            }
        }

        private void goToNextElement(int start) {
            final int n = bucket.length;
            this.pos = start;
            while ((this.pos < n) && ((this.bucket[this.pos] == null) || (this.bucket[this.pos] == this.AVAILABLE))) {
                this.pos++;
            }
        }

        @Override
        public boolean hasNext() {
            return (this.pos < this.bucket.length);
        }

        @Override
        public Entry<T, U> next() {
            if (hasNext()) {
                int currentPos = this.pos;
                goToNextElement(this.pos + 1);
                return (Entry<T, U>) this.bucket[currentPos];
            } else {
                throw new IllegalStateException("The map has not more elements");
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not implemented.");
        }
    }

    private class HashTableMapKeyIterator<T, U> implements Iterator<T> {

        public HashTableMapIterator<T, U> it;

        public HashTableMapKeyIterator(HashTableMapIterator<T, U> it) {
            this.it = it;
        }

        @Override
        public T next() {
            return it.next().getKey();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not implemented.");
        }
    }

    private class HashTableMapValueIterator<T, U> implements Iterator<U> {

        public HashTableMapIterator<T, U> it;

        public HashTableMapValueIterator(HashTableMapIterator<T, U> it) {
            this.it = it;
        }

        @Override
        public U next() {
            return it.next().getValue();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not implemented.");
        }
    }

    protected class HashEntryIndex {

        int index;
        boolean found;

        public HashEntryIndex(int index, boolean f) {
            this.index = index;
            this.found = f;
        }
    }

    protected int n = 0; // number of entries in the dictionary
    protected int prime, capacity; // prime factor and capacity of bucket array
    protected long scale, shift; // the shift and scaling factors
    protected HashEntry<K, V>[] bucket;// bucket array
    protected final Entry<K, V> AVAILABLE = new HashEntry<>(null, null);

    /**
     * Creates a hash table with prime factor 109345121 and capacity 1000.
     */
    protected AbstractHashTableMap() {
        this(109345121, 1000); // reusing the constructor HashTableMap(int p, int cap)
    }

    /**
     * Creates a hash table with prime factor 109345121 and given capacity.
     *
     * @param cap initial capacity
     */
    protected AbstractHashTableMap(int cap) {
        this(109345121, cap); // reusing the constructor HashTableMap(int p, int cap)
    }

    /**
     * Creates a hash table with the given prime factor and capacity.
     *
     * @param p prime number
     * @param cap initial capacity
     */
    protected AbstractHashTableMap(int p, int cap) {
        this.n = 0;
        this.prime = p;
        this.capacity = cap;
        this.bucket = (HashEntry<K, V>[]) new HashEntry[capacity]; // safe cast
        Random rand = new Random();
        this.scale = rand.nextInt(prime - 1) + 1;
        this.shift = rand.nextInt(prime);
    }

    /**
     * Returns the number of entries in the hash table.
     *
     * @return the size
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * Returns whether or not the table is empty.
     *
     * @return true if the size is 0
     */
    @Override
    public boolean isEmpty() {
        return (n == 0);
    }
    
    abstract protected int offset(int hashKey, int p);

    protected HashEntryIndex findEntry(K key) throws IllegalStateException {
        checkKey(key);
        boolean encontrado = false;
        int hv = hashValue(key);
        int i = 0;
        int index = offset(hv,i);
        HashEntry<K,V> entry = bucket[index];
        if(entry != null){
            while (!encontrado && i<bucket.length && entry!=null){
                if(key.equals(entry.getKey())){
                    encontrado = true;
                    break;
                }
                i++;
                index = offset(hv,i);
                entry = bucket[index];
            }
        }
        return new HashEntryIndex(index,encontrado);
    }

    /**
     * Returns the value associated with a key.
     *
     * @param key
     * @return value
     */
    @Override
    public V get(K key) throws IllegalStateException {
        checkKey(key);
        HashEntryIndex hentry = findEntry(key);
        if(hentry.found){
            return bucket[hentry.index].value;
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
        checkKey(key);
        if((n +1 )/bucket.length >0.5){
            rehash();
        }
        V antiguoValor = null;
        int hv = hashValue(key);
        int i = 0;
        int index = offset(hv,i);
        HashEntry<K,V> entry = bucket[index];
        int indice_entry = -1;
        if(entry != null){
            while (i<bucket.length && entry!=null){
                if(key.equals(entry.getKey())){
                    antiguoValor= entry.value;
                    bucket[index] = new HashEntry<K,V>(key,value);
                    n++;
                    break;
                }
                if(entry==AVAILABLE && indice_entry<0){
                    indice_entry = index;
                }
                i++;
                index = offset(hv,i);
                entry = bucket[index];
            }
            if(antiguoValor == null && indice_entry<0){//No ha encontrado ningun available y no ha conseguido encontrar el valor
                bucket[index] = new HashEntry<K,V>(key,value);
                n++;
            }
            else if(indice_entry>=0){
                bucket[indice_entry] = new HashEntry<K,V>(key,value);
                n++;
            }
        }
        else {
            bucket[index] = new HashEntry<K,V>(key,value);
            n++;
        }
        return antiguoValor;
    }

    /**
     * Removes the key-value pair with a specified key.
     *
     * @param key
     * @return
     */
    @Override
    public V remove(K key) throws IllegalStateException {
        checkKey(key);
        HashEntryIndex hentry = findEntry(key);
        if(hentry.found){
            n--;
            V value = bucket[hentry.index].value;
            bucket[hentry.index] = null;
            return value;
        }
        return null;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashTableMapIterator<K, V>(this.bucket, this.AVAILABLE, this.n);

    }

    /**
     * Returns an iterable object containing all of the keys.
     *
     * @return
     */
    @Override
    public Iterable<K> keys() {
        return new Iterable<K>() {
            public Iterator<K> iterator() {
                return new HashTableMapKeyIterator<K, V>(new HashTableMapIterator<K, V>(bucket, AVAILABLE, n));
            }
        };
    }

    /**
     * Returns an iterable object containing all of the values.
     *
     * @return
     */
    @Override
    public Iterable<V> values() {
        return new Iterable<V>() {
            public Iterator<V> iterator() {
                return new HashTableMapValueIterator<K, V>(new HashTableMapIterator<K, V>(bucket, AVAILABLE, n));
            }
        };
    }

    /**
     * Returns an iterable object containing all of the entries.
     *
     * @return
     */
    @Override
    public Iterable<Entry<K, V>> entries() {
        return new Iterable<Entry<K, V>>() {
            public Iterator<Entry<K, V>> iterator() {
                return new HashTableMapIterator<K, V>(bucket, AVAILABLE, n);
            }
        };
    }

    /**
     * Determines whether a key is valid.
     *
     * @param k Key
     */
    protected void checkKey(K k) {
        // We cannot check the second test (i.e., k instanceof K) since we do not know the class K
        if (k == null) {
            throw new IllegalStateException("Invalid key: null.");
        }
    }

    /**
     * Hash function applying MAD method to default hash code.
     *
     * @param key Key
     * @return
     */
    protected int hashValue(K key) {
        return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) %capacity);
    }

    /**
     * Doubles the size of the hash table and rehashes all the entries.
     */
    protected void rehash() {
        LinkedList<Entry<K, V>> entries = new LinkedList<>();
        for(Entry<K, V> entry: entries()){
            entries.addLast(entry);
        }
        this.n = 0;
        this.prime = 109345121;
        this.capacity = capacity*2;
        this.bucket = (HashEntry<K, V>[]) new HashEntry[capacity]; // safe cast
        Random rand = new Random();
        this.scale = rand.nextInt(prime - 1) + 1;
        this.shift = rand.nextInt(prime);
        for(Entry<K, V> entry: entries){
            put(entry.getKey(), entry.getValue());
        }
    }

    protected void rehash(int newcap) {
        Iterable<Entry<K,V>> entries= entries();
        this.n = 0;
        this.prime = 109345121;
        this.capacity = newcap;
        this.bucket = (HashEntry<K, V>[]) new HashEntry[capacity]; // safe cast
        Random rand = new Random();
        this.scale = rand.nextInt(prime - 1) + 1;
        this.shift = rand.nextInt(prime);
        for(Entry<K, V> entry: entries){
            put(entry.getKey(), entry.getValue());
        }
    }

}
