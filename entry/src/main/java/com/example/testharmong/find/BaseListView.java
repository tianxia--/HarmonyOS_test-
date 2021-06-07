package com.example.testharmong.find;

import com.example.testharmong.ResourceTable;
import com.example.testharmong.entry.BaseEntry;
import com.example.testharmong.provider.MainListProvider;
import ohos.agp.components.*;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BaseListView extends DependentLayout {
    private ListContainer listContainer;

    private Text tempText;
    public BaseListView(Context context) {
        super(context);
        initView();
    }

    public BaseListView(Context context, AttrSet attrSet) {
        super(context, attrSet);
        initView();
    }

    public BaseListView(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);
        initView();
    }

    private void initView(){
        LayoutScatter.getInstance(getContext()).parse(ResourceTable.Layout_view_find,this,true);
        listContainer = (ListContainer) findComponentById(ResourceTable.Id_view_list);
        tempText = (Text) findComponentById(ResourceTable.Id_temp_text);

    }

    public void setData(List<BaseEntry> datas){
        MainListProvider provider;
        if(listContainer.getItemProvider() != null){
            System.out.println("自定义listView设置了数据:" + datas.size());
            provider = (MainListProvider) listContainer.getItemProvider();
            provider.notifyDataChanged();
            listContainer.setItemProvider(provider);
        }else{
           provider = new MainListProvider(datas, getContext());
            provider.notifyDataChanged();
            listContainer.setItemProvider(provider);
        }

    }
}
