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


    // specify the row layout file and click for each row
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view,context,null);
        return myViewHolder;
    }

    // load data in each row element
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        holder.article = articleList.get(listPosition);

//        TextView tvAnnouncementTitle = holder.tvAnnouncementTitle;
//        if (announcement!=null){
//            if (announcement.getAnnouncementTitle()!=null && !TextUtils.isEmpty(announcement.getAnnouncementTitle().getValue()))
//                tvAnnouncementTitle.setText(announcement.getAnnouncementTitle().getValue());
//
//            if (announcement.getAnnouncementImage()!=null && !TextUtils.isEmpty(announcement.getAnnouncementImage().getValue())){
//                displayImage(context,holder.ivAnnouncementImage,announcement.getAnnouncementImage().getValue());
//            }
//
//
//        }
        Result article = articleList.get(listPosition);

        if (article.getTitle() != null) {
            holder.tvArticleTitle.setText(""+article.getTitle().toString());
        }else {
            holder.tvArticleTitle.setText("");
        }

        if (article.getByline() != null) {
//            SpannableString ss = new SpannableString(article.getByline());
//            Drawable d = ContextCompat.getDrawable(context, R.drawable.cal_name);
//            d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
//            ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
//            ss.setSpan(span, 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

            holder.tvArticleAuthor.setText(""+article.getByline());

        }else {
            holder.tvArticleAuthor.setText(" ");
        }

        if (article.getPublishedDate() != null) {
            holder.tvDate.setText(""+article.getPublishedDate());
        }else {
            holder.tvDate.setText("");
        }
        if (article.getUrl() != null) {
//            displayImage(context,holder.ivArticleImage,article.getUrl());
            holder.ivArticleImage.setCircleBackgroundColorResource(R.color.grey);

        }



    }
    private void displayImage(Context c, ImageView iv, String url){

//        DisplayImageOptions options = new DisplayImageOptions.Builder()
//               .resetViewBeforeLoading(true).build();

        ImageLoader imageLoader = ImageLoader.getInstance();

        imageLoader.displayImage(url, iv);

//        Glide.with(c).load(url)
//                /*.thumbnail(0.5f)*/
//                .crossFade()
//
//                .override(1200,600)
//                .placeholder(R.drawable.placeholder)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(iv);
    }
    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvArticleTitle;
        public CircleImageView ivArticleImage;
        public Context context;
        public Result article;
        public TextView tvArticleAuthor;
        public TextView tvDate;
        public ViewHolder(View itemView,Context context,Result article) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvArticleTitle = (TextView) itemView.findViewById(R.id.tv_title);
            ivArticleImage = (CircleImageView) itemView.findViewById(R.id.cv_image);
            tvArticleAuthor=(TextView)itemView.findViewById(R.id.tv_author);
            tvDate = (TextView)itemView.findViewById(R.id.tv_date);
            this.context = context;
            this.article = article;
        }
        @Override
        public void onClick(View view) {
//            int position = getLayoutPosition();
//            Announcement announcement = announcmentList.get(position);

            String announcementHtml = article.getUrl();
            if (!TextUtils.isEmpty(announcementHtml)){
                Intent i = new Intent(context, WebViewActivity.class);
                i.putExtra("announcement_html",announcementHtml);
                context.startActivity(i); }
            else {
                Toast.makeText(context,"No data found.",Toast.LENGTH_LONG);
            }

            //Log.d("onclick", "onClick " + getLayoutPosition() + " " + item.getText());
        }
    }
}