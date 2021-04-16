package com.wakeapp.inventario_offline.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.wakeapp.inventario_offline.R;
import com.wakeapp.inventario_offline.databinding.ActivityConfigurationBinding;
import com.wakeapp.inventario_offline.databinding.ActivityRateAppBinding;
import com.wakeapp.inventario_offline.utils.Functions;
import com.wakeapp.inventario_offline.utils.Local;

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
        setRateStar();

        binding.btnEnviar.setOnClickListener(v -> sendRate());
    }

    public void setRateStar(){
        binding.rateStar1.setOnClickListener(v->Functions.changueStarRate(getApplicationContext(), 1, binding));
        binding.rateStar2.setOnClickListener(v->Functions.changueStarRate(getApplicationContext(), 2, binding));
        binding.rateStar3.setOnClickListener(v->Functions.changueStarRate(getApplicationContext(), 3, binding));
        binding.rateStar4.setOnClickListener(v->Functions.changueStarRate(getApplicationContext(), 4, binding));
        binding.rateStar5.setOnClickListener(v->Functions.changueStarRate(getApplicationContext(), 5, binding));
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