package Tree.BinaryTree;

import material.Position;

import java.util.Iterator;

/**
 * @author mayte
 */
public class LinkedBinaryTree<E>extends DrawableTree<E> {

    private class BTNode<T> implements Position<T>{

        private T element;

        private BTNode left, right, parent;

        public BTNode(T element, BTNode left, BTNode right, BTNode parent) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public BTNode(BTNode left, BTNode right, BTNode parent) {
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        @Override
        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public BTNode getLeft() {
            return left;
        }

        public void setLeft(BTNode left) {
            this.left = left;
        }

        public BTNode getRight() {
            return right;
        }

        public void setRight(BTNode right) {
            this.right = right;
        }

        public BTNode getParent() {
            return parent;
        }

        public void setParent(BTNode parent) {
            this.parent = parent;
        }
    }

    private BTNode root;

    private int size;

    private LinkedBinaryTree(){
        root = null;
        size = 0;
    }

    private BTNode<E> checkPosition(Position<E> v){
        if(!(v instanceof BTNode<E>)){
            throw new IllegalArgumentException("Invalid posistion");
        }
        return (BTNode<E>) v;
    }


    @Override
    public Position<E> left(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Position<E> right(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasLeft(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasRight(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isInternal(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isLeaf(Position<E> p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isRoot(Position<E> p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Position<E> root() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E replace(Position<E> p, E e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Position<E> sibling(Position<E> p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Position<E> addRoot(E e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Position<E> insertLeft(Position<E> p, E e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Position<E> insertRight(Position<E> p, E e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E remove(Position<E> p) {
        BTNode<E> nodeToRemove = checkPosition(p);
        BTNode<E> parent = nodeToRemove.getParent();
        BTNode<E> left = nodeToRemove.getLeft();
        BTNode<E> right = nodeToRemove.getRight();

        if (left != null && right !=null){
            throw new IllegalArgumentException("It has two childreen");
        }

        BTNode<E> child = left != null ? left : right;

        if (nodeToRemove == root){
            if (child != null){
                child.setParent(null);
            }
            root = child;
        } else {
            if (nodeToRemove == parent.getLeft()){
                parent.setLeft(child);
            }else {
                parent.setRight(child);
            }
            if (child !=null){
                child.setParent(parent);
            }
        }
        size--;
        return nodeToRemove.getElement();
    }

    @Override
    public void swap(Position<E> p1, Position<E> p2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Position<E> parent(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<Position<E>> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public LinkedBinaryTree<E> subTree(Position<E> h) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void attachLeft(Position<E> h, BinaryTree<E> t1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void attachRight(Position<E> h, BinaryTree<E> t1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
