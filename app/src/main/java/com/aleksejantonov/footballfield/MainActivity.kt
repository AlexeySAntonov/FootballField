package com.aleksejantonov.footballfield

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_player.view.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { FieldAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initList()
        setPlayers()
    }

    private fun initList() {
        recycler.adapter = adapter
    }

    private fun setPlayers() {
        recycler.onGlobalLayout {
            for (player in getFakePlayers()) {
                val view = LayoutInflater.from(this@MainActivity).inflate(R.layout.item_player, null)
                view.number.text = player.number.toString()
                view.name.text = player.name
                view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
                val viewWidth = view.measuredWidth
                val playerCircleHeight = getPxFromDp(PLAYER_CIRCLE_DIMENSION)
                val cellWidth = getPxFromDp(CELL_WIDTH)
                val cellHeight = getPxFromDp(CELL_HEIGHT)
                val horizontalBias = (viewWidth - cellWidth) / 2 - recycler.x
                val verticalBias = (playerCircleHeight - cellHeight) / 2 - recycler.y

                val cellView = recycler.findViewHolderForLayoutPosition(player.position - 1)?.itemView
                val cellX = cellView?.x ?: 0f
                val cellY = cellView?.y ?: 0f
                view.x = cellX - horizontalBias
                view.y = cellY - verticalBias

                rootLayout.addView(view)
            }
        }
    }

    private fun getFakePlayers(): List<Player> {
        return mutableListOf<Player>().apply {
            add(Player(name = "Agurero", number = 10, position = 13))
            add(Player(name = "Zane", number = 19, position = 15))
            add(Player(name = "De Hruyne", number = 17, position = 32))
            add(Player(name = "Silva", number = 21, position = 46))
            add(Player(name = "Runner", number = 2, position = 49))
            add(Player(name = "Ferjinandino", number = 25, position = 51))
            add(Player(name = "Andy", number = 22, position = 54))
            add(Player(name = "Stone", number = 5, position = 75))
            add(Player(name = "Company", number = 4, position = 77))
            add(Player(name = "Aporte", number = 14, position = 79))
            add(Player(name = "Emerson", number = 1, position = 95))
        }
    }

    companion object {
        const val CELL_WIDTH = 33
        const val CELL_HEIGHT = 33
        const val PLAYER_CIRCLE_DIMENSION = 40
    }
}
