package com.trabajo.juan.pokemon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.trabajo.juan.pokemon.models.Items;

import java.util.ArrayList;


public class ListaItemsAdapter extends RecyclerView.Adapter<ListaItemsAdapter.ViewHolder> {

    private ArrayList<Items> dataset ;

    private Context context;

    public ListaItemsAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Items i = dataset.get(position);
        holder.nombreTextView.setText(i.getName());

        Glide.with(context)
                .load("http://pokeapi.co/media/sprites/items/" + i.getName() + ".png")
                .centerCrop()
                .crossFade()
                .placeholder(R.drawable.emoticon_regalo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaItems(ArrayList<Items> listaItems) {
        dataset.addAll(listaItems);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView fotoImageView;
        private TextView nombreTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            fotoImageView = (ImageView) itemView.findViewById(R.id.fotoImageView);
            nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
        }
    }
}