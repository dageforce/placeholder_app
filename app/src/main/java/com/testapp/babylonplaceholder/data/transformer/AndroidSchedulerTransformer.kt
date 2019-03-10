package com.testapp.babylonplaceholder.data.transformer

import com.testapp.babylonplaceholder.domain.transformer.SchedulerTransformer
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleTransformer
import javax.inject.Inject

class AndroidSchedulerTransformer @Inject constructor(
    private val subscribeScheduler: Scheduler,
    private val observeScheduler: Scheduler
) : SchedulerTransformer {

    override fun <T> applySingleSchedulers(): SingleTransformer<T, T> {
        return SingleTransformer { single ->
            single.subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
        }
    }

}
