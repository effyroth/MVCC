package com.effyroth.mvcc.impl;

import com.effyroth.mvcc.Cache;
import com.effyroth.mvcc.RevisionMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by guzhen on 15/10/20.
 */
public class MVCCCache<K,V> implements Cache<K,V> {

//    private RevisionMap<V> revisions;
    private Map<K, RevisionMap<V>> cache;
    private ReadWriteLock lock = new ReentrantReadWriteLock();


    public MVCCCache(){
        cache = new HashMap<K, RevisionMap<V>>();

    }

    @Override
    public
//    synchronized
    V set(K key, V value) {

            RevisionMap revisions = cache.get(key);
            if (revisions == null){
                revisions = new RevisionMap();
                cache.put(key, revisions);
            }
        try {
            lock.writeLock().lock();
            revisions.add(value);
            return value;

        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public
    synchronized
    V delete(K key) {
        V value = this.get(key);
        RevisionMap revisions = cache.remove(key);
        return value;
    }

    @Override
//    synchronized
    public V get(K key) {
            RevisionMap<V> revisions = cache.get(key);
            if (revisions == null){
                log.debug("revisions null");
                return null;
            }
        try {
            lock.readLock().lock();
            V value;
            value = revisions.get();
            return value;
        } finally {
            lock.readLock().unlock();
        }
    }

    Logger log = LoggerFactory.getLogger(this.getClass());
}
