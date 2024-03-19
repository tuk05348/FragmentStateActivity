package edu.temple.diceroll

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

const val DIE_SIDES = "dIcE_SiDeS"

class DiceFragment : Fragment() {
    private var sides: Int? = null
    private val CURRENT_DIE_ROLL_KEY: String = "currentDieRoll"
    private var currentDieRoll = 0
    lateinit var numberDisplayTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            sides = it.getInt(DIE_SIDES)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dice, container, false).apply {

            numberDisplayTextView = findViewById<TextView>(R.id.numberDisplay)
            findViewById<Button>(R.id.rollButton).setOnClickListener {
                currentDieRoll = Random.nextInt(sides!!) + 1
                numberDisplayTextView.text = (currentDieRoll).toString()

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedInstanceState?.run {
            currentDieRoll = getInt(CURRENT_DIE_ROLL_KEY, 0)
        }

        if (currentDieRoll == 0) {
            currentDieRoll = Random.nextInt(sides!!) + 1
            numberDisplayTextView.text = (currentDieRoll).toString()
        }
        else {
            numberDisplayTextView.text = (currentDieRoll).toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(CURRENT_DIE_ROLL_KEY, currentDieRoll)
    }

    companion object {

        @JvmStatic
        fun newInstance(sides: Int) =
            DiceFragment().apply {
                arguments = Bundle().apply {
                    putInt(DIE_SIDES, sides)
                }
            }
    }
}