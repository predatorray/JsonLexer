/*
 * Copyright (C) 2013 rAy <predator.ray@gmail.com>
 */
package cn.edu.seu.cose.ray.jsonlexer.token;

import cn.edu.seu.cose.ray.jsonlexer.Token;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
public class StringToken implements Token<String> {

    private String value;

    public StringToken(String value) {
        this.value = value;
    }

    public String getName() {
        return "STRING";
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof StringToken)) {
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
