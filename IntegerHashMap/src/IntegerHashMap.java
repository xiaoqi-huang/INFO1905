import java.util.ArrayList;

public class IntegerHashMap<V> implements Map<Integer, V> {

    class HashMapEntry<Integer, V> implements Entry<Integer, V>{

        public Integer key;
        public V value;

        public HashMapEntry(Integer key, V value) {
            this.key = key;
            this.value = value;
        }

		@Override
		public Integer getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}
    }

    private HashMapEntry<Integer, V>[] items;
    private int numberOfItems;

    private int hash(Integer key) {
        return key;
    }

    private int compress(int hash) {
        return Math.abs(hash) % items.length;
    }

    @SuppressWarnings("unchecked")
    public IntegerHashMap(int size) {
        items = (IntegerHashMap<V>.HashMapEntry<Integer, V>[]) new IntegerHashMap.HashMapEntry[size];
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
	public V get(Integer key) {
		int index = compress(hash(key));
        if (items[index] == null || items[index].getKey() != key) {
            return null;
        }
		return items[index].getValue();
	}

	@Override
	public V put(Integer key, V value) {
        int index = compress(hash(key));
        if (items[index] != null) {
            if (items[index].getKey() != key) {
                throw new IllegalArgumentException("Key conflict");
            } else {
                V oldValue = get(key);
                items[index] = new HashMapEntry<Integer, V>(key, value);
                return oldValue;
            }
        } else {
            items[index] = new HashMapEntry<Integer, V>(key, value);
            this.numberOfItems++;
            return null;
        }
	}

	@Override
	public V remove(Integer key) {
        int index = compress(hash(key));
        if (items[index] == null || items[index].getKey() != key) {
            return null;
        }
        V oldValue = get(key);
		items[index] = null;
        this.numberOfItems--;
        return oldValue;
	}

	@Override
	public Iterable<Integer> keySet() {
		if (isEmpty()) {
		    return new ArrayList<Integer>();
		}
        ArrayList<Integer> keys = new ArrayList<Integer>();
        for (HashMapEntry<Integer, V> e : this.items) {
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
        for (HashMapEntry<Integer, V> e : this.items) {
            if (e != null) {
                values.add(e.getValue());
            }
        }
		return values;
	}

	@Override
	public Iterable<Entry<Integer, V>> entrySet() {
        if (isEmpty()) {
		    return new ArrayList<Entry<Integer, V>>();
		}
        ArrayList<Entry<Integer, V>> entries = new ArrayList<Entry<Integer, V>>();
        for (HashMapEntry<Integer, V> e : this.items) {
            if (e != null) {
                entries.add(e);
            }
        }
		return entries;
	}

}
