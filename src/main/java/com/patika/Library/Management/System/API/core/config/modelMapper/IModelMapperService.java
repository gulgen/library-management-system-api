package com.patika.Library.Management.System.API.core.config.modelMapper;

import org.modelmapper.ModelMapper;

public interface IModelMapperService {
    ModelMapper forRequest();

    ModelMapper forResponse();
}
