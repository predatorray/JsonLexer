/*
 * Copyright (C) 2013 rAy <predator.ray@gmail.com>
 */
package cn.edu.seu.cose.ray.jsonlexer;

import cn.edu.seu.cose.ray.jsonlexer.token.ColonToken;
import cn.edu.seu.cose.ray.jsonlexer.token.CommaToken;
import cn.edu.seu.cose.ray.jsonlexer.token.LeftBraceToken;
import cn.edu.seu.cose.ray.jsonlexer.token.LeftSquareBracketToken;
import cn.edu.seu.cose.ray.jsonlexer.token.NullToken;
import cn.edu.seu.cose.ray.jsonlexer.token.RightBraceToken;
import cn.edu.seu.cose.ray.jsonlexer.token.RightSquareBracketToken;
import cn.edu.seu.cose.ray.jsonlexer.token.StringToken;
import java.util.Iterator;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
public class JsonLexer implements Iterator<Token<?>> {

    private String text;
    private int cursor = -1;

    public JsonLexer(String text) {
        this.text = text;
    }

    public boolean hasNext() {
        // if come to last
        if ((cursor + 1) >= text.length()) {
            return false;
        }
        // omit blank chars
        char nextCh = text.charAt(cursor + 1);
        boolean isBlank = (nextCh == ' ' || nextCh == '\n' || nextCh == '\r');
        if (isBlank) {
            ++cursor;
            return hasNext();
        }
        return (cursor + 1 < text.length());
    }

    public Token<?> next() {
        ++cursor;
        char ch = text.charAt(cursor);

        if (ch == '/') {
            return omitComments();
        }

        if (ch == '\"') {
            return parseString();
        }

        // omit blank chars
        boolean isBlank = (ch == ' ' || ch == '\n' || ch == '\r');
        if (isBlank) {
            return new NullToken();
        }

        boolean isNumeric = (ch <= '9' && ch >= '0');
        if (isNumeric) {
            throw new UnsupportedOperationException();
        }

        if (ch == '{') {
            return new LeftBraceToken();
        }

        if (ch == '}') {
            return new RightBraceToken();
        }

        if (ch == '[') {
            return new LeftSquareBracketToken();
        }

        if (ch == ']') {
            return new RightSquareBracketToken();
        }

        if (ch == ':') {
            return new ColonToken();
        }

        if (ch == ',') {
            return new CommaToken();
        }

        throw new RuntimeException("exception occurred during analyzing the "
                + "json, unexpected character: " + ch);
    }

    public void remove() {
        throw new UnsupportedOperationException("Not supported.");
    }

    private Token<?> parseString() {
        StringBuilder strBuilder = new StringBuilder();
        while (true) {
            ++cursor;
            // check if cursor is out of bound
            if (cursor > text.length()) {
                throw new RuntimeException("Right quote is required");
            }

            char ch = text.charAt(cursor);
            if (ch == '\"') {
                break;
            }
            strBuilder.append(ch);
        }
        return new StringToken(strBuilder.toString());
    }

    private Token<?> omitComments() {
        // check if cursor is out of bound
        ++cursor;
        if (cursor > text.length() || text.charAt(cursor) != '/') {
            throw new RuntimeException("Another slash is required");
        }

        // ommit comments till the next line
        while ((cursor + 1) < text.length()) {
            ++cursor;
            char nextChar = text.charAt(cursor);
            if (nextChar == '\n' || nextChar == '\r') {
                break;
            }
        }
        return next();
    }
}
