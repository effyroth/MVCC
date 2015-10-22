package com.effyroth.mvcc;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by guzhen on 15/10/20.
 */
public class RevisionGenerator {

    private AtomicLong currentRevision;

    private RevisionGenerator(){
        currentRevision = new AtomicLong(0);
    }

    public static RevisionGenerator getInstance(){
        return Nest.inner;
    }

    private static class Nest{
        static RevisionGenerator inner = new RevisionGenerator();
    }

    public Revision newRevision(){
        return new Revision(currentRevision.incrementAndGet());
    }
}
