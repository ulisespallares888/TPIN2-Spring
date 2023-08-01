package com.GameDev.TaskManager.model.record.developer;


import com.GameDev.TaskManager.domain.enumeration.RoleEnum;
import lombok.*;


@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperRecordCsv {
    private String name;
    private String lastName;
    private String email;
    private RoleEnum roleEnum;
  //  private List<TaskDto> taskDtos = new ArrayList<>();
   // private List<GameDto> gamesDtos = new ArrayList<>();
}
