package kr.co.ureca.employee.domain;

import jakarta.persistence.*;

import java.util.ArrayList;

public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Long positionId;
    private String type;

    @OneToMany(mappedBy = "position")
    ArrayList<Position> positionArrayList = new ArrayList<>();
}
