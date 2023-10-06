package Tree;

import material.Position;

public interface Tree<E> extends Iterable<Position<E>> {


    /**
     * Checks if the object is empty.
     *
     * @return true if the object is empty, false otherwise.
     */
    public boolean isEmpty();


    /**
     * Returns the root position of the tree.
     *
     * @return the root position of the tree.
     */
    public Position<E> root();


    /**
     * Returns the parent position of the specified position in the tree.
     *
     * @param v the position whose parent position is to be returned.
     * @return the parent position of the specified
     */
    public Position<E> parent(Position<E> v);


    /**
     * Returns an iterable collection of the child positions of the specified position in the tree.
     *
     * @param v the position whose child positions are to be returned.
     * @return an iterable collection of the child positions of the specified position.
     */
    public Iterable<? extends Position<E>> children(Position<E> v);


    /**
     * Determines whether the specified position is an internal position in the tree.
     *
     * @param v the position to be checked.
     * @return true if the position is internal, false otherwise.
     */
    public boolean isInternal(Position<E> v);


    /**
     * Determines whether the specified position is a leaf position in the tree.
     *
     * @param v the position to be checked.
     * @return true if the position is a leaf, false otherwise.
     */
    public boolean isLeaf(Position<E> v);


    /**
     * Determines whether the specified position is the root position in the tree.
     *
     * @param v the position to be checked.
     * @return true if the position is the root, false otherwise.
     */
    public boolean isRoot(Position<E> v);
}
