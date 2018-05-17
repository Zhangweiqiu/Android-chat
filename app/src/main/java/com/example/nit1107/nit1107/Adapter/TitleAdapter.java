package com.example.nit1107.nit1107.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nit1107.nit1107.R;
import com.example.nit1107.nit1107.model.Title;

import java.util.List;

/**
 * Created by qiuzhangwi on 2018/5/17.
 */

public class TitleAdapter extends ArrayAdapter<Title> {

    private int resourceId;

    public TitleAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Title> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Title title = getItem(position);
        View view;
        ViewHolder viewHolder;
        /**
         * 缓存布局和实例，优化 listView
         */
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.titleText = view.findViewById(R.id.title_text);
            viewHolder.titlePic =  view.findViewById(R.id.title_pics);
            viewHolder.titleDescr = view.findViewById(R.id.descr_text);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        Glide.with(getContext()).load(title.getImageUrl()).into(viewHolder.titlePic);
        viewHolder.titleText.setText(title.getTitle());
        viewHolder.titleDescr.setText(title.getDescr());

        return view;

    }

    public class ViewHolder{
        TextView titleText;
        TextView titleDescr;
        ImageView titlePic;
    }

}
