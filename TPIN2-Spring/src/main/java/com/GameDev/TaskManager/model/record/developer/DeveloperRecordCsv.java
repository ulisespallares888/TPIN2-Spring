package com.GameDev.TaskManager.model.record.developer;


import com.GameDev.TaskManager.domain.enumeration.RoleEnum;
import com.opencsv.bean.CsvBindByName;
import lombok.*;


@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperRecordCsv {
    @CsvBindByName(column = "name")
    private String name;
    @CsvBindByName(column = "last_name")
    private String lastName;
    @CsvBindByName(column = "email")
    private String email;
    @CsvBindByName(column = "role_enum")
    private RoleEnum roleEnum;
}
