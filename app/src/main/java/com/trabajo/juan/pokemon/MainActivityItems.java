package com.trabajo.juan.pokemon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.trabajo.juan.pokemon.models.Items;
import com.trabajo.juan.pokemon.models.ItemsRespuesta;
import com.trabajo.juan.pokemon.pokeapi.PokeapiServiceItems;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivityItems extends AppCompatActivity implements View.OnClickListener
{
    private static final String TAG = "POKEDEX";

    private Retrofit retrofit;

    private RecyclerView recyclerView;
    private ListaItemsAdapter listaItemsAdapter;

    private int offset;

    private boolean aptoParaCargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listaItemsAdapter = new ListaItemsAdapter(this);
        recyclerView.setAdapter(listaItemsAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (aptoParaCargar) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, " Llegamos al final.");

                            aptoParaCargar = false;
                            offset += 20;
                            obtenerDatos(offset);
                        }
                    }
                }
            }
        });


        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        aptoParaCargar = true;
        offset = 0;
        obtenerDatos(offset);
    }

    private void obtenerDatos(int offset) {
        PokeapiServiceItems service = retrofit.create(PokeapiServiceItems.class);
        Call<ItemsRespuesta> itemsRespuestaCall = service.obtenerListaItems(20, offset);

        itemsRespuestaCall.enqueue(new Callback<ItemsRespuesta>() {
            @Override
            public void onResponse(Call<ItemsRespuesta> call, Response<ItemsRespuesta> response) {
                aptoParaCargar = true;
                if (response.isSuccessful()) {

                    ItemsRespuesta itemsRespuesta = response.body();
                    ArrayList<Items> listaItems = itemsRespuesta.getResults();

                    listaItemsAdapter.adicionarListaItems(listaItems);

                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ItemsRespuesta> call, Throwable t) {
                aptoParaCargar = true;
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });
    }

    public void onClick(View v)
    {
        Intent intent = new Intent(MainActivityItems.this, MainActivityPokemon.class);
        startActivity(intent);
    }
}