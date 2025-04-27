package com.example.lutemon;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
public class AddLutemonActivity extends AppCompatActivity {
    private EditText textInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_lutemon);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void addLutemon(View view){
        RadioGroup rgLutemonType = findViewById(R.id.lutemonType);
        textInput = findViewById(R.id.lutemonName);
        String nameInput = String.valueOf(textInput.getText());

        Storage storage = Storage.getInstance();
        int checkedRadioButtonId = rgLutemonType.getCheckedRadioButtonId();

        if (checkedRadioButtonId == R.id.fireButton) {
            storage.addLutemon(new Lutemon(nameInput, LutemonType.FIRE, 10));
        } else if (checkedRadioButtonId == R.id.waterButton) {
            storage.addLutemon(new Lutemon(nameInput, LutemonType.WATER, 10));
        } else if (checkedRadioButtonId == R.id.grassButton) {
            storage.addLutemon(new Lutemon(nameInput, LutemonType.GRASS, 10));
        } else if (checkedRadioButtonId == R.id.electricButton) {
            storage.addLutemon(new Lutemon(nameInput, LutemonType.ELECTRIC, 10));
        } else if (checkedRadioButtonId == R.id.ghostButton) {
            storage.addLutemon(new Lutemon(nameInput, LutemonType.GHOST, 10));
        } else {
            System.out.println("error: cant add lutemon without type.");
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


}