package com.example.testharmong.provider;

import com.example.testharmong.ResourceTable;
import com.example.testharmong.entry.BaseEntry;
import com.example.testharmong.find.BaseListView;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.*;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Color;
import ohos.agp.utils.TextAlignment;
import ohos.app.Context;

import java.util.ArrayList;
import java.util.List;

public class MainPagerProvider extends PageSliderProvider {

    private List<String> list;
    private Context context;

    public MainPagerProvider(Context context) {
        super();
        this.context = context;
    }

    public MainPagerProvider(List<String> list,Context context){
        this.list = list;
        this.context = context;
    }

    public void setData(List<String> list){
        this.list = list;
    }

    @Override
    public int getCount() {
        return list != null? list.size(): 0;
    }

    @Override
    public Object createPageInContainer(ComponentContainer componentContainer, int i) {
        final String data = list.get(i);
        Component component = LayoutScatter.getInstance(context).parse(ResourceTable.Layout_item_pager,null,true);
        Text label = (Text) component.findComponentById(ResourceTable.Id_item_text);
        BaseListView listView = (BaseListView) component.findComponentById(ResourceTable.Id_listView);

        List<BaseEntry> datas = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            BaseEntry baseEntry = new BaseEntry();
            baseEntry.title = "test_" + i + "_" + j;
            baseEntry.imageUrl = "";
            datas.add(baseEntry);
        }
        listView.setData(datas);
        label.setText(data);
        componentContainer.addComponent(component);
        return component;
    }

    @Override
    public void destroyPageFromContainer(ComponentContainer componentContainer, int i, Object o) {
        componentContainer.removeComponent((Component) o);
    }

    @Override
    public boolean isPageMatchToObject(Component component, Object o) {
        return true;
    }
}
