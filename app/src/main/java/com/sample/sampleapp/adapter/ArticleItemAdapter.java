package com.sample.sampleapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sample.sampleapp.R;
import com.sample.sampleapp.WebViewActivity;
import com.sample.sampleapp.pojo.Result;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/** To set data in recycler view's child. ArticleItemAapter is used
 * and also the link of article is passed by this class to WebViewActivity */

public class ArticleItemAdapter extends RecyclerView.Adapter<ArticleItemAdapter.ViewHolder> {

    //All methods in this adapter are required for a bare minimum recyclerview adapter
    public List<Result> articleList;
    private Context context;

    // Constructor of the class
    public ArticleItemAdapter(Context context, List<Result> itemList) {
        this.context = context;
        this.articleList = itemList;

    }

    // get the size of the list
    @Override
    public int getItemCount() {
        return articleList == null ? 0 : articleList.size();
    }


    // specify the child layout file and click for each child
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view, context, null);
        return myViewHolder;
    }

    // load data in each child view in recycler view
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        holder.article = articleList.get(listPosition);

        Result article = articleList.get(listPosition);
    // set data to child
        if (article.getTitle() != null) {
            holder.tvArticleTitle.setText("" + article.getTitle().toString());
        } else {
            holder.tvArticleTitle.setText("");
        }

        if (article.getByline() != null) {
            holder.tvArticleAuthor.setText("" + article.getByline());

        } else {
            holder.tvArticleAuthor.setText(" ");
        }

        if (article.getPublishedDate() != null) {
            holder.tvDate.setText("" + article.getPublishedDate());
        } else {
            holder.tvDate.setText("");
        }
        if (article.getUrl() != null) {
            displayImage(context, holder.ivArticleImage, article.getMedia().get(0).getMediaMetadata().get(0).getUrl());

        }else {
            holder.ivArticleImage.setImageResource(R.drawable.image_demo);
        }


    }
    //set images on chid
    private void displayImage(Context c, ImageView iv, String url) {

        ImageLoader imageLoader = ImageLoader.getInstance();

        imageLoader.displayImage(url, iv);

    }

    // Static inner class to initialize the views of child
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvArticleTitle;
        public CircleImageView ivArticleImage;
        public Context context;
        public Result article;
        public TextView tvArticleAuthor;
        public TextView tvDate;

        public ViewHolder(View itemView, Context context, Result article) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvArticleTitle = (TextView) itemView.findViewById(R.id.tv_title);
            ivArticleImage = (CircleImageView) itemView.findViewById(R.id.cv_image);
            tvArticleAuthor = (TextView) itemView.findViewById(R.id.tv_author);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
            this.context = context;
            this.article = article;
        }
        // on child item click action
        @Override
        public void onClick(View view) {

            String articleHtml = article.getUrl();
            if (!TextUtils.isEmpty(articleHtml)) {

                // passing url to webview activity
                Intent i = new Intent(context, WebViewActivity.class);
                i.putExtra("article_html", articleHtml);
                context.startActivity(i);
            } else {

                Toast.makeText(context, "No data found.", Toast.LENGTH_LONG);
            }

            //Log.d("onclick", "onClick " + getLayoutPosition() + " " + item.getText());
        }
    }
}