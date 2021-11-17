
package dictionary;

import sun.awt.image.ImageWatched;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author mayte
 * @param <K>
 * @param <V>
 */
public class MyDictionary<K,V> implements Dictionary<K,V> {

    private LinkedList<HashEntry<K,V>>[] dictionary;
    private int a;
    private int b;
    private int primo;
    private int size;

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
    
    private class HashDictionaryIterator<T, U> implements Iterator<Entry<T, U>> {

        private MyDictionary dictionary;
        private Iterable<Entry<T, U>> lista;

        public HashDictionaryIterator(MyDictionary dictionary) {
            this.dictionary = dictionary;
            lista = dictionary.entries();
        }

        @Override
        public boolean hasNext() {
            return lista.iterator().hasNext();
        }

        @Override
        public Entry<T, U> next() {
            return lista.iterator().next();
        }

        
        
    }
    
    public MyDictionary(){
        dictionary = new LinkedList[20];
        int p = 20 +1;
        size = 0;
        while(!esPrimo(p)){
            p++;
        }
        primo = p;
        a = (int) (Math.random() * (primo-1));
        b = (int) (Math.random() * (primo-1));
    }

    public MyDictionary(int cap){
        dictionary = new LinkedList[cap];
        int p = cap +1;
        size = 0;
        while(!esPrimo(p)){
            p++;
        }
        primo = p;
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
     * Hash function applying MAD method to default hash code.
     *
     * @param key Key
     * @return
     */
    private int hashValue(K key) {
        return (a*key.hashCode()+b) % dictionary.length;
    }
    
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalStateException {
        if(key == null){
            throw new IllegalStateException("Null key");
        }
        if((size +1 )/dictionary.length >0.75){
            rehash();
        }
        HashEntry<K,V> entrada = new HashEntry<>(key,value);
        int hash = hashValue(key);
        if(dictionary[hash] == null){
            dictionary[hash] = new LinkedList<>();
        }
        dictionary[hash].add(entrada);
        size++;
        return entrada;
    }

    @Override
    public Entry<K, V> find(K key) throws IllegalStateException {
        if(key == null){
            throw new IllegalStateException("Null key");
        }
        int hash = hashValue(key);
        for (HashEntry<K,V> entry: dictionary[hash]) {
            if(key.equals(entry)){
                return entry;
            }
        }
        return null;
    }

    @Override
    public Iterable<Entry<K, V>> findAll(K key) throws IllegalStateException {
        if(key == null){
            throw new IllegalStateException("Null key");
        }
        int hash = hashValue(key);
        LinkedList<Entry<K, V>> lista = new LinkedList<>();
        for (HashEntry<K,V> entry: dictionary[hash]) {
            if(key.equals(entry)){
                lista.add(entry);
            }
        }
        return lista;
    }

    @Override
    public Entry<K, V> remove(Entry<K, V> e) throws IllegalStateException {
        if(e == null || e.getKey() == null ){
            throw new IllegalStateException("Null key");
        }
        int hash = hashValue(e.getKey());
        if(dictionary[hash] != null){
            if(dictionary[hash].remove(e))
                size--;
                return e;
        }
        return null;
    }

    @Override
    public Iterable<Entry<K, V>> entries() {
        LinkedList<Entry<K,V>> list = new LinkedList<>();
        for (int i = 0; i < dictionary.length; i++) {
            list.addAll(dictionary[i]);
        }
        return list;
    }
    
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashDictionaryIterator<K, V>(this);
    }
    /**
     * Doubles the size of the hash table and rehashes all the entries.
     */
    private void rehash() {
        MyDictionary<K,V> nDictionary = new MyDictionary<>(2* dictionary.length);
        for (int i = 0; i < dictionary.length; i++) {
            for(HashEntry<K,V> entry :dictionary[i]){
                nDictionary.insert(entry.getKey(),entry.getValue());
            }
        }
        dictionary = nDictionary.getDictionary();
        a = nDictionary.getA();
        b = nDictionary.getB();
        primo = nDictionary.getPrimo();
    }

    private LinkedList<HashEntry<K, V>>[] getDictionary() {
        return dictionary;
    }

    private int getA() {
        return a;
    }

    private int getB() {
        return b;
    }

    private int getPrimo() {
        return primo;
    }
}
