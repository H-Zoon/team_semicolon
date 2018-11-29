package com.semicolon.project.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class InputActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_activity);

        ImageView pic = findViewById(R.id.image);
        Button save=findViewById(R.id.save);
        Button cancel=findViewById(R.id.cancel);
        Spinner spinner = (Spinner)findViewById(R.id.spinner);// 카테고리

        save.setOnClickListener(this);
        cancel.setOnClickListener(this);

        EditText name=findViewById(R.id.name);

        //인텐트로 쓸거
        String intent_name = "";
        String intent_value = "";

        //인텐트
        Intent intent = getIntent();

        intent_name=intent.getStringExtra("Name");
        intent_value=intent.getStringExtra("Value");

        if(intent_name!=null) {
            name.setText(intent_name);
        }

        //input array data
        final ArrayList<String> list = new ArrayList<>();
        list.add("유제품");
        list.add("즉석식품");
        list.add("가공식품");
        list.add("제과, 제빵");
        list.add("과체류");
        list.add("육류");
        list.add("해산물");
        list.add("소스류");
        list.add("기타");

        //using ArrayAdapter
        ArrayAdapter spinnerAdapter;
        spinnerAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(spinnerAdapter);

        if(intent_value!=null){
            int int_v = Integer.parseInt(intent_value);
            spinner.setSelection(int_v);

        }

        //event listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(InputActivity.this,"선택된 아이템 : "+spinner.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
            case R.id.save:
                DBManager db=new DBManager(this);

                //이름입력
                EditText name=findViewById(R.id.name);
                String Name=name.getText().toString();

                //메모입력
                EditText memo=findViewById(R.id.edit_memo);
                String Memo=memo.getText().toString();

                //날짜입력
                DatePicker datePicker=findViewById(R.id.datePicker);
                String Date=onDateChange(datePicker,datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth());

                if(Name.length()!=0){
                   db.addData(Name,Memo,Date);
                   finish();
                }else{
                    Toast.makeText(InputActivity.this,"값을 입력해주세요.",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.cancel:
                finish();
                break;
        }
    }

    public String onDateChange(DatePicker view,int year,int monthOfYear,int dayOfMonth){
        String msg=String.format("%d/%d/%d",year,monthOfYear+1,dayOfMonth);
        return msg;
    }
}
