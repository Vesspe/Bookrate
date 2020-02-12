package com.example.bookrate.ui.read;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.bookrate.MainActivity;
import com.example.bookrate.R;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ReadFragment extends Fragment implements ZXingScannerView.ResultHandler{

    private ReadViewModel readViewModel;
    private ZXingScannerView scannerView;
    private TextView result;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        readViewModel =
                new ViewModelProvider(this).get(ReadViewModel.class);
        View root = inflater.inflate(R.layout.fragment_read, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        result = root.findViewById(R.id.txt_result);

        //init
        scannerView = root.findViewById(R.id.zxscan);


        //request permission
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        scannerView.setResultHandler(ReadFragment.this);
                        scannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                })
                .check();
        /*readViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/




        return root;
    }

    @Override
    public void onDestroy() {
        scannerView.stopCamera();
        super.onDestroy();
    }

    //@Override
    public void handleResult(Result rawResult){
        result.setText(rawResult.getText());
    }


}