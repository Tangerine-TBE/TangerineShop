package com.tangerine.ecui.main;

import android.graphics.Color;

import com.tangerine.eccore.EcShopping.Fragment.Bottom.BaseBottomFragment;
import com.tangerine.eccore.EcShopping.Fragment.Bottom.BottomItemFragment;
import com.tangerine.eccore.EcShopping.Fragment.Bottom.BottomTabBean;
import com.tangerine.eccore.EcShopping.Fragment.Bottom.ItemBuilder;
import com.tangerine.ecui.main.index.IndexFragment;

import java.util.LinkedHashMap;

public class EcBottomFragment extends BaseBottomFragment {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemFragment> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean,BottomItemFragment> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-main}", "主页"), new IndexFragment());
        items.put(new BottomTabBean("{fa-feilei}", "分类"), new IndexFragment());
        items.put(new BottomTabBean("{fa-discover}", "发现"), new IndexFragment());
        items.put(new BottomTabBean("{fa-shop}", "购物车"), new IndexFragment());
        items.put(new BottomTabBean("{fa-mine}", "我的"), new IndexFragment());


        return builder.addItems(items).build();
    }

    @Override
    public int setIndexFragment() {
        return 0;
    }

    @Override
    public int setClickColor() {
        return Color.parseColor("#ffff8800");
    }
}
