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
    I("い", "イ"),
    U("う", "ウ"),
    E("え", "エ"),
    O("お", "オ"),
    
    KA("か", "カ"),
    KI("き", "キ"),
    KU("く", "ク"),
    KE("け", "ケ"),
    KO("こ", "コ"),
    
    SA("さ", "サ"),
    SHI("し", "シ"),
    SU("す", "ス"),
    SE("せ", "セ"),
    SO("そ", "ソ"),
    
    TA("た", "タ"),
    CHI("ち", "チ"),
    TSU("つ", "ツ"),
    TE("て", "テ"),
    TO("と", "ト"),
    
    NA("な", "ナ"),
    NI("に", "ニ"),
    NU("ぬ", "ヌ"),
    NE("ね", "ネ"),
    NO("の", "ノ"),
    
    HA("は", "ハ"),
    HI("ひ", "ヒ"),
    FU("ふ", "フ"),
    HE("へ", "ヘ"),
    HO("ほ", "ホ"),
    
    MA("ま", "マ"),
    MI("み", "ミ"),
    MU("む", "ム"),
    ME("め", "メ"),
    MO("も", "モ"),
    
    YA("や", "ヤ"),
    YU("ゆ", "ユ"),
    YO("よ", "ヨ"),
    
    RA("ら", "ラ"),
    RI("り", "リ"),
    RU("る", "ル"),
    RE("れ", "レ"),
    RO("ろ", "ロ"),
    
    WA("わ", "ワ"),
    WI("ゐ", "ヰ"),
    WE("ゑ", "ヱ"),
    WO("を", "ヲ"),
    
    N("ん", "ン"),
    
    GA("が", "ガ"),
    GI("ぎ", "ギ"),
    GU("ぐ", "グ"),
    GE("げ", "ゲ"),
    GO("ご", "ゴ"),
    
    ZA("ざ", "ザ"),
    JI("じ", "ジ"),
    ZU("ず", "ズ"),
    ZE("ぜ", "ゼ"),
    ZO("ぞ", "ゾ"),
    
    DA("だ", "ダ"),
    DI("ぢ", "ヂ"),
    DU("づ", "ヅ"),
    DE("で", "デ"),
    DO("ど", "ド"),
    
    BA("ば", "バ"),
    BI("び", "ビ"),
    BU("ぶ", "ブ"),
    BE("べ", "ベ"),
    BO("ぼ", "ボ"),
    
    PA("ぱ", "パ"),
    PI("ぴ", "ピ"),
    PU("ぷ", "プ"),
    PE("ぺ", "ペ"),
    PO("ぽ", "ポ");
    

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
