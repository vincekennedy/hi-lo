package com.example.hi_lo.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Team(val golfer1: Golfer = Golfer(), val golfer2: Golfer = Golfer())

data class Golfer(var name: String = "", var hcp: Int = 0)

class MatchViewModel : ViewModel() {
  var team1: Team = Team()
  var team2: Team = Team()

  val team1Score: MutableLiveData<Int> = MutableLiveData(0)
  val team2Score: MutableLiveData<Int> = MutableLiveData(0)

  fun addPointsToTeam1Score(pts: Int) {
    team1Score.value = team1Score.value?.plus(pts)
  }

  fun addPointsToTeam2Score(pts: Int) {
    team1Score.value = team2Score.value?.plus(pts)
  }

  val hole: MutableLiveData<Int> = MutableLiveData(1)

  fun setMatch(team1: Team, team2: Team) {
    this.team1 = team1
    this.team2 = team2
  }

  fun hasNextHole() : Boolean {
    return hole.value!! < 18
  }

  fun nextHole() {
    hole.value = hole.value?.inc()
  }

  fun resetMatch() {
    team1Score.value = 0
    team2Score.value = 0
    hole.value = 1
  }
}