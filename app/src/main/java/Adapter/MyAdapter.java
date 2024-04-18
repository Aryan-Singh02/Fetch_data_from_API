package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restapi.R;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.List;

import Model.PokemonData;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<PokemonData> itemList;

    public MyAdapter(Context context, List<PokemonData> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PokemonData data = itemList.get(position);
        String imageUrl = data.getImage();
        Picasso.get().load(imageUrl).into(holder.img);
        holder.title.setText(data.getName());
        holder.type.setText(data.getType());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title,type;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.pokemonImg);
            title = itemView.findViewById(R.id.pokemonName);
            type = itemView.findViewById(R.id.PokemonType);

        }
    }
}
