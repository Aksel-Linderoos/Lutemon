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

        if (rgLutemonType.getCheckedRadioButtonId() == R.id.fireButton){
           Lutemon lutemon = new Lutemon(nameInput, LutemonType.FIRE);
            Storage.getInstance().addLutemon(lutemon);

        }
        else if (rgLutemonType.getCheckedRadioButtonId() == R.id.waterButton){
            Lutemon lutemon = new Lutemon(nameInput, LutemonType.WATER);
            Storage.getInstance().addLutemon(lutemon);

        }
        else if (rgLutemonType.getCheckedRadioButtonId() == R.id.grassButton){
            Lutemon lutemon = new Lutemon(nameInput, LutemonType.GRASS);
            Storage.getInstance().addLutemon(lutemon);

        }
        else if (rgLutemonType.getCheckedRadioButtonId() == R.id.electricButton){
            Lutemon lutemon = new Lutemon(nameInput, LutemonType.ELECTRIC);
            Storage.getInstance().addLutemon(lutemon);

        }
        else if (rgLutemonType.getCheckedRadioButtonId() == R.id.ghostButton){
            Lutemon lutemon = new Lutemon(nameInput, LutemonType.GHOST);
            Storage.getInstance().addLutemon(lutemon);

        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


}