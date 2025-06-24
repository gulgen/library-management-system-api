package com.patika.Library.Management.System.API.dto.request.publisher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherSaveRequest {
    private String name;
    private int establishmentYear;
    private String address;
}
