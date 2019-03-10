package com.testapp.babylonplaceholder.domain.usecase

import io.reactivex.Single

abstract class SingleParameterisedUseCase<T, P> : BaseUseCase() {

    protected abstract fun build(params: P): Single<T>

    private fun make(params: P, wrap: Boolean): Single<T> {
        if (params == null) {
            throw IllegalArgumentException("Params must be defined")
        }

        val single = build(params)
        return if (wrap) wrap(single) else single
    }

    fun get(params: P): Single<T> {
        return make(params, true)
    }

    fun chain(params: P): Single<T> {
        return make(params, false)
    }

    private fun wrap(observable: Single<T>): Single<T> {
        return observable.compose(schedulerTransformer().applySingleSchedulers())
    }

}
