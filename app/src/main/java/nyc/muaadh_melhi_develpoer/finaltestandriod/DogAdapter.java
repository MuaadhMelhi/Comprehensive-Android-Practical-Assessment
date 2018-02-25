package nyc.muaadh_melhi_develpoer.finaltestandriod;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by c4q on 2/25/18.
 */

public class DogAdapter extends RecyclerView.Adapter<DogViewHolder> {
    private List<String> dogImageList;

    public DogAdapter(List<String> dogImageList) {
        this.dogImageList = dogImageList;
    }

    @Override
    public DogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_layout, parent, false);
        return new DogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DogViewHolder holder, int position) {
        holder.onBind(dogImageList.get(position));
    }

    @Override
    public int getItemCount() {
        return dogImageList.size();
    }
}
