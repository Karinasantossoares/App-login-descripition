package com.example.app_description_apiary.repository


import com.example.app_description_apiary.service.UserService
import com.example.app_description_apiary.ui.viewModel.LoginViewModel
import com.example.app_description_apiary.ui.fragment.initRetrofit
import com.example.app_description_apiary.ui.viewModel.DetailsViewModel
import com.example.app_description_apiary.ui.viewModel.ForgetViewModel
import com.example.app_description_apiary.useCase.UserUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val viewModelModule = module {
    viewModel { (LoginViewModel(androidContext(), get())) }
    viewModel { DetailsViewModel(androidContext(), get()) }
    viewModel { ForgetViewModel(androidContext(), get()) }

}

val repositoryModule = module {
    single { UserRepository(get()) }
}


val serviceModule = module {
    single { initRetrofit() }
    single { get<Retrofit>().create(UserService::class.java) }
}

val useCaseModule = module {
    single { UserUseCase(get(),get()) }
}