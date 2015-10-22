package com.effyroth.mvcc;

import com.effyroth.mvcc.impl.RevisionGeneratorFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by guzhen on 15/10/20.
 */
public class RevisionMap<V> extends HashMap<Revision, V> {

    Logger log = LoggerFactory.getLogger(RevisionMap.class);
    private Revision currentRevision;
    private RevisionGenerator revisionGenerator;
    public RevisionMap(){
        RevisionGeneratorFactory revisionGeneratorFactory = new RevisionGeneratorFactoryImpl();
        revisionGenerator = revisionGeneratorFactory.createRevisionGenerator();
    }

    public V add(V value){
        Revision revision = revisionGenerator.newRevision();
        value = this.put(revision, value);
        this.currentRevision = revision;
        return value;
    }

    public V get(){
        if (currentRevision == null){
            return null;
        }
        V value = this.get(currentRevision);
        return value;
    }
}
