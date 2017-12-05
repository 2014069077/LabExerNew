package com.uy.labexernew;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText inputText;
    Button buttonSave, buttonDisplay, buttonClear;
    TextView tvDisplay;
    FileOutputStream fos;
    FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonDisplay = (Button) findViewById(R.id.btnDisplay);
        buttonSave = (Button) findViewById(R.id.btnSave);
        buttonClear = (Button) findViewById(R.id.btnClear);
        inputText = (EditText) findViewById(R.id.etParagraph);
        tvDisplay = (TextView) findViewById(R.id.tvDisplay);
    }

    public void saveParagraph(View view) {
        String message = inputText.getText().toString();

        try {
            fos = openFileOutput("record.txt", Context.MODE_PRIVATE);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Toast.makeText(this, "Paragraph saved!", Toast.LENGTH_SHORT).show();
    }

    public void displayParagraph(View view) {
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try {
            fis = openFileInput("record.txt");
            while ((read = fis.read()) != -1) {
                buffer.append((char) read);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvDisplay.setText(buffer.toString());
    }

    public void clearParagraph(View view) {
        tvDisplay.setText("");


    }
}