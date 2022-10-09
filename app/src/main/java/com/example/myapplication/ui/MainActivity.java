package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.ApiClient;
import com.example.myapplication.model.Model;
import com.example.myapplication.model.ProducetModel;
import com.example.myapplication.ui.adapter.Adapter;
import com.example.myapplication.ui.adapter.OnItemClick;
import com.example.myapplication.viewModel.ProduectViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnItemClick {
    Adapter adapter;
    RecyclerView recyclerView;
    EditText searech;
    ProduectViewModel produectViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searech = findViewById(R.id.searchBar_tv);
        ImageView option = findViewById(R.id.iv_option);
        produectViewModel = new ViewModelProvider(this).get(ProduectViewModel.class);
        produectViewModel.getFromRepo();
        produectViewModel.allProducet().observe(this, new Observer<ArrayList<Model>>() {
            @Override
            public void onChanged(ArrayList<Model> models) {
                showData(models);
            }
        });
        produectViewModel.error().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
        //edit text to searech in a list
        searech.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                produectViewModel.getsearechFromRepo(editable.toString());
                produectViewModel.searech().observe(MainActivity.this, new Observer<ArrayList<Model>>() {
                    @Override
                    public void onChanged(ArrayList<Model> models) {
                        showData(models);
                    }
                });
            }
        });
        // dialog to filiter list catagory
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this, R.style.bottomSheet);
                View sheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bootom_sheet, null);
                sheetView.findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        produectViewModel.searechForCatogry("smart");
                        produectViewModel.searechCatogry().observe(MainActivity.this, new Observer<ArrayList<Model>>() {
                            @Override
                            public void onChanged(ArrayList<Model> models) {
                                showData(models);
                            }
                        });
                    }
                });
                sheetView.findViewById(R.id.laptops).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        produectViewModel.searechForCatogry("laptops");
                        produectViewModel.searechCatogry().observe(MainActivity.this, new Observer<ArrayList<Model>>() {
                            @Override
                            public void onChanged(ArrayList<Model> models) {
                                showData(models);
                            }
                        });
                    }
                });
                sheetView.findViewById(R.id.fragrances).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        produectViewModel.searechForCatogry("fragrances");
                        produectViewModel.searechCatogry().observe(MainActivity.this, new Observer<ArrayList<Model>>() {
                            @Override
                            public void onChanged(ArrayList<Model> models) {
                                showData(models);
                            }
                        });
                    }
                });
                sheetView.findViewById(R.id.skincare).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        produectViewModel.searechForCatogry("skincare");
                        produectViewModel.searechCatogry().observe(MainActivity.this, new Observer<ArrayList<Model>>() {
                            @Override
                            public void onChanged(ArrayList<Model> models) {
                                showData(models);
                            }
                        });
                    }
                });
                sheetView.findViewById(R.id.groceries).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        produectViewModel.searechForCatogry("groceries");
                        produectViewModel.searechCatogry().observe(MainActivity.this, new Observer<ArrayList<Model>>() {
                            @Override
                            public void onChanged(ArrayList<Model> models) {
                                showData(models);
                            }
                        });
                    }
                });
                sheetView.findViewById(R.id.decoration).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        produectViewModel.searechForCatogry("decoration");
                        produectViewModel.searechCatogry().observe(MainActivity.this, new Observer<ArrayList<Model>>() {
                            @Override
                            public void onChanged(ArrayList<Model> models) {
                                showData(models);
                            }
                        });
                    }
                });
                bottomSheetDialog.setContentView(sheetView);
                bottomSheetDialog.show();
            }
        });

    }
    // fun to set adapter and recycel view
    private void showData(ArrayList<Model> models) {
        recyclerView = findViewById(R.id.rec);
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        adapter = new Adapter(MainActivity.this);
        adapter.setAdapter(models);
        recyclerView.setAdapter(adapter);
    }
    //send data to prefrenace screen
    @Override
    public void onClick(Model model) {

        Intent intent = new Intent(MainActivity.this, PrefrenceActivity.class);
        intent.putExtra("title", model.getTitle());
        intent.putExtra("price", model.getPrice());
        intent.putExtra("rate", model.getRating());
        intent.putExtra("review", model.getStock());
        intent.putExtra("img", model.getThumbnail());
        intent.putExtra("imgOne", model.getImages().get(0));
        intent.putExtra("imgTwo", model.getImages().get(1));
        intent.putExtra("imgThree", model.getImages().get(2));
        intent.putExtra("des", model.getDescription());
        startActivity(intent);
    }

}