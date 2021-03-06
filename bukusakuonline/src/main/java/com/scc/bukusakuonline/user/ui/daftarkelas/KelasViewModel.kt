package com.scc.bukusakuonline.user.ui.daftarkelas

import android.content.Context
import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scc.bukusakuonline.user.connection.ApiService
import com.scc.bukusakuonline.user.connection.RetroConfig
import com.scc.bukusakuonline.user.model.kelas.KelasItems
import com.scc.bukusakuonline.user.model.kelas.KelasResponse

import retrofit2.Call
import retrofit2.Response

class KelasViewModel : ViewModel(){
    private  var listPoint : MutableLiveData<List<KelasItems>> = MutableLiveData()


    fun loadData(context: Context, jurusan :String){
        d("viewmodel", "viewmodel")
        val sharedPreferences = context.getSharedPreferences("PREF", Context.MODE_PRIVATE)
        val token ="Bearer "+ sharedPreferences.getString("TOKEN","abc")
        RetroConfig.getRetrofit().create(ApiService::class.java).getKelas(token,jurusan).enqueue(object : retrofit2.Callback<KelasResponse>{
            override fun onFailure(call: Call<KelasResponse>, t: Throwable) {
                d("error",t.message)
            }

            override fun onResponse(call: Call<KelasResponse>, response: Response<KelasResponse>) {
                response.body()?.data.let {
                    listPoint.postValue(it)
                }
            }
        })
    }
    val listData: LiveData<List<KelasItems>> = listPoint
}