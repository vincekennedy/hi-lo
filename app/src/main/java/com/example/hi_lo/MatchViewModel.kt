package com.example.hi_lo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MatchViewModel : ViewModel() {

  private val stateFlow = MutableStateFlow(MatchUiState())
  val state: StateFlow<MatchUiState> = stateFlow.asStateFlow()

  private val team1StateFlow = MutableStateFlow(Team(listOf()))
  val team1State: StateFlow<Team> = team1StateFlow.asStateFlow()

  fun updateTeam1(player1: Player, player2: Player){
    team1StateFlow.update { team1 ->
      team1.copy(listOf(player1, player2))
    }
  }

  fun setMatch() {
    stateFlow.update { state ->
      state.copy(currentHole = state.currentHole.inc())
    }
  }
}

data class Team(val players: List<Player>)

data class Player(val name: String = "", val hcp: Int = 0)

data class MatchUiState(
  val currentHole: Int = 1,
  val currentScore: Int = 0,
)