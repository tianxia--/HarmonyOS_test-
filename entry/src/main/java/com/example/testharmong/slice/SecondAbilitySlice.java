package com.example.testharmong.slice;

import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.DependentLayout;
import ohos.agp.components.Text;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Color;

public class SecondAbilitySlice extends AbilitySlice {

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        DependentLayout dependentLayout = new DependentLayout(this);

        dependentLayout.setWidth(DependentLayout.LayoutConfig.MATCH_PARENT);
        dependentLayout.setHeight(DependentLayout.LayoutConfig.MATCH_PARENT);


        ShapeElement backGround = new ShapeElement();
        backGround.setRgbColor(new RgbColor(255,255,255));

        dependentLayout.setBackground(backGround);

        Text text = new Text(this);
        text.setText("测试");
        text.setTextColor(Color.BLUE);
        text.setTextSize(100);

        DependentLayout.LayoutConfig textLayoutConfig = new DependentLayout.LayoutConfig();
        textLayoutConfig.width = DependentLayout.LayoutConfig.MATCH_PARENT;
        textLayoutConfig.height = DependentLayout.LayoutConfig.MATCH_CONTENT;
        text.setLayoutConfig(textLayoutConfig);
        dependentLayout.addComponent(text);
        super.setUIContent(dependentLayout);
    }
}
