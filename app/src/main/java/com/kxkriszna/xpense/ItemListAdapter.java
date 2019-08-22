package com.kxkriszna.xpense;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ItemListAdapter extends BaseAdapter {



    Activity mActivity;
    ArrayList<SingleItem> itemList;

    public ItemListAdapter(Activity activity, ArrayList<SingleItem> list){
        mActivity = activity;
        itemList = list;
    }

    static class ViewHolder{
        TextView itemName;
        TextView itemPrice;
        LinearLayout.LayoutParams params;
    }

    public void addItemToAdapter(SingleItem item){
        itemList.add(item);
    }

    public void addListToAdapter(ArrayList<SingleItem> list){
        itemList = list;
    }

    public void removeItemFromAdapter(){
        if(itemList.size()!=0){
            itemList.remove(itemList.size()-1);
            notifyDataSetChanged();
            Toast.makeText(mActivity, "Removed!",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(mActivity, "List is empty!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public String getTotalAmount(){
        double amount = 0;
        for (SingleItem item: itemList){
            amount+=item.getItemPrice();
        }
        return String.valueOf(amount);
    }

    public void writeListToAFile(Context context, String fileName) throws IOException {
        Log.d("Xpense", "Proba zapisu do pliku");
        FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(itemList);
        Log.d("Xpense", "Udane zapisanie do pliku");
        os.close();
        fos.close();
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public SingleItem getItem(int i) {

        SingleItem item = itemList.get(i);

        return item;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.single_item_row,viewGroup ,false);
            final ViewHolder holder = new ViewHolder();
            holder.itemName = (TextView) view.findViewById(R.id.singleItemNameLabel);
            holder.itemPrice = (TextView) view.findViewById(R.id.singleItemPriceLabel);
            holder.params = (LinearLayout.LayoutParams) holder.itemName.getLayoutParams();
            view.setTag(holder);
        }

        final SingleItem item = getItem(i);
        final ViewHolder holder = (ViewHolder) view.getTag();

        String itemName = item.getItemName();
        holder.itemName.setText(itemName);

        String price = String.valueOf(item.getItemPrice());
        holder.itemPrice.setText(price);

        return view;
    }
}
