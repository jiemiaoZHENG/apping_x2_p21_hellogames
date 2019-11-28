package fr.epita.android.hellogames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         val items = IntArray(4)
         val idGame = IntArray(4)

         val baseURL = "https://androidlessonsapi.herokuapp.com/api/game/"
         val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())

         val retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(jsonConverter)
                .build()

         val service = retrofit.create(WSInterface :: class.java)

         val callback: Callback<List<listGames>> = object: Callback<List<listGames>> {
                override fun onFailure(call: Call<List<listGames>>, t: Throwable) {
                    Log.w("TAG", "WebService call failed")
                }

                override fun onResponse(call: Call<List<listGames>>,
                                        response: Response<List<listGames>>) {
                    if (response.code() == 200) {
                        val res= response.body()

                        items[0] = (0..8).random()
                        idGame[0] = res?.get(items[0])!!.id
                        Glide.with(this@MainActivity)
                            .load(res?.get(items[0])?.urlPicture)
                            .into(activity_main_img_1)

                        items[1] = (0..8).random()
                        idGame[1] = res?.get(items[1])!!.id
                        Glide.with(this@MainActivity)
                            .load(res?.get(items[1])?.urlPicture)
                            .into(activity_main_img_2)

                        items[2] = (0..8).random()
                        idGame[2] = res?.get(items[2])!!.id
                        Glide.with(this@MainActivity)
                            .load(res?.get(items[2])?.urlPicture)
                            .into(activity_main_img_3)

                        items[3] = (0..8).random()
                        idGame[3] = res?.get(items[3])!!.id
                        Glide.with(this@MainActivity)
                            .load(res?.get(items[3])?.urlPicture)
                            .into(activity_main_img_4)

                    }
                }
            }
            service.getlistGames().enqueue(callback)
        }
}
