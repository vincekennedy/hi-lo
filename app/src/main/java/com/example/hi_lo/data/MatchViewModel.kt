package com.example.hi_lo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hi_lo.ui.course

data class Team(val golfer1: Golfer = Golfer(), val golfer2: Golfer = Golfer())

data class Golfer(val name: String = "Test", val hcp: Int = (Math.random() * 36).toInt())

class MatchViewModel : ViewModel() {

    private var _title: MutableLiveData<String> = MutableLiveData<String>("Setup Match")
    val title: LiveData<String> get() = _title

    var team1: Team = Team()
    var team2: Team = Team()

    var pricePerPoint: MutableLiveData<Int> = MutableLiveData(1)

    val team1Score: MutableLiveData<Int> = MutableLiveData(0)
    val team2Score: MutableLiveData<Int> = MutableLiveData(0)
    val hole: MutableLiveData<Int> = MutableLiveData(1)

    fun addPointsToTeam1Score(pts: Int) {
        team1Score.value = team1Score.value?.plus(pts)
    }

    fun addPointsToTeam2Score(pts: Int) {
        team2Score.value = team2Score.value?.plus(pts)
    }

    fun startMatch(team1: Team, team2: Team) {
        this.team1 = team1
        this.team2 = team2
        setTitle(1)
    }

    fun hasNextHole(): Boolean {
        return hole.value!! < 18
    }

    fun nextHole() {
        hole.value = hole.value?.inc()
        setTitle(hole.value!!)
    }

    fun getFinalScore(): String {
        pricePerPoint.value?.let { price ->
            team1Score.value?.let { t1 ->
                team2Score.value?.let { t2 ->
                    val diff = t1.minus(t2)
                    if (diff > 0) return "Team 2 owes Team 1 $${diff.times(price)}"
                    else if (diff < 0) return "Team 1 owes Team 2 $${diff.times(price).times(-1)}"
                    else return "All tied up"
                }
            }
        }
        return "Something went wrong, figure it out yourself."
    }

    /**
     * Current Score
     *
     * A positive value means team 1 is winning, a negative value means team two is winning
     */
    private fun currentScore() = team2Score.value?.let { team1Score.value!!.minus(it) }

    /**
     * @param value - Hole Number
     */
    private fun setTitle(value: Int) {
        course.holes[value - 1].apply {
            _title.value =
                "Hole $holeNum     HCP: $holeHcp     PAR: $holePar   Score : ${currentScore()}"
        }
    }

    fun resetMatch() {
        team1Score.value = 0
        team2Score.value = 0
        hole.value = 1
        _title.value = "Setup Match"
    }
}