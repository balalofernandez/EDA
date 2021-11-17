package maps;

/**
 * @param <K> The hey
 * @param <V> The stored value
 */
public class HashTableMapDH<K, V> extends AbstractHashTableMap<K, V> {

    private int q;

    public HashTableMapDH(int size) {
        super(size);
        q = (int) (Math.random()*(capacity +1));
    }

    public HashTableMapDH() {
        super();
        q = (int) (Math.random()*(capacity +1));
    }

    public HashTableMapDH(int p, int cap) {
        super(p, cap);
        q = (int) (Math.random()*(capacity +1));
    }

    @Override
    protected int offset(int hashKey, int p) {
        return ((hashKey + (q- hashKey%q)*p)%capacity);
    }
}
