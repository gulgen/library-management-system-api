package com.patika.Library.Management.System.API.dto.request.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorySaveRequest {
    private String name;
    private String description;
}
