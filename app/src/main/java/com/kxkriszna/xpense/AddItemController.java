package com.kxkriszna.xpense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddItemController extends AppCompatActivity {

    EditText mItemNameInput;
    EditText mItemPriceInput;
    boolean positive = false;
    ImageButton mPositiveButton;
    ImageButton mNegativeButton;
    Button mAddButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_item_layout);


        mItemNameInput = (EditText) findViewById(R.id.itemNameInputTMP);
        mItemPriceInput = (EditText) findViewById(R.id.itemPriceInputTMP);
        mPositiveButton = (ImageButton) findViewById(R.id.positiveButton);
        mNegativeButton = (ImageButton) findViewById(R.id.negativeButton);
        mAddButton = (Button) findViewById(R.id.addItemButtonTMP);



        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    String itemName = mItemNameInput.getText().toString();
                    String itemPrice = mItemPriceInput.getText().toString();

                    Intent myIntent = new Intent(AddItemController.this, MainActivity.class);

                    myIntent.putExtra("Name",itemName);
                    myIntent.putExtra("Price",itemPrice);
                    myIntent.putExtra("Prop",positive);

                    startActivity(myIntent);



                }catch (NumberFormatException e){

                }
            }
        });

        mPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(positive){

                }else{
                    positive = true;
                    mPositiveButton.setImageResource(R.drawable.add_clicked);
                    mNegativeButton.setImageResource(R.drawable.remove_unclicked);
                }
            }
        });

        mNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!positive){

                }else{
                    positive = false;
                    mPositiveButton.setImageResource(R.drawable.add_unclicked);
                    mNegativeButton.setImageResource(R.drawable.remove_clicked);
                }
            }
        });


    }

}
