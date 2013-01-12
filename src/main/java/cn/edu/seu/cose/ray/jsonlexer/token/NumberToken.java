/*
 * Copyright (C) 2013 rAy <predator.ray@gmail.com>
 */
package cn.edu.seu.cose.ray.jsonlexer.token;

import cn.edu.seu.cose.ray.jsonlexer.Token;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
public class NumberToken implements Token<Number> {

    private Number number;

    public NumberToken(Number number) {
        this.number = number;
    }

    public String getName() {
        return "NUMBER";
    }

    public Number getValue() {
        return number;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NumberToken)) {
            return false;
        }
        Token compared = (Token) obj;
        boolean nameEquals = (getName() == null && compared.getName() == null)
                || getName().equals(compared.getName());
        boolean valueEquals = (getValue() == null
                    && compared.getValue() == null)
                || getValue().equals(compared.getValue());
        return nameEquals && valueEquals;
    }

    @Override
    public int hashCode() {
        int nameCode = (getName() == null)
                ? 0
                : getName().hashCode();
        int valueCode = (getValue() == null)
                ? 0
                : getValue().hashCode();
        return nameCode + valueCode;
    }
}
