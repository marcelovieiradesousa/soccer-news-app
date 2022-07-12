package me.mcl.soccernews.data.remote;

import java.util.List;

import me.mcl.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.http.GET;


public interface SoccerNewsApi {
    @GET("news.json")
    Call<List<News>> getNews();
}
