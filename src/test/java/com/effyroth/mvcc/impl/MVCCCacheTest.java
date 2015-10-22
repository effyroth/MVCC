package com.effyroth.mvcc.impl;

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 
* MVCCCache Tester. 
* 
* @author <Authors name> 
* @since <pre>十月 21, 2015</pre> 
* @version 1.0 
*/ 
public class MVCCCacheTest {

    private MVCCCache<String, String> mvccCache = new MVCCCache<String, String>();

    private Logger log = LoggerFactory.getLogger(MVCCCacheTest.class);

@Before
public void before() throws Exception {
    mvccCache.set("test", "test");
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: set(K key, V value) 
* 
*/ 
@Test
public void testSet() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: delete(K key) 
* 
*/ 
@Test
public void testDelete() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: get(K key) 
* 
*/ 
@Test
public void testGet() throws Exception { 
//TODO: Test goes here... 
}
    @Test
    public void testMultiThread(){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Writer writer1 = new Writer();
        executorService.execute(writer1);
        Reader reader1 = new Reader();
        executorService.execute(reader1);
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
            log.debug("reader loop {}", reader1.i);
            log.debug("writer loop {}", writer1.tail);
        }
    }

    class Reader implements Runnable{
        public int i = 0;

        @Override
        public void run() {
            while (true){
                ++i;
                String value = mvccCache.get("test");
            }
        }
    }

    class Writer implements Runnable{

        long tail = 0;

        @Override
        public void run() {
            while (true){
                String value = mvccCache.set("test", "test " + ++tail);
            }
        }
    }

} 
