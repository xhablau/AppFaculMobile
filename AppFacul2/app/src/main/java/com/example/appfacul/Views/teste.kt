package com.example.appfacul.Views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appfacul.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class teste extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
        String dataFormatada = sdf.format(hora);

    }

}
