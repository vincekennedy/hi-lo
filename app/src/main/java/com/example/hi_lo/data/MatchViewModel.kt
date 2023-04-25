package com.example.hi_lo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Team(val golfer1: Golfer = Golfer(), val golfer2: Golfer = Golfer())

data class Golfer(var name: String = "Test", var hcp: Int = (Math.random() * 36).toInt())

class MatchViewModel : ViewModel() {

  private var _title: MutableLiveData<String> = MutableLiveData<String>("Setup Match")
  val title: LiveData<String> get() = _title

  var team1: Team = Team()
  var team2: Team = Team()

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
    course[value - 1].apply {
      _title.value = "Hole $first     HCP: $second     PAR: $third   Score : ${currentScore()}"
    }
  }

  fun resetMatch() {
    team1Score.value = 0
    team2Score.value = 0
    hole.value = 1
    _title.value = "Setup Match"
  }
}