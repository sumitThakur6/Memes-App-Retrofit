package com.example.retrofit

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.android.volley.Network
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.retrofit.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MemeRepository(private val memeDao : MemeDao, private val context : Context) {

    private val memeLiveData = MutableLiveData<List<Memes>>()

    val memes : LiveData<List<Memes>> = memeLiveData

    suspend fun getMeme(){
        if(NetworkUtils.isOnline(context)){
            val queue = Volley.newRequestQueue(context)
            val apiUrl = "https://meme-api.com/gimme/2"
            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, apiUrl, null,{
                val jsonArray = it.getJSONArray("memes")
                val list = ArrayList<Memes>()
                for (i in 0 until jsonArray.length()){
                    val jsonObject = jsonArray.getJSONObject(i)
                    val title = jsonObject.getString("title")
                    val memeUrl = jsonObject.getString("url")
                    GlobalScope.launch {
                        val meme = Memes(title, getBitmap(memeUrl))
                        withContext(Dispatchers.Main){
                            memeDao.insertMeme(meme)
                            list.add(meme)
                            memeLiveData.postValue(list)
                        }
                    }
                } },
                {error ->
                    Log.d("Error", error.toString())
                })
            queue.add(jsonObjectRequest)
        }
         else{
             val result = memeDao.getMemes()
             memeLiveData.postValue(result)
        }
    }

    private suspend fun getBitmap(url : String) : Bitmap {
        val loading = ImageLoader(context)
        val request = ImageRequest.Builder(context).data(url).build()
        val result = (loading.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }
}

