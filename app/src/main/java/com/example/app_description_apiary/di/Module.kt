package com.example.app_description_apiary.di


import android.content.Context
import com.example.app_description_apiary.data.ResponseUser
import com.example.app_description_apiary.monitoring.AuthenticateAnalytics
import com.example.app_description_apiary.monitoring.CheckDetailsAnalytcs
import com.example.app_description_apiary.monitoring.ForgotPasswordAnalytcs
import com.example.app_description_apiary.persistence.preferences.AppPreferences
import com.example.app_description_apiary.repository.UserRepository
import com.example.app_description_apiary.service.UserService
import com.example.app_description_apiary.ui.fragment.initRetrofit
import com.example.app_description_apiary.ui.viewModel.*
import com.example.app_description_apiary.useCase.UserUseCase
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val viewModelModule = module {
    viewModel { (LoginViewModel(androidContext(), get(), get(), get())) }
    viewModel { (responseUser: ResponseUser) -> DetailsViewModel(androidContext(), get(), get(), responseUser, get()) }
    viewModel { ForgotViewModel(androidContext(), get(),get()) }
    viewModel { RegisterViewModel(androidContext(), get(),get()) }

}

val repositoryModule = module {
    single { UserRepository(get()) }
}


val serviceModule = module {
    single { initRetrofit() }
    single { get<Retrofit>().create(UserService::class.java) }
}

val useCaseModule = module {
    single { UserUseCase(get(), get(),get()) }
}

val localPersistenceModule = module {
    single {
        androidContext().getSharedPreferences(AppPreferences.NAME, Context.MODE_PRIVATE)
    }
    single { AppPreferences(get()) }
}

val firebaseAuthenticateModule = module {
    single { Firebase.analytics }
    single { AuthenticateAnalytics(get()) }
    single{CheckDetailsAnalytcs(get())}
    single { ForgotPasswordAnalytcs(get()) }
}