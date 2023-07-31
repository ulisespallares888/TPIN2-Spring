package com.GameDev.TaskManager.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy="org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36,columnDefinition = "varchar(36)", updatable = false,nullable = false)
    private UUID uuid;

    @Column
    private String title;

    @OneToMany(mappedBy = "game")
    private List<Task> tasks = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "developer_game",joinColumns = @JoinColumn(name = "game_uuid"),
            inverseJoinColumns = @JoinColumn(name = "developer_uuid"))
    private List<Developer> developers = new ArrayList<>();

    @Column
    private String description;

    @Column
    private LocalDate releaseDate;


}
