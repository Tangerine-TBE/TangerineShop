package com.tangerine.android.generator;


import com.tangerine.ecannotations.PayEntryGenerator;
import com.tangerine.eccore.EcShopping.wechat.templates.WXPayEntryTemplate;

@PayEntryGenerator(
        packageName = "com.tangerine.android",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
    
}
