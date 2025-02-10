package com.spacier.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

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
  @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
  @JoinColumn(name = "first_player", referencedColumnName = "id", nullable = false)
  private Player firstPlayer;

  @ManyToOne
  @JoinColumn(name = "second_player", referencedColumnName = "id", nullable = false)
  @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
  private Player secondPlayer;

  @ManyToOne
  @JoinColumn(name = "winner", referencedColumnName = "id", nullable = false)
  private Player winner;

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
