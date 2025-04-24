package adt;

import java.io.Serializable;
import java.util.Iterator;
import java.util.function.Function;

public class ArrayList<T> implements ListInterface<T>, Serializable {

    private T[] array;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY_SIZE = 10;

    public ArrayList() {
        this(DEFAULT_CAPACITY_SIZE);
    }

    public ArrayList(int initialCapacity) {
        numberOfEntries = 0;
        array = (T[]) new Object[initialCapacity];
    }

    @Override
    public boolean add(T newEntry) {
        if (isArrayFull()) {
            doubleArray();
        }

        array[numberOfEntries] = newEntry;
        numberOfEntries++;
        return true;
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
            if (isArrayFull()) {
                doubleArray();
            }
            makeRoom(newPosition);
            array[newPosition - 1] = newEntry;
            numberOfEntries++;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public T remove(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = array[givenPosition - 1];

            if (givenPosition < numberOfEntries) {
                removeGap(givenPosition);
            }

            numberOfEntries--;
        }

        return result;
    }

    @Override
    public void clear() {
        numberOfEntries = 0;
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            array[givenPosition - 1] = newEntry;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public T getEntry(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = array[givenPosition - 1];
        }

        return result;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        for (int index = 0; !found && (index < numberOfEntries); index++) {
            if (anEntry.equals(array[index])) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public int indexOf(T element) {
        for (int index = 0; index < numberOfEntries; index++) {
            if (element.equals(array[index])) {
                return index; // Return the index of the found element
            }
        }
        return -1; // Return -1 if the element is not found
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean isFull() {
        return numberOfEntries == array.length;
    }

    private void doubleArray() {
        T[] oldArray = array;
        array = (T[]) new Object[oldArray.length * 2];
        for (int i = 0; i < oldArray.length; i++) {
            array[i] = oldArray[i];
        }
    }

    private boolean isArrayFull() {
        return numberOfEntries == array.length;
    }

    @Override
    public String toString() {
        String outputStr = "";
        for (int index = 0; index < numberOfEntries; ++index) {
            outputStr += array[index] + "\n";
        }

        return outputStr;
    }

    /**
     * Task: Makes room for a new entry at newPosition. Precondition: 1 <=
     * newPosition <= numberOfEntries + 1; numberOfEntries is array's
     * numberOfEntries before addition.
     */
    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = numberOfEntries - 1;

        // move each entry to next higher index, starting at end of
        // array and continuing until the entry at newIndex is moved
        for (int index = lastIndex; index >= newIndex; index--) {
            array[index + 1] = array[index];
        }
    }

    /**
     * Task: Shifts entries that are beyond the entry to be removed to the next
     * lower position. Precondition: array is not empty; 1 <= givenPosition <
     * numberOfEntries; numberOfEntries is array's numberOfEntries before
     * removal.
     */
    private void removeGap(int givenPosition) {
        // move each entry to next lower position starting at entry after the
        // one removed and continuing until end of array
        int removedIndex = givenPosition - 1;
        int lastIndex = numberOfEntries - 1;

        for (int index = removedIndex; index < lastIndex; index++) {
            array[index] = array[index + 1];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    // Inner class implementing Iterator interface
    private class ArrayListIterator implements Iterator<T> {

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < numberOfEntries;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T element = array[currentIndex];
            currentIndex++;
            return element;
        }
    }

    @Override
    public void sortByAscending(Function<T, Comparable<?>> keyExtractor) {
        quickSort(0, numberOfEntries - 1, keyExtractor);
    }

    // Sort in descending order based on a particular variable
    @Override
    public void sortByDescending(Function<T, Comparable<?>> keyExtractor) {
        quickSortDescending(0, numberOfEntries - 1, keyExtractor);
    }

    // Quicksort algorithm for ascending order
    private void quickSort(int low, int high, Function<T, Comparable<?>> keyExtractor) {
        if (low < high) {
            int pi = partition(low, high, keyExtractor);
            quickSort(low, pi - 1, keyExtractor);
            quickSort(pi + 1, high, keyExtractor);
        }
    }

    // Quicksort algorithm for descending order
    private void quickSortDescending(int low, int high, Function<T, Comparable<?>> keyExtractor) {
        if (low < high) {
            int pi = partitionDescending(low, high, keyExtractor);
            quickSortDescending(low, pi - 1, keyExtractor);
            quickSortDescending(pi + 1, high, keyExtractor);
        }
    }

    // Partition for ascending order
    private int partition(int low, int high, Function<T, Comparable<?>> keyExtractor) {
        T pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            Comparable<Object> keyJ = (Comparable<Object>) keyExtractor.apply(array[j]);
            Comparable<Object> pivotKey = (Comparable<Object>) keyExtractor.apply(pivot);
            if (keyJ.compareTo(pivotKey) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }
    
    // Partition for descending order
    private int partitionDescending(int low, int high, Function<T, Comparable<?>> keyExtractor) {
        T pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            Comparable<Object> keyJ = (Comparable<Object>) keyExtractor.apply(array[j]);
            Comparable<Object> pivotKey = (Comparable<Object>) keyExtractor.apply(pivot);
            if (keyJ.compareTo(pivotKey) > 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    // Utility method to swap elements in the array
    private void swap(int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
