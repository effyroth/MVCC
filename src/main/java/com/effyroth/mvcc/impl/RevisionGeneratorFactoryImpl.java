package com.effyroth.mvcc.impl;

import com.effyroth.mvcc.RevisionGenerator;
import com.effyroth.mvcc.RevisionGeneratorFactory;

/**
 * Created by guzhen on 15/10/20.
 */
public class RevisionGeneratorFactoryImpl implements RevisionGeneratorFactory {

    @Override
    public RevisionGenerator createRevisionGenerator() {
        return RevisionGenerator.getInstance();
    }
}
