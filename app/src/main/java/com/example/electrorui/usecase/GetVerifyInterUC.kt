package com.example.electrorui.usecase

import com.example.electrorui.db.RepositoryApp
import javax.inject.Inject

class GetVerifyInterUC @Inject constructor(
    private val repository : RepositoryApp
) {
    suspend operator fun invoke(): Boolean {
        return repository.verifyInterFromApi()
    }
}