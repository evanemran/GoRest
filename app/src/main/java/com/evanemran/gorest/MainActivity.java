package com.evanemran.gorest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText tv_name, tv_email, tv_gender, tv_status;
    Button btn_post;
    Manager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_name = findViewById(R.id.tv_name);
        tv_email = findViewById(R.id.tv_email);
        tv_gender = findViewById(R.id.tv_gender);
        tv_status = findViewById(R.id.tv_status);
        btn_post = findViewById(R.id.btn_post);

        manager = new Manager(this);


        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserResponse model = new UserResponse();
                model.id = 0;
                model.name = tv_name.getText().toString();
                model.email = tv_email.getText().toString();
                model.gender = tv_gender.getText().toString();
                model.status = tv_status.getText().toString();

                manager.postUser(listener, model);
            }
        });

    }
    private final UserResponseListener listener = new UserResponseListener() {
        @Override
        public void didFetch(UserResponse response, String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}