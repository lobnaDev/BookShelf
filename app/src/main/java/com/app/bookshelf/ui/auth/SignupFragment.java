package com.app.bookshelf.ui.auth;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.app.bookshelf.R;
import com.app.bookshelf.databinding.FragmentSignupBinding;
import com.google.firebase.auth.FirebaseUser;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class SignupFragment extends Fragment {

    AuthViewModel viewModel;
    FragmentSignupBinding binding;
    String email;
    String password;
    String name;
    String gender;
    Uri imagePath;
    private static final int GALLERY_REQUEST_CODE = 50;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        viewModel.getUserData().observe(getActivity(), new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser != null){
                    //
                }
            }
        });

        binding.gnderButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton userType_radiobutton = radioGroup.findViewById(i);
                if (null !=  userType_radiobutton) {
                    userType_radiobutton.setTextColor(getResources().getColor(R.color.bluelight));
                    gender = userType_radiobutton.getText().toString();
                    //localization
                    if (gender.equals(getString(R.string.male))) {
                        gender = "Male";

                    } else if (gender.equals(getString(R.string.female))) {
                        gender = "Female";
                    }
                }

            }
        });

        binding.uploadimageButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetContent.launch("image/*");
            }
        });

        binding.userSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateForm()) {
                    return;
                }
                //showProgressAnim
                //register
                viewModel.userRegister(email,password,name,gender,imagePath);
            }
        });

    }

    //validate user input
    private  boolean  validateForm() {
        name = binding.userFullnameEditText.getText().toString().trim();
        email = binding.userEmailEditText.getText().toString().trim();
        password = binding.userPasswordEditText.getText().toString().trim();

        //Validate user input
        if (TextUtils.isEmpty(name)) {
            binding.userFullnameEditText.setError(getString(R.string.name_is_required));
            return false;
        }
        if (TextUtils.isEmpty(email)) {
            binding.userEmailEditText.setError(getString(R.string.email_is_required));
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            binding.userPasswordEditText.setError(getString(R.string.password_is_required));
            return false;
        }

        if (password.length() < 6) {
            binding.userPasswordEditText.setError(getString(R.string.password_must_be_more_than_6));
            return false;
        }return true;
    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    //You are provided with uri of the image . Take this uri and assign it to Picasso
                    try {
                        imagePath = uri.normalizeScheme();
                        final InputStream imageStream =getActivity(). getContentResolver().openInputStream(imagePath);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        binding.imageView.setImageBitmap(selectedImage);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText( getActivity(),
                                R.string.something_went_, Toast.LENGTH_LONG).show();
                    }
                }
            });





}