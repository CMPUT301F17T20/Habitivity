package main.habitivity.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.util.List;

import main.habitivity.R;
import main.habitivity.habits.Habit;

/**
 * Created by Shally on 2017-11-29.
 */

public class HomeFeedViewAdapter extends RecyclerView.Adapter<HomeFeedViewAdapter.ViewHolder>{
    private List<Habit> mFeed;
    private final ClickListener listener;
    private Context mContext;

    public HomeFeedViewAdapter(Context context, ClickListener listener) {
        mContext = context;
        this.listener = listener;
    }

    @Override
    public HomeFeedViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext(); //returns the context the view is running in, gets access to the resources
        LayoutInflater inflater = LayoutInflater.from(context);
        View requestView = inflater.inflate(R.layout.item_main_feed, parent, false);
        HomeFeedViewAdapter.ViewHolder viewHolder = new HomeFeedViewAdapter.ViewHolder(requestView, this.listener);

        return viewHolder;
    }

    public void setRequestList(List<Habit> requests) {
        this.mFeed = requests;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final HomeFeedViewAdapter.ViewHolder holder, int position) {
        Habit habit = mFeed.get(position);
        TextView name = holder.name;
        TextView habitTitle = holder.habitTitle;
        ImageView level_icon = holder.level_icon;
        name.setText("UserName: " + habit.getUserName());
        habitTitle.setText("Habit Title: " + habit.getTitle());
        //level_icon.setImageResource(R.drawable.level_icon_bronze);
        level_icon.setImageResource(habit.getLevelIcon());
    }


    @Override
    public int getItemCount() {
        if (mFeed == null) {
            return 0;
        }
        return mFeed.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView habitTitle;
        public ImageView level_icon;
        private WeakReference<ClickListener> listenerRef;

        public ViewHolder(final View itemView, ClickListener listener) {
            super(itemView);
            listenerRef = new WeakReference<>(listener);
            name = (TextView) itemView.findViewById(R.id.username);
            habitTitle = (TextView) itemView.findViewById(R.id.habitTitle);
            level_icon = (ImageView) itemView.findViewById(R.id.level_icon);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listenerRef.get().onPositionClicked(getAdapterPosition());
        }
    }
}
