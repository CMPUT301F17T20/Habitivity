package main.habitivity.adapters;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import main.habitivity.R;
import main.habitivity.habits.HabitEvent;


public class HabitHistoryViewAdapter extends RecyclerView.Adapter<HabitHistoryViewAdapter.ViewHolder> {

    private List<HabitEvent> mFeed;
    private final ClickListener listener;
    private Context mContext;

    public HabitHistoryViewAdapter(Context context, ClickListener listener) {
        mContext = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext(); //returns the context the view is running in, gets access to the resources
        LayoutInflater inflater = LayoutInflater.from(context);
        View requestView = inflater.inflate(R.layout.event_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(requestView, this.listener);

        return viewHolder;
    }

    public void setRequestList(List<HabitEvent> requests) {
        this.mFeed = requests;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        HabitEvent event = mFeed.get(position);
        holder.habitName.setText(event.getId());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA);
        holder.eventCompleted.setText(String.format("Completed: %s", df.format(event.getCompletionDate())));
        holder.eventComment.setText(event.getComment());

        if (event.getPhoto() == null) {
            holder.userImage.setImageResource(R.drawable.ic_menu_camera);
        }
        else {
            holder.userImage.setImageBitmap(event.getPhoto());
        }
        if (event.getLocation() != null) {
            holder.locImage.setImageResource(R.drawable.ic_location_icon_vector_red_pin);
        }
    }


    @Override
    public int getItemCount() {
        if (mFeed == null) {
            return 0;
        }
        return mFeed.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView habitName;
        public TextView eventCompleted;
        public TextView eventComment;
        public ImageView locImage;
        public ImageView userImage;
        public Button settings;
        private WeakReference<ClickListener> listenerRef;

        public ViewHolder(final View itemView, ClickListener listener) {
            super(itemView);
            listenerRef = new WeakReference<>(listener);
            habitName = (TextView) itemView.findViewById(R.id.HabitName);
            eventCompleted = (TextView) itemView.findViewById(R.id.eventCompleted);
            eventComment = (TextView) itemView.findViewById(R.id.EventComment);
            locImage = (ImageView) itemView.findViewById(R.id.locationAdded);
            userImage = (ImageView) itemView.findViewById(R.id.userImage);
            settings = (Button) itemView.findViewById(R.id.settingsButton);

            settings.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listenerRef.get().onPositionClicked(getAdapterPosition());
        }
    }
}