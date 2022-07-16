package com.imecatro.nyc_schools.network

import com.imecatro.nyc_schools.model.Schools
import com.imecatro.nyc_schools.model.Score
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolService {

    @GET(SCHOOLS)
     suspend fun getSchools(): Response<List<Schools>>


     @GET(SCORE_ENDPOINT)
    suspend fun getSatResults(
         @Query("dbn") dbn : String
     ): Response<List<Score>>

    companion object {
        const val BASE_URL = "https://data.cityofnewyork.us/resource/"
        const val SCHOOLS = "s3k6-pzi2.json"
        const val SCORE_ENDPOINT = "f9bf-2cp4.json"
    }
}
