package com.patika.Library.Management.System.API.dto.request.author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorUpdateRequest {
    private int id;

    private String name;

    private int birthDate;

    private String country;
}
