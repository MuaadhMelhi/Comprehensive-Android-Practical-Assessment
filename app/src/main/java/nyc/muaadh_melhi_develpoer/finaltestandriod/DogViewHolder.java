package nyc.muaadh_melhi_develpoer.finaltestandriod;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by c4q on 2/25/18.
 */

class DogViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView dogImage;
    private static final String URL = "url";
    private String url;

    public DogViewHolder(View itemView) {
        super(itemView);
        dogImage = itemView.findViewById(R.id.itemview_image);
        dogImage.setOnClickListener(this);
    }

    public void onBind(String s) {
        Picasso.with(itemView.getContext()).load(s).into(dogImage);
        url = s;
    }

    @Override
    public void onClick(View v) {
        Context context = itemView.getContext();
        Intent intent = new Intent(context, PhotoActivity.class);
        intent.putExtra(URL, url);
        Log.d("onClick: ", url);
        context.startActivity(intent);

    }
}
