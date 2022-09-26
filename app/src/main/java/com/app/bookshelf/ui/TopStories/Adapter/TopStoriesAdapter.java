package com.app.bookshelf.ui.TopStories.Adapter;

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
import com.app.bookshelf.databinding.TopstoryItemBinding;
import com.app.bookshelf.model.MyBooks;
import com.app.bookshelf.model.MyStoriesResult;
import com.app.bookshelf.model.multimedia;
import com.app.bookshelf.ui.Books.Adapter.BooksAadapter;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.ViewHolder> {
    private List<MyStoriesResult> model = new ArrayList<>();
    multimedia multimedia;
    private Context context;
    private ViewHolder.onTopStoriesListener mOnTopListener;
    //for animate
    long DURATION = 150;
    private boolean on_attach = true;
    //bind
    TopstoryItemBinding binding;

    public TopStoriesAdapter(List<MyStoriesResult> model, Context context, ViewHolder.onTopStoriesListener mOnTopListener) {
        this.model = model;
        this.context = context;
        this.mOnTopListener = mOnTopListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = TopstoryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        TopStoriesAdapter.ViewHolder mViewHolder = new TopStoriesAdapter.ViewHolder(mOnTopListener,binding);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try{

                Picasso.get()
                        .load(model.get(position).getMultimedia().get(0).getUrl())
                        .into(holder.StoriesItemBinding.imageView);

            holder.StoriesItemBinding.title.setText(model.get(position).getTitle());
            holder.StoriesItemBinding.autherName.setText(model.get(position).getByline());

            String date = model.get(position).getPublished_date();
            DateFormat originalFormat = new SimpleDateFormat("dd-MM-yy", Locale.US);
            Date Fdate = originalFormat.parse(date);
            String  formattedDate = originalFormat.format(Fdate);
            holder.StoriesItemBinding.publishedDate.setText(formattedDate);


            //for animate
            FromLeftToRight(holder.StoriesItemBinding.getRoot(), position);




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

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener , MaterialFavoriteButton.OnFavoriteChangeListener {
        onTopStoriesListener StoriesListener;
        TopstoryItemBinding StoriesItemBinding;

        public ViewHolder( onTopStoriesListener storiesListener, TopstoryItemBinding storiesItemBinding) {
            super(storiesItemBinding.getRoot());
            StoriesListener = storiesListener;
            StoriesItemBinding = storiesItemBinding;
            storiesItemBinding.getRoot().setOnClickListener(this);
            StoriesItemBinding.mfavorite.setOnFavoriteChangeListener(this);

        }



        @Override
        public void onClick(View view) {
            StoriesListener.onTopClick(getAdapterPosition());
        }

        @Override
        public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
            StoriesListener.Favorite(getAdapterPosition(),favorite);
        }

        public interface onTopStoriesListener {
            void onTopClick(int position);
            void Favorite(int position,boolean satate);
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

