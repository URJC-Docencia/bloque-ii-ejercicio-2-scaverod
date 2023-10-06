package Tree.BinaryTree;

import Tree.Tree;
import material.Position;

/**
 * An interface for a binary tree, where each node can have zero, one, or two
 * children.
 */
public interface BinaryTree<E> extends Tree<E>, Iterable<Position<E>> {

    /**
     * Returns the left child of a node.
     *
     * @param v node
     * @return the left child of the node
     */
    public Position<E> left(Position<E> v);

    /**
     * Returns the right child of a node.
     *
     * @param v node
     * @return the right child of the node
     */
    public Position<E> right(Position<E> v);

    /**
     * Returns whether a node has a left child.
     *
     * @param v node
     * @return true if the node has a left child, false otherwise
     */
    public boolean hasLeft(Position<E> v);

    /**
     * Returns whether a node has a right child.
     *
     * @param v node
     * @return true if the node has a right child, false otherwise
     */
    public boolean hasRight(Position<E> v);


    /**
     * Returns whether a node is internal.
     *
     * @param v node
     * @return true if the node is internal, false otherwise
     */
    @Override
    public boolean isInternal(Position<E> v);


    /**
     * Returns whether a node is a leaf.
     *
     * @param p the position of the node
     * @return true if the node is a leaf, false otherwise
     */
    @Override
    public boolean isLeaf(Position<E> p);


    /**
     * Returns whether a node is the root of the tree.
     *
     * @param p the position of the node
     * @return true if the node is the root, false otherwise
     */
    @Override
    public boolean isRoot(Position<E> p);


    /**
     * Returns the root position of the tree.
     *
     * @return the position of the root node
     */
    @Override
    public Position<E> root();

    /**
     * Replaces the element at the given position with the specified element.
     *
     * @param p the position of the node to be replaced
     * @param e the new element to replace the existing element
     * @return the previous element that was replaced
     */
    public E replace(Position<E> p, E e);


    /**
     * Returns the sibling of the node at the given position.
     *
     * @param p the position of the node to find the sibling of
     * @return the position of the sibling node
     */
    public Position<E> sibling(Position<E> p);


    /**
     * Adds a root node with the specified element to the tree.
     *
     * @param e the element to be added as the root node
     * @return the position of the newly added root node
     */
    public Position<E> addRoot(E e);


    /**
     * Inserts a new node with the specified element as the left child of the given position in the tree.
     *
     * @param p the position to insert the new node as the left child
     * @param e the element to be added as the left child
     * @return the position of the newly inserted left child node
     */
    public Position<E> insertLeft(Position<E> p, E e);


    /**
     * Inserts a new node with the specified element as the right child of the given position in the tree.
     *
     * @param p the position to insert the new node as the right child
     * @param e the element to be added as the right child
     * @return the position of the newly inserted right child node
     */
    public Position<E> insertRight(Position<E> p, E e);

    /**
     * Removes the node at the specified position from the tree and returns its element.
     *
     * @param p the position of the node to be removed
     * @return the element of the removed node
     */
    public E remove(Position<E> p);


    /**
     * Swaps the elements of the nodes at the specified positions in the tree.
     *
     * @param p1 the position of the first node
     * @param p2 the position of the second node
     */
    public void swap(Position<E> p1, Position<E> p2);


    /**
     * Attaches the given binary tree as the left child of the node at the specified position.
     *
     * @param h  the position of the node to which the binary tree is attached as the left child
     * @param t1 the binary tree to be attached as the left child
     */
    void attachLeft(Position<E> h, BinaryTree<E> t1);


    /**
     * Attaches the given binary tree as the right child of the node at the specified position.
     *
     * @param h  the position of the node to which the binary tree is attached as the right child
     * @param t1 the binary tree to be attached as the right child
     */
    void attachRight(Position<E> h, BinaryTree<E> t1);


    /**
     * Returns the subtree rooted at the specified position.
     *
     * @param h the position of the root node of the subtree
     * @return the subtree rooted at the specified position
     */
    public BinaryTree<E> subTree(Position<E> h);
}
