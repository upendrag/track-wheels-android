package com.trackwheels.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.trackwheels.models.ChannelParentModel;

import java.util.List;

/**
 * Created by shruthi on 26/3/16.
 */
public class ChannelAdapter extends BaseAdapter {
    Context ctx;
    List<ChannelParentModel> channelData;

    public ChannelAdapter(Context ctx, List<ChannelParentModel> data) {
        this.channelData = data;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return channelData.size();
    }

    @Override
    public Object getItem(int i) {
        return channelData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        return null;
    }
}
