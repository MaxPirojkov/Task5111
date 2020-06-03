package com.example.task222;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ItemsDataAdapter extends BaseAdapter {


    private final MyInterface listener;
    private List<ItemData> items;


    private LayoutInflater inflater;




    ItemsDataAdapter(Context context, List<ItemData> items, MyInterface listener) {
        if (items == null) {
            this.items = new ArrayList<>();
        } else {
            this.items = items;
        }
        this.listener = listener;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    void addItem(ItemData item) {
        this.items.add(item);
        notifyDataSetChanged();
    }

    void removeItem(int position) {
        items.remove(position);
        notifyDataSetChanged();

    }

    void addItems(List<ItemData> items){
        this.items.addAll(items);
    }


    @Override
    public int getCount() {
        return items.size();
    }



    @Override
    public ItemData getItem(int position) {
        if (position < items.size()) {
            return items.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_list_view, parent, false);
        }

        ItemData itemData = items.get(position);

        ImageView image = view.findViewById(R.id.icon);
        TextView title = view.findViewById(R.id.title);
        TextView subtitle = view.findViewById(R.id.subtitle);
        final Button button = view.findViewById(R.id.delBtn);

        image.setImageResource(itemData.getImage());
        title.setText(itemData.getTitle());
        subtitle.setText(itemData.getSubtitle());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(position);
                listener.picAd();

            }
        });

        return view;
    }

    public List<ItemData> getItems() {
        return items;
    }


    public interface MyInterface{
        void picAd();
    }


}