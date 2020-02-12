package com.example.bookrate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookrate.ui.review.ReviewFragment;

import java.util.List;



public class RecyclerView_Config extends FragmentActivity {
    private Context mContext;
    private BooksAdapter mBooksAdapter;
    private RecyclerView mRecyclerView;
    private View.OnClickListener onItemClickListener;


    public void setConfig(RecyclerView recyclerView, Context context, List<Book> books, List<String> keys ){

        mContext = context;
        mBooksAdapter = new BooksAdapter(books, keys);
        mRecyclerView = recyclerView;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setAdapter(mBooksAdapter);

    }

    //inflate layout BookListItem
    class BookItemView extends RecyclerView.ViewHolder{

        private TextView mTitle;
        private TextView mAuthor;
        private TextView mIsbn;
        private TextView mCategory;

        private String key;

        public BookItemView(View inflatedView){
            super(inflatedView);
            inflatedView.setTag(this);
            inflatedView.setOnClickListener(onItemClickListener);
            
            mTitle = inflatedView.findViewById(R.id.title_textView);
            mAuthor = inflatedView.findViewById(R.id.author_textView);
            mCategory = inflatedView.findViewById(R.id.category_textView);
            mIsbn = inflatedView.findViewById(R.id.isbn_textView);
        }

        public void bind (Book book, String key){
            mTitle.setText(book.getTitle());
            mAuthor.setText(book.getAuthor());
            mCategory.setText(book.getCategory());
            mIsbn.setText(book.getIsbn());
            this.key = key;
        }

        public void setItemClickListener(View.OnClickListener clickListener)
        {
            onItemClickListener = clickListener;
        }
    }

    // creating bookItemView
    class BooksAdapter extends RecyclerView.Adapter<BookItemView> {
        private List<Book> mBookList;
        private List<String> mKeys;
        private ReviewFragment mFragment;
        public static final String EXTRA_MESSAGE = "com.example.bookrate.MESSAGE";


        public BooksAdapter(List<Book> mBookList, List<String> mKeys) {
            this.mBookList = mBookList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public BookItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BookItemView(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.book_list_item,parent, false));

        }

        @Override
        public void onBindViewHolder(@NonNull BookItemView holder, final int position) {
            holder.bind(mBookList.get(position), mKeys.get(position));
            holder.setItemClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String isbn = mBookList.get(position).isbn;
                    Intent intent = new Intent(RecyclerView_Config.this, BookDetailActivity.class);
                    intent.putExtra(EXTRA_MESSAGE, isbn);
                    startActivity(intent);

                }
            });
        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }
    }
}


/*        private void fragmentJump(){
            mFragment = new Fragment();
            mBundle = new Bundle();
            mBundle.putParcelable();
            mFragment.setArguments(mBundle);
            switchContent();

        }

        public void switchContent(int id, Fragment fragment){
            if(mContext == null)
                return;
            if(mContext instanceof MainActivity){

            }
        }*/

//for some reason i cant execute this so i will go with new intent instead
                    /*mFragment = new ReviewFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.nav_browse, mFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();*/