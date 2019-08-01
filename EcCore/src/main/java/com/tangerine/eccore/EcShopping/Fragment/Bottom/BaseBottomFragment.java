package com.tangerine.eccore.EcShopping.Fragment.Bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.tangerine.eccore.EcShopping.Fragment.StartFragment;
import com.tangerine.eccore.R;
import com.tangerine.eccore.R2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

public abstract class BaseBottomFragment  extends StartFragment  implements View.OnClickListener {
    private final ArrayList<BottomItemFragment> ITEM_FRAGMENTS = new ArrayList<>();
    private final ArrayList<BottomTabBean> TAG_BEANS = new ArrayList<>();

    private final LinkedHashMap<BottomTabBean, BottomItemFragment> ITEMS = new LinkedHashMap<>();
    private int mCurrentFragment = 0;
    private int mIndexFragment = 0;
    private int mClickColor = Color.RED;

    public abstract LinkedHashMap<BottomTabBean,BottomItemFragment> setItems(ItemBuilder builder);

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar = null;

    @Override
    public Object setLayout() {
        return R.layout.fragment_bottom;
    }

    public abstract int setIndexFragment();
    @ColorInt
    public abstract int setClickColor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexFragment = setIndexFragment();
        if (setClickColor() != 0){
            mClickColor = setClickColor();
        }
        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean,BottomItemFragment> items = setItems(builder);
        ITEMS.putAll(items);
        for (Map.Entry<BottomTabBean,BottomItemFragment> item: ITEMS.entrySet()){
            final BottomItemFragment value = item.getValue();
            final BottomTabBean key = item.getKey();
            TAG_BEANS.add(key);
            ITEM_FRAGMENTS.add(value);
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final int size = ITEMS.size();
        for (int i = 0 ; i < size ; i ++){
          LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
          final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
          item.setTag(i);
          item.setOnClickListener(this);
          final IconTextView iconTextView = (IconTextView) item.getChildAt(0);
          final AppCompatTextView itemTittle = (AppCompatTextView) item.getChildAt(1);
          final BottomTabBean bean = TAG_BEANS.get(i);
          iconTextView.setText(bean.getICON());
          itemTittle.setText(bean.getTITLE());
          if (i  == mIndexFragment){
              iconTextView.setTextColor(mClickColor);
              itemTittle.setTextColor(mClickColor);
          }
        }
        final SupportFragment[] fragmentArray = ITEM_FRAGMENTS.toArray(new SupportFragment[size]);
        loadMultipleRootFragment(R.id.bottom_bar_fragment_container, mIndexFragment, fragmentArray);
    }
    private void resetColor(){
        final int count = mBottomBar.getChildCount();
        for (int i = 0; i < count ; i ++){
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            itemIcon.setTextColor(Color.GRAY);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemTitle.setTextColor(Color.GRAY);

        }
    }

    @Override
    public void onClick(View view) {
        final int tag = (int) view.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout) view;
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        itemIcon.setTextColor(mClickColor);
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mClickColor);
        showHideFragment(ITEM_FRAGMENTS.get(tag),ITEM_FRAGMENTS.get(mCurrentFragment));
        mCurrentFragment = tag;

    }
}
