package com.wakeapp.inventario_offline.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.wakeapp.inventario_offline.R;
import com.wakeapp.inventario_offline.databinding.ActivityAddActiveBinding;

public class AddActiveActivity extends AppCompatActivity {

    private ActivityAddActiveBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddActiveBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_menu_popup_item,
                        getResources().getStringArray(R.array.options_active));

        binding.dropdownOptions.setAdapter(adapter);
    }
}