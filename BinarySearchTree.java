/*
 * Zachary Williams
 * Completed on 10/15/2022
 */


import java.util.NoSuchElementException;
import javax.lang.model.util.ElementScanner6;

// Following methods created by Mark Allen Weiss:
// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
 
//
// Following methods created by Zachary Williams:
// int nodeCount( )                     --> Return number of nodes
// boolean isFull()                     --> returns whether tree is full
// boolean compareStructure( t )        --> compares the original tree structure to t
// boolean equals( t )                  --> checks if the original tree is identical to t
// BinarySearchTree<AnyType> copy()     --> copies the original tree to a new tree
// BinarySearchTree<AnyType> mirror()   --> mirrors the original tree to a new tree
// boolean isMirror( t )                --> checks if t is a mirror of the original tree
// void rotateRight( x )                --> rotates x to the right
// void rotateLeft( x )                 --> rotates x to the left
// void printLevels()                   --> prints the tree level by level
//
/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    public int nodeCount()
    {
        int nodes = 0;
        if(root == null)    // returning 0 if tree is empty
            return nodes;
        else
        {
            nodes = nodeCount(root);
            return nodes;
        }

    }

    public boolean isFull()
    {
        if(isEmpty())
        {
            System.out.println("Empty tree");
            return false;
        }
        else
            return isFull(root);
    }

    public boolean compareStructure(BinaryNode<AnyType> t)
    {
        return compareStructure(root, t);
    }
    public boolean equals(BinaryNode<AnyType> t)
    {
        
        return equals(root, t);
    }
    public BinarySearchTree<AnyType> copy()
    {
        BinarySearchTree<AnyType> copiedTree = new BinarySearchTree<>();

        
        copiedTree.root = copy(root);
        return copiedTree;
        
    }
    public BinarySearchTree<AnyType> mirror()
    {
        BinarySearchTree<AnyType> mirrorTree = new BinarySearchTree<>();
    
        mirrorTree.root = mirror(root);
        return mirrorTree;
    }
    public boolean isMirror(BinaryNode<AnyType> t)
    {
        return isMirror(root,t);
    }
    public void rotateRight(AnyType x)
    {
        BinaryNode<AnyType> balancer = new BinaryNode<>(null,null,null);
        BinaryNode<AnyType> parent = new BinaryNode<>(null, null, null);

        balancer = findNode(x, root);   // the node that will be rotated

        if(balancer == null)
        {
            System.out.println("Node not found");
            return;
        }
        else
        {
            parent = findParent(x, root, null); // getting the parent of the node
            root = rotateRight(parent, balancer);
        }            
    }
    
    public void rotateLeft(AnyType x)
    {
        BinaryNode<AnyType> balancer = new BinaryNode<>(null, null, null);
        BinaryNode<AnyType> parent = new BinaryNode<>(null, null, null);

        balancer = findNode(x, root);   // the node that will be rotated

        if (balancer == null)
        {
            System.out.println("Node not found");
            return;
        }
        else
        {
            parent = findParent(x, root, null); // getting the parent of the node
            root = rotateLeft(parent, balancer);
        }

    }

    public void printLevels()
    {
        if (isEmpty())
            System.out.println("Empty tree");
        else
            printLevels(root);
    }










    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {

        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    private int nodeCount(BinaryNode<AnyType> t )
    {

        int count = 1;
        if (t.left != null)
            count += nodeCount(t.left);
        if (t.right != null)
            count += nodeCount(t.right);

        return count;
    }
    private boolean isFull(BinaryNode<AnyType> t)
    {
        if(t == null)
            return true;
        else if(t.left == null && t.right == null)
            return true;
        else if((t.left!= null) && (t.right != null))
            return (isFull(t.left) && isFull(t.right));
        else
            return false;

    }
    
    private boolean compareStructure(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2)
    {
        if (t1 == null && t2 == null)   // empty
            return true;
        else if (t1 != null && t2 != null) // not empty
            return (compareStructure(t1.left, t2.left) && compareStructure(t1.right,t2.right));
        else
            return false;   // one is empty and the other is not

    }
    private boolean equals(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2)
    {
        if(t1 == null && t2 == null)    // both nodes null
            return true;
        else if(t1 != null && t2 == null || t1 == null && t2 != null)   // one empty & one non-empty
            return false;
        else
        {
            if(t1.element.compareTo(t2.element) == 0 && equals(t1.left, t2.left) && equals(t1.right, t2.right)) // checking if each element is equal to each other
                return true;   
            else
                return false;   // case for elements not being equal
        }
    }
    private BinaryNode<AnyType> copy(BinaryNode<AnyType> t)
    {   
        if(t == null)   // base case
            return null;
        else
        {// postorder traversal used so that the last return is the root of the new tree
            BinaryNode<AnyType> newNode = new BinaryNode<>(t.element, null, null);  // creating a new node and copying the content
            newNode.left = copy(t.left);
            newNode.right = copy(t.right);
            return newNode;
        }
    }
    private BinaryNode<AnyType> mirror(BinaryNode<AnyType> t)
    {
        if(t == null)
            return null;
        else
        {
            BinaryNode<AnyType> newNode = new BinaryNode<>(t.element, null, null);
            newNode.left = mirror(t.right);
            newNode.right = mirror(t.left);
            return newNode;
        }

    }
    private boolean isMirror(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2)
    {
        if (t1 == null && t2 == null)   // both nodes are empty
            return true;
        else if (t1 == null ||  t2 == null)  // one is empty
            return false;
        else
        {

            if (t1.element.compareTo(t2.element) == 0 && isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left))    // checking opposite sides
                return true;
            else
                return false;
        }
    }

    private BinaryNode<AnyType> findNode(AnyType x, BinaryNode<AnyType> t )
    {   // returns the node with the given value
        if (t == null)
            return null;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return findNode(x, t.left);
        else if (compareResult > 0)
            return findNode(x, t.right);
        else
            return t; // Match
    }

    private BinaryNode<AnyType> findParent(AnyType x, BinaryNode<AnyType> node, BinaryNode<AnyType> parent)
    {
        if (node == null)
            return node;

        int compareResult = x.compareTo(node.element);

        if (compareResult < 0)
            return findParent(x, node.left, node);
        else if (compareResult > 0)
            return findParent(x, node.right, node);
        else
            return parent; // Match
    }

    private BinaryNode<AnyType> rotateRight(BinaryNode<AnyType> parent, BinaryNode<AnyType> balancer)
    {   // this func seems to work properly

        if(balancer.left == null)
            throw new NoSuchElementException(); // element is unable to be rotated right
        BinaryNode<AnyType> oldLeft = balancer.left;    // storing the left child of the node in a temp var
        balancer.left = oldLeft.right;  // changing the left child of the node to the left->right grandchild of the node

        if(parent == null)  // if the node we are rotating is the root, change the root to its left child
            root = oldLeft;
        else if(parent.left == balancer)    // if the node is to the left of its parent, point the left of the parent to the left child of the node
            parent.left = oldLeft;
        else    // if the node is to the right of its parent, point the parent to the left child
            parent.right = oldLeft;

        oldLeft.right = balancer;   // now that the left child has been moved up, move the node to the right of the old left child


        return root;


    }
    private BinaryNode<AnyType> rotateLeft(BinaryNode<AnyType> parent, BinaryNode<AnyType> balancer) 
    { 
        if (balancer.right == null)
            throw new NoSuchElementException(); // element is unable to be rotated left
        BinaryNode<AnyType> oldRight = balancer.right;  // storing the right child of the node in a temp var
        balancer.right = oldRight.left;    // changing the right child of the node to the right->left grandchild of the node

        if (parent == null) // if the node we are rotating is the root, change the root to its right child
            root = oldRight;
        else if (parent.left == balancer)   // if the node is to the left of its parent, point the parent to the right child of the node
            parent.left = oldRight;
        else    // if the node is to the right of its parent, point the parent  to the right child
            parent.right = oldRight;

        oldRight.left = balancer;   // now that the right child has been moved up, move the node to the left of the old right child

        return root;

    }

    private void printLevels(BinaryNode<AnyType> t) 
    {
        int height = height(t); // calculating the height of the tree

        for(int i = 0; i<= height; i++)
        {
            printThisLevel(t,i);
            System.out.println();   // separating each level with a new line
        }



    }
    private void printThisLevel(BinaryNode<AnyType> t, int level)
    {
        if(t == null)
        {
            return; // a node doesn't exist
        }
        else if(level == 0)
        {
            System.out.print(t.element + " ");  
            return;
        }
        else
        {
            printThisLevel(t.left, level - 1);  // will go left until it reaches the current level
            printThisLevel(t.right, level - 1); // will go right until it reaches the current level
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;


        // Test program
    public static void main( String [ ] args )
    {
        BinarySearchTree<Integer> t = new BinarySearchTree<>( );
        BinarySearchTree<Integer> utilTree = new BinarySearchTree<>();
        boolean result;

        for (int i = 50; i < 100; i = i + 3)
        {
            t.insert(i);
            utilTree.insert(i);
            
        }
        for (int i = 30; i > 0; i = i -2)
        {
            t.insert(i);
            utilTree.insert(i);
          
        }

        System.out.println("Number of nodes in the tree: " + t.nodeCount());

        System.out.println("Checking if Binary tree is full...");
        result = t.isFull();
        System.out.println("Result: " + result);

        System.out.println("Checking if the two trees are of similar structure... ");
        result = t.compareStructure(utilTree.root);
        System.out.println("Result: " + result);

        System.out.println("Checking if the two trees are identical... ");
        result = t.equals(utilTree.root);
        System.out.println("Result: " + result);

        utilTree.makeEmpty();
        System.out.println("Copying the tree...");
        utilTree = t.copy();

        System.out.println("Mirroring the tree to utilTree...");
        utilTree = t.mirror();

        System.out.println("Checking if utilTree is a mirror of the original tree...");
        result = t.isMirror(utilTree.root);
        System.out.println("Result: " + result);

        System.out.println("Rotating 62 to the left...");
        t.rotateLeft(62);

        System.out.println("Rotating 30 to the right...");
        t.rotateRight(30);

        System.out.println("Printing utilTree by level...");
        utilTree.printLevels();
        System.out.println("Printing the original tree by level...");
        t.printLevels();

        System.out.println("Program Complete--------------------------------");

    }
}
