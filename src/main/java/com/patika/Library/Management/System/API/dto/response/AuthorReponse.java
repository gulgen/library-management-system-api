package com.patika.Library.Management.System.API.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorReponse {
    private String name;
    private String country;
    private LocalDate birthDate;
}
