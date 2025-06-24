package com.patika.Library.Management.System.API.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherResponse {
    private int id;
    private String name;
    private int establishmentYear;
    private String address;
}
