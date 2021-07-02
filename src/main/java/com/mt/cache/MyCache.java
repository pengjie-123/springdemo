package com.mt.cache;

import java.util.HashMap;
import java.util.Map;

public class MyCache {
    private Map cache = new HashMap();


    public void putVal(String key, Object value, long ttl) {
        long timestamp = System.currentTimeMillis();
        Object[] o =  {value, ttl, timestamp};
        cache.put(key, o);
    }

    public Object getVal(String key) {
        long current = System.currentTimeMillis();
        Object [] cls = (Object[]) cache.get(key);
        if (cls == null) {
            return null;
        }
        long old = (long) cls[2];
        long ttl = (long) cls[1];
        if (current < (ttl*1000 + old)) {
            return cls[0];
        } else {
            System.out.println("time expired for this key: " + key);
            cache.remove(key);
            return null;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyCache myCache = new MyCache();
        myCache.putVal("token", "1234456qweewqasdAAAAAADGFDSF", 60);
        for (int i = 0; i < 10; i++) {
            System.out.println("the cache size is: " + myCache.cache.size());
            System.out.println(myCache.getVal("token"));
            Thread.sleep(10 * 1000);
        }
    }

}
