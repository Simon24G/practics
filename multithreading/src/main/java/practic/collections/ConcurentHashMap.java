package practic.collections;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Игорь on 25.03.2018.
 */

public class ConcurentHashMap {
    private Map map;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public ConcurentHashMap(Map map) {
        this.map = map;
    }

    public int size() {
        lock.readLock().lock();
        int size;
        try {
            size = map.size();
        } finally {
            lock.readLock().unlock();
        }
        return size;
    }

    public boolean isEmpty() {
        lock.readLock().lock();
        boolean isEmpty;
        try {
            isEmpty = map.isEmpty();
        } finally {
            lock.readLock().unlock();
        }
        return isEmpty;
    }

    public boolean containsKey(Object key) {
        lock.readLock().lock();
        boolean containsKey;
        try {
            containsKey = map.containsKey(key);
        } finally {
            lock.readLock().unlock();
        }
        return containsKey;
    }

    public boolean containsValue(Object value) {
        lock.readLock().lock();
        boolean containsValue;
        try {
            containsValue = map.containsValue(value);
        } finally {
            lock.readLock().unlock();
        }
        return containsValue;
    }

    public Object get(Object key) {
        lock.readLock().lock();
        Object o;
        try {
            o = map.get(key);
        } finally {
            lock.readLock().unlock();
        }
        return o;
    }

    public Object put(Object key, Object value) {
        lock.writeLock().lock();
        Object o;
        try {
            o = map.put(key,value);
        } finally {
            lock.writeLock().unlock();
        }
        return o;
    }

    public Object remove(Object key) {
        lock.writeLock().lock();
        Object o;
        try {
            o = remove(key);
        } finally {
            lock.writeLock().unlock();
        }
        return o;
    }

    public void putAll(Map m) {
        lock.writeLock().lock();
        try {
            map.putAll(m);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void clear() {
        lock.writeLock().lock();
        try {
            map.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Set keySet() {
        lock.readLock().lock();
        Set set;
        try {
            set = map.keySet();
        } finally {
            lock.readLock().unlock();
        }
        return set;
    }

    public Collection values() {
        lock.readLock().lock();
        Collection collection;
        try {
            collection = map.values();
        } finally {
            lock.readLock().unlock();
        }
        return collection;
    }

    public Set entrySet() {
        lock.readLock().lock();
        Set set;
        try {
            set = map.entrySet();
        } finally {
            lock.readLock().unlock();
        }
        return set;
    }

    public boolean equals(Object o) {
        lock.readLock().lock();
        boolean eq;
        try {
            eq = map.equals(o);
        } finally {
            lock.readLock().unlock();
        }
        return eq;
    }

    public int hashCode() {
        lock.readLock().lock();
        int hash;
        try {
            hash = map.hashCode();
        } finally {
            lock.readLock().unlock();
        }
        return hash;
    }
}