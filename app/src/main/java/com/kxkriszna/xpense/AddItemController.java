package com.kxkriszna.xpense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AddItemController extends AppCompatActivity {

    EditText mItemNameInput;
    EditText mItemPriceInput;
    boolean positive = false;
    ImageButton mPositiveButton;
    ImageButton mNegativeButton;
    Button mAddButton;

    ImageButton type1;
    ImageButton type2;
    ImageButton type3;
    ImageButton type4;
    TextView label;

    private int type = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_item_layout);


        mItemNameInput = (EditText) findViewById(R.id.itemNameInputTMP);
        mItemPriceInput = (EditText) findViewById(R.id.itemPriceInputTMP);
        mPositiveButton = (ImageButton) findViewById(R.id.positiveButton);
        mNegativeButton = (ImageButton) findViewById(R.id.negativeButton);
        mAddButton = (Button) findViewById(R.id.addItemButtonTMP);

        type1 = (ImageButton) findViewById(R.id.type1Button);
        type2 = (ImageButton) findViewById(R.id.type2Button);
        type3 = (ImageButton) findViewById(R.id.type3Button);
        type4 = (ImageButton) findViewById(R.id.type4Button);

        label = (TextView) findViewById(R.id.typeLabel);


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
                    myIntent.putExtra("Type",type);

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

        type1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 1;
                label.setText("Food");
            }
        });

        type2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 2;
                label.setText("Social life");
            }
        });

        type3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 3;
                label.setText("Return");
            }
        });

        type4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 4;
                label.setText("Special");
            }
        });


    }

}
