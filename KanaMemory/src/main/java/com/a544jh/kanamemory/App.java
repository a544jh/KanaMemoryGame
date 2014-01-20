package com.a544jh.kanamemory;

import com.a544jh.kanamemory.characters.*;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println("Hello World!");
        System.out.println(KanaSyllables.A.getHiragana());
        PrintStream out = new PrintStream(System.out, true, "UTF-8");
        out.println(KanaSyllables.A.getHiragana());
        System.out.println("あ");
        System.out.println(KanaSyllables.A.getRomaji());
        System.out.println(KanaSyllables.I.getKatakana());
    }
}
