package com.example.testharmong.provider;

import com.example.testharmong.ResourceTable;
import com.example.testharmong.entry.BaseEntry;
import ohos.agp.components.*;
import ohos.app.Context;

import java.util.List;
import java.util.Optional;

public class MainListProvider extends BaseItemProvider {
    private List<BaseEntry> newsInfoList;
    private Context context;

    public MainListProvider(List<BaseEntry> listBasicInfo, Context context) {
        this.newsInfoList = listBasicInfo;
        this.context = context;
    }

    @Override
    public int getCount() {
        return newsInfoList == null ? 0 : newsInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return Optional.of(this.newsInfoList.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Component getComponent(int position, Component componentP, ComponentContainer componentContainer) {
        ViewHolder viewHolder = null;
        Component component = componentP;
        if (component == null) {
            component = LayoutScatter.getInstance(context).parse(ResourceTable.Layout_item_list, null, false);
            viewHolder = new ViewHolder();
            Component componentTitle = component.findComponentById(ResourceTable.Id_item_news_title);
            Component componentImage = component.findComponentById(ResourceTable.Id_item_news_image);
            if (componentTitle instanceof Text) {
                viewHolder.title = (Text) componentTitle;
            }
            if (componentImage instanceof Image) {
                viewHolder.image = (Image) componentImage;
            }
            component.setTag(viewHolder);
        } else {
            if (component.getTag() instanceof ViewHolder) {
                viewHolder = (ViewHolder) component.getTag();
            }
        }
        if (viewHolder != null) {
            viewHolder.title.setText(newsInfoList.get(position).title);
            viewHolder.image.setScaleMode(Image.ScaleMode.STRETCH);
        }
        return component;
    }

    private static class ViewHolder {
        Text title;
        Image image;
    }
}