package Tree.BinaryTree;

import material.Position;

import java.util.*;


/**
 * An implementation of the BinaryTree interface using an array-based representation.
 *
 * @param <E> the type of elements in the tree
 */
public class ArrayBinaryTree<E> extends DrawableTree<E> {

    private ArrayList<BTPos<E>> tree = new ArrayList<>();

    public ArrayBinaryTree() {
        tree.add(null);

    }

    /**
     * Calculates the position of the left child element in a binary tree given the index of the parent element.
     *
     * @param i the index of the parent element
     * @return the position of the left child element
     */
    private int getLeftChildPosition(int i) {
        return (2 * (i + 1)) - 1;
    }

    /**
     * Calculates the position of the right child element in a binary tree given the index of the parent element.
     *
     * @param i the index of the parent element
     * @return the position of the right child element
     */
    private int getRightChildPosition(int i) {
        return (i + 1) * 2;
    }

    /**
     * Auxiliary method to check if a position is valid and it is not the root
     *
     * @param v the position to check
     * @return the position casted to BTPos
     */
    private BTPos<E> checkPosition(Position<E> v) {
        if (!(v instanceof BTPos)) {
            throw new IllegalStateException("The position is invalid");
        }
        return (BTPos<E>) v;
    }

    /* Returns the left child of a node.
     *
     * @param v node
     * @return the left child of the node
     * @throws IllegalStateException if the node does not have a left child
     */
    @Override
    public Position<E> left(Position<E> v) {
        BTPos<E> aux = checkPosition(v);
        if (hasLeft(v)) {
            return tree.get(getLeftChildPosition(aux.pos));
        }
        throw new RuntimeException("This Position have not left children");
    }

    @Override
    public Position<E> right(Position<E> v) throws RuntimeException {
        BTPos<E> aux = checkPosition(v);
        if (hasRight(v)) {
            return tree.get(getRightChildPosition(aux.pos));
        }
        throw new RuntimeException("This Position have not right children");
    }

    /**
     * Checks if a given position has a left child.
     *
     * @param v the position to check
     * @return true if the position has a left child, false otherwise
     */
    @Override
    public boolean hasLeft(Position<E> v) {
        BTPos<E> aux = checkPosition(v);
        int posLeftChild = getLeftChildPosition(aux.pos);

        return ((tree.size() > posLeftChild) && (tree.get(posLeftChild) != null));

    }

    /**
     * Determines whether the given position has a right child.
     *
     * @param v the position to check
     * @return true if the position has a right child, false otherwise
     */
    @Override
    public boolean hasRight(Position<E> v) {
        BTPos<E> aux = checkPosition(v);
        int posRightChild = getRightChildPosition(aux.pos);

        return ((tree.size() > posRightChild) && (tree.get(posRightChild) != null));
    }

    /**
     * Determines whether the given position is internal.
     *
     * @param v the position to check
     * @return true if the position is internal (i.e., has at least one child), false otherwise
     */
    @Override
    public boolean isInternal(Position<E> v) {
        return hasLeft(v) || hasRight(v);
    }

    /**
     * Determines whether the given position is a leaf node.
     *
     * @param p the position to check
     * @return true if the position is a leaf node (i.e., has no children), false otherwise
     */
    @Override
    public boolean isLeaf(Position<E> p) {
        return (!isInternal(p));
    }

    /**
     * Determines whether the given position is the root node of the binary tree.
     *
     * @param p the position to check
     * @return true if the position is the root node, false otherwise
     */
    @Override
    public boolean isRoot(Position<E> p) {
        BTPos<E> aux = checkPosition(p);
        return (aux.pos == 0);
    }

    @Override
    public Position<E> root() {
        return tree.get(0);
    }

    @Override
    public E replace(Position<E> p, E e) {
        BTPos<E> node = checkPosition(p);
        E old = node.getElement();
        node.setElement(e);
        return old;
    }

    @Override
    public Position<E> sibling(Position<E> p) {
        BTPos<E> node = checkPosition(p);
        if (!isRoot(node)) {
            BTPos<E> parent = (BTPos<E>) parent(node);
            if (node == left(parent)) {
                return right(parent);
            } else {
                return left(parent);
            }
        }
        throw new RuntimeException("The node is root");
    }

    @Override
    public Position<E> addRoot(E e) {
        if (isEmpty()) {
            tree.set(0, new BTPos<>(0, e));
        } else {
            throw new IllegalStateException("The tree already has a root");
        }
        return tree.get(0);
    }

    private void insert(BTPos<E> node, int pos) {
        if (pos >= tree.size()) {
            BTPos[] array = new BTPos[pos - this.tree.size() + 1];
            List<BTPos<E>> list = Arrays.asList(array);
            tree.addAll(list);
        }
        tree.set(pos, node);
        node.pos = pos;

        //Remove is lazy, so we need remove posible non empty childs when inserting
        final int leftPos = this.getLeftChildPosition(pos);
        if (tree.size() > leftPos)
            tree.set(leftPos, null);

        final int rightPos = getRightChildPosition(pos);
        if (tree.size() > rightPos)
            tree.set(rightPos, null);
    }

    @Override
    public Position<E> insertLeft(Position<E> p, E e) {
        BTPos<E> node = checkPosition(p);
        if (hasLeft(node)) {
            throw new IllegalStateException("The node already has a left child");
        }
        int pos = getLeftChildPosition(node.pos);
        BTPos<E> newNode = new BTPos<>(pos, e);
        insert(newNode, pos);
        return newNode;
    }

    @Override
    public Position<E> insertRight(Position<E> p, E e) {
        BTPos<E> node = checkPosition(p);
        if (hasRight(node)) {
            throw new IllegalStateException("The node already has a right child");
        }
        int pos = getRightChildPosition(node.pos);
        BTPos<E> newNode = new BTPos<>(pos, e);
        insert(newNode, pos);
        return newNode;
    }

    @Override
    public E remove(Position<E> p) {
        BTPos<E> node = checkPosition(p);
        E element = node.getElement();
        if (isLeaf(node)) {
            tree.set(node.pos, null);
        } else if (!hasLeft(node)) {
            BTPos<E> child = checkPosition(right(node));
            tree.set(node.pos, null);
            insert(child, node.pos);
        } else if (!hasRight(node)) {
            BTPos<E> child = checkPosition(left(node));
            tree.set(node.pos, null);
            insert(child, node.pos);
        } else {
            throw new IllegalStateException("The node has two children");
        }
        return element;
    }

    @Override
    public void swap(Position<E> p1, Position<E> p2) {
        BTPos<E> node1 = checkPosition(p1);
        BTPos<E> node2 = checkPosition(p2);
        E aux = node1.getElement();
        node1.setElement(node2.getElement());
        node2.setElement(aux);
    }

    @Override
    public boolean isEmpty() {
        return tree.get(0) == null;
    }

    @Override
    public Position<E> parent(Position<E> v) {
        BTPos<E> node = checkPosition(v);
        if (node.pos == 0)
            throw new RuntimeException("Root does not have parent");

        int pos;
        if ((node.pos % 2) == 0) {//hijo derecho
            pos = (node.pos / 2) - 1;
        } else {//hijo izquierdo
            pos = ((1 + node.pos) / 2) - 1;
        }
        return tree.get(pos);
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        BTPos<E> node = checkPosition(v);
        ArrayList<Position<E>> children = new ArrayList<>();
        if (hasLeft(v)) {
            children.add(tree.get(getLeftChildPosition(node.pos)));
        }
        if (hasRight(v)) {
            children.add(tree.get(getRightChildPosition(node.pos)));
        }
        return children;
    }

    /**
     * Inorden: un nodo es visitado
     * después de su hijo izquierdo pero
     * antes que su hijo derecho
     * <p>
     * Algorithm inOrder(v)
     * if hasLeft(v) then
     * inOrder(left(v))
     * visit(v)
     * if hasRight(v) then
     * inOrder(right(v))
     *
     * @return the iterator
     */
    @Override
    public Iterator<Position<E>> iterator() {
        return new InOrderIterator();
    }

    private void attach(int pos, ArrayBinaryTree<E> t) {
        if (pos >= tree.size()) {
            BTPos[] array = new BTPos[pos - this.tree.size() + 1];
            List<BTPos<E>> list = Arrays.asList(array);
            tree.addAll(list);
        }
        tree.set(pos, t.tree.get(0));
        for (Position<E> child : t.children(t.root())) {
            if (t.hasLeft(child)) {
                attach(getLeftChildPosition(pos), (ArrayBinaryTree<E>) t.subTree(child));
            } else if (t.hasRight(child)) {
                attach(getRightChildPosition(pos), (ArrayBinaryTree<E>) t.subTree(child));
            }
        }
    }

    @Override
    public void attachLeft(Position<E> h, BinaryTree<E> t1) {
        if (hasLeft(h))
            throw new RuntimeException("This position has left child");
        if (!t1.isEmpty()) {
            BTPos<E> p2 = (BTPos<E>) h;
            //calculamos la posicion en la que tenemos que empezar a colocar el arbol
            int pos = getLeftChildPosition(p2.pos);
            attach(pos, (ArrayBinaryTree<E>) t1);
        }
    }

    @Override
    public void attachRight(Position<E> h, BinaryTree<E> t1) {
        if (hasRight(h))
            throw new RuntimeException("This position has right child");
        if (!t1.isEmpty()) {
            BTPos<E> p2 = (BTPos<E>) h;
            //calculamos la posicion en la que tenemos que empezar a colocar el arbol
            int pos = getRightChildPosition(p2.pos);
            attach(pos, (ArrayBinaryTree<E>) t1);
        }
    }

    @Override
    public BinaryTree<E> subTree(Position<E> v) {
        BTPos<E> aux = checkPosition(v);
        ArrayBinaryTree<E> newTree = new ArrayBinaryTree<>();
        newTree.insert(new BTPos<>(0, aux.getElement()), 0);
        if (hasLeft(aux)) {
            newTree.attachLeft(newTree.root(), subTree(left(aux)));
        }
        if (hasRight(aux)) {
            newTree.attachRight(newTree.root(), subTree(right(aux)));
        }
        return newTree;
    }

    private class BTPos<T> implements Position<T> {

        int pos;
        T element;

        public BTPos(int pos, T element) {
            this.pos = pos;
            this.element = element;
        }

        @Override
        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    private class InOrderIterator implements Iterator<Position<E>> {
        private Stack<Position<E>> stack = new Stack<>();

        public InOrderIterator() {
            if (!isEmpty()) {
                stack.push(root());
                goToLeft(root());
            }
        }

        private void goToLeft(Position<E> v) {
            if (hasLeft(v)) {
                stack.push(left(v));
                goToLeft(left(v));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Position<E> next() {
            Position<E> aux = stack.pop();
            if (hasRight(aux)) {
                stack.push(right(aux));
                goToLeft(right(aux));
            }
            return aux;
        }
    }

}
