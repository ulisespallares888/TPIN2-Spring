package com.GameDev.TaskManager.domain;

import com.GameDev.TaskManager.domain.enumeration.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;


@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "developers")
public class Developer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy="org.hibernate.id.UUIDGenerator")
    @Column(length = 36,columnDefinition = "varchar(36)",updatable = false,nullable = false)
    private UUID uuid;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;
}
