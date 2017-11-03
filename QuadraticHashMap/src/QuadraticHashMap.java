import java.util.ArrayList;

public class QuadraticHashMap<K, V> implements Map<K, V> {

    class HashMapEntry<K, V> implements Entry<K, V>{

        public K key;
        public V value;

        public HashMapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}
    }

    private HashMapEntry<K, V>[] items;
    private int numberOfItems;

    private int hash(K key) {
        return key.hashCode();
    }

    private int compress(int hash, int i) {
        return (Math.abs(hash) + i^2) % items.length;
    }

    private boolean isDefunct(HashMapEntry e) {
        return (e.getKey() == null) && (e.getValue() == null);
    }

    @SuppressWarnings("unchecked")
    private void expand() {
        HashMapEntry<K, V>[] temp = this.items;
        this.items = (QuadraticHashMap<K, V>.HashMapEntry<K, V>[]) new QuadraticHashMap.HashMapEntry[items.length * 2];
        this.numberOfItems = 0;

        for (HashMapEntry<K, V> e : temp) {
            if (e != null && !isDefunct(e)) {
                put(e.getKey(), e.getValue());
             }
         }
    }

    private boolean isPrime(int value) {
		for (int i = 2; i < value; i++) {
		    if (value % i == 0) {
                return false;
		    }
		}
		return true;
	}

    @SuppressWarnings("unchecked")
    public QuadraticHashMap(int size) throws IllegalArgumentException {
        if (!isPrime(size)) {
            throw new IllegalArgumentException();
        }
        items = (QuadraticHashMap<K, V>.HashMapEntry<K, V>[]) new QuadraticHashMap.HashMapEntry[size];
        this.numberOfItems = 0;
    }

	@Override
    public int size() {
        return numberOfItems;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    // 1. Check null
    // 2. Check DEFUNCT --> DEFUNCT has null key and null value
    // 3. Check key
    @Override
    public V get(K key) {
		int hash = hash(key);
        int index;
        for (int i = 0; i < items.length; i++) {
            index = compress(hash, i);
            if (items[index] == null) {
                return null;
            }
            if (isDefunct(items[index])) {
                continue;
            }
            if (items[index].getKey().equals(key)) {
                return items[index].getValue();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (numberOfItems > ((double)items.length / 2)) {
            expand();
        }
        int hash = hash(key);
		int index;
        int defunctIndex = -1;
        for (int i = 0; i < items.length; i++) {
            index = compress(hash, i);
            // When a null is reached,
            // if there is no defunct, store new item in the end (null)
            // if there are defuncts, store new item in the first defunct
            if (items[index] == null) {
                if (defunctIndex == -1) {
                    items[index] = new HashMapEntry<K, V>(key, value);
                } else {
                    items[defunctIndex] = new HashMapEntry<K, V>(key, value);
                }
                this.numberOfItems++;
                return null;
            }
            // Keep the first defunct
            if (isDefunct(items[index])) {
                if (defunctIndex == -1) {
                    defunctIndex = index;
                }
                continue;
            }
            if (items[index].getKey().equals(key)) {
                V oldValue = items[index].getValue();
                items[index] = new HashMapEntry<K, V>(key, value);
                return oldValue;
            }
        }
        throw new RuntimeException("Table is full");
    }

    @Override
    public V remove(K key) {
        int hash = hash(key);
		int index;
        for (int i = 0; i < items.length; i++) {
            index = compress(hash, i);
            if (items[index] == null) {
                return null;
            }
            if (isDefunct(items[index])) {
                continue;
            }
            if (items[index].getKey().equals(key)) {
                V oldValue = items[index].getValue();
                items[index] = new HashMapEntry<K, V>(null, null);
                this.numberOfItems--;
                return oldValue;
            }
        }
        return null;
    }

    @Override
    public Iterable<K> keySet() {
        if (isEmpty()) {
            return new ArrayList<K>();
        }
        ArrayList<K> keys = new ArrayList<K>();
        for (HashMapEntry<K, V> e : this.items) {
            if (e != null && !isDefunct(e)) {
                keys.add(e.getKey());
            }
        }
        return keys;
    }

    @Override
    public Iterable<V> values() {
        if (isEmpty()) {
            return new ArrayList<V>();
        }
        ArrayList<V> values = new ArrayList<V>();
        for (HashMapEntry<K, V> e : this.items) {
            if (e != null && !isDefunct(e)) {
                values.add(e.getValue());
            }
        }
        return values;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        if (isEmpty()) {
            return new ArrayList<Entry<K, V>>();
        }
        ArrayList<Entry<K, V>> entries = new ArrayList<Entry<K, V>>();
        for (HashMapEntry<K, V> e : this.items) {
            if (e != null && !isDefunct(e)) {
                entries.add(e);
            }
        }
        return entries;
    }
}
