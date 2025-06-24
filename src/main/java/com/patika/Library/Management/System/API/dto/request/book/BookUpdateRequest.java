package com.patika.Library.Management.System.API.dto.request.book;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateRequest {
    private int id;
    @NotEmpty
    private String name;
    private int publicationYear;
    private int stock;
    private int authorId;
    private int publisherId;
    private List<Integer> categoryIds;
}
