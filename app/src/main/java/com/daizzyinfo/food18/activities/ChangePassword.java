package com.daizzyinfo.food18.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.databinding.ActivityChangePasswordBinding;

public class ChangePassword extends AppCompatActivity {
//    EditText edOldPassword,edNewPassword,edConfirmPassword;
//    AppCompatButton btn_Submit;
    ActivityChangePasswordBinding binding;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_change_password);
//        edOldPassword=findViewById(R.id.edOldPassword);
//        edNewPassword=findViewById(R.id.edNewPassword);
//        edConfirmPassword=findViewById(R.id.edConfirmPassword);
//        btn_Submit=findViewById(R.id.btn_Submit);

        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initOnClickListener();
        openToolBar();

    }


    protected void initOnClickListener(){

         binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Setting.class);
                String oldPassword = binding.edOldPassword.getText().toString();
                String newPassword = binding.edNewPassword.getText().toString();
                String confirmPassword = binding.edConfirmPassword.getText().toString();
                if (oldPassword.equals("") || oldPassword.length() < 4) {
                    binding.edOldPassword.setError("Please enter valid passcode");
                    binding.edOldPassword.requestFocus();

                } else if (newPassword.equals("") || newPassword.length() < 4) {
                    binding.edNewPassword.setError("Please enter valid passcode");
                    binding.edNewPassword.requestFocus();
                    binding.edOldPassword.setError(null);
                    binding.edOldPassword.clearFocus();

                } else if (confirmPassword.equals("") || confirmPassword.length() < 4) {
                    binding.edConfirmPassword.setError("Please enter valid passcode");
                    binding.edConfirmPassword.requestFocus();
                    binding.edNewPassword.setError(null);
                    binding.edNewPassword.clearFocus();
                    binding.edOldPassword.setError(null);
                    binding.edOldPassword.clearFocus();

                } else if (!confirmPassword.equals(newPassword)) {

                    binding.edConfirmPassword.setError("Please enter same passcode");
                    binding.edConfirmPassword.requestFocus();
                    binding.edNewPassword.setError(null);
                    binding.edNewPassword.clearFocus();
                    binding.edOldPassword.setError(null);
                    binding.edOldPassword.clearFocus();

                } else {
                    binding.edConfirmPassword.setError(null);
                    binding.edConfirmPassword.clearFocus();
                    binding.edNewPassword.setError(null);
                    binding.edNewPassword.clearFocus();
                    binding.edOldPassword.setError(null);
                    binding.edOldPassword.clearFocus();

                    Toast.makeText(ChangePassword.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(i);

                }

            }
        });


    }


    protected void openToolBar(){

        View view = findViewById(R.id.Header);

        TextView TxtHeader =view.findViewById(R.id.TxtHeader);
        TxtHeader.setText("Change Password");
        ImageView ImgBack=view.findViewById(R.id.ImgBack);
        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}
