package com.hi_lo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Team(val golfer1: Golfer = Golfer(), val golfer2: Golfer = Golfer())

data class Golfer(val name: String = "Test", val hcp: Int = (Math.random() * 36).toInt())

data class Score(val playerNumber: Int, var strokes: Int = 0, var points: Int = 0)

private val holes = listOf(
    Hole(1, 7, 4),
    Hole(2, 1, 4),
    Hole(3, 3, 4),
    Hole(4, 13, 4),
    Hole(5, 15, 3),
    Hole(6, 9, 5),
    Hole(7, 5, 4),
    Hole(8, 11, 3),
    Hole(9, 17, 5),
    Hole(10, 6, 4),
    Hole(11, 2, 4),
    Hole(12, 10, 4),
    Hole(13, 14, 5),
    Hole(14, 8, 4),
    Hole(15, 18, 3),
    Hole(16, 16, 5),
    Hole(17, 4, 4),
    Hole(18, 12, 3),
)

val course = Course(name = "Maple Creek", slope = 134, rating = 72.5f, holes = holes)

class MatchViewModel : ViewModel() {

    private var _title: MutableLiveData<String> = MutableLiveData<String>("Course Select")
    val title: LiveData<String> get() = _title

    var team1: Team = Team()
    var team2: Team = Team()

    var pricePerPoint: MutableLiveData<Int> = MutableLiveData(1)
    private var selectedCourse: String = ""

    val team1Score: MutableLiveData<Int> = MutableLiveData(0)
    val team2Score: MutableLiveData<Int> = MutableLiveData(0)
    private val currentHole: MutableLiveData<Int> = MutableLiveData(1)

    fun addPointsToTeam1Score(pts: Int) {
        team1Score.value = team1Score.value?.plus(pts)
    }

    fun addPointsToTeam2Score(pts: Int) {
        team2Score.value = team2Score.value?.plus(pts)
    }

    fun setupMatch(course: String) {
        this._title.value = "Setup Match"
        this.selectedCourse = course
    }

    fun startMatch(team1: Team, team2: Team) {
        this.team1 = team1
        this.team2 = team2
        setTitle(1)
    }

    fun currentHole(): Hole {
        return holes[currentHole.value!!.minus(1)]
    }

    fun hasNextHole(): Boolean {
        return currentHole.value!! < 18
    }

    fun nextHole() {
        currentHole.value = currentHole.value?.inc()
        setTitle(currentHole.value!!)
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
        currentHole.value = 1
        _title.value = "Setup Match"
    }

}