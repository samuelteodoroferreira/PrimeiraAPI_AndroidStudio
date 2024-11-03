package br.edu.utfpr.minhaprimeiraapi_app.service

import br.edu.utfpr.minhaprimeiraapi_app.model.Item
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
	@GET("Items")
	suspend fun getItems(): List<Item>

	@GET("items/{id}")
	suspend fun getItem(@Path("id") id: String): Item
}