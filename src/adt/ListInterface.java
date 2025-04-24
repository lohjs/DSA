package adt;
import java.util.Iterator;
import java.util.function.Function;

public interface ListInterface<T> {

    /**
     * Task: Adds a new entry to the end of the list. Entries currently in the
     * list are unaffected. The list's size is increased by 1.
     *
     * @param newEntry the object to be added as a new entry
     * @return true if the addition is successful, or false if the list is full
     */
    public boolean add(T newEntry);

    /**
     * Task: Adds a new entry at a specified position within the list. Entries
     * originally at and above the specified position are at the next higher
     * position within the list. The list's size is increased by 1.
     *
     * @param newPosition an integer that specifies the desired position of the
     * new entry
     * @param newEntry the object to be added as a new entry
     * @return true if the addition is successful, or false if either the list
     * is full, newPosition < 1, or
     *          newPosition > getNumberOfEntries()+1
     */
    public boolean add(int newPosition, T newEntry);

    /**
     * Task: Removes the entry at a given position from the list. Entries
     * originally at positions higher than the given position are at the next
     * lower position within the list, and the list's size is decreased by 1.
     *
     * @param givenPosition an integer that indicates the position of the entry
     * to be removed
     * @return a reference to the removed entry or null, if either the list was
     * empty, givenPosition < 1, or
     *          givenPosition > getNumberOfEntries()
     */
    public T remove(int givenPosition);

    /**
     * Task: Removes all entries from the list.
     */
    public void clear();

    /**
     * Task: Replaces the entry at the specified position in the list with the 
     * given entry.
     *
     * @param givenPosition an integer that indicates the position of the entry
     * to be replaced
     * @param newEntry the object that will replace the entry at the position
     * givenPosition
     * @return true if the replacement occurs, or false if either the list is
     * empty, givenPosition < 1, or givenPosition > getNumberOfEntries()
     */
    public boolean replace(int givenPosition, T newEntry);

    /**
     * Task: Retrieves the entry at a given position in the list.
     *
     * @param givenPosition an integer that indicates the position of the
     * desired entry
     * @return a reference to the indicated entry or null, if either the list is
     * empty, givenPosition < 1, or givenPosition > getNumberOfEntries()
     */
    public T getEntry(int givenPosition);

    /**
     * Task: Checks if the list contains the specified element.
     *
     * @param anEntry the object that is the desired entry
     * @return true if the list contains anEntry, or false if not
     */
    public boolean contains(T anEntry);

    /**
     * Task: Retrieves the index of the first occurrence of the specified
     * element in the list.
     *
     * @param element the element whose index is to be found
     * @return the index of the first occurrence of the specified element in the
     * list,
     */
    public int indexOf(T element);

    /**
     * Task: Retrieves the number of entries in the list.
     *
     * @return the integer number of entries currently in the list
     */
    public int getNumberOfEntries();

    /**
     * Task: Checks if the list is empty.
     *
     * @return true if the list is empty, or false if not
     */
    public boolean isEmpty();

    /**
     * Task: Checks if the list is full.
     *
     * @return true if the list is full, or false if not
     */
    public boolean isFull();

    /**
     *
     * Task: Returns an iterator over the elements in this list in proper
     * sequence.
     *
     * @return an iterator over the elements in this list
     */
    Iterator<T> iterator();

    /**
     *
     * Task: Sorts the elements of the list in ascending order based on the
     * values extracted using the provided keyExtractor function.
     *
     * @param keyExtractor a function that extracts a comparable key from each
     * element
     * @param <T> the type of elements in the list
     */
    public void sortByAscending(Function<T, Comparable<?>> keyExtractor);

    /**
     *
     * Task: Sorts the elements of the list in descending order based on the
     * values extracted using the provided keyExtractor function.
     *
     * @param keyExtractor a function that extracts a comparable key from each
     * element
     * @param <T> the type of elements in the list
     */
    public void sortByDescending(Function<T, Comparable<?>> keyExtractor);

}
