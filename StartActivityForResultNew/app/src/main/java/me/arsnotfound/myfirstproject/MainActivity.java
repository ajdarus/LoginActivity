package me.arsnotfound.myfirstproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import me.arsnotfound.myfirstproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private final ActivityResultLauncher<String> loginActivityLauncher = registerForActivityResult(new LoginActivity.LoginActivityContract(), creds ->
            Log.i(TAG, "Got credentials from login: " + creds.getUsername() + " " + creds.getPassword()));




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.continueButton.setVisibility(View.GONE);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.startLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginActivityLauncher.launch("Test string pls ignore");
                binding.startLoginActivity.setVisibility(View.GONE);
                binding.continueButton.setVisibility(View.VISIBLE);

            }
        });
        binding.continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), NewActivity.class);
                view.getContext().startActivity(intent);}
        });




    }
}
