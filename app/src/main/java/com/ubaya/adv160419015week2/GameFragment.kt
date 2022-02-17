package com.ubaya.adv160419015week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_game.*
import kotlin.random.Random

class GameFragment : Fragment() {
    var firstNum = Random.nextInt(1,50)
    var secondNum = Random.nextInt(1,50)
    var score = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtFirstNum.text = firstNum.toString()
        txtSecondNum.text = secondNum.toString()

        if(arguments != null) {
            var playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            txtTurn.text = "$playerName's Turn"
        }

        btnSubmit.setOnClickListener {
            val answer = txtInputAnswer.text.toString().toInt()

            if (answer == (firstNum + secondNum)) {
                score++

                firstNum = Random.nextInt(1,50)
                secondNum = Random.nextInt(1,50)

                txtFirstNum.text = firstNum.toString()
                txtSecondNum.text = secondNum.toString()
                txtInputAnswer.setText(0)
            } else {
                val playerScore = score
                val action = GameFragmentDirections.actionResultFragment(score)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}