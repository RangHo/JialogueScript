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
     * Returns given amount of values in the stream without moving the pointer.
     * @param amount number of elements in stream to peek
     * @return {@link List<T>} of elements in stream
     */
    public List<T> peek(int amount) {
        // For this part, I would love to use T[] but Java won't let me construct T[] directly
        // Converting from Object[] is not ideal, nor is guaranteed to work

        List<T> list = new ArrayList<>();

        for (int i = 0; i < amount; i++)
            list.add(this.list.get(this.position + i));

        return list;
    }

    /**
     * Returns given amount of values in the stream then moves the pointer to the appropriate position.
     * @param amount number of elements in stream to read
     * @return {@link List<T>} of elements in stream
     */
    public List<T> read(int amount) {
        // For the same reason as above, I have to use List<T>
        // Hecc you Java for that

        List<T> list = new ArrayList<>();

        for (int i = 0; i < amount; i++)
            list.add(read());

        return list;
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
