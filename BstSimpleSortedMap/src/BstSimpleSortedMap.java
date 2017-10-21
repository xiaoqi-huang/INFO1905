import java.util.ArrayList;

public class BstSimpleSortedMap implements SimpleSortedMap {

  public class MySimpleEntry implements SimpleEntry {

    private final Integer key;
    private final String value;
    private MySimpleEntry left;
    private MySimpleEntry right;
    private MySimpleEntry parent;

    MySimpleEntry(Integer key, String value) {
      this.key = key;
      this.value = value;
      this.left = null;
      this.right = null;
      this.parent = null;
    }

    @Override
    public Integer getKey() { return key; }

    @Override
    public String getValue() { return value; }

    protected MySimpleEntry getLeft() { return left; }
    protected MySimpleEntry getRight() { return right; }
    protected MySimpleEntry getParent() { return parent; }

    protected void setLeft(MySimpleEntry entry) { left = entry; }
    protected void setRight(MySimpleEntry entry) { right = entry; }
    protected void setParent(MySimpleEntry entry) { parent = entry; }
  }

  private int size;
  private MySimpleEntry root;

  public BstSimpleSortedMap() {
    size = 0;
    root = null;
  }

  // attaches the subtree rooted at 'child', to the parent
  private void attachLeft(MySimpleEntry parent, MySimpleEntry child) {
    if(child != null) { child.setParent(parent); }
    if(parent != null) { parent.setLeft(child); }
  }

  // attaches the subtree rooted at 'child', to the parent
  private void attachRight(MySimpleEntry parent, MySimpleEntry child) {
    if(child != null) { child.setParent(parent); }
    if(parent != null) { parent.setRight(child); }
  }

  private MySimpleEntry predecessor(MySimpleEntry entry) {
      if (entry.getLeft() != null) {
          MySimpleEntry pre = entry.getLeft();
          if (pre.getRight() != null) {
              pre = pre.getRight();
          }
          return pre;
      } else {
          MySimpleEntry pre = entry;
          while (pre != root) {
              if (pre == pre.getParent().getRight()) {
                  return pre;
              }
              pre = pre.getParent();
          }
          return null;
      }
  }

  private MySimpleEntry successor(MySimpleEntry entry) {
      if (entry.getRight() != null) {
          MySimpleEntry suc = entry.getRight();
          if (suc.getLeft() != null) {
              suc = suc.getLeft();
          }
          return suc;
      } else {
          MySimpleEntry suc = entry;
          while (suc != root) {
              if (suc == suc.getParent().getLeft()) {
                  return suc;
              }
              suc = suc.getParent();
          }
          return null;
      }
  }

  //////////////////////////
  // Map ADT methods:
  //////////////////////////

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public String get(Integer k) {
    return get(k, root);
  }

  private String get(Integer k, MySimpleEntry subtreeRoot) {
    if(subtreeRoot == null) {
      return null;
    }

    if(k.compareTo(subtreeRoot.getKey()) == 0) {
      return subtreeRoot.getValue();
    }
    else if(k.compareTo(subtreeRoot.getKey()) < 0) {
      return get(k, subtreeRoot.getLeft());
    }
    else {
      return get(k, subtreeRoot.getRight());
    }
  }

  @Override
  public String put(Integer k, String v) {

    // If the key already exists, we will need to return the old value
    String oldValue = get(k);

    // Replace the subtree rooted at 'root' with
    // the resulting subtree after doing the put
    root = put(k, v, root);

    return oldValue;
  }

  // Recursive helper method for put
  // Returns the subtree rooted at entry after performing the put
  private MySimpleEntry put(Integer k, String v, MySimpleEntry subtreeRoot) {
    // base case: the key wasn't in the subtree
    if(subtreeRoot == null) {
    	MySimpleEntry e = new MySimpleEntry(k, v);
    	size++;
    	return e;
    }

    // base case: k matches the one in the current entry
    if(k.compareTo(subtreeRoot.getKey()) == 0) {
    	MySimpleEntry e = new MySimpleEntry(k, v);
    	attachLeft(e, subtreeRoot.getLeft());
    	attachRight(e, subtreeRoot.getRight());
    	return e;
    }
    // recursive case: k < the current entry
    else if(k.compareTo(subtreeRoot.getKey()) < 0) {
      // TODO: get the subtree resulting from recursing left
      // TODO: attach that subtree to the current entry
    	attachLeft(subtreeRoot, put(k, v, subtreeRoot.getLeft()));
    	return subtreeRoot;
    }
    // recursive case: k > the current entry
    else {
      // TODO: get the subtree resulting from recursing right
      // TODO: attach that subtree to the current entry
    	attachRight(subtreeRoot, put(k, v, subtreeRoot.getRight()));
    	return subtreeRoot;
    }
  }

  @Override
  public String remove(Integer k) {
	String oldValue = get(k);
	root = remove(k, root);
    return oldValue;
  }

  private MySimpleEntry remove(Integer k, MySimpleEntry subtreeRoot) {

	  // No such entry
	  if (subtreeRoot == null) {
		return null;
	  }

	  if (k.compareTo(subtreeRoot.getKey()) == 0) {
		  size--;
		  // two children
		  if (subtreeRoot.getLeft() != null && subtreeRoot.getRight() != null) {
			  MySimpleEntry replace = subtreeRoot.getLeft();
			  while (replace.getRight() != null) {
				  replace = replace.getRight();
			  }
			  // The left child of subtreeRoot does not have a right child
			  if (replace == replace.getParent().getLeft()) {
				  attachRight(replace, subtreeRoot.getRight());
			  }
			  // The left child of subtreeRoot have a right child
			  else {
				  // Attach the left child of replace to its parent
				  attachRight(replace.getParent(), replace.getLeft());
				  attachLeft(replace, subtreeRoot.getLeft());
				  attachRight(replace, subtreeRoot.getRight());
			  }
			  return replace;
		  }
		  // One right child or no children
		  else if (subtreeRoot.getLeft() == null) {
			  return subtreeRoot.getRight();
		  }
		  // One left child or no children
		  else {
			  return subtreeRoot.getLeft();
		  }
	  }
	  else if (k.compareTo(subtreeRoot.getKey()) < 0) {
		  attachLeft(subtreeRoot, remove(k, subtreeRoot.getLeft()));
		  return subtreeRoot;
	  }
	  else {
		  attachRight(subtreeRoot, remove(k, subtreeRoot.getRight()));
		  return subtreeRoot;
	  }
  }


  @Override
  public Iterable<Integer> keySet() {
      ArrayList<Integer> al = new ArrayList<Integer>();
      MySimpleEntry e = root;
      while (e.getLeft() != null) {
          e = e.getLeft();
      }
      while (successor(e) != null) {
          al.add(e.getKey());
          e = successor(e);
      }
      return al;
  }

  @Override
  public Iterable<String> values() {
      ArrayList<String> al = new ArrayList<String>();
      MySimpleEntry e = root;
      while (e.getLeft() != null) {
          e = e.getLeft();
      }
      while (successor(e) != null) {
          al.add(e.getValue());
          e = successor(e);
      }
      return al;
  }

  @Override
  public Iterable<SimpleEntry> entrySet() {
      ArrayList<SimpleEntry> al = new ArrayList<SimpleEntry>();
      MySimpleEntry e = root;
      while (e.getLeft() != null) {
          e = e.getLeft();
      }
      while (successor(e) != null) {
          al.add(e);
          e = successor(e);
      }
      return al;
  }

  //////////////////////////
  // SortedMap ADT methods:
  //////////////////////////

  @Override
  public SimpleEntry firstEntry() {
    MySimpleEntry e = root;
    while (e.getLeft() != null) {
        e.getLeft();
    }
    return e;
  }

  @Override
  public SimpleEntry lastEntry() {
      MySimpleEntry e = root;
      while (e.getRight() != null) {
          e.getRight();
      }
      return e;
  }

  @Override
  public SimpleEntry ceilingEntry(Integer key) {
      MySimpleEntry e = (MySimpleEntry) firstEntry();
      while (successor(e) != null) {
          if (e.getKey().compareTo(key) >= 0) {
              return e;
          }
          e = successor(e);
      }
      return e;
  }

  @Override
  public SimpleEntry floorEntry(Integer key) {
      MySimpleEntry e = (MySimpleEntry) firstEntry();
      while (e.getKey().compareTo(key) <= 0 || successor(e) != null) {
          if (successor(e).getKey().compareTo(key) > 0) {
              return e;
          }
          e = successor(e);
      }
      return e;
  }

  @Override
  public SimpleEntry lowerEntry(Integer key) {
      MySimpleEntry e = (MySimpleEntry) firstEntry();
      while (e.getKey().compareTo(key) < 0 && successor(e) != null) {
          if (successor(e).getKey().compareTo(key) >= 0) {
              return e;
          }
          e = successor(e);
      }
      return e;
  }

  @Override
  public SimpleEntry higherEntry(Integer key) {
      MySimpleEntry e = (MySimpleEntry) firstEntry();
      while (successor(e) != null) {
          if (e.getKey().compareTo(key) >= 0) {
              return e;
          }
          e = successor(e);
      }
      return e;
  }

  @Override
  public Iterable<Integer> subMap(Integer fromKey, Integer toKey) {
      MySimpleEntry e = (MySimpleEntry) firstEntry();
      ArrayList<Integer> al = new ArrayList<Integer>();
      while (e.getKey().compareTo(toKey) < 0 && successor(e) != null) {
          if (e.getKey().compareTo(fromKey) >= 0) {
              al.add(e.getKey());
          }
      }
      return al;
  }

}
