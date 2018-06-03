package moe.rangho.jialoguescript.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Utility class that contains easier way of accessing a string as a stream.
 *
 * Note that manipulation of the internal list is not possible.
 */
public class CharacterStream {

    private List<Character> list;

    private int position;

    /**
     * Constructs a CharacterStream without any content.
     */
    public CharacterStream() {
        this.list = new ArrayList<>();
        this.position = 0;
    }

    /**
     * Constructs a CharacterStream object from existing array.
     * @param input existing array to initialize from.
     */
    public CharacterStream(String input) {
        List<Character> inputList = new ArrayList<>();

        // why primitive types no object java wtf
        for (int i = 0; i < input.length(); i++)
            inputList.set(i, input.charAt(i));

        this.position = 0;
    }

    /**
     * Returns the value at current location without moving the pointer.
     * @return the element at current location
     */
    public Character peek() {
        return this.list.get(this.position);
    }

    /**
     * Returns the value at current location, then moves the pointer by 1.
     * @return the element at current location
     */
    public Character read() {
        return this.list.get(this.position++);
    }

    /**
     * Returns given amount of values in the stream without moving the pointer.
     * @param amount number of elements in stream to peek
     * @return {@link List<Character>} of elements in stream
     */
    public List<Character> peek(int amount) {
        // For this part, I would love to use T[] but Java won't let me construct T[] directly
        // Converting from Object[] is not ideal, nor is guaranteed to work

        List<Character> list = new ArrayList<>();

        for (int i = 0; i < amount; i++)
            list.add(this.list.get(this.position + i));

        return list;
    }

    /**
     * Returns given amount of values in the stream then moves the pointer to the appropriate position.
     * @param amount number of elements in stream to read
     * @return {@link List<Character>} of elements in stream
     */
    public List<Character> read(int amount) {
        // For the same reason as above, I have to use List<Character>
        // Hecc you Java for that

        List<Character> list = new ArrayList<>();

        for (int i = 0; i < amount; i++)
            list.add(read());

        return list;
    }

    public String readWhile(Predicate<Character> predicate) {
        StringBuilder sb = new StringBuilder();

        while (predicate.test(peek()))
            sb.append(read());

        return sb.toString();
    }

    public String readUntil(Predicate<Character> predicate) {
        StringBuilder sb = new StringBuilder();

        while (true)
            if (predicate.test(peek()))
                return sb.toString();
            else
                sb.append(read());
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
