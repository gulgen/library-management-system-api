package com.patika.Library.Management.System.API.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private String name;
    private int publicationYear;
    private String authorName;
    private int stock;
    private String publisherName;
    private List<String> categories ;
}
