package com.app.bookshelf.ui.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.bookshelf.R;
import com.app.bookshelf.databinding.FragmentLoginBinding;


public class LoginFragment extends Fragment {
    AuthViewModel authViewModel;
    FragmentLoginBinding binding;
    String EmailAddress;
    String Password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        authViewModel  = new ViewModelProvider(this).get(AuthViewModel.class);

        binding.singinButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (!validateForm()) {
                   return;
               }
               //showProgressAnim
               //login
               authViewModel.userLogin(EmailAddress,Password);

           }
       });



       binding.forgetpasswordtextView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //start forget password ui
           }
       });
       binding.newaccounttextView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Navigation.findNavController(getView()).navigate(R.id.signupFragment);
           }
       });
    }
//    private void closeKeyboard() {
//        try {
//            // this will give us the view which is currently focus in this layout
//            View view =  binding.getRoot().getRootView().getCurrentFocus();
//            // if nothing is currently focus then this will protect the app from crash
//            if (view != null) {
//                // now assign the system service to InputMethodManager
//              //  InputMethodManager manager = (InputMethodManager)  getSystemService(Context.INPUT_METHOD_SERVICE);
//              //  manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }

    //validate user input
    private  boolean  validateForm() {
        EmailAddress = binding.userEmailEditText.getText().toString().trim();
        Password = binding.userPasswordEditText.getText().toString().trim();

        //Validate user input
        if (TextUtils.isEmpty(EmailAddress)) {
            binding.userEmailEditText.setError(getString(R.string.email_is_required));
            return false;
        }
        if (TextUtils.isEmpty(Password)) {
            binding.userPasswordEditText.setError(getString(R.string.password_is_required));
            return false;
        }

        if (Password.length() < 6) {
            binding.userPasswordEditText.setError(getString(R.string.password_must_be_more_than_6));
            return false;
        }return true;
    }
}