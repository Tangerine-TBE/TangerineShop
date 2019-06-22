package com.tangerine.ecui.Icon;

public enum  Icon implements com.joanzapata.iconify.Icon {
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
