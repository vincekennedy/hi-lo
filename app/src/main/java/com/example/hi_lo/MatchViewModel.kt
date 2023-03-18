package com.example.hi_lo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Team(val golfer1: Golfer = Golfer(), val golfer2: Golfer = Golfer())

data class Golfer(var name: String = "", var hcp: Int = 0)

class ScoreViewModel : ViewModel() {
  val team1Score: MutableLiveData<Int> = MutableLiveData(0)
  val team2Score: MutableLiveData<Int> = MutableLiveData(0)
  val hole: MutableLiveData<Int> = MutableLiveData(1)

  fun addPointsToTeam1Score(pts: Int) {
    team1Score.value = team1Score.value?.plus(pts)
  }

  fun addPointsToTeam2Score(pts: Int) {
    team1Score.value = team2Score.value?.plus(pts)
  }

  fun nextHole() {
    hole.value = hole.value?.inc()
  }

  fun resetGame() {
    team1Score.value = 0
    team2Score.value = 0
    hole.value = 1
  }
}

class MatchViewModel : ViewModel() {
  var team1: Team = Team()
  var team2: Team = Team()

  fun setMatch(team1: Team, team2: Team) {
    this.team1 = team1
    this.team2 = team2
  }
}