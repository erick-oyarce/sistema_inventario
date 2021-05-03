package com.wakeapp.inventario_offline.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.wakeapp.inventario_offline.R;
import com.wakeapp.inventario_offline.databinding.ActivityRateAppBinding;
import com.wakeapp.inventario_offline.utils.Local;
import com.wakeapp.inventario_offline.controller.Functions.RateFunction;

public class RateAppActivity extends AppCompatActivity {

    private ActivityRateAppBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRateAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }

    public void init(){

        binding.appbar.topAppBar.setTitle(R.string.opinion);
        binding.appbar.topAppBar.setNavigationOnClickListener(v->finish());


        binding.btnEnviar.setOnClickListener(v -> sendRate());
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_menu_popup_item,
                        getResources().getStringArray(R.array.options_rate));

        binding.dropdownOptions.setAdapter(adapter);

        RateFunction.setRateStar(getApplicationContext(), binding);
    }



    public void sendRate(){
        String rate;
        if(Local.getData("admin", getApplicationContext(), "rate").equals("")){
            rate = "0";
        }else{
            rate = Local.getData("admin", getApplicationContext(), "rate");
        }
    }
}