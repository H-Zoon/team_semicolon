package com.semicolon.project.myapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_activity);

        Button select = findViewById(R.id.select_date);
        ImageView pic = findViewById(R.id.image);
        Button save=findViewById(R.id.save);
        Button cancel=findViewById(R.id.cancel);

        select.setOnClickListener(this);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

        @Override

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            Toast.makeText(getApplicationContext(), year + "년" + monthOfYear + "월" + dayOfMonth + "일", Toast.LENGTH_SHORT).show();

        }

    };

    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.select_date:
                new DatePickerDialog(this, listener, 2018, 11, 04).show();
                Toast.makeText(this, "Floating Action Button", Toast.LENGTH_SHORT).show();
                break;
            case R.id.save:
                finish();
                break;
            case R.id.cancel:
                finish();
                break;
        }

    }

}
