package com.example.onmeasure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
String a="k";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zdViewGroup zdViewGroup=(com.example.onmeasure.zdViewGroup)findViewById(R.id.viewGroup);
        Button button=new Button(this);
        button.setText("哈哈哈哈哈");
        button.setOnClickListener(MainActivity.this);
        zdViewGroup.addView(button);
        for(int i=0;i<25;i++){
            a=a+"b";
            Button button1=new Button(this);
            button1.setText(a);
            button1.setBackground(getResources().getDrawable(R.drawable.shape));

            zdViewGroup.addView(button1);
        }
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            
        }

    }
}