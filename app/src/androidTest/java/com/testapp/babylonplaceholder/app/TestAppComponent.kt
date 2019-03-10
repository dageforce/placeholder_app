package com.testapp.babylonplaceholder.app

import android.app.Application
import com.testapp.babylonplaceholder.data.network.PlaceholderApi
import com.testapp.babylonplaceholder.data.repository.PlaceholderRepositoryImpl
import com.testapp.babylonplaceholder.data.transformer.AndroidSchedulerTransformer
import com.testapp.babylonplaceholder.domain.repository.PlaceholderRepository
import com.testapp.babylonplaceholder.domain.transformer.SchedulerTransformer
import com.testapp.babylonplaceholder.presentation.di.ActivityBuilder
import com.testapp.babylonplaceholder.presentation.di.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        TestApplicationModule::class,
        ViewModelModule::class,
        ActivityBuilder::class]
)
interface TestAppComponent : AndroidInjector<DaggerApplication> {

    override fun inject(application: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): TestAppComponent
    }
}

@Module
class TestApplicationModule {

    @Provides
    @Singleton
    fun providePlaceHolderApi(): PlaceholderApi {
        return TestPlaceHolderApi()
    }

    @Provides
    internal fun providesSchedulerTransformer(): SchedulerTransformer {
        return AndroidSchedulerTransformer(
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

    @Singleton
    @Provides
    internal fun providePlaceHolderRepository(api: PlaceholderApi): PlaceholderRepository {
        return PlaceholderRepositoryImpl(api)
    }

}
