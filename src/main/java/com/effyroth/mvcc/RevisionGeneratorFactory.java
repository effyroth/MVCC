package com.effyroth.mvcc;

/**
 * Created by guzhen on 15/10/20.
 */
public interface RevisionGeneratorFactory {
    public RevisionGenerator createRevisionGenerator();
}
