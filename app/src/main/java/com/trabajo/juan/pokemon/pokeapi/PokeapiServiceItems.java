package com.trabajo.juan.pokemon.pokeapi;

import com.trabajo.juan.pokemon.models.ItemsRespuesta;
import com.trabajo.juan.pokemon.models.PokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface PokeapiServiceItems
{
    @GET("item")
    Call<ItemsRespuesta> obtenerListaItems(@Query("limit") int limit, @Query("offset") int offset);

}
