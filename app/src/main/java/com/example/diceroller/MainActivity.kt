package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Size
import android.view.View
import com.example.diceroller.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var currentPlayer: Int = 1
    private var playerOneScore: Int = 0
    private var playerSecondScore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        currentPlayer = 1

        val htmlText = "Player <b>${currentPlayer.toString()}</b> turn"
        binding.textview21.text = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY)

        binding.rollBtn.setOnClickListener {

            binding.playAgain.visibility = View.GONE

            val randomNum: Int = Random.nextInt(6) + 1
            val drawableRes = when (randomNum) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
            binding.dice.setImageResource(drawableRes)

            if (currentPlayer == 1) {
                playerOneScore += randomNum
                binding.p1score.text = playerOneScore.toString()
                currentPlayer = 2
            } else {
                playerSecondScore += randomNum
                binding.p2score.text = playerSecondScore.toString()
                currentPlayer = 1
            }
            var htmlText = "Player <b>${currentPlayer.toString()}</b> turn"
            binding.textview21.text = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY)

            if (playerOneScore >= 20) {
                binding.winnerText.text = getString(R.string.player_1_is_winner)
                binding.winnerText.setBackgroundResource(R.drawable.winner_bg)
                binding.imageView.visibility = View.VISIBLE
                binding.p1score.setTextColor(resources.getColor(R.color.green))
                binding.p2score.setTextColor(resources.getColor(R.color.red))
                binding.playAgain.visibility = View.VISIBLE
                binding.rollBtn.isClickable = false
            } else if (playerSecondScore >= 20) {
                binding.winnerText.text = getString(R.string.player_2_is_winner)
                binding.winnerText.setBackgroundResource(R.drawable.winner_bg)
                binding.p2score.setTextColor(resources.getColor(R.color.green))
                binding.p1score.setTextColor(resources.getColor(R.color.red))
                binding.imageView.visibility = View.VISIBLE
                binding.playAgain.visibility = View.VISIBLE
                binding.rollBtn.isCheckable = false
            }

        }

        binding.playAgain.setOnClickListener {
            binding.rollBtn.isClickable = true
            binding.winnerText.text = getString(R.string.winner_score_30)
            binding.imageView.visibility = View.GONE
            binding.winnerText.setBackgroundResource(R.drawable.winner_bg2)
            binding.p1score.text = getString(R.string._0)
            binding.p2score.text = getString(R.string._0)
            binding.p1score.setTextColor(resources.getColor(R.color.black))
            binding.p2score.setTextColor(resources.getColor(R.color.black))
            currentPlayer = 1
            val htmlText = "Player <b>${currentPlayer.toString()}</b> turn"
            binding.textview21.text = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY)
            playerOneScore = 0
            playerSecondScore = 0

        }

    }
}