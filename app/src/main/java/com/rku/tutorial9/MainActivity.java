package com.rku.tutorial9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    //Declaration Part start here...
    EditText Notes;
    Button SaveNotes;
    Button ReadNotes;
    Button ReadAssets;
    TextView txtHeading;
    TextView txtData;

    //Declaration Part finished here...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Variables Initialization start here....

        Notes=findViewById(R.id.edtNotes);
        SaveNotes=findViewById(R.id.btnSaveNotes);
        ReadNotes=findViewById(R.id.btnReadNotes);
        ReadAssets=findViewById(R.id.btnReadAssets);
        txtHeading=findViewById(R.id.txtHeading);
        txtData=findViewById(R.id.txtData);

        //Variables Initialization finished here....

        //TextView Data Scroll View method...
        txtData.setMovementMethod(new ScrollingMovementMethod());
    }
    public void SaveNotes(View view)
    {
        if(Notes.getText().toString().isEmpty())
        {
            Notes.setError("Please Enter any notes");
            Notes.requestFocus();
            return;
        }

        String Data=Notes.getText().toString();

        try {
            FileOutputStream Fout=openFileOutput("My_Notes.txt", Context.MODE_PRIVATE);
            Fout.write(Data.getBytes());
            Fout.close();
            Toast.makeText(this, "Successfully Saved Notes", Toast.LENGTH_SHORT).show();
            Notes.setText("");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



    }
    public void ReadNotes(View view)
    {
            try {
                FileInputStream Fin =openFileInput("My_Notes.txt");
                int c;
                String Data="";
                while( (c=Fin.read() )!=-1)
                {
                    Data+=Character.toString((char) c);
                }
                txtHeading.setText("Your Notes :");
                txtData.setText(Data);
                Fin.close();
            }
            catch (Exception e)
            {
                Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
    }

    public void readAssets(View view)
    {
       //get the json file from assets folder

        try {


            InputStream is = getAssets().open("data.json");

            //Reading json file.
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");
            txtHeading.setText("Assets JSON Data");
            txtData.setText(json);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}