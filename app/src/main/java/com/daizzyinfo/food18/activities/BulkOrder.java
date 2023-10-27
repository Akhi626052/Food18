package com.daizzyinfo.food18.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.daizzyinfo.food18.R;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BulkOrder extends AppCompatActivity {

    EditText     edDatePicker,edTimePicker;
    ImageView imgDatePicker,imgTimePicker;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulk_order);

        imgDatePicker=findViewById(R.id.imgDatePicker);
        edDatePicker=findViewById(R.id.edDatePicker);
        imgTimePicker=findViewById(R.id.imgTimePicker);
        edTimePicker=findViewById(R.id.edTimePicker);


        OpenToolbar();
        intOnClickListener();


    }



    private void intOnClickListener() {

        imgDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                Date newDate = c.getTime();
                DatePickerDialog dialog = new DatePickerDialog(BulkOrder.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String _year = String.valueOf(year);
                        String _month = (month+1) < 10 ? "0" + (month+1) : String.valueOf(month+1);
                        String _date = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                        String _pickedDate =  _date+ "-" + _month + "-" + year;

                        Log.e("PickedDate: ", "Date: " + _pickedDate); //2019-02-12
                        edDatePicker.setText(" Date : " + _pickedDate);
                    }
                },c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.MONTH));
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dialog.show();
            }
        });


        imgTimePicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    Calendar calendar = Calendar.getInstance();
                    int hour = calendar.get(Calendar.HOUR_OF_DAY);
                    int minute = calendar.get(Calendar.MINUTE);

                    TimePickerDialog timePickerDialog = new TimePickerDialog(
                            BulkOrder.this,
                            new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    String selectedTime;
                                    if (hourOfDay == 0) {
                                        selectedTime = "12:" + String.format(Locale.getDefault(), "%02d", minute) + " am";
                                    } else {
                                        String period = (hourOfDay < 12) ? "am" : "pm";
                                        int hourIn12Format = (hourOfDay > 12) ? hourOfDay - 12 : hourOfDay;
                                        selectedTime = "Time : "+hourIn12Format + ":" + String.format(Locale.getDefault(), "%02d", minute) + " " + period;
                                    }
                                    edTimePicker.setText(selectedTime);
                                }
                            }, hour, minute, false);


                    timePickerDialog.show();
                }



    });

}
    private void OpenToolbar() {

        View view = findViewById(R.id.txtBulOrder);

        TextView TxtHeader =view.findViewById(R.id.TxtHeader);
        TxtHeader.setText("Bulk Order");
        ImageView ImgBack=view.findViewById(R.id.ImgBack);
        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });


    }




}