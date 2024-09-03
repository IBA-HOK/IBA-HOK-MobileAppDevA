package com.example.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;

    @Override
    protected void onStart() {
        super.onStart();
        prefDataStore.getString("name")
                .ifPresent(name -> binding.text.setText(name));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prefDataStore = PrefDataStore.getInstance(this);
        binding.imageView2.setImageResource(R.drawable.baseline_lte_plus_mobiledata_24);//"@drawable/baseline_2k_24"
        binding.button.setText("test button");
        binding.button.setHighlightColor(100);
        binding.button2.setText("test button");
//        binding.button3.setText("apply");
        binding.button2.setHighlightColor(100);
        binding.button.setOnClickListener(view -> {
            binding.text.setText(R.string.name);
            binding.imageView2.setImageResource(R.drawable.baseline_2k_24);//"@drawable/baseline_2k_24"
        });
        binding.button2.setOnClickListener(view -> {
            binding.imageView2.setImageResource(R.drawable.baseline_lte_plus_mobiledata_24);//"@drawable/baseline_2k_24"
            binding.text.setText(R.string.app_str2);
        });
        binding.saveButton.setOnClickListener(view -> {
            var text = binding.editTextText.getText().toString();
            prefDataStore.setString("name", text);
        });

//        binding.button3.setOnClickListener(view -> {
//            String input=binding.editTextText.getText().toString();
//            binding.text.setText(input);
//        });
        binding.editTextText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = binding.editTextText.getText().toString() + binding.editTextText2.getText().toString();
                binding.text.setText(input);
            }
        });
        binding.editTextText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = binding.editTextText.getText().toString() + binding.editTextText2.getText().toString();
                binding.text.setText(input);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;
        });
    }
}