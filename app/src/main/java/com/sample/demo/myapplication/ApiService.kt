package tw.com.test.retrofitdemo

import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("/posts")
    fun getPosts(): Single<List<Posts>>
}
