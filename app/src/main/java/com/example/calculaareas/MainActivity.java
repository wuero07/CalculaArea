package com.example.calculaareas;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner figureSpinner;
    EditText inputRadius, inputBase, inputHeight, inputSide;
    TextView resultArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        figureSpinner = findViewById(R.id.figure_spinner);
        inputRadius = findViewById(R.id.input_radio);
        inputBase = findViewById(R.id.input_base);
        inputHeight = findViewById(R.id.input_altura);
        inputSide = findViewById(R.id.input_lado);
        resultArea = findViewById(R.id.result_area);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.figure_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        figureSpinner.setAdapter(adapter);

        figureSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateInputFieldsVisibility(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void updateInputFieldsVisibility(int position) {
        switch (position) {
            case 0: // Círculo
                inputRadius.setVisibility(View.VISIBLE);
                inputBase.setVisibility(View.GONE);
                inputHeight.setVisibility(View.GONE);
                inputSide.setVisibility(View.GONE);
                break;
            case 1: // Triángulo
                inputRadius.setVisibility(View.GONE);
                inputBase.setVisibility(View.VISIBLE);
                inputHeight.setVisibility(View.VISIBLE);
                inputSide.setVisibility(View.GONE);
                break;
            case 2: // Cuadrado
                inputRadius.setVisibility(View.GONE);
                inputBase.setVisibility(View.GONE);
                inputHeight.setVisibility(View.GONE);
                inputSide.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void calculateArea(View view) {
        int position = figureSpinner.getSelectedItemPosition();
        double area = 0;

        switch (position) {
            case 0: // Círculo
                double radius = Double.parseDouble(inputRadius.getText().toString());
                area = Math.PI * Math.pow(radius, 2);
                break;
            case 1: // Triángulo
                double base = Double.parseDouble(inputBase.getText().toString());
                double height = Double.parseDouble(inputHeight.getText().toString());
                area = 0.5 * base * height;
                break;
            case 2: // Cuadrado
                double side = Double.parseDouble(inputSide.getText().toString());
                area = Math.pow(side, 2);
                break;
        }

        resultArea.setText(String.format("Área: %.2f", area));
    }

    public void clearFields(View view) {
        inputRadius.setText("");
        inputBase.setText("");
        inputHeight.setText("");
        inputSide.setText("");
        resultArea.setText("");
    }

    public void exitApp(View view) {
        finish();
    }
}
