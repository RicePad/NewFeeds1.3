package com.udacity.jonathan.newfeeds;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class NewsfeedAdapter extends RecyclerView.Adapter<NewsfeedAdapter.MyViewHolder>  {

    private List<Newsfeed> newsfeedList = new ArrayList<>();
    private ItemClickListener clickListener;
    private int mrawLayout;
    private Context mContext;



    public NewsfeedAdapter(List<Newsfeed> newsfeedList, int rawLayout, Context context) {
        this.newsfeedList = newsfeedList;
        this.mrawLayout = rawLayout;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView  newsfeedTitleTextView, newsfeedSectionTextView, newsfeedDateView, newsfeedAuthorsTextView;
        private MyViewHolder(View view) {
            super(view);
            newsfeedTitleTextView = (TextView) view.findViewById(R.id.title_text_view);
            newsfeedSectionTextView = (TextView) view.findViewById(R.id.newsfeed_section_text_view);
            newsfeedDateView = (TextView) view.findViewById(R.id.newsfeed_date);
            newsfeedAuthorsTextView = (TextView) view.findViewById(R.id.author_section);
            view.setOnClickListener(this);
            }
        // Handles the row being being clicked
        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {

        Newsfeed currentArticle = newsfeedList.get(position);

        if (currentArticle.getAuthors().size() > 0) {
            StringBuilder authorsBuilder = new StringBuilder();
            boolean first = true;
            for (int i = 0; i < currentArticle.getAuthors().size(); i++) {
                if (first) {
                    first = false;
                } else {
                    authorsBuilder.append(System.getProperty("line.separator"));
                }
                authorsBuilder.append(currentArticle.getAuthors().get(i));
            }
            holder.newsfeedAuthorsTextView.setText(authorsBuilder.toString());
        }
        else {
            holder.newsfeedAuthorsTextView.setVisibility(View.GONE);
        }

        Date dateTimeObject = new Date (currentArticle.getDateTime());
        String formattedDateTime = formatDate(dateTimeObject);
        holder.newsfeedDateView.setText(formattedDateTime);
    }



//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        // Check if an existing view is being reused, otherwise inflate the view
//        View listItemView = convertView;
//        if (listItemView == null) {
//            listItemView = LayoutInflater.from(getContext()).inflate(
//                    R.layout.new_list_item, parent, false);
//        }
//
//        // Get the {@link Newsfeed} object located at this position in the list
//        Newsfeed currentNewsfeed = getItem(position);
//
//        // Find the TextView in the list_item.xml layout with the correct IDs
//        TextView newsfeedTextView = (TextView) listItemView.findViewById(R.id.title_text_view);
//        TextView newsfeedSectionTextView = (TextView) listItemView.findViewById(R.id.newsfeed_section_text_view);
//        TextView newsfeedAuthorTextView = (TextView) listItemView.findViewById(R.id.author_section);
//
//
//
//        // Get the NewsfeedTitle and section from the currentNewsfeed object and set this text on
//        // the TextView.
//        newsfeedTextView.setText(currentNewsfeed.getNewsfeedTitle());
//        newsfeedSectionTextView.setText(currentNewsfeed.getSection());
//
//        // Create a new Date object for newsfeed
//        Date dateObject = new Date(currentNewsfeed.getDateTime());
//
//        // Find the TextView with view ID date
//        TextView dateView = (TextView) listItemView.findViewById(R.id.newsfeed_date);
//        // Format the date string (i.e. "Mar 3, 1984")
//        String formattedDate = formatDate(dateObject);
//        // Display the date of the current newsfeed in that TextView
//        dateView.setText(formattedDate);
//
//
//        return listItemView;
//
//    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }


    @Override
    public int getItemCount() {
        if (newsfeedList == null) {
            return 0;
        } else { return newsfeedList.size(); }
    }

}
