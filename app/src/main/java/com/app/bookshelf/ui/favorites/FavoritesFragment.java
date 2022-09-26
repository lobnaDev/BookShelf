package com.app.bookshelf.ui.favorites;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.bookshelf.R;
import com.app.bookshelf.databinding.FragmentBookListBinding;
import com.app.bookshelf.databinding.FragmentFavoritesBinding;
import com.app.bookshelf.model.MyBooks;
import com.app.bookshelf.model.MyStoriesResult;
import com.app.bookshelf.ui.Books.Adapter.BooksAadapter;
import com.app.bookshelf.ui.Books.BookListFragment;
import com.app.bookshelf.ui.Books.BookListViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class FavoritesFragment extends Fragment implements FavoritesAdapter.MyViewHolder.onFavoriateListener {
    FavoritesAdapter adapter;
    FragmentFavoritesBinding binding;
    FavoritesViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);
        viewModel.getFavorites();

        viewModel.listMutableLiveData.observe(getActivity(), new Observer<List<MyStoriesResult>>() {
            @Override
            public void onChanged(List<MyStoriesResult> myStoriesResults) {
                    adapter = new FavoritesAdapter(myStoriesResults,getContext(), FavoritesFragment.this);
                    binding.recyclerview.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
            }
        });

//        // on below line we are creating a method to create item touch helper
//        // method for adding swipe to delete functionality.
//        // in this we are specifying drag direction and position to right
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                // this method is called
//                // when the item is moved.
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                // this method is called when we swipe our item to right direction.
//                // on below line we are getting the item at a particular position.
//                MyStoriesResult deletedCourse = recyclerDataArrayList.get(viewHolder.getAdapterPosition());
//
//                // below line is to get the position
//                // of the item at that position.
//                int position = viewHolder.getAdapterPosition();
//
//                // this method is called when item is swiped.
//                // below line is to remove item from our array list.
//                recyclerDataArrayList.remove(viewHolder.getAdapterPosition());
//
//                // below line is to notify our item is removed from adapter.
//                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
//
//                // below line is to display our snackbar with action.
//                Snackbar.make(binding.recyclerview, deletedCourse.getTitle(), Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // adding on click listener to our action of snack bar.
//                        // below line is to add our item to array list with a position.
//                        recyclerDataArrayList.add(position, deletedCourse);
//
//                        // below line is to notify item is
//                        // added to our adapter class.
//                        mAdapter.notifyItemInserted(position);
//                    }
//                }).show();
//            }
//            // at last we are adding this
//            // to our recycler view.
//        }).attachToRecyclerView(binding.recyclerview);
    }

    @Override
    public void onFavClick(int position) {
        viewModel.listMutableLiveData.getValue().get(position);
        Bundle bundle = new Bundle();
        bundle.putString("url", viewModel.listMutableLiveData.getValue().get(position).getUrl());
        Navigation.findNavController(getView()).navigate(R.id.storyDetailesFragment, bundle);
    }
}