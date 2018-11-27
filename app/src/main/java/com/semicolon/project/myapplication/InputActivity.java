package com.semicolon.project.myapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_activity);

        ImageView pic = findViewById(R.id.image);
        Button save=findViewById(R.id.save);
        Button cancel=findViewById(R.id.cancel);

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
