/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

/**
 *
 * @author boonx
 */
public interface MapInterface <K, V>{

    /**
     * Task: Associates the specified value with the specified key in this map.
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    void put(K key, V value);

    /**
     * Task: Retrieves the value associated with the specified key in this map.
     *
     * @param key the key whose associated value is to be retrieved
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    V get(K key);

    /**
     * Task:Retrieves the value associated with the specified key in this map, or returns a default value if the key is not present.
     *
     * @param key the key whose associated value is to be retrieved
     * @param defaultValue the default value to return if the key is not present in the map
     * @return the value to which the specified key is mapped, or the default value if the key is not present
     */
    V getOrDefault(K key, V defaultValue);

    /**
     * Task: Checks if this map contains a mapping for the specified key.
     *
     * @param key the key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key, false otherwise
     */
    boolean containsKey(K key);

    /**
     * Task: Associates the specified value with the specified key in this map if the key is not already associated with a value.
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return the previous value associated with the specified key, or null if there was no mapping for the key
     */
    V putIfAbsent(K key, V value);

    /**
     * Task: Removes the mapping for the specified key from this map if present.
     *
     * @param key the key whose mapping is to be removed from the map
     */
    void remove(K key);

    /**
     * Task: Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     */
    int size();

    /**
     * Task: Checks if this map contains no key-value mappings.
     *
     * @return true if this map contains no key-value mappings, false otherwise
     */
    boolean isEmpty();

    /**
     * Task: Removes all of the mappings from this map. The map will be empty after this call returns.
     */
    void clear();
}
