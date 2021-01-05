package com.example.app_description_apiary.repository


import com.example.app_description_apiary.service.UserService
import com.example.app_description_apiary.ui.viewModel.UserViewModel
import com.example.app_description_apiary.ui.fragment.initRetrofit
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val viewModelModule = module {
    viewModel { (UserViewModel()) }
}

val repositoryModule = module {
    single { UserRepository(get())  }
}


val serviceModule = module {
    single { initRetrofit()}
    single { get<Retrofit>().create(UserService::class.java) }
}