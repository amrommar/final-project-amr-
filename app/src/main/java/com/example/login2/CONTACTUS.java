package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class CONTACTUS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_o_n_t_a_c_t_u_s);
        TextView textView=findViewById(R.id.textView222);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}