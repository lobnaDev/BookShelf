package com.app.bookshelf.ui.favorites;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.bookshelf.R;
import com.app.bookshelf.databinding.BookListitemBinding;
import com.app.bookshelf.databinding.FavoritesItemBinding;
import com.app.bookshelf.model.MyStoriesResult;
import com.app.bookshelf.ui.Books.Adapter.BooksAadapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.MyViewHolder> {
    private List<MyStoriesResult> model = new ArrayList<>();
    private Context context;
    private MyViewHolder.onFavoriateListener mOnfavListener;
    //for animate
    long DURATION = 150;
    private boolean on_attach = true;
    //bind
    FavoritesItemBinding binding;

    public FavoritesAdapter(List<MyStoriesResult> model, Context context, MyViewHolder.onFavoriateListener mOnfavListener) {
        this.model = model;
        this.context = context;
        this.mOnfavListener = mOnfavListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        binding = FavoritesItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        FavoritesAdapter.MyViewHolder mViewHolder = new FavoritesAdapter.MyViewHolder(mOnfavListener,binding);
        return mViewHolder;


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        try{
            Picasso.get()
                    .load(model.get(position).getMedia())
                    .into(holder.favoritesItemBinding.imageView);
            holder.favoritesItemBinding.title.setText(model.get(position).getTitle());
            holder.favoritesItemBinding.autherName.setText(model.get(position).getByline());
            holder.favoritesItemBinding.date.setText(model.get(position).getPublished_date());
            holder.favoritesItemBinding.bookUrl.setText(model.get(position).getUrl());
            //for animate
            FromLeftToRight(holder.favoritesItemBinding.getRoot(), position);


        }catch (Exception e){e.printStackTrace();}
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public void setList(ArrayList<MyStoriesResult> List) {
        this.model = List;
        notifyDataSetChanged();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        onFavoriateListener favListener;
        FavoritesItemBinding favoritesItemBinding;

        public MyViewHolder( onFavoriateListener favListener, FavoritesItemBinding favoritesItemBinding) {
            super(favoritesItemBinding.getRoot());
            this.favListener = favListener;
            this.favoritesItemBinding = favoritesItemBinding;
            favoritesItemBinding.getRoot().setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
           favListener.onFavClick(getAdapterPosition());
        }

        public interface onFavoriateListener  {
            void onFavClick(int position);
        }
    }
    //for animate
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                on_attach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        super.onAttachedToRecyclerView(recyclerView);
    }
    //animate
    private void FromLeftToRight(View itemView, int i) {
        if(!on_attach){
            i = -1;
        }
        boolean not_first_item = i == -1;
        i = i + 1;
        itemView.setTranslationX(-400f);
        itemView.setAlpha(0.f);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(itemView, "translationX", -400f, 0);
        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(itemView, "alpha", 1.f);
        ObjectAnimator.ofFloat(itemView, "alpha", 0.f).start();
        animatorTranslateY.setStartDelay(not_first_item ? DURATION : (i * DURATION));
        animatorTranslateY.setDuration((not_first_item ? 2 : 1) * DURATION);
        animatorSet.playTogether(animatorTranslateY, animatorAlpha);
        animatorSet.start();
    }
}
