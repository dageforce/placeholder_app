package com.testapp.babylonplaceholder.domain.transformer

import io.reactivex.SingleTransformer

interface SchedulerTransformer {

    //Normally will have ones for all the rx types here

    fun <T> applySingleSchedulers(): SingleTransformer<T, T>

}
