package com.testapp.babylonplaceholder.domain.usecase

import io.reactivex.Single

abstract class SingleUseCase<T> : BaseUseCase() {

    protected abstract fun build(): Single<T>

    private fun make(wrap: Boolean): Single<T> {
        val single = build()
        return if (wrap) wrap(single) else single
    }

    fun get(): Single<T> {
        return make(true)
    }

    fun chain(): Single<T> {
        return make(false)
    }

    private fun wrap(observable: Single<T>): Single<T> {
        return observable.compose(schedulerTransformer().applySingleSchedulers())
    }
}
