
import com.example.appelwebexample.`class`.User
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL =
    "https://64343c2f582420e23177630d.mockapi.io/api/v1/project1/"

/**
 * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * The Retrofit object with the Moshi converter.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getUsers] method
 */
interface UserApiService {
    /**
     * Returns a [List] of [User] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "user" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("USER")
    suspend fun getUsers() : List<User>

    /**
     * Returns a [User] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "user" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("USER/{id}")
    suspend fun getUser(@Path("id") id: String) : User
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object UserApi {
    val retrofitService: UserApiService by lazy { retrofit.create(UserApiService::class.java) }
}
