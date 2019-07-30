package com.tangerine.android.generator;


import com.tangerine.ecannotations.EntryGenerator;
import com.tangerine.eccore.EcShopping.wechat.templates.WXEntryTemplate;

@EntryGenerator(
        packageName = "com.tangerine.android",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {

}
