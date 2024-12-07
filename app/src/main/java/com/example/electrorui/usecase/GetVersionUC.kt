package com.example.electrorui.usecase

import com.example.electrorui.db.RepositoryApp
import com.example.electrorui.networkApi.model.UpdateModel
import javax.inject.Inject

class GetVersionUC @Inject constructor(
    private val repository : RepositoryApp
) {
    suspend operator fun invoke(): UpdateModel {

        val versionUP = repository.getVersionFromApi()

        return if(versionUP != null){
            versionUP
        } else {
            val nullVersion = UpdateModel("", "", false)
            nullVersion
        }
    }
}