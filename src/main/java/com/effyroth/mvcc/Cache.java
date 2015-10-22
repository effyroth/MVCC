package com.effyroth.mvcc;

/**
 * Created by guzhen on 15/10/20.
 */
public interface Cache<K,V> {
    public V set(K key, V value);

    public V delete(K key);

    public V get(K key);

}
