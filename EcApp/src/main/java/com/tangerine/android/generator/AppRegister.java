package com.tangerine.android.generator;

import com.tangerine.ecannotations.AppRegisterGenerator;
import com.tangerine.eccore.EcShopping.wechat.templates.AppRegisterTemplate;

@AppRegisterGenerator(packageName = "com.tangerine.android",
        registerTemplate = AppRegisterTemplate.class)
public interface AppRegister {

}
