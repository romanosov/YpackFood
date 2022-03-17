package com.example.ypackfood.viewModels

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ypackfood.repository.Repository
import com.example.ypackfood.retrofit.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {
    private var mainRepository: Repository

    init {
        val x = RetrofitBuilder.apiService
        mainRepository = Repository(x)
    }


    lateinit var listContentState: LazyListState
        private set
    lateinit var listCategoryState: LazyListState
        private set
    lateinit var scaffoldState: ScaffoldState
        private set
    var toolbarOffsetState by mutableStateOf(0f)
        private set

    fun listContentStateInit(newListState: LazyListState) {
        listContentState = newListState
    }
    fun listCategoryStateInit(newListState: LazyListState) {
        listCategoryState = newListState
    }
    fun scaffoldStateInit(newScaffoldState: ScaffoldState) {
        scaffoldState = newScaffoldState
    }
    fun setToolbarOffset(newOffsetPx: Float) {
        toolbarOffsetState = newOffsetPx
    }

    init {
        getHello()
        getCard()
        getPath()
        getBody()
        getTea()
        getError()
    }

    private fun getHello() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = mainRepository.getHello()
                if (response.isSuccessful)
                    Log.d("hello ok ", response.body().toString())
                else
                    Log.d("hello not ok ", response.message().toString())
            } catch (e: Exception) {
                Log.d("hello error ", e.toString())
            }
        }
    }

    private fun getCard() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = mainRepository.getCard("card Vizda and Masturbate")
                if (response.isSuccessful)
                    Log.d("card ok ", response.body().toString())
                else
                    Log.d("card not ok ", response.message().toString())
            } catch (e: Exception) {
                Log.d("card error ", e.toString())
            }
        }
    }

    private fun getPath() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = mainRepository.getPath(13)
                if (response.isSuccessful)
                    Log.d("path ok ", response.body().toString())
                else
                    Log.d("path not ok ", response.message().toString())
            } catch (e: Exception) {
                Log.d("path error ", e.toString())
            }
        }
    }

    private fun getBody() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = mainRepository.getBody("13")
                if (response.isSuccessful)
                    Log.d("body ok ", response.body().toString())
                else
                    Log.d("body not ok ", response.message().toString())
            } catch (e: Exception) {
                Log.d("body error ", e.toString())
            }
        }
    }

    private fun getTea() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = mainRepository.getTea()
                val str = response.errorBody()!!.string() + response.code().toString()
                if (response.isSuccessful)
                    Log.d("tea ok ", str)
                else
                    Log.d("tea not ok ", str)
            } catch (e: Exception) {
                Log.d("tea error ", e.toString())
            }
        }
    }

    private fun getError() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = mainRepository.getError()
                if (response.isSuccessful)
                    Log.d("err1 ok ", response.body().toString())
                else
                {
                    Log.d("err1 not ok ",
                        response.errorBody()!!.string() + response.code().toString()
                    )
                }

            } catch (e: Exception) {
                Log.d("err1 error ", e.toString())
            }
        }
    }
}