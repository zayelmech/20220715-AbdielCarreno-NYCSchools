package com.imecatro.nyc_schools.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imecatro.nyc_schools.model.Score
import com.imecatro.nyc_schools.network.SchoolRepository
import com.imecatro.nyc_schools.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * This is my ViewModel
 * In simple terms this is the most valuable player which means that it is in charge of handle all the data
 * It get the data from the repository and then it update the data in the LiveData
 * once the data change in the LiveData, the Views can react to the changes
 * I am using Dependency injection
 *
 * */

@HiltViewModel
class SchoolViewModel @Inject constructor(
    private val schoolRepository: SchoolRepository
) : ViewModel() {

    /**
     * schools is
     */
    private val _schools: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val schools: LiveData<UIState> get() = _schools

    private val _scoreData = MutableLiveData<List<Score>>()
    val scoreData: LiveData<List<Score>> get() = _scoreData

    fun getSchoolsList() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = schoolRepository.getSchools()
                response.body()?.let {
                    withContext(Dispatchers.Main) {
                        _schools.value = UIState.SUCCESS(it)
                    }
                } ?: throw Exception("DATA IS NULL")

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    //here you moved to the main thread
                    //you update de UI
                    _schools.value = UIState.ERROR(e)
                }
                Log.d("CLASS::${javaClass.simpleName} MESSAGE ->", e.message.toString())
            }
        }
    }
    fun getThisScore(dbn:String){
        CoroutineScope(Dispatchers.IO).launch {
            val response= schoolRepository.getSatResults(dbn)
            if (response.isSuccessful){
                _scoreData.postValue(response.body())
            }
            else{
                _scoreData.postValue(emptyList())
            }
        }
    }

}