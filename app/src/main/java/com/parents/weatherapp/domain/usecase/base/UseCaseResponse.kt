package com.parents.weatherapp.domain.usecase.base

import com.parents.weatherapp.domain.model.ErrorModel

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(errorModel: ErrorModel?)
}

