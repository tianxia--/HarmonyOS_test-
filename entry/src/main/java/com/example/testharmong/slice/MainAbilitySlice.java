package com.example.testharmong.slice;

import com.example.testharmong.ResourceTable;
import com.example.testharmong.entry.BaseEntry;
import com.example.testharmong.find.BaseListProvider;
import com.example.testharmong.provider.MainListProvider;
import com.example.testharmong.provider.MainPagerProvider;

import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.bundle.AbilityInfo;
import ohos.global.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * MainAbilitySlice
 */
public class MainAbilitySlice extends AbilitySlice implements TabList.TabSelectedListener {
    private TabList tabList;
    private PageSlider pageSlider;
    private Text text;

    private ListContainer listContainerTemp;
    @Override
    public void onStart(Intent intent) {

        super.onStart(intent);
        int orientation = getResourceManager().getConfiguration().direction;
        if (orientation == Configuration.DIRECTION_HORIZONTAL) {
            super.setUIContent(ResourceTable.Layout_ability_main_landscape);
        } else {
            super.setUIContent(ResourceTable.Layout_ability_main);

        }

        initView();
    }

    @Override
    protected void onOrientationChanged(AbilityInfo.DisplayOrientation displayOrientation) {
        if (displayOrientation == AbilityInfo.DisplayOrientation.LANDSCAPE) {
            setUIContent(ResourceTable.Layout_ability_main_landscape);
        } else if (displayOrientation == AbilityInfo.DisplayOrientation.PORTRAIT) {
            setUIContent(ResourceTable.Layout_ability_main);

        }
        initView();
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    private void initView(){
        text = (Text) findComponentById(ResourceTable.Id_tab_content);
        tabList = (TabList) findComponentById(ResourceTable.Id_main_page_tablist);
        pageSlider = (PageSlider) findComponentById(ResourceTable.Id_pageslider);
        initTab();
        initPagerProvider();

    }


    private void initTab(){
        if(tabList.getTabCount() == 0){
            tabList.addTab(createTab(ResourceTable.String_find));
            tabList.addTab(createTab(ResourceTable.String_info));
            tabList.addTab(createTab(ResourceTable.String_chat));
            tabList.addTab(createTab(ResourceTable.String_my));

            tabList.setFixedMode(true);
            tabList.getTabAt(0).select();

            text.setText(tabList.getTabAt(0).getText());
            pageSlider.setCurrentPage(0);
        }

        tabList.addTabSelectedListener(this);
    }

    private TabList.Tab createTab(int title){
        TabList.Tab tab = tabList.new Tab(this);
        tab.setText(title);
        tab.setMinWidth(64);
        tab.setPadding(12,0,12,0);
        return tab;
    }

    @Override
    public void onSelected(TabList.Tab tab) {
        text.setText(tab.getText());
        pageSlider.setCurrentPage(tab.getPosition());
    }

    @Override
    public void onUnselected(TabList.Tab tab) {

    }

    @Override
    public void onReselected(TabList.Tab tab) {

    }

    private void initPagerProvider(){
        MainPagerProvider pagerProvider = new MainPagerProvider(this);
        pageSlider.setProvider(pagerProvider);

        List<String> list = new ArrayList<>(4);
        list.add(getString(ResourceTable.String_find));
        list.add(getString(ResourceTable.String_info));
        list.add(getString(ResourceTable.String_chat));
        list.add(getString(ResourceTable.String_my));
        pagerProvider.setData(list);
        pagerProvider.notifyDataChanged();

        pageSlider.addPageChangedListener(new PageSlider.PageChangedListener() {
            @Override
            public void onPageSliding(int i, float v, int i1) {

            }

            @Override
            public void onPageSlideStateChanged(int i) {

            }

            @Override
            public void onPageChosen(int i) {
                tabList.getTabAt(i).select();
            }
        });
    }
}
