interface Worklist<E> {
    public void add(E item);
    public E remove();
    public boolean isEmpty();
    public E peek();
    public void clear();
}
