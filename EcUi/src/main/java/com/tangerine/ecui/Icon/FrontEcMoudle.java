package com.tangerine.ecui.Icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

public class FrontEcMoudle implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return com.tangerine.ecui.Icon.Icon.values();
    }
}
