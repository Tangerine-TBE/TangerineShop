package com.tangerine.eccore.EcShopping.Fragment.Bottom;

import java.util.LinkedHashMap;

public final class ItemBuilder {
    private final LinkedHashMap<BottomTabBean,BottomItemFragment> ITEMS = new LinkedHashMap<>();
    static ItemBuilder builder(){
        return new ItemBuilder();
    }
    public final ItemBuilder addItem(BottomTabBean bean,BottomItemFragment fragment){
        ITEMS.put(bean, fragment);
        return this;

    }
    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean,BottomItemFragment> ITEMS){
        this.ITEMS.putAll(ITEMS);
        return this;
    }
    public final LinkedHashMap<BottomTabBean,BottomItemFragment> build(){
        return ITEMS;
    }
}
