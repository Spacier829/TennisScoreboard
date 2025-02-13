package com.spacier.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Players")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", nullable = false, unique = true, length = 50)
  private String name;

  @Builder
  public Player(String name){
    this.name = name;
  }

  @Override
  public String toString() {
    return "Player {id=" + id + ", name=" + name + "}";
  }
}
