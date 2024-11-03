package br.edu.utfpr.minhaprimeiraapi_app.service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
	private const val BASE_URL = "http://10.0.2.2:3000/"
	//Endereco usando para acessar o servidor dentro do emulador Android

	private val instance: Retrofit by lazy{
		Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}
	val apiService: ApiService = instance.create(ApiService::class.java)
}

