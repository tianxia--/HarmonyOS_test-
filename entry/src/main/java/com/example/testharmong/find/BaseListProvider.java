package com.example.testharmong.find;

import com.example.testharmong.ResourceTable;
import com.example.testharmong.entry.BaseEntry;
import ohos.agp.components.*;
import ohos.app.Context;

import java.util.List;
import java.util.Optional;

public class BaseListProvider extends BaseItemProvider {

    private Context context;
    public List<BaseEntry> datas;
    public BaseListProvider(Context context) {
        super();
        this.context = context;
    }

    public void setDatas(List<BaseEntry> datas){
        this.datas = datas;
    }

    @Override
    public int getCount() {
        System.out.println("创建了新的item:" + datas);
        int siez = datas != null ? datas.size(): 0;
        System.out.println("创建了新的item:" + siez);
        return siez;
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Component getComponent(int i, Component component, ComponentContainer componentContainer) {
        Component c = component;
        ViewHoloder holoder;
        if(c == null){
            c = LayoutScatter.getInstance(context).parse(ResourceTable.Layout_item_list,null,false);

            holoder = new ViewHoloder(c);
            c.setTag(holoder);
        }else{
            holoder = (ViewHoloder) component.getTag();
        }

        BaseEntry entry = datas.get(i);
        holoder.title.setText(entry.title);
        componentContainer.addComponent(c);
        System.out.println("创建了新的item");
        return c;

    }

    class ViewHoloder{
        Component component;
        Text title;
        Image image;

        public ViewHoloder(Component component){
            this.component = component;
            title = (Text) component.findComponentById(ResourceTable.Id_item_news_title);
            image = (Image) component.findComponentById(ResourceTable.Id_item_news_image);
        }
    }
}
