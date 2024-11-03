package br.edu.utfpr.minhaprimeiraapi_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.utfpr.minhaprimeiraapi_app.adapter.ItemAdapter
import br.edu.utfpr.minhaprimeiraapi_app.databinding.ActivityMainBinding
import br.edu.utfpr.minhaprimeiraapi_app.model.Item
import br.edu.utfpr.minhaprimeiraapi_app.service.Result
import br.edu.utfpr.minhaprimeiraapi_app.service.RetrofitClient
import br.edu.utfpr.minhaprimeiraapi_app.service.safeApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    override fun onResume() {
        super.onResume()
        fetchItems()
    }

    private fun fetchItems() {
        //Alterando execucao para IO Thread (Theread de ebtrada e saida)
        CoroutineScope(Dispatchers.IO).launch {
            val result = safeApiCall{ RetrofitClient.apiService.getItems()}

            //Alterando para a main thead (Thread principal)
            withContext(Dispatchers.Main){
                when(result){
                    is Result.Error -> {}
                    is Result.Success -> {
                        handleSuccess(result.data)
                    }
                }
            }
        }
    }

    private fun handleSuccess(data: List<Item>) {
        val adapter = ItemAdapter(data){
            //Listener do item clicado
            startActivity(ItemDetailActivity.newIntent(this,
                it.id))
        }
        binding.recyclerView.adapter = adapter
    }


    private fun setupView(){
        LinearLayoutManager(this).also { binding.recyclerView.layoutManager = it }

    }
}

