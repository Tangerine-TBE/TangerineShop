package com.tangerine.ecui.Icon;

public enum  Icon implements com.joanzapata.iconify.Icon {
    fa_WeChat('\ue679')
    ;
    char aChar;
    Icon(char aChar){
        this.aChar = aChar;
    }
    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return aChar;
    }
}
