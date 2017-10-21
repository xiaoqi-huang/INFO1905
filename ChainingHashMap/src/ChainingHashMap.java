import java.util.ArrayList;

public class ChainingHashMap<K, V> implements Map<K, V> {

    	class HashMapEntry<K, V> implements Entry<K, V>{

            public K key;
            public V value;
            public HashMapEntry<K, V> next;

            public HashMapEntry(K key, V value) {
                this.key = key;
                this.value = value;
                this.next = null;
            }

    		@Override
    		public K getKey() {
    			return key;
    		}

    		@Override
    		public V getValue() {
    			return value;
    		}

            public HashMapEntry<K, V> getNext() {
                return next;
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
        public ChainingHashMap(int size) {
            items = (ChainingHashMap<K, V>.HashMapEntry<K, V>[]) new ChainingHashMap.HashMapEntry[size];
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
            if (items[index] == null) {
                return null;
            }
            HashMapEntry<K, V> p = items[index];
            while (p != null) {
                if (p.getKey().equals(key)) {
                    return p.getValue();
                } else {
                    p = p.getNext();
                }
            }
    		return null;
    	}

    	@Override
    	public V put(K key, V value) {
            int index = compress(hash(key));
            if (items[index] == null) {
                items[index] = new HashMapEntry<K, V>(key, value);
                this.numberOfItems++;
                return null;
            }
            if (items[index].getKey().equals(key)) {
                HashMapEntry<K, V> newItem = new HashMapEntry<K, V>(key, value);
                newItem.next = items[index].getNext();
                items[index] = newItem;
                this.numberOfItems++;
                return null;
            }
            HashMapEntry<K, V> p = items[index];
            while (p.getNext() != null) {
                if (p.getNext().getKey().equals(key)) {
                    V oldValue = p.getNext().getValue();
                    HashMapEntry<K, V> newItem = new HashMapEntry<K, V>(key, value);
                    newItem.next = p.getNext().getNext();
                    p.next = newItem;
                    return oldValue;
                } else {
                    p = p.getNext();
                }
            }
            p.next = new HashMapEntry<K, V>(key, value);
            this.numberOfItems++;
            return null;
    	}

    	@Override
    	public V remove(K key) {
            int index = compress(hash(key));
            if (items[index] == null) {
                return null;
            }
            if (items[index].getKey().equals(key)) {
                V oldValue = items[index].getValue();
                items[index] = items[index].getNext();
                this.numberOfItems--;
                return oldValue;
            }
            HashMapEntry<K, V> p = items[index];
            while (p.getNext() != null) {
                if (p.getNext().getKey().equals(key)) {
                    V oldValue = p.getNext().getValue();
                    p.next = p.getNext().getNext();
                    this.numberOfItems--;
                    return oldValue;
                } else {
                    p = p.getNext();
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
