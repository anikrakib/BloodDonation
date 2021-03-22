package com.anikrakib.blooddonation.Fragment.Profile;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anikrakib.blooddonation.Utills.SharedPreferencesHelper;
import com.anikrakib.blooddonation.databinding.FragmentQRCodeBinding;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QRCodeFragment extends Fragment {
    FragmentQRCodeBinding fragmentQRCodeBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentQRCodeBinding = FragmentQRCodeBinding.inflate(getLayoutInflater());

        generateQrCode();

        return fragmentQRCodeBinding.getRoot();
    }

    public void generateQrCode(){
//        // Initializing the QR Encoder with your value to be encoded, type you required and Dimension
        QRGEncoder qrgEncoder = new QRGEncoder("SharedPreferencesHelper.getUserName(getContext())", null, QRGContents.Type.TEXT, 500);
//        qrgEncoder.setColorBlack(Color.RED);
//        qrgEncoder.setColorWhite(Color.BLUE);
        // Getting QR-Code as Bitmap
        Bitmap bitmap = qrgEncoder.getBitmap();
        // Setting Bitmap to ImageView
        fragmentQRCodeBinding.qrCode.setImageBitmap(bitmap);
    }


}