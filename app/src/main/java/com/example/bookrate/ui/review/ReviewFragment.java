package com.example.bookrate.ui.review;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.bookrate.R;

import static android.Manifest.permission_group.CAMERA;

public class ReviewFragment extends Fragment {

    private ReviewViewModel reviewViewModel;
    private static final int REQUEST_CAMERA = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        reviewViewModel =
                new ViewModelProvider(this).get(ReviewViewModel.class);
        View root = inflater.inflate(R.layout.fragment_review, container, false);
/*        final TextView textView = root.findViewById(R.id.text_slideshow);
        reviewViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }


    private boolean checkPermission(){
        return (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission()
    {
        ActivityCompat.requestPermissions(getActivity(), new String[]{CAMERA}, REQUEST_CAMERA);
    }

    public void onRequestPermissionsResult
            (int requestCode, String permission[], int grantResults[]){
        switch(requestCode)
        {
            case REQUEST_CAMERA :
                if(grantResults.length > 0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    //if(cameraAccepted)

                }
        }
    }
}