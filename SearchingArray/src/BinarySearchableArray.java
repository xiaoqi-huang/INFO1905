
public class BinarySearchableArray<T extends Comparable<T>> extends SearchableArray<T> {

	// constructor
	public BinarySearchableArray(T[] data) {
		super(data); //call the constructor of the super-type as SearchableArray<T>(data)
	}

	@Override
	public T search(T target) {	
		if (found(target, 0, this.data.length - 1)) {
			return target;
		}		
		return null;
	}
	
	private boolean found(T target, int low, int high) {
		int mid = low + (high - low) / 2;
		if ((low > high) || ((low == high) && (this.data[mid].compareTo(target) != 0))) {
			return false;
		}
		if (this.data[mid].compareTo(target) < 0) {
			return found(target, mid + 1, high);
		}
		if (this.data[mid].compareTo(target) == 0) {
			return true;
		}
		if (this.data[mid].compareTo(target) > 0) {
			return found(target, low, mid - 1);
		}
		return false;
	}

}
