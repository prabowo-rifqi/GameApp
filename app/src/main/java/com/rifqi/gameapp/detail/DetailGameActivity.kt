package com.rifqi.gameapp.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.rifqi.core.data.Resource
import com.rifqi.core.domain.model.Game
import com.rifqi.core.utils.DateUtils
import com.rifqi.gameapp.R
import com.rifqi.gameapp.databinding.ActivityDetailGameBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailGameActivity : AppCompatActivity() {

    private val detailGameViewModel: DetailGameViewModel by viewModel()
    private var statusFavorite: Boolean = false

    private val binding: ActivityDetailGameBinding by lazy {
        ActivityDetailGameBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val gameId = intent.getIntExtra(EXTRA_DATA, 0)
        statusFavorite = intent.getBooleanExtra(EXTRA_IS_FAVORITE, false)

        setStatusFavorite(statusFavorite)

        Log.d("asd", "onCreate: $statusFavorite")

        showDetailGame(gameId)
    }

    private fun showDetailGame(gameId: Int) {
        val fab = binding.fab
        detailGameViewModel.getDetailGame(gameId).observe(this) { detailGame ->
            when (detailGame) {
                is Resource.Loading -> {
                    showLoadingState(true)
                    fab.hide()
                }

                is Resource.Success -> {
                    showLoadingState(false)
                    fab.show()
                    detailGame.data?.let { game ->
                        bindGameDetails(game)
                        fab.setOnClickListener {
                            toggleFavoriteStatus(game)
                        }
                    }
                }

                is Resource.Error -> {
                    showLoadingState(false)
                    fab.hide()
                    Toast.makeText(
                        this,
                        detailGame.message ?: "An error occurred",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun toggleFavoriteStatus(game: Game) {
        statusFavorite = !statusFavorite
        detailGameViewModel.setFavoriteGame(game, statusFavorite)
        setStatusFavorite(statusFavorite)
    }

    private fun bindGameDetails(game: Game) {
        binding.collapsingToolbar.title = game.name
        with(binding.contentDetailGame) {
            tvRating.text = getString(R.string.rating, game.rating)
            tvReleaseDate.text = DateUtils.formatDate(game.releaseDate)
            tvDescription.text = game.description
        }
        Glide.with(this)
            .load(game.imageUrl)
            .into(binding.ivDetailImage)
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        val drawableRes =
            if (statusFavorite) R.drawable.ic_favorite_white else R.drawable.ic_not_favorite_white
        binding.fab.setImageDrawable(ContextCompat.getDrawable(this, drawableRes))
    }

    private fun showLoadingState(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.loadingOverlay.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_IS_FAVORITE = "extra_is_favorite"
    }
}