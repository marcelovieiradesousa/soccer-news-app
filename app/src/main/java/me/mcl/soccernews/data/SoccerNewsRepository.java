package me.mcl.soccernews.data;

import androidx.room.Room;

import me.mcl.soccernews.App;
import me.mcl.soccernews.data.local.AppDatabase;
import me.mcl.soccernews.data.remote.SoccerNewsApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SoccerNewsRepository {

    //region - Constantes
    private static final String REMOTE_API_URL="https://marcelovieiradesousa.github.io/soccer-news-api/";
    private static final String LOCAL_DB_NAME="soccer-news";
    //endregion

    //region - Atributos: encapsulam o acesso a nossa API (Retrofit) e banco de dados local (Room).
    private SoccerNewsApi remoteApi;
    private AppDatabase localDb;
    //endregion

    //region - Singleton: garante uma instância única dos atributos relacionados ao Retrofit e Room.
    private SoccerNewsRepository() {
        remoteApi=new Retrofit.Builder()
                .baseUrl(REMOTE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SoccerNewsApi.class);

        localDb=Room.databaseBuilder(App.getInstance(), AppDatabase.class, LOCAL_DB_NAME).build();
    }

    public static SoccerNewsRepository getInstance() {
        return LazyHolder.INSTANCE;
    }

    public SoccerNewsApi getRemoteApi() {
        return remoteApi;
    }

    public AppDatabase getLocalDb() {
        return localDb;
    }

    private static class LazyHolder {
        private static final SoccerNewsRepository INSTANCE=new SoccerNewsRepository();
    }
    //endregion
}
