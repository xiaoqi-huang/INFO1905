import java.util.ArrayList;

public class SimpleHashMap<K, V> implements Map<K, V> {

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

    private int compress(int hash) {
        return Math.abs(hash) % items.length;
    }

    @SuppressWarnings("unchecked")
    public SimpleHashMap(int size) {
        items = (SimpleHashMap<K, V>.HashMapEntry<K, V>[]) new SimpleHashMap.HashMapEntry[size];
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

	@Override
	public V get(K key) {
		int index = compress(hash(key));
        if (items[index] == null || !items[index].getKey().equals(key)) {
            return null;
        }
		return items[index].getValue();
	}

	@Override
	public V put(K key, V value) {
		int index = compress(hash(key));
        if (items[index] != null) {
            if (!items[index].getKey().equals(key)) {
                throw new IllegalArgumentException("Key conflict");
            } else {
                V oldValue = get(key);
                items[index] = new HashMapEntry<K, V>(key, value);
                return oldValue;
            }
        } else {
            items[index] = new HashMapEntry<K, V>(key, value);
            this.numberOfItems++;
            return null;
        }
	}

	@Override
	public V remove(K key) {
		int index = compress(hash(key));
        if (items[index] == null || !items[index].getKey().equals(key)) {
            return null;
        }
        V oldValue = get(key);
		items[index] = null;
        this.numberOfItems--;
        return oldValue;
	}

	@Override
	public Iterable<K> keySet() {
		if (isEmpty()) {
		    return new ArrayList<K>();
		}
        ArrayList<K> keys = new ArrayList<K>();
        for (HashMapEntry<K, V> e : this.items) {
            if (e != null) {
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
            if (e != null) {
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
            if (e != null) {
                entries.add(e);
            }
        }
		return entries;
	}
}
