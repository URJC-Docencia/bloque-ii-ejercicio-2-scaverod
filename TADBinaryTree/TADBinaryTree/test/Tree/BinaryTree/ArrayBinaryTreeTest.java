package Tree.BinaryTree;

import material.Position;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;


public class ArrayBinaryTreeTest {


    /**
     * Test of isEmpty method, of class ArrayBinaryTree.
     */
    @Test
    public void testIsEmpty() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        assertTrue(t.isEmpty());
        Position<String> p = t.addRoot("+");
        t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertLeft(h, "3");
        t.insertRight(h, "5");
        assertFalse(t.isEmpty());
    }

    /**
     * Test of isInternal method, of class ArrayBinaryTree.
     */
    @Test
    public void testIsInternal() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertLeft(h, "3");
        Position<String> n5 = t.insertRight(h, "5");
        assertTrue(t.isInternal(h));
        assertFalse(t.isInternal(n5));
    }

    /**
     * Test of isLeaf method, of class ArrayBinaryTree.
     */
    @Test
    public void testIsLeaf() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertLeft(h, "3");
        Position<String> n5 = t.insertRight(h, "5");
        assertFalse(t.isLeaf(h));
        assertTrue(t.isLeaf(n5));
    }

    /**
     * Test of isRoot method, of class ArrayBinaryTree.
     */
    @Test
    public void testIsRoot() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertLeft(h, "3");
        Position<String> n5 = t.insertRight(h, "5");
        assertTrue(t.isRoot(p));
        assertFalse(t.isRoot(h));
    }

    /**
     * Test of hasLeft method, of class ArrayBinaryTree.
     */
    @Test
    public void testHasLeft() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        assertTrue(t.hasLeft(p));
        assertFalse(t.hasLeft(h));
    }

    /**
     * Test of hasRight method, of class ArrayBinaryTree.
     */
    @Test
    public void testHasRight() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        assertTrue(t.hasRight(p));
        assertFalse(t.hasRight(h));
    }

    /**
     * Test of root method, of class ArrayBinaryTree.
     */
    @Test
    public void testRoot() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertLeft(h, "3");
        Position<String> n5 = t.insertRight(h, "5");
        assertEquals(t.root(), p);
    }

    /**
     * Test of left method, of class ArrayBinaryTree.
     */
    @Test
    public void testLeft() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        Position<String> n2 = t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertLeft(h, "3");
        Position<String> n5 = t.insertRight(h, "5");
        assertEquals(t.left(p), n2);
    }

    /**
     * Test of left method, of class ArrayBinaryTree.
     */
    @Test
    public void testLeftException() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertRight(h, "5");
        assertFalse(t.hasLeft(h));
    }

    /**
     * Test of right method, of class ArrayBinaryTree.
     */
    @Test
    public void testRight() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        Position<String> n2 = t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertLeft(h, "3");
        Position<String> n5 = t.insertRight(h, "5");
        assertEquals(t.right(p), h);
    }

    /**
     * Test of right method, of class ArrayBinaryTree.
     */
    @Test
    public void testRightException() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertLeft(h, "5");
        assertFalse(t.hasRight(h));
    }

    /**
     * Test of parent method, of class ArrayBinaryTree.
     */
    @Test
    public void testParent() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        Position<String> n2 = t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertLeft(h, "3");
        Position<String> n5 = t.insertRight(h, "5");
        assertEquals(t.parent(n2), p);
    }

    /**
     * Test of parent method, of class ArrayBinaryTree.
     */
    @Test
    public void testParentException() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        Position<String> n2 = t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertLeft(h, "3");
        Position<String> n5 = t.insertRight(h, "5");
        try {
            t.parent(p);
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }
    }

    /**
     * Test of children method, of class ArrayBinaryTree.
     */
    @Test
    public void testChildren() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        Position<String> n2 = t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertLeft(h, "3");
        Position<String> n5 = t.insertRight(h, "5");
        List<Position<String>> myChildren = new ArrayList<>();
        myChildren.add(n2);
        myChildren.add(h);
        Iterator<Position<String>> myIt = myChildren.iterator();
        for (Position<String> child : t.children(p)) {
            Position<String> next = myIt.next();
            assertEquals(child, next);
        }
    }

    /**
     * Test of iterator method, of class ArrayBinaryTree.
     */
    @Test
    public void testIterator() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        t.insertLeft(p, "2");
        t.insertRight(p, "3");
        StringBuilder salida = new StringBuilder();
        for (Position<String> e : t) {
            salida.append(e.getElement());
        }
        assertEquals("2+3", salida.toString());
    }

    /**
     * Test of replace method, of class ArrayBinaryTree.
     */
    @Test
    public void testReplace() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        Position<String> n2 = t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertLeft(h, "3");
        Position<String> n5 = t.insertRight(h, "5");
        t.replace(h, "+");
        assertEquals(h.getElement(), "+");
    }

    /**
     * Test of sibling method, of class ArrayBinaryTree.
     */
    @Test
    public void testSibling() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        Position<String> n2 = t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertLeft(h, "3");
        Position<String> n5 = t.insertRight(h, "5");
        assertEquals(t.sibling(n2), h);
    }

    /**
     * Test of sibling method, of class ArrayBinaryTree.
     */
    @Test
    public void testSiblingException() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        Position<String> n2 = t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertLeft(h, "3");
        Position<String> n5 = t.insertRight(h, "5");
        try {
            t.sibling(p);
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }
    }

    /**
     * Test of addRoot method, of class ArrayBinaryTree.
     */
    @Test
    public void testAddRoot() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        assertEquals(t.root(), p);
    }

    /**
     * Test of insertLeft method, of class ArrayBinaryTree.
     */
    @Test
    public void testInsertLeft() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        Position<String> n2 = t.insertLeft(p, "2");
        assertEquals(t.left(p), n2);
    }

    /**
     * Test of insertRight method, of class ArrayBinaryTree.
     */
    @Test
    public void testInsertRight() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        Position<String> n2 = t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        assertEquals(t.right(p), h);
    }

    /**
     * Test of remove method, of class ArrayBinaryTree.
     */
    @Test
    public void testRemove() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        Position<String> q = t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertLeft(h, "3");
        t.insertRight(h, "5");
        t.remove(q);
        assertFalse(t.hasLeft(p));
    }

    /**
     * Test of remove method, of class ArrayBinaryTree.
     * The test should catch an exception
     */
    @Test
    public void testRemoveException() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        Position<String> q = t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertLeft(h, "3");
        t.insertRight(h, "5");
        try {
            t.remove(p);
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }
    }

    /**
     * Test of attach method, of class ArrayBinaryTree.
     */
    @Test
    public void testAttach() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        Position<String> n2 = t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertLeft(h, "3");
        Position<String> n5 = t.insertRight(h, "5");
        ArrayBinaryTree<String> t1 = new ArrayBinaryTree<>();
        t1.addRoot("*");
        t1.insertLeft(t1.root(), "3");
        t1.insertRight(t1.root(), "9");
        ArrayBinaryTree<String> t2 = new ArrayBinaryTree<>();
        t2.addRoot("-");
        t2.insertLeft(t2.root(), "1");
        t2.insertRight(t2.root(), "7");
        t.attachLeft(n5, t1);
        t.attachRight(n5, t2);
        assertEquals(t.left(n5).getElement(), "*");
        assertEquals(t.right(n5).getElement(), "-");
    }

    /**
     * Test of attach method to a non empty node, of class ArrayBinaryTree.
     */
    @Test
    public void testAttachException1() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        Position<String> n2 = t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertLeft(h, "3");
        Position<String> n5 = t.insertRight(h, "5");
        ArrayBinaryTree<String> t1 = new ArrayBinaryTree<>();
        t1.addRoot("*");
        t1.insertLeft(t1.root(), "3");
        t1.insertRight(t1.root(), "9");
        ArrayBinaryTree<String> t2 = new ArrayBinaryTree<>();
        t2.addRoot("-");
        t2.insertLeft(t2.root(), "1");
        t2.insertRight(t2.root(), "7");
        try {
            t.attachRight(h, t2);
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }
    }

    /**
     * Test of attach method, of class ArrayBinaryTree.
     */
    @Test
    public void testAttachException2() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        Position<String> n2 = t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertLeft(h, "3");
        ArrayBinaryTree<String> t1 = new ArrayBinaryTree<>();
        t1.addRoot("*");
        t1.insertLeft(t1.root(), "3");
        t1.insertRight(t1.root(), "9");
        try {

            t.attachLeft(h, t1);
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }
    }

    /**
     * Test of swapElements method, of class ArrayBinaryTree.
     */
    @Test
    public void testSwap() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        Position<String> p1 = t.insertLeft(p, "2");
        Position<String> p2 = t.insertRight(p, "3");
        t.swap(p1, p2);
        StringBuilder salida = new StringBuilder();
        for (Position<String> e : t) {
            salida.append(e.getElement());
        }
        assertEquals(salida.toString(), "3+2");
    }

    /**
     * Test of subTree method, of class ArrayBinaryTree.
     */
    @Test
    public void testSubTree() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<>();
        Position<String> p = t.addRoot("+");
        Position<String> n2 = t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        Position<String> n3 = t.insertLeft(h, "3");
        Position<String> n5 = t.insertRight(h, "5");
        BinaryTree<String> t2 = t.subTree(h);
        assertEquals(h.getElement(), t2.root().getElement());
        assertEquals(n3.getElement(), t2.left(t2.root()).getElement());
        assertEquals(n5.getElement(), t2.right(t2.root()).getElement());

    }
}