/*
 * Copyright (C) 2013 rAy <predator.ray@gmail.com>
 */
package cn.edu.seu.cose.ray.jsonlexer.token;

import cn.edu.seu.cose.ray.jsonlexer.Token;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
public class ColonToken implements Token<Void> {

    public String getName() {
        return "COLON";
    }

    public Void getValue() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ColonToken)) {
            return false;
        }
        ColonToken compared = (ColonToken) obj;
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
