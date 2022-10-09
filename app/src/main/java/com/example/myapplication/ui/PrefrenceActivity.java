package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

public class PrefrenceActivity extends AppCompatActivity {
    TextView tx_count;
    String imgOne,imgTwo,imgThree;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefrence);
        TextView tx_title = findViewById(R.id.tx_title);
        TextView tx_price = findViewById(R.id.tx_price);
        TextView tx_rate = findViewById(R.id.tx_rate);
        TextView tx_review = findViewById(R.id.tx_review);
        TextView tx_des = findViewById(R.id.dicrption);
        tx_count = findViewById(R.id.tx_count);
         imageView= findViewById(R.id.imageView);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String name = bundle.getString("title");
            String price = bundle.getString("price");
            String rate = bundle.getString("rate");
            String review = bundle.getString("review");
            String img = bundle.getString("img");
            String des = bundle.getString("des");
            imgOne=bundle.getString("imgOne");
            imgTwo=bundle.getString("imgTwo");
            imgThree=bundle.getString("imgThree");
            tx_title.setText(name);
            tx_price.setText("$ " + price);
            tx_rate.setText(rate);
            tx_review.setText("(" + review + " reviews)");
            tx_des.setText(des);
            Picasso.get().load(img).into(imageView);
        }
    }

    public void action(View view) {
        int id = view.getId();
        if (id == R.id.btn_add) {
            Toast.makeText(PrefrenceActivity.this, "item add to cart", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.add) {
            int num= Integer.parseInt(tx_count.getText().toString());
            tx_count.setText((num+1)+"");
        } else if (id == R.id.remove) {
            int num= Integer.parseInt(tx_count.getText().toString());
            tx_count.setText((num-1)+"");
        }
    }

    public void chageImage(View view) {
        int id=view.getId();
        if (id==R.id.imageView3){
            Picasso.get().load(imgOne).into(imageView);
        }
        else if(id==R.id.imageView4){
            Picasso.get().load(imgTwo).into(imageView);
        }
        else {
            Picasso.get().load(imgThree).into(imageView);
        }
    }
}