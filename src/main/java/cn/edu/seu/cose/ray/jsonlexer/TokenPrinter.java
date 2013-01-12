/*
 * Copyright (C) 2013 rAy <predator.ray@gmail.com>
 */
package cn.edu.seu.cose.ray.jsonlexer;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
public class TokenPrinter {

    public String printToken(Token<?> token) {
        StringBuilder builder = new StringBuilder();
        builder.append("<");
        builder.append(token.getName());
        builder.append(", ");
        builder.append(token.getValue());
        builder.append(">");
        return builder.toString();
    }
}
