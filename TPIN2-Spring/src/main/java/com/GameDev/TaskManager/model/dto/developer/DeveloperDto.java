package com.GameDev.TaskManager.model.dto.developer;


import com.GameDev.TaskManager.domain.enumeration.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperDto {
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotNull
    private RoleEnum roleEnum;
}
