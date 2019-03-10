package com.testapp.babylonplaceholder.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

class TestObserver<T> : Observer<T> {
    private val observedValues = mutableListOf<T>()

    override fun onChanged(value: T) {
        observedValues.add(value)
    }

    fun assertValueAt(position: Int, expectation: (T) -> Boolean): TestObserver<T> {
        val item = observedValues[position]
        assert(expectation(item))
        return this
    }

    fun assertLatest(expectation: (T) -> Boolean): TestObserver<T> {
        return assertValueAt(observedValues.size - 1, expectation)
    }

    fun assertIsEmpty() {
        assert(observedValues.isEmpty())
    }
}

fun <T> LiveData<T>.test() = TestObserver<T>().also(this::observeForever)
