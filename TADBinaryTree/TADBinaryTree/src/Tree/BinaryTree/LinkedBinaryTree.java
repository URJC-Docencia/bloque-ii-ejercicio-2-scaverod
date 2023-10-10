package Tree.BinaryTree;

import material.Position;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author mayte
 */
public class LinkedBinaryTree<E> extends DrawableTree<E> {


    /**
     * Represents the root node of a binary tree.
     *
     * <p>
     * The {@code BTNode} class is a generic class that represents a binary tree node. This class is used to store the
     * root node of a binary tree.
     * </p>
     *
     * @param <E> the type of element stored in the binary tree node
     */
    private BTNode<E> root;
    /**
     * The size of the variable.
     * <p>
     * This variable represents the size of a specific entity or data structure.
     * It is used to store the number of elements or the length of an array, for instance.
     *
     * @since 1.0
     */
    private int size;


    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    /**
     * Returns the left child of a node.
     *
     * @param v node
     * @return the left child of the node
     * @throws RuntimeException if the node does not have a left child
     */
    @Override
    public Position<E> left(Position<E> v) throws RuntimeException {
        BTNode<E> node = checkPosition(v);
        if (node.getLeft() == null) {
            throw new RuntimeException("Node does not have a left child");
        }
        return node.getLeft();
    }

    private BTNode<E> checkPosition(Position<E> v) {
        if (!(v instanceof BTNode)) {
            throw new IllegalArgumentException("Invalid p");
        }
        return (BTNode<E>) v;
    }

    @Override
    public Position<E> right(Position<E> v) throws RuntimeException {
        BTNode<E> node = checkPosition(v);
        if (node.getRight() == null) {
            throw new RuntimeException("Node does not have a right child");
        }
        return node.getRight();
    }

    @Override
    public boolean hasLeft(Position<E> v) {
        return checkPosition(v).getLeft() != null;
    }

    @Override
    public boolean hasRight(Position<E> v) {
        return checkPosition(v).getRight() != null;
    }

    @Override
    public boolean isInternal(Position<E> v) {
        return checkPosition(v).getLeft() != null || checkPosition(v).getRight() != null;
    }

    @Override
    public boolean isLeaf(Position<E> p) {
        return checkPosition(p).getLeft() == null && checkPosition(p).getRight() == null;
    }

    @Override
    public boolean isRoot(Position<E> p) {
        return checkPosition(p) == root;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public E replace(Position<E> p, E e) {
        BTNode<E> node = checkPosition(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    @Override
    public Position<E> sibling(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        BTNode<E> parent = node.getParent();
        if (parent == null) {
            throw new RuntimeException("p has no parent");
        }
        if (node == parent.getLeft()) {
            return parent.getRight();
        } else {
            return parent.getLeft();
        }

    }

    @Override
    public Position<E> addRoot(E e) {
        if (!isEmpty()) {
            throw new IllegalStateException("Tree is not empty");
        }
        root = new BTNode<>(e);
        size = 1;
        return root;
    }

    @Override
    public Position<E> insertLeft(Position<E> p, E e) {
        BTNode<E> node = checkPosition(p);
        BTNode<E> newNode = new BTNode<>(e);
        node.setLeft(newNode);
        newNode.setParent(node);
        size++;
        return newNode;
    }

    @Override
    public Position<E> insertRight(Position<E> p, E e) {
        BTNode<E> node = checkPosition(p);
        BTNode<E> newNode = new BTNode<>(e);
        node.setRight(newNode);
        newNode.setParent(node);
        size++;
        return newNode;
    }

    @Override
    public E remove(Position<E> p) {
        BTNode<E> nodeToRemove = checkPosition(p);
        BTNode<E> parent = nodeToRemove.getParent();
        BTNode<E> left = nodeToRemove.getLeft();
        BTNode<E> right = nodeToRemove.getRight();
        // If statement to check whether the node has both left and right children.
        // If it does, an IllegalArgumentException is thrown. This is because this removal method is not designed to handle nodes with two children.
        if (left != null && right != null) {
            throw new IllegalArgumentException("p has two children");
        }

        // Child of the node to be removed is determined. If the node has a left child, it is considered as child;
        // else the right child is considered.
        BTNode<E> child;
        if (left != null) {
            child = left;
        } else child = right;

        // Check if the node to remove is the root node.
        // If it's the root, its child becomes the new root.
        if (nodeToRemove == root) {
            if (child != null) {
                child.setParent(null);
            }
            root = child;
        } else {
            // If the node to remove is not the root, the child of the node to remove
            // is attached to the parent of the node to remove.
            if (nodeToRemove == parent.getLeft()) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
            // The parent of the child is set to the parent of the node to remove.
            if (child != null) {
                child.setParent(parent);
            }
        }
        size--;
        return nodeToRemove.getElement();
    }

    @Override
    public void swap(Position<E> p1, Position<E> p2) {
        BTNode<E> node1 = checkPosition(p1);
        BTNode<E> node2 = checkPosition(p2);
        E temp = node1.getElement();
        node1.setElement(node2.getElement());
        node2.setElement(temp);
    }

    /**
     * Attaches the given binary tree as the right child of the node at the specified position.
     *
     * @param h  the position of the node to which the binary tree is attached as the right child
     * @param t1 the binary tree to be attached as the right child
     * @throws RuntimeException if the node already has a right child
     */
    @Override
    public void attachLeft(Position<E> h, BinaryTree<E> t1) throws RuntimeException {
        if (hasLeft(h)) {
            throw new RuntimeException("Node already has a left child");
        } else {
            LinkedBinaryTree<E> treeToAttach = checkTree(t1);
            BTNode<E> node = checkPosition(h);
            BTNode<E> root = checkPosition(t1.root());
            node.setLeft(root);
            root.setParent(node);
            size += treeToAttach.getSize();
        }
    }

    @Override
    public void attachRight(Position<E> h, BinaryTree<E> t1) throws RuntimeException {
        if (hasRight(h)) {
            throw new RuntimeException("Node already has a right child");
        } else {
            LinkedBinaryTree<E> treeToAttach = checkTree(t1);
            BTNode<E> node = checkPosition(h);
            BTNode<E> root = checkPosition(t1.root());
            node.setRight(root);
            root.setParent(node);
            size += treeToAttach.getSize();
        }

    }

    private LinkedBinaryTree<E> checkTree(BinaryTree<E> t1) {
        if (!(t1 instanceof LinkedBinaryTree)) {
            throw new IllegalArgumentException("Invalid tree");
        }
        return (LinkedBinaryTree<E>) t1;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public Position<E> parent(Position<E> v) throws RuntimeException {
        BTNode<E> node = checkPosition(v);
        if (node.getParent() == null) {
            throw new RuntimeException("Node does not have a parent");
        }
        return node.getParent();
    }

    /**
     * Returns an iterable collection of the child positions of the specified position in the tree.
     *
     * @param v the position whose child positions are to be returned.
     * @return an iterable collection of the child positions of the specified position.
     */
    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        BTNode<E> node = checkPosition(v);
        ArrayList<Position<E>> list = new ArrayList<>();
        if (node.getLeft() != null) {
            list.add(node.getLeft());
        }
        if (node.getRight() != null) {
            list.add(node.getRight());
        }
        return list;
    }

    /**
     * Iterate over the three inOrder
     *
     * @return
     */
    @Override
    public Iterator<Position<E>> iterator() {
        ArrayList<Position<E>> list = new ArrayList<>();
        inOrder(root, list);
        return list.iterator();
    }

    /**
     * un nodo es visitado
     * después de su hijo izquierdo pero
     * antes que su hijo derecho
     *
     * @param node el nodo en el que comienzo
     * @param list la lista con el recorrido de los nodos.
     */
    private void inOrder(BTNode<E> node, ArrayList<Position<E>> list) {
        // primero me voy a ir lo más a la izquierda posible
        // visito el nodo (no tiene hijos)
        // me voy a ir a la derecha
        if (node != null) {
            if (node.getLeft() != null) {
                inOrder(node.getLeft(), list);
            }
            list.add(node);
            if (node.getRight() != null) {
                inOrder(node.getRight(), list);
            }
        }
    }

    /**
     * Returns the subtree rooted at the specified position.
     *
     * @param h the position of the root node of the subtree
     * @return the subtree rooted at the specified position
     */
    @Override
    public LinkedBinaryTree<E> subTree(Position<E> h) {
        BTNode<E> node = checkPosition(h);
        LinkedBinaryTree<E> tree = new LinkedBinaryTree<>();
        tree.root = node;
        tree.size = computeSize(node);
        return tree;
    }

    private int computeSize(BTNode<E> node) {
        int size = 1;
        if (node.getLeft() != null) {
            size += computeSize(node.getLeft());
        }
        if (node.getRight() != null) {
            size += computeSize(node.getRight());
        }
        return size;
    }

    /**
     * Returns the size of the binary tree.
     *
     * @return the size of the binary tree
     */
    public int getSize() {
        return size;
    }

    /**
     * A private class representing a node in a Binary Tree.
     *
     * @param <T> the type of element stored in the node
     */
    private class BTNode<T> implements Position<T> {
        /**
         * Represents a variable of type T.
         *
         * @param <T> the type of the variable
         */
        private T element;
        /**
         * Represents the left child of a binary tree node.
         *
         * @param <T> the type of data stored in the binary tree node
         */
        private BTNode<T> left, right, parent;

        /**
         * Constructs a BTNode with the given element, parent, left child, and right child.
         *
         * @param element the element value for the node
         * @param parent  the parent node of the current node
         * @param left    the left child node of the current node
         * @param right   the right child node of the current node
         */
        public BTNode(T element, BTNode<T> parent, BTNode<T> left, BTNode<T> right) {
            this.element = element;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        /**
         * Constructs a BTNode with the given element.
         *
         * @param element the element value for the node
         */
        public BTNode(T element) {
            this.element = element;
            this.parent = null;
            this.left = null;
            this.right = null;
        }


        @Override
        public T getElement() {
            return element;
        }

        /**
         * Sets the element value of the BTNode.
         *
         * @param element the new element value for the node
         */
        public void setElement(T element) {
            this.element = element;
        }

        /**
         * Returns the left child node of the current node.
         *
         * @return the left child node of the current node
         */
        public BTNode<T> getLeft() {
            return left;
        }

        /**
         * Sets the left child node of the current node.
         *
         * @param left the left child node to be set
         */
        public void setLeft(BTNode<T> left) {
            this.left = left;
        }

        /**
         * Returns the right child node of the current node.
         *
         * @return the right child node
         */
        public BTNode<T> getRight() {
            return right;
        }

        /**
         * Sets the right child node of the current node.
         *
         * @param right the right child node to set
         */
        public void setRight(BTNode<T> right) {
            this.right = right;
        }

        /**
         * Returns the parent node of the current node.
         *
         * @return the parent node of the current node
         */
        public BTNode<T> getParent() {
            return parent;
        }

        /**
         * Sets the parent node of the current node.
         *
         * @param parent the parent node to be set
         */
        public void setParent(BTNode<T> parent) {
            this.parent = parent;
        }
    }

}
