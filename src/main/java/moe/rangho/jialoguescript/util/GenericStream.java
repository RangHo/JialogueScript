package moe.rangho.jialoguescript.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Utility class that contains easier way of accessing data as a stream.
 *
 * Note that manipulation of the internal list is not possible.
 */
public class GenericStream<T> {

    private List<T> list;

    private int position;

    /**
     * Constructs a GenericStream object from existing array.
     * @param input existing array to initialize from.
     */
    public GenericStream(List<T> input) {
        this.list = input;
        this.position = 0;
    }

    /**
     * Returns true if the stream reaches its end.
     * @return
     */
    public boolean isEnd() {
        return this.position >= this.list.size();
    }

    /**
     * Returns amount of items left in the stream
     * @return number of items before the stream runs out
     */
    public int getAmountLeft() {
        return this.list.size() - this.position - 1;
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
        // Using List<T> for the same reason as above

        List<T> list = new ArrayList<>();

        for (int i = 0; i < amount; i++)
            list.add(read());

        return list;
    }

    /**
     * Returns the current location.
     * @return current location of pointer
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
