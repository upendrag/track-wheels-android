package com.trackwheels.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.trackwheels.R;
import com.trackwheels.entities.Channel;

/**
 * Created by shruthi on 26/3/16.
 */
public class ChannelAdapter extends BaseAdapter {
    Context ctx;
    Channel[] channelData;
    public static Channel[] ITEMS;
    public ChannelAdapter(Context ctx, Channel[] data) {
        this.channelData = data;
        this.ctx = ctx;
        ITEMS = data;
    }

    @Override
    public int getCount() {
        return channelData.length;
    }

    @Override
    public Object getItem(int i) {
        return channelData[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.channel_item_leg, null, false);
        TextView label = (TextView) rowView.findViewById(R.id.label);
        TextView subLabel = (TextView) rowView.findViewById(R.id.sublabel);
        ImageView subscribe = (ImageView) rowView.findViewById(R.id.subscribe);
        Channel c = channelData[i];
        label.setText(c.getName());
        subLabel.setText("UTD Comet Cab");


        return rowView;

    }
}
