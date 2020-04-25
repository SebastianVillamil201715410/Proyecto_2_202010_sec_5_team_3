package model.data_structures;

import java.util.NoSuchElementException;

public class ArbolNegroRojo <Key extends Comparable <Key>,Value> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private Node root;
	
	private class Node 
	{
		 private Key key; 
		 private Value val;  
		 private Node left, right;
		 private boolean color;
		 private int size; 
		 
		 public Node(Key key, Value val, boolean color, int size)
		 {
			 this.key = key;
			 this.val = val;
			 this.color = color;
			 this.size = size;
		 }
	}
	public ArbolNegroRojo()
	{
	}
	
	private boolean esRojo(Node x)
	{
			if(x == null) return false;
			return x.color == RED;
	}
	private int size(Node x)
	{
		if(x== null) return 0;
		return x.size;
	}
	public int size()
	{
		return size(root);
	}
	public boolean isEmpty()
	{
		return root == null;
	}
	public Value get(Key key)
	{
		if (key == null) throw new IllegalArgumentException("El argumento toget() es null");
        return get(root, key);
	}
	private Value get(Node x,Key key)
	{
		 while (x != null) {
	            int cmp = key.compareTo(x.key);
	            if      (cmp < 0) x = x.left;
	            else if (cmp > 0) x = x.right;
	            else              return x.val;
	        }
	        return null;
	}
	public int darAltura(Key key)
	{
		int respuesta=0;
    	if (key == null) throw new IllegalArgumentException("El argumento toget() es null");
        respuesta=darAltura(root, key);
        return respuesta;
	}
	private int darAltura(Node x, Key key)
	{
		int i=0;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else if (cmp == 0) return i;
            else              return -100;
            i++;
        }
        return i;
	}
	public boolean contains(Key key)
	{
		return get(key) != null;
	}
	
	
	public void put(Key key, Value val)
	{
		if (key == null) throw new IllegalArgumentException("El primer argumento to put() es null");
        if (val == null) {
            return;
        }

        root = put(root, key, val);
        root.color = BLACK;
	}
	private Node put(Node h, Key key, Value val) 
	{ 
        if (h == null) return new Node(key, val, RED, 1);

        int cmp = key.compareTo(h.key);
        if      (cmp < 0) h.left  = put(h.left,  key, val); 
        else if (cmp > 0) h.right = put(h.right, key, val); 
        else              h.val   = val;

        if (esRojo(h.right) && !esRojo(h.left))      h = rotateLeft(h);
        if (esRojo(h.left)  &&  esRojo(h.left.left)) h = rotateRight(h);
        if (esRojo(h.left)  &&  esRojo(h.right))     flipColors(h);
        h.size = size(h.left) + size(h.right) + 1;

        return h;
    }
	
	
	public void deleteMin()
	{
		if (isEmpty()) throw new NoSuchElementException("BST underflow");

        
        if (!esRojo(root.left) && !esRojo(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
	}
	private Node deleteMin(Node h) { 
        if (h.left == null)
            return null;

        if (!esRojo(h.left) && !esRojo(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }
	 public void deleteMax() {
         if (isEmpty()) throw new NoSuchElementException("BST underflow");

         if (!esRojo(root.left) && !esRojo(root.right))
             root.color = RED;

         root = deleteMax(root);
         if (!isEmpty()) root.color = BLACK;
     }

     private Node deleteMax(Node h) { 
         if (esRojo(h.left))
             h = rotateRight(h);

         if (h.right == null)
             return null;

         if (!esRojo(h.right) && !esRojo(h.right.left))
             h = moveRedRight(h);

         h.right = deleteMax(h.right);

         return balance(h);
     }

     /**
      * Removes the specified key and its associated value from this symbol table     
      * (if the key is in this symbol table).    
      *
      * @param  key the key
      * @throws IllegalArgumentException if {@code key} is {@code null}
      */
     public void delete(Key key) { 
         if (key == null) throw new IllegalArgumentException("argument to delete() is null");
         if (!contains(key)) return;

         if (!esRojo(root.left) && !esRojo(root.right))
             root.color = RED;

         root = delete(root, key);
         if (!isEmpty()) root.color = BLACK;
         // assert check();
     }

     // delete the key-value pair with the given key rooted at h
     private Node delete(Node h, Key key) { 
         // assert get(h, key) != null;

         if (key.compareTo(h.key) < 0)  {
             if (!esRojo(h.left) && !esRojo(h.left.left))
                 h = moveRedLeft(h);
             h.left = delete(h.left, key);
         }
         else {
             if (esRojo(h.left))
                 h = rotateRight(h);
             if (key.compareTo(h.key) == 0 && (h.right == null))
                 return null;
             if (!esRojo(h.right) && !esRojo(h.right.left))
                 h = moveRedRight(h);
             if (key.compareTo(h.key) == 0) {
                 Node x = min(h.right);
                 h.key = x.key;
                 h.val = x.val;
                 // h.val = get(h.right, min(h.right).key);
                 // h.key = min(h.right).key;
                 h.right = deleteMin(h.right);
             }
             else h.right = delete(h.right, key);
         }
         return balance(h);
     }

   /***************************************************************************
    *  Red-black tree helper functions.
    ***************************************************************************/

    // make a left-leaning link lean to the right
    private Node rotateRight(Node h) {
        // assert (h != null) && isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // make a right-leaning link lean to the left
    private Node rotateLeft(Node h) {
        // assert (h != null) && isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // flip the colors of a node and its two children
    private void flipColors(Node h) {
        // h must have opposite color of its two children
        // assert (h != null) && (h.left != null) && (h.right != null);
        // assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
        //    || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private Node moveRedLeft(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

        flipColors(h);
        if (esRojo(h.right.left)) { 
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private Node moveRedRight(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColors(h);
        if (esRojo(h.left.left)) { 
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    // restore red-black tree invariant
    private Node balance(Node h) {
        // assert (h != null);

        if (esRojo(h.right))                      h = rotateLeft(h);
        if (esRojo(h.left) && esRojo(h.left.left)) h = rotateRight(h);
        if (esRojo(h.left) && esRojo(h.right))     flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }


   /***************************************************************************
    *  Utility functions.
    ***************************************************************************/

    /**
     * Returns the height of the BST (for debugging).
     * @return the height of the BST (a 1-node tree has height 0)
     */
    public int height() {
        return height(root);
    }
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

   /***************************************************************************
    *  Ordered symbol table methods.
    ***************************************************************************/

    /**
     * Returns the smallest key in the symbol table.
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    } 

    // the smallest key in subtree rooted at x; null if no such key
    private Node min(Node x) { 
        // assert x != null;
        if (x.left == null) return x; 
        else                return min(x.left); 
    } 

    /**
     * Returns the largest key in the symbol table.
     * @return the largest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    } 

    // the largest key in the subtree rooted at x; null if no such key
    private Node max(Node x) { 
        // assert x != null;
        if (x.right == null) return x; 
        else                 return max(x.right); 
    } 


    /**
     * Returns the largest key in the symbol table less than or equal to {@code key}.
     * @param key the key
     * @return the largest key in the symbol table less than or equal to {@code key}
     * @throws NoSuchElementException if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null) return null;
        else           return x.key;
    }    

    // the largest key in the subtree rooted at x less than or equal to the given key
    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0)  return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t; 
        else           return x;
    }

    /**
     * Returns the smallest key in the symbol table greater than or equal to {@code key}.
     * @param key the key
     * @return the smallest key in the symbol table greater than or equal to {@code key}
     * @throws NoSuchElementException if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("calls ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) return null;
        else           return x.key;  
    }

    // the smallest key in the subtree rooted at x greater than or equal to the given key
    private Node ceiling(Node x, Key key) {  
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0)  return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t; 
        else           return x;
    }

    /**
     * Return the key in the symbol table whose rank is {@code k}.
     * This is the (k+1)st smallest key in the symbol table. 
     *
     * @param  k the order statistic
     * @return the key in the symbol table of rank {@code k}
     * @throws IllegalArgumentException unless {@code k} is between 0 and
     *        <em>n</em>–1
     */
    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + k);
        }
        Node x = select(root, k);
        return x.key;
    }

    // the key of rank k in the subtree rooted at x
    private Node select(Node x, int k) {
        // assert x != null;
        // assert k >= 0 && k < size(x);
        int t = size(x.left); 
        if      (t > k) return select(x.left,  k); 
        else if (t < k) return select(x.right, k-t-1); 
        else            return x; 
    } 

    /**
     * Return the number of keys in the symbol table strictly less than {@code key}.
     * @param key the key
     * @return the number of keys in the symbol table strictly less than {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    } 

    // number of keys less than key in the subtree rooted at x
    private int rank(Key key, Node x) {
        if (x == null) return 0; 
        int cmp = key.compareTo(x.key); 
        if      (cmp < 0) return rank(key, x.left); 
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right); 
        else              return size(x.left); 
    } 

   /***************************************************************************
    *  Range count and range search.
    ***************************************************************************/

    /**
     * Returns all keys in the symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     * @return all keys in the symbol table as an {@code Iterable}
     */
    public Iterable<Key> keys() {
        if (isEmpty()) return (Iterable<Key>) new Queue<Key>();
        return keysInRange(min(), max());
    }
    /**
     * Returns all values in the symbol table in the given range,
     * as an {@code Iterable}.
     *
     * @param  lo minimum endpoint
     * @param  hi maximum endpoint
     * @return all keys in the sybol table between {@code lo} 
     *    (inclusive) and {@code hi} (inclusive) as an {@code Iterable}
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *    is {@code null}
     */
    
    /**
    public Iterable<Value> valuesInRange(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Value> queue = new Queue<Value>();
        // if (isEmpty() || lo.compareTo(hi) > 0) return queue;
        keys1(root, queue, lo, hi);
        return (Iterable<Value>) queue;
    } 
	*/
    // add the keys between lo and hi in the subtree rooted at x
    // to the queue
    
    /**
    private void keys1(Node x, Queue<Value> queue, Key lo, Key hi) { 
        if (x == null) return; 
        int cmplo = lo.compareTo(x.key); 
        int cmphi = hi.compareTo(x.key); 
        if (cmplo < 0) keys1(x.left, queue, lo, hi); 
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.val); 
        if (cmphi > 0) keys1(x.right, queue, lo, hi); 
    */
     
   
    
    /**
     * Returns all keys in the symbol table in the given range,
     * as an {@code Iterable}.
     *
     * @param  lo minimum endpoint
     * @param  hi maximum endpoint
     * @return all keys in the sybol table between {@code lo} 
     *    (inclusive) and {@code hi} (inclusive) as an {@code Iterable}
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *    is {@code null}
     */
    public Iterable<Key> keysInRange(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        // if (isEmpty() || lo.compareTo(hi) > 0) return queue;
        keys(root, queue, lo, hi);
        return (Iterable<Key>) queue;
    } 

    // add the keys between lo and hi in the subtree rooted at x
    // to the queue
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) { 
        if (x == null) return; 
        int cmplo = lo.compareTo(x.key); 
        int cmphi = hi.compareTo(x.key); 
        if (cmplo < 0) keys(x.left, queue, lo, hi); 
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key); 
        if (cmphi > 0) keys(x.right, queue, lo, hi); 
    } 

    /**
     * Returns the number of keys in the symbol table in the given range.
     *
     * @param  lo minimum endpoint
     * @param  hi maximum endpoint
     * @return the number of keys in the sybol table between {@code lo} 
     *    (inclusive) and {@code hi} (inclusive)
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *    is {@code null}
     */
    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else              return rank(hi) - rank(lo);
    }


   /***************************************************************************
    *  Check integrity of red-black tree data structure.
    ***************************************************************************/
    private boolean check() {
        if (!isBST())           System.out.println("Not in symmetric order");
        if (!isSizeConsistent())System.out.println("Subtree counts not consistent");
        if (!isRankConsistent())System.out.println("Ranks not consistent");
        if (!is23())            System.out.println("Not a 2-3 tree");
        if (!isBalanced())      System.out.println("Not balanced");
        return isBST() && isSizeConsistent() && isRankConsistent() && is23() && isBalanced();
    }

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST() {
        return isBST(root, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    } 

    // are the size fields correct?
    private boolean isSizeConsistent() { return isSizeConsistent(root); }
    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (x.size != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    } 

    // check that ranks are consistent
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }

    // Does the tree have no red right links, and at most one (left)
    // red links in a row on any path?
    private boolean is23() { return is23(root); }
    private boolean is23(Node x) {
        if (x == null) return true;
        if (esRojo(x.right)) return false;
        if (x != root && esRojo(x) && esRojo(x.left))
            return false;
        return is23(x.left) && is23(x.right);
    } 

    // do all paths from root to leaf have same number of black edges?
    private boolean isBalanced() { 
        int black = 0;     // number of black links on path from root to min
        Node x = root;
        while (x != null) {
            if (!esRojo(x)) black++;
            x = x.left;
        }
        return isBalanced(root, black);
    }

    // does every path from the root to a leaf have the given number of black links?
    private boolean isBalanced(Node x, int black) {
        if (x == null) return black == 0;
        if (!esRojo(x)) black--;
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    } 
}