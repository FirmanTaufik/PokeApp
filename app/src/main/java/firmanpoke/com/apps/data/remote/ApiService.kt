package firmanpoke.com.apps.data.remote

import firmanpoke.com.apps.ui.presentation.detail.DetailModelResponse
import firmanpoke.com.apps.ui.presentation.main.home.HomeModelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon?")
    suspend fun getListPokemon(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Response<HomeModelResponse>


    @GET("pokemon/{name}")
    suspend fun getDetail(@Path("name") name: String): Response<DetailModelResponse>
}