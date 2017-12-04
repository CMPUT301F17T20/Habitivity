package main.habitivity.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import main.habitivity.R;
import main.habitivity.habits.Habit;

/**
 * Created by Shally on 2017-12-04.
 */

public class AllHabitsViewAdapter extends RecyclerView.Adapter<AllHabitsViewAdapter.ViewHolder> {
    private List<Habit> mFeed;
    private final ClickListener listener;
    private Context mContext;

    public AllHabitsViewAdapter(Context context, ClickListener listener) {
        mContext = context;
        this.listener = listener;
    }

    @Override
    public AllHabitsViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext(); //returns the context the view is running in, gets access to the resources
        LayoutInflater inflater = LayoutInflater.from(context);
        View requestView = inflater.inflate(R.layout.all_habits_item, parent, false);
        AllHabitsViewAdapter.ViewHolder viewHolder = new AllHabitsViewAdapter.ViewHolder(requestView, this.listener);

        return viewHolder;
    }

    public void setRequestList(List<Habit> requests) {
        this.mFeed = requests;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final AllHabitsViewAdapter.ViewHolder holder, int position) {
        Habit habit = mFeed.get(position);
        TextView habitTitle = holder.habitTitle;
        habitTitle.setText("Habit Title: " + habit.getTitle());
    }


    @Override
    public int getItemCount() {
        if (mFeed == null) {
            return 0;
        }
        return mFeed.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView habitTitle;
        private WeakReference<ClickListener> listenerRef;

        public ViewHolder(final View itemView, ClickListener listener) {
            super(itemView);
            listenerRef = new WeakReference<>(listener);
            habitTitle = (TextView) itemView.findViewById(R.id.habitTitle);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listenerRef.get().onPositionClicked(getAdapterPosition());
        }
    }
}
