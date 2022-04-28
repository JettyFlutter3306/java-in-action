package cn.element.collection.circularArrayQueue;

import java.util.AbstractQueue;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A first-in, first-out bounded collection.
 */
public class CircularArrayQueue<E> extends AbstractQueue<E> {
    private final Object[] elements;
    private int head;
    private int tail;
    private int count;
    private int modCount;

    /**
     * Constructs an empty queue.
     *
     * @param capacity the maximum capacity of the queue
     */
    public CircularArrayQueue(int capacity) {
        elements = new Object[capacity];
        count = 0;
        head = 0;
        tail = 0;
    }

    public boolean offer(E newElement) {
        assert newElement != null;
        if (count < elements.length) {
            elements[tail] = newElement;
            tail = (tail + 1) % elements.length;
            count++;
            modCount++;
            return true;
        } else
            return false;
    }

    public E poll() {
        if (count == 0) return null;
        E r = peek();
        head = (head + 1) % elements.length;
        count--;
        modCount++;
        return r;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        if (count == 0) return null;
        return (E) elements[head];
    }

    public int size() {
        return count;
    }

    public Iterator<E> iterator() {
        return new QueueIterator();

    }

    private class QueueIterator implements Iterator<E> {
        private int offset;
        private final int modCountAtConstruction;

        public QueueIterator() {
            modCountAtConstruction = modCount;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            E r = (E) elements[(head + offset) % elements.length];
            offset++;
            return r;
        }

        public boolean hasNext() {
            if (modCount != modCountAtConstruction)
                throw new ConcurrentModificationException();
            return offset < count;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
