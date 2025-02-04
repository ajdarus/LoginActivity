package me.arsnotfound.myfirstproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import me.arsnotfound.myfirstproject.data.Credentials;
import me.arsnotfound.myfirstproject.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent inputIntent = getIntent();
        if (
                inputIntent != null &&
                        inputIntent.hasExtra(LoginActivityContract.INPUT_STRING_KEY)
        ) {
            Log.i(TAG, "Got intent: " + inputIntent.getStringExtra(LoginActivityContract.INPUT_STRING_KEY));
        }

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.loginButton.setOnClickListener(view -> {
            setResult(LoginActivity.RESULT_OK,
                    new Intent().putExtra(LoginActivityContract.OUTPUT_STRING_KEY,
                                    new Credentials(
                                            binding.usernameEt.getText().toString(),
                                            binding.passwordEt.getText().toString()

                                    )
                            ));
            System.out.println(binding.usernameEt);
            System.out.println(binding.passwordEt);
            CharSequence text = "Успешный Вход";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this /* MyActivity */, text, duration);
            toast.show();
            finish();
        });
    }

    public static class LoginActivityContract extends ActivityResultContract<String, Credentials> {
        final static String INPUT_STRING_KEY = "some_string";
        final static String OUTPUT_STRING_KEY = "credentials";

        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, String input) {
            Intent intent = new Intent(context, LoginActivity.class);
            intent.putExtra(INPUT_STRING_KEY, input);
            return intent;
        }

        @Override
        public Credentials parseResult(int resultCode, @Nullable Intent intent) {
            if (resultCode != LoginActivity.RESULT_OK || intent == null) {
                return null;
            }

            return intent.getParcelableExtra(OUTPUT_STRING_KEY);
        }


    }
}
