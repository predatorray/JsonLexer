/*
 * Copyright (C) 2013 rAy <predator.ray@gmail.com>
 */
package cn.edu.seu.cose.ray.jsonlexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
public class JsonLexerApp {

    public static void main(String[] args) throws FileNotFoundException,
            IOException {
        boolean givenArgs = (args.length > 0);
        String filePath = givenArgs
                ? args[0]
                : JsonLexerApp.class.getClassLoader()
                    .getResource("cn/edu/seu/cose/ray/jsonlexer/sample.json")
                    .getFile();
        String text = readTextFromFile(filePath);
        JsonLexer lexer = new JsonLexer(text);
        printTokens(lexer);
    }

    private static String readTextFromFile(String filePath)
            throws FileNotFoundException, IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("File is not found from path: " + filePath);
        }

        BufferedReader in = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        while (true) {
            String str = in.readLine();
            if (str == null) {
                break;
            }
            builder.append(str);
            builder.append("\n");
        }
        return builder.toString();
    }

    private static void printTokens(Iterator<Token<?>> tokenIt) {
        TokenPrinter printer = new TokenPrinter();
        while (tokenIt.hasNext()) {
            Token<?> token = tokenIt.next();
            String tokenToDisplay = printer.printToken(token);
            System.out.println(tokenToDisplay);
        }
    }
}
