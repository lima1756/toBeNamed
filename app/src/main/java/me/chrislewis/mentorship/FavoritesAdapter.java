package me.chrislewis.mentorship;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {
    private Context mContext;
    private String favoriteKey;
    private List<ParseUser> favorites;

    public FavoritesAdapter(Context context, List<ParseUser> favorites) {
        mContext = context;
        this.favoriteKey = favoriteKey;
        this.favorites = favorites;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_favorite, viewGroup, false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final ParseUser user = favorites.get(i);
        user.fetchInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                viewHolder.tvName.setText(user.getUsername());
                Glide.with(mContext)
                        .load(user.getParseFile("profileImage").getUrl())
                        .into(viewHolder.ivProfile);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (favorites == null) {
            return 0;
        } else {
            return favorites.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfile;
        TextView tvName;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfile = itemView.findViewById(R.id.ivProfile);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}
