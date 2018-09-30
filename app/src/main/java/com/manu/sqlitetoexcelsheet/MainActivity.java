package com.manu.sqlitetoexcelsheet;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Button sqliteToExcelButton;
    private Button readExcelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqliteToExcelButton =  findViewById(R.id.SqliteToExcel);
        sqliteToExcelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SqliteActivity.class);
                startActivity(intent);
            }
        });


        readExcelButton =  findViewById(R.id.readExcelButton);
        readExcelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doIntent();
            }
        });


    }

    private void doIntent(){
        File file = new File(Environment.getExternalStorageDirectory()
                + "myData.xls");
        Uri path = Uri.fromFile(file );
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path , "application/vnd.ms-excel");
        if (file .exists())
        {
            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            try
            {
                startActivity(pdfIntent ); }
            catch (ActivityNotFoundException e)
            {
                Toast.makeText(MainActivity.this,"Please install MS-Excel app to view the file.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

}

