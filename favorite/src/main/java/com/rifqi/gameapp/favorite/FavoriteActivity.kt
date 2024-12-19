package com.rifqi.gameapp.favorite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifqi.core.ui.GameAdapter
import com.rifqi.gameapp.detail.DetailGameActivity
import com.rifqi.gameapp.favorite.databinding.ActivityFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private val binding: ActivityFavoriteBinding by lazy {
        ActivityFavoriteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        supportActionBar?.title = "Favorite Games"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val gameAdapter = GameAdapter()
        gameAdapter.onItemClick = {
            val intent = Intent(this, DetailGameActivity::class.java)
            intent.putExtra(DetailGameActivity.EXTRA_DATA, it.gameId)
            intent.putExtra(DetailGameActivity.EXTRA_ISFAVORITE, it.isFavorite)
            startActivity(intent)
        }

        favoriteViewModel.favoriteGames.observe(this) { favoriteGame ->
            gameAdapter.submitList(favoriteGame)
            binding.viewEmpty.root.visibility =
                if (favoriteGame.isNotEmpty()) View.GONE else View.VISIBLE
        }
        with(binding.rvGame) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = gameAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        Log.d("FavoriteActivity", "Back button clicked")
        onBackPressedDispatcher.onBackPressed()
        return true
    }

}