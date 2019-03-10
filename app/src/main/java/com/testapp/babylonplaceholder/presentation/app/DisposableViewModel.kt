package com.testapp.babylonplaceholder.presentation.app

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign

abstract class DisposableViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun Disposable.disposedBy(vm: DisposableViewModel) {
        vm.disposables += this
    }
}
