package com.imecatro.nyc_schools.network

import com.imecatro.nyc_schools.model.Schools
import com.imecatro.nyc_schools.model.Score
import retrofit2.Response
import javax.inject.Inject

interface SchoolRepository {

    suspend fun getSchools(): Response<List<Schools>>
    suspend fun getSatResults(dbn: String): Response<List<Score>>
}

class SchoolRepositoryImp @Inject constructor(
    private val schoolService: SchoolService
) : SchoolRepository {

    override suspend fun getSchools(): Response<List<Schools>> =
        schoolService.getSchools()

    override suspend fun getSatResults(dbn: String): Response<List<Score>> {
        return schoolService.getSatResults(dbn)
    }

}
