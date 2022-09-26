package com.app.bookshelf.ui.Books.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.bookshelf.databinding.FragmentBookInfoBinding;
import com.app.bookshelf.model.MyBooks;
import com.app.bookshelf.ui.Books.BookListViewModel;

import java.util.List;


public class BookInfoFragment extends Fragment {
   private FragmentBookInfoBinding binding;
    BookListViewModel listViewModel;
    int position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBookInfoBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listViewModel = new ViewModelProvider(this).get(BookListViewModel.class);
        position  =  getArguments().getInt("position");
        String name = getArguments().getString("name");
        String ListName = getArguments().getString("ListName");
        listViewModel.getBookinfo(name,ListName);

        listViewModel.listMutableLiveData.observe(getActivity(), new Observer<List<MyBooks>>() {
            @Override
            public void onChanged(List<MyBooks> myBooks) {
                binding.textView.setText( myBooks.get(position).getTitle());
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;
    }
}