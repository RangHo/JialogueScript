package moe.rangho.jialoguescript.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class that contains easier way of accessing stream-like data.
 *
 * Note that manipulation of the internal list is not possible.
 * @param <T> represents the type of values stored in the stream.
 */
public class GenericStream<T> {

    private List<T> list;

    private int position;

    /**
     * Constructs a GenericStream without any content.
     */
    public GenericStream() {
        this.list = new ArrayList<>();
        this.position = 0;
    }

    /**
     * Constructs a GenericStream object from existing array.
     * @param input existing array to initialize from.
     */
    public GenericStream(T[] input) {
        this.list = Arrays.asList(input);
        this.position = 0;
    }

    /**
     * Returns the value at certain point of this stream.
     * @param position location of the value
     * @return the actual value at given location.
     */
    public T get(int position) {
        return this.list.get(position);
    }

    /**
     * Sets the value at certain point of this stream.
     * @param position location of the value
     * @param object the value to be set
     */
    public void set(int position, T object) {
        this.list.set(position, object);
    }

    /**
     * Returns the value at current location without moving the pointer.
     * @return the element at current location
     */
    public T peek() {
        return this.list.get(this.position);
    }

    /**
     * Returns the value at current location, then moves the pointer by 1.
     * @return the element at current location
     */
    public T read() {
        return this.list.get(this.position++);
    }


    /**
     * Returns the current location.
     * @return current locatio/;p0n of pointer
     */
    public int getPosition() {
        return position;
    }

    /**
     * Moves the pointer to specific location.
     * @param position
     */
    public void setPosition(int position) {
        if (position < 0 || position > this.list.size() - 1)
            throw new IndexOutOfBoundsException("Position in stream is not valid.");

        this.position = position;
    }
}
