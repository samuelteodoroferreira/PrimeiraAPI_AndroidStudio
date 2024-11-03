package br.edu.utfpr.minhaprimeiraapi_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.utfpr.minhaprimeiraapi_app.databinding.ActivityItemDetailBinding
import br.edu.utfpr.minhaprimeiraapi_app.model.Item
import br.edu.utfpr.minhaprimeiraapi_app.service.Result
import br.edu.utfpr.minhaprimeiraapi_app.service.RetrofitClient
import br.edu.utfpr.minhaprimeiraapi_app.service.safeApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ItemDetailActivity : AppCompatActivity() {


	private lateinit var binding: ActivityItemDetailBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityItemDetailBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setupView()
		loadItem()
	}

	private fun setupView() {
		setSupportActionBar(binding.toolbar)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		supportActionBar?.setDisplayShowHomeEnabled(true)
		binding.toolbar.setNavigationOnClickListener {
			finish()
		}

	}

	companion object{ //Isso e um metodo estatico
		private const val ARG_ID = "ARG_ID"
		fun newIntent(
			context: Context,
			ItemId: String,
		)=
			Intent(context, ItemDetailActivity::class.java).apply{
				putExtra(ARG_ID, ItemId)
			}
	}

	//carregando os item na segunda janela
	private fun loadItem() {
		val itemId = intent.getStringExtra(ARG_ID)?:""
		CoroutineScope(Dispatchers.IO).launch {
			val result: Result<Item> = safeApiCall { RetrofitClient.apiService.getItem(itemId) }

			}
		}

	}

