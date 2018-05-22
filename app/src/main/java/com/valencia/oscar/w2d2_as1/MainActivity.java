package com.valencia.oscar.w2d2_as1;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final String TEXT_VALUE = "TEXT_VALUE";
    EditText editText;
    static TextView textView;
    static Handler handler = new Handler(){
            public void handleMessage(Message message){
                String value = message.getData().getString(TEXT_VALUE);
                textView.append(value+"\n");
            }

    };
    static int increasedNumber = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initElements();
    }

    public void triggerThreads(View view) {
        textView.setText("");
        increasedNumber = 0;
        if(editText.getText().toString().length()>0){
            MyThread myThread1 = new MyThread(Integer.parseInt(editText.getText().toString()),"thread1",Thread.currentThread());
            MyThread myThread2 = new MyThread(Integer.parseInt(editText.getText().toString()),"thread2",Thread.currentThread());

            myThread1.start();
            myThread2.start();

        }else{
            Toast.makeText(this,"target field cannot be empty",Toast.LENGTH_SHORT).show();
        }
    }

    public void initElements(){
        editText = findViewById(R.id.targetText);
        textView = findViewById(R.id.result);
    }
}
