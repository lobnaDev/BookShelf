package com.app.bookshelf.ui.Books.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.bookshelf.databinding.BookListitemBinding;
import com.app.bookshelf.model.MyBooks;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class BooksAadapter extends RecyclerView.Adapter<BooksAadapter.MyViewHolder> {
    private List<MyBooks> model ;
    private Context context;
    private MyViewHolder.onBookListener mOnBookListener;
    //for animate
    long DURATION = 150;
    private boolean on_attach = true;
    //bind
    BookListitemBinding binding;


    public BooksAadapter(List<MyBooks> model, Context context, MyViewHolder.onBookListener mOnBookListener) {
        this.model = model;
        this.context = context;
        this.mOnBookListener = mOnBookListener;
    }

    public BooksAadapter() {
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = BookListitemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(binding,mOnBookListener);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        try{
            Picasso.get()
                    .load(model.get(position).getBook_image())
                    .into(holder.bookListitemBinding.imageView);
            holder.bookListitemBinding.title.setText(model.get(position).getTitle());
            holder.bookListitemBinding.autherName.setText(model.get(position).getAuthor());
            holder.bookListitemBinding.price.setText(model.get(position).getPrice());
            holder.bookListitemBinding.bookUrl.setText(model.get(position).getBook_url());
            //for animate
            FromLeftToRight(holder.bookListitemBinding.getRoot(), position);




        }catch (Exception e){e.printStackTrace();}
    }


    @Override
    public int getItemCount() {
        return model.size();
    }

    public void setList(ArrayList<MyBooks> List) {
        this.model = List;
        notifyDataSetChanged();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        onBookListener BooksListener;
        BookListitemBinding bookListitemBinding;





        public MyViewHolder(BookListitemBinding bookListitemBinding, onBookListener booksListener) {
            super(bookListitemBinding.getRoot());
            this.bookListitemBinding = bookListitemBinding;
            this.BooksListener = booksListener;

            bookListitemBinding.getRoot().setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            BooksListener.onBookClick(getAdapterPosition());
        }

        public interface onBookListener {
            void onBookClick(int position);
        }
    }
    private int getImage(String imageName) {
        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        return drawableResourceId;}

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
