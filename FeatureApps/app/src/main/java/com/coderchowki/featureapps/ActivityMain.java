package com.coderchowki.featureapps;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class ActivityMain extends AppCompatActivity implements View.OnClickListener, OnCompleteListener<Void> {

    ImageView iv_UserPic;
    private final int ImagetCaptureRequestCode = 786;
    private final int REQUEST_CODE_ASK_PERMISSIONS = 787;

    FirebaseRemoteConfig remoteConfig;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViewComponent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == ImagetCaptureRequestCode) && (resultCode == RESULT_OK)) {
            Bundle bundle = data.getExtras();
            Bitmap img = (Bitmap) bundle.get("data");
            //iv_UserPic.setImageBitmap(img);
            uploadImageToFireBase(img);

        }
    }

    private void uploadImageToFireBase(Bitmap img) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        storage = FirebaseStorage.getInstance();
        remoteConfig = FirebaseRemoteConfig.getInstance();
        remoteConfig.setDefaults(R.xml.dynamic_config);
        remoteConfig.fetch(0).addOnCompleteListener(this);
        StorageReference storageRef = storage.getReferenceFromUrl("gs://featureapp-1e09a.appspot.com");
        StorageReference spaceRef = storageRef.child(remoteConfig.getString("storage_location"));

        UploadTask uploadTask = spaceRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast.makeText(ActivityMain.this, "FireBase Failed To Upload", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                Log.i("ImageUrl" , "\nurl:" + downloadUrl);
                Glide.with(ActivityMain.this)
                    .load(downloadUrl)
                    .asBitmap()
                    .centerCrop()
                    .into(iv_UserPic);
                // Do what you want
            }

        });
    }

    private void initializeViewComponent() {

        iv_UserPic = (ImageView) findViewById(R.id.ivUserPic);
        iv_UserPic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivUserPic:
                checkUserPermsions();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    captureImage();
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void captureImage() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, ImagetCaptureRequestCode);
        Toast.makeText(this, "capture Image", Toast.LENGTH_SHORT).show();
    }

    void checkUserPermsions() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                                android.Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
        }

        captureImage();// init the contact list

    }

    @Override
    public void onComplete(@NonNull Task<Void> task) {
        if(task.isSuccessful()){
            remoteConfig.activateFetched();
            Log.d("firebaseconfig", "Fetch Succeeded");
        }else{
            Log.d("firebaseconfig", "Fetch failed");
        }
    }
}
