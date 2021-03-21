package com.anikrakib.blooddonation.Fragment;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.ColorUtils;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;

import com.anikrakib.blooddonation.Activity.MainActivity;
import com.anikrakib.blooddonation.Activity.SignUpActivity;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.Utills.HelperClass;
import com.anikrakib.blooddonation.databinding.FragmentUploadImageBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.ramijemli.percentagechartview.callback.AdaptiveColorProvider;

import java.util.Objects;

public class UploadImageFragment extends Fragment {
    FragmentUploadImageBinding fragmentUploadImageBinding;
    FirebaseAuth auth;
    FirebaseFirestore database;
    ProgressDialog dialog;
    Uri uri;
    String imageUri = "";
    StorageTask uploadTask;
    FirebaseStorage storage;
    StorageReference storageReference;
    private static final int INTENT_REQUEST_CODE = 100;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentUploadImageBinding = FragmentUploadImageBinding.inflate(getLayoutInflater());

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Create an account");
        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("profile_pic");

        fragmentUploadImageBinding.uploadPhotoLayout.setOnClickListener(v->{

            if (ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 100);
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 44);
            }

        });

        fragmentUploadImageBinding.userProfilePic.setOnClickListener(v->{

            if (ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 100);
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 44);
            }

        });

        fragmentUploadImageBinding.startJourneyButton.setOnClickListener(v->{
            createUser();
        });

        return fragmentUploadImageBinding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == INTENT_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                uri = data.getData();
                //postInputStream = getActivity().getContentResolver().openInputStream(Objects.requireNonNull(data.getData()));
                fragmentUploadImageBinding.userProfilePic.setImageURI(uri);
                fragmentUploadImageBinding.uploadPhotoLayout.setVisibility(View.GONE);
                fragmentUploadImageBinding.progressBar.setVisibility(View.VISIBLE);
                Log.d("picture","Uri ->"+uri.toString());
                upload();
                //uploadProfilePic();
            }
        }

    }

    private void upload(){
        if(uri!=null){
            final StorageReference fileRef = storageReference.child(SignUpActivity.userDataModel.getUserName()+".jpg");
            fileRef.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                            {
                                @Override
                                public void onSuccess(Uri downloadUrl)
                                {
                                    SignUpActivity.userDataModel.setUserProfilePic(downloadUrl.toString());
                                    Log.d("picture",downloadUrl.toString());
                                    //do something with downloadurl
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("picture",e.getMessage());
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                            Log.d("progress",progress+"");
                            if(progress == 100.0) {
                                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        // Your Code
                                        fragmentUploadImageBinding.userProfilePic.setVisibility(View.VISIBLE);
                                        fragmentUploadImageBinding.progressBar.setVisibility(View.GONE);
                                    }
                                }, 1000);
                            }
                            else {
                                fragmentUploadImageBinding.progressBar.setProgress((float) progress,true);
                                fragmentUploadImageBinding.progressBar.setAnimationInterpolator(new AnticipateOvershootInterpolator());
                                fragmentUploadImageBinding.progressBar.setAnimationDuration(100);
                            }
                        }
                    });
        }else {
            Log.d("picture","noFileSelected");
        }
    }

    private void createUser(){
        dialog.show();
        auth.createUserWithEmailAndPassword(SignUpActivity.userDataModel.getEmail(), HelperClass.getHash(SignUpActivity.userDataModel.getPassword())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    String uid = Objects.requireNonNull(Objects.requireNonNull(task.getResult()).getUser()).getUid();
                    String password = SignUpActivity.userDataModel.getPassword();
                    SignUpActivity.userDataModel.setPassword(HelperClass.getHash(password));
                    SignUpActivity.userDataModel.setUserId(uid);
                    // create user Data Table
                    database.collection(HelperClass.USERS_COLLECTION_NAME)
                            .document(SignUpActivity.userDataModel.getUserName())
                            .set(SignUpActivity.userDataModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                dialog.dismiss();
                                startActivity(new Intent(getContext(), MainActivity.class));
                                getActivity().finish();
                            } else {
                                HelperClass.snackBar(Objects.requireNonNull(task.getException()).getLocalizedMessage(), R.color.colorPrimary,getContext());
                            }
                        }
                    });

                } else {
                    dialog.dismiss();
                    HelperClass.snackBar(Objects.requireNonNull(task.getException()).getLocalizedMessage(),R.color.colorPrimary,getContext());
                }
            }
        });
    }

}