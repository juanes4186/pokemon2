package com.trabajo.juan.pokemon.pokeapi;



import com.trabajo.juan.pokemon.models.Pokemon;
import com.trabajo.juan.pokemon.models.PokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("pokemon")
    Call<PokemonRespuesta> obtenerListaPokemon(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon/{id}/")
    Call<Pokemon> obtenerPokemon(@Path("id")int id);

}
