package com.app.bookshelf.ui.Books;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.app.bookshelf.R;
import com.app.bookshelf.databinding.FragmentBookListBinding;
import com.app.bookshelf.ui.Books.Adapter.BooksAadapter;
import com.app.bookshelf.model.MyBooks;

import java.util.List;

public class BookListFragment extends Fragment implements BooksAadapter.MyViewHolder.onBookListener {
    BooksAadapter mBookAdapter;
    BookListViewModel listViewModel;
    FragmentBookListBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBookListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        binding.rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        listViewModel = new ViewModelProvider(this).get(BookListViewModel.class);
        listViewModel. getCurrentnonficBooklist();


        listViewModel.listMutableLiveData.observe(getActivity(), new Observer<List<MyBooks>>() {
            @Override
            public void onChanged(List<MyBooks> myBooks) {
                mBookAdapter = new BooksAadapter(myBooks,getContext(),BookListFragment.this);
               binding.rec.setAdapter(mBookAdapter);
               mBookAdapter.notifyDataSetChanged();
            }
        });

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.filter_menu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_fiction:
                listViewModel.getCurrentFictionBooklist();
                return true;
            case R.id.action_nonFiction:
                listViewModel.getCurrentnonficBooklist();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBookClick(int position) {
        listViewModel.listMutableLiveData.getValue().get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putString("ListName",listViewModel.listMutableLiveData.getValue().get(position).getList_name());
        bundle.putString("name",listViewModel.listMutableLiveData.getValue().get(position).getTitle());
        Navigation.findNavController(getView()).navigate(R.id.bookInfoFragment, bundle);
        //NavHostFragment.findNavController(BookListFragment.class,bundle);

        Log.i("Activity", "clicked  " + '\n' + listViewModel.listMutableLiveData.getValue().get(position).getTitle() + '\n');

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
       // binding = null;
    }
}