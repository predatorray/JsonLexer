/*
 * Copyright (C) 2013 rAy <predator.ray@gmail.com>
 */
package cn.edu.seu.cose.ray.jsonlexer;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
public interface Token<ValueType> {

    String getName();

    ValueType getValue();

    @Override
    boolean equals(Object obj);

    @Override
    int hashCode();
}
