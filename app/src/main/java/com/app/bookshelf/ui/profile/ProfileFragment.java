package com.app.bookshelf.ui.profile;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.bookshelf.R;
import com.app.bookshelf.databinding.FragmentFavoritesBinding;
import com.app.bookshelf.databinding.FragmentProfileBinding;
import com.app.bookshelf.model.MyUsers;
import com.app.bookshelf.repository.AuthRepo;
import com.app.bookshelf.repository.DatabaseRepo;
import com.app.bookshelf.ui.favorites.FavoritesViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ProfileFragment extends Fragment {
    DatabaseRepo mRepo;
    AuthRepo repo;
    Application application;
    profileViewModel viewModel;
    FragmentProfileBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel  = new ViewModelProvider(this).get(profileViewModel.class);
        viewModel.getProfileInfo();

        viewModel.listMutableLiveData.observe(getActivity(), new Observer<List<MyUsers>>() {
            @Override
            public void onChanged(List<MyUsers> myUsers) {
                binding.userNmeTextView.setText(myUsers.get(0).getName());
                binding.emailTextView.setText(myUsers.get(0).getEmail());
                Picasso.get()
                        .load(myUsers.get(0).getImage())
                        .into(binding.profileImage);
            }
        });

        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repo.Logout();
            }
        });
    }
}