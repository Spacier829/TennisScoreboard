package com.spacier.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Matches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Match {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "first_player", referencedColumnName = "id", nullable = false)
  private Player firstPlayer;

  @ManyToOne
  @JoinColumn(name = "second_player", referencedColumnName = "id", nullable = false)
  private Player secondPlayer;

  @ManyToOne
  @JoinColumn(name = "winner", referencedColumnName = "id", nullable = false)
  private Player winner;

  @Builder
  public Match(Player firstPlayer, Player secondPlayer, Player winner) {
    this.firstPlayer = firstPlayer;
    this.secondPlayer = secondPlayer;
    this.winner = winner;
  }

  @Override
  public String toString() {
    return "Match{" +
           "id=" + id +
           ", firstPlayer=" + firstPlayer +
           ", secondPlayer=" + secondPlayer +
           ", winner=" + winner +
           '}';
  }
}
