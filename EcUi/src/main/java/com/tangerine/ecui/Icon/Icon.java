package com.tangerine.ecui.Icon;

public enum  Icon implements com.joanzapata.iconify.Icon {
    fa_weChat('\ue620'),
    fa_shop('\ue60d'),
    fa_feilei('\ue60e'),
    fa_discover('\ue607'),
    fa_mine('\ue62a'),
    fa_main('\ue64b'),
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
