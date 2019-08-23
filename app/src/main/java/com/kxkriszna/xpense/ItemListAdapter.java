package com.kxkriszna.xpense;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
        TextView itemDate;
        LinearLayout.LayoutParams params;
    }

    public void addItemToAdapter(SingleItem item){
        Log.d("Xpense", "ADDING ITEM");
        itemList.add(0,item);
        notifyDataSetChanged();
    }

    public void addListToAdapter(ArrayList<SingleItem> list){
        itemList = list;
    }

    public void removeItemFromAdapter(){
        if(itemList.size()!=0){
            //itemList.remove(itemList.size()-1);
            itemList.remove(0);
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
            if(item.getProperties()){
                amount-=item.getItemPrice();
            }
            else{
                amount+=item.getItemPrice();
            }

        }

        String numberToString = String.format("%.2f",amount);

        return numberToString;
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

    public String getCurrentDate(){
        String pattern = "dd/MM/yyyy";

        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date according to the chosen pattern
        DateFormat df = new SimpleDateFormat(pattern);

        // Get the today date using Calendar object.
        Date today = Calendar.getInstance().getTime();
        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        String todayAsString = df.format(today);

        // Print the result!
        return todayAsString;
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
            holder.itemDate = (TextView) view.findViewById(R.id.dateLabel);
            holder.params = (LinearLayout.LayoutParams) holder.itemName.getLayoutParams();
            view.setTag(holder);
        }

        final SingleItem item = getItem(i);
        final ViewHolder holder = (ViewHolder) view.getTag();

        String itemName = item.getItemName();
        holder.itemName.setText(itemName);

        //String price = String.valueOf(item.getItemPrice());
        String priceText = String.format("%.2f",item.getItemPrice());

        if(item.getProperties()){
            holder.itemPrice.setTextColor(Color.parseColor("#4CAF50"));
            holder.itemPrice.setText("+"+priceText);
        }
        else {
            holder.itemPrice.setTextColor(Color.parseColor("#e53935"));
            holder.itemPrice.setText("-"+priceText);
        }



        String dateCreated = item.getDate();
        holder.itemDate.setText(dateCreated);

        return view;
    }
}
