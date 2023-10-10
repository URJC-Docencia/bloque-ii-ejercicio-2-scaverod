package Tree.BinaryTree;

import material.Position;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;


public class ArrayBinaryTree<E> extends DrawableTree<E> {

    private ArrayList<BTPos<E>> tree = new ArrayList<>();

    public ArrayBinaryTree() {
        tree.add(null);
    }

    private int getLeftChildPosition(int i) {
        return (2 * (i + 1)) - 1;
    }

    private int getRightChildPosition(int i) {
        return (2 * (i + 1));
    }

    private BTPos<E> checkPosition(Position<E> v) {
        if (!(v instanceof BTPos)) {
            throw new IllegalStateException("The position is invalid");
        }
        return (BTPos<E>) v;
    }

    @Override
    public Position<E> left(Position<E> v) {
        BTPos<E> aux = checkPosition(v);
        if (hasLeft(v)) {
            return tree.get(getLeftChildPosition(aux.pos));
        }
        throw new RuntimeException("This position does not have a left children");
    }

    @Override
    public Position<E> right(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasLeft(Position<E> v) {
        BTPos<E> aux = checkPosition(v);
        int posLeftChild = getLeftChildPosition(aux.pos);
        return (tree.size() > posLeftChild) && (tree.get(posLeftChild) != null);
    }

    @Override
    public boolean hasRight(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isInternal(Position<E> v) {
        return hasLeft(v) || hasRight(v);
    }

    @Override
    public boolean isLeaf(Position<E> p) {
        return (!isInternal(p));
    }

    @Override
    public boolean isRoot(Position<E> p) {
        BTPos<E> aux = checkPosition(p);
        return aux == root();
    }

    @Override
    public Position<E> root() {
        return tree.get(0);
    }

    @Override
    public E replace(Position<E> p, E e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Position<E> sibling(Position<E> p) {
        BTPos<E> aux = checkPosition(p);
        if(!isRoot(aux)){
            BTPos<E> parent = (BTPos<E>) parent(aux);
            if(aux == left(parent)){
                return right(parent);
            }else {
                return left(parent);
            }
        }
        throw new RuntimeException();
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
        throw new UnsupportedOperationException("Not supported yet.");
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
       return new InOrderIterator();
    }

    @Override
    public void attachLeft(Position<E> h, BinaryTree<E> t1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void attachRight(Position<E> h, BinaryTree<E> t1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BinaryTree<E> subTree(Position<E> h) {
        throw new UnsupportedOperationException("Not supported yet.");
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

        public InOrderIterator(){
            if(!isEmpty()){
                stack.push(root());
                goToLeft(root());
            }
        }
        
        private void goToLeft(Position<E> v){
            if (hasLeft(v)){
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
            if(hasRight(aux)){
                stack.push(right(aux));
                goToLeft(aux);
            }
            return aux;
        }
    }
}
