package com.testapp.babylonplaceholder.domain.usecase

import com.testapp.babylonplaceholder.domain.transformer.SchedulerTransformer
import javax.inject.Inject

abstract class BaseUseCase {
    lateinit var schedulerTransformer: SchedulerTransformer
        @Inject set

    protected fun schedulerTransformer(): SchedulerTransformer {
        return schedulerTransformer
    }
}
