package com.intell.comm.base.repository

import com.intell.comm.localDatabase.post.PostModel
import com.intell.comm.localDatbase.language.LanguageResponse
import com.intell.comm.model.*
import com.intell.comm.network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 * This class use for handle data operation from API
 * get the data from API and updated using Flow.
 * @param apiServiceImpl use for background threads
 * */
class MainApiRepository
@Inject
constructor(private val apiServiceImpl: ApiServiceImpl) {
    /**
     * get translation API data
     * */
    fun getLanguageData(): Flow<LanguageResponse> = flow {
        emit(apiServiceImpl.getLanguageData())
    }.flowOn(Dispatchers.IO)

    /**
     * @see simpleApiResponsePost use for a API which response [SimpleApiResponse] model.
     * */
    fun simpleApiResponsePost(url: String, map: Map<String, String>): Flow<SimpleApiResponse> =
        flow {
            emit(apiServiceImpl.simpleApiResponsePost(url, map))
        }.flowOn(Dispatchers.IO)

    fun registerLoginAccount(isLogin: Boolean, map: HashMap<String, String>): Flow<ApiResponse<RegisterLoginModel>> = flow {
        emit(apiServiceImpl.registerLoginAccount(isLogin,map))
    }.flowOn(Dispatchers.IO)

    fun getAllRentAndPostRent(isGet: Boolean, map: HashMap<String, String>): Flow<ApiResponse<List<PostModel>>> = flow {
        emit(apiServiceImpl.getAllRentAndPostRent(isGet,map))
    }.flowOn(Dispatchers.IO)

}