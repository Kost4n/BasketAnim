package dropsscs.basket.ball.gamez

import android.app.AlertDialog
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import dropsscs.basket.ball.gamez.databinding.ActivityPlugBinding


class PlugActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlugBinding
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlugBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        binding.gifView.setImageResource(R.drawable.anim_bouncing_ball)

        binding.buttonStart.setOnClickListener {
            val random = getRandom()
            val gif = pl.droidsonroids.gif.GifDrawable(resources, getAnim(random))
            gif.loopCount = 1

            binding.gifView.setImageDrawable(gif)

            gif.addAnimationListener { showResult(random) }
        }
    }

    private fun getAnim(random: Int): Int {
        return when (random) {
            1 -> return R.drawable.anim_hit_clear
            2 -> return R.drawable.anim_hit_ring
            3 -> return R.drawable.anim_stuck
            4 -> return R.drawable.anim_miss_ring
            5 -> return R.drawable.anim_miss_shield
            else -> 0
        }
    }

    private fun getRandom() = (1..5).random()

    private fun showResult(random: Int) {
        val text = " scored"
        val title = if (random == 1 || random == 2) "You win!" else "You miss"
        if (random == 1 || random == 2) score++ else score = 0
        val builder = AlertDialog.Builder(this@PlugActivity)
            .setTitle(title)
            .setPositiveButton("Ok") { d, _ ->
                d.dismiss()
            }
            .setCancelable(true)
            .show()
        binding.textviewScore.text = "$score$text"
    }
}