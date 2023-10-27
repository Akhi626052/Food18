package com.daizzyinfo.food18.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.utils.Constant;

public class Setting extends AppCompatActivity {
    LinearLayout LnContact,LnTermsConditions,LnPrivacy,LnHelpFaq, LnChangeP, LnNotification;
    ImageView imgCamera;
    ImageView img1;

    RelativeLayout Logout;
    protected String[] optionsMenu = new String[]{"Camera", "Gallery", "Exit"};
    protected static final int cameraCode = 0;
    protected static final int galleryCode = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        img1 = findViewById(R.id.img1);
        Logout=findViewById(R.id.Logout);
        imgCamera = findViewById(R.id.imgCamera);
        LnContact=findViewById(R.id.LnContact);
        LnTermsConditions=findViewById(R.id.LnTermsConditions);
        LnPrivacy=findViewById(R.id.LnPrivacy);
        LnHelpFaq=findViewById(R.id.LnHelpFaq);
        LnChangeP=findViewById(R.id.LnChangeP);
        LnNotification = findViewById(R.id.LnNotification);

        openToolBar();

        initOnClickListener();



    }

    private void initOnClickListener() {

        imgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionsMenu();
            }
        });




        LnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, ContactWithUs.class);
                startActivity(intent);
            }
        });



        LnChangeP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, ChangePassword.class);
                startActivity(intent);
            }
        });



        LnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, Notification.class);
                startActivity(intent);
            }
        });


        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutDialog();
            }
        });


        LnTermsConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, TermsAndConditions.class);
                startActivity(intent);
            }
        });

        LnPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, PrivacyPolicy.class);
                startActivity(intent);
            }
        });

        LnHelpFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, HelpsAndFaq.class);
                startActivity(intent);
            }
        });


    }


    private void openToolBar(){

        View view = findViewById(R.id.Header);

        TextView TxtHeader =view.findViewById(R.id.TxtHeader);
        TxtHeader.setText("My Profile");
        ImageView ImgBack=view.findViewById(R.id.ImgBack);
        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void logoutDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Setting.this);
        builder.setTitle("Confirm Logout   !!");
        builder.setMessage("Are you sure you want to logout ?");
        builder.setIcon(R.drawable.logout1);
        builder.setCancelable(false);
        builder.setPositiveButton(" Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Constant.clearAllData();
                Intent intent = new Intent(Setting.this, SplashScreen.class);
                startActivity(intent);
                finishAffinity();
            }
        });
        builder.setNegativeButton(" No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }

    private void showOptionsMenu() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Setting.this);
        builder.setTitle("Choose one");
        builder.setCancelable(false);
        builder.setItems(optionsMenu, (dialogInterface, i) -> {
            if (optionsMenu[i].equals("Camera")) {
                if (ContextCompat.checkSelfPermission(Setting.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, cameraCode);
                } else
                {
                    ActivityCompat.requestPermissions(Setting.this, new String[]{Manifest.permission.CAMERA}, cameraCode);
                }
            } else if (optionsMenu[i].equals("Gallery")) {
                if (ContextCompat.checkSelfPermission(Setting.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, galleryCode);
                } else {
                    ActivityCompat.requestPermissions(Setting.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, galleryCode);
                }
            } else if (optionsMenu[i].equals("Exit")) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == cameraCode && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, cameraCode);
        } else if (requestCode == galleryCode && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, galleryCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == cameraCode && data != null) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                img1.setImageBitmap(photo);



            }
            else if (requestCode == galleryCode && data != null) {
                Uri selectedImageUri = data.getData();
                String[] filepathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImageUri, filepathColumn, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex(filepathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();
                    Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                    img1.setImageBitmap(bitmap);

                }
            }
        }
    }



}