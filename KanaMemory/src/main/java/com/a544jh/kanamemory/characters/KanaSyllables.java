/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.a544jh.kanamemory.characters;

/**
 *
 * @author Axel
 */
public enum KanaSyllables {

    A("あ", "ア"),
    I("\u3044", "\u30a4");
    //...U,E,O, KA,KI,KU,KE,KO...


    private final String hiragana, katakana;

    private KanaSyllables(String hiragana, String katakana) {
        this.hiragana = hiragana;
        this.katakana = katakana;
    }

    public String getHiragana() {
        return hiragana;
    }

    public String getKatakana() {
        return katakana;
    }

    public String getRomaji() {
        return this.toString().toLowerCase();
    }
}
