package com.patika.Library.Management.System.API.api;

import com.patika.Library.Management.System.API.business.abstracts.IPublisherService;
import com.patika.Library.Management.System.API.core.config.modelMapper.IModelMapperService;
import com.patika.Library.Management.System.API.core.result.Result;
import com.patika.Library.Management.System.API.core.result.ResultData;
import com.patika.Library.Management.System.API.core.utils.Msg;
import com.patika.Library.Management.System.API.core.utils.ResultHelper;
import com.patika.Library.Management.System.API.dto.request.publisher.PublisherSaveRequest;
import com.patika.Library.Management.System.API.dto.request.publisher.PublisherUpdateRequest;
import com.patika.Library.Management.System.API.dto.response.CursorResponse;
import com.patika.Library.Management.System.API.dto.response.PublisherResponse;
import com.patika.Library.Management.System.API.entities.Publisher;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/publishers")
public class PublisherController {

    private final IPublisherService publisherService;
    private final IModelMapperService modelMapperService;

    public PublisherController(IPublisherService publisherService, IModelMapperService modelMapperService) {
        this.publisherService = publisherService;
        this.modelMapperService = modelMapperService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<PublisherResponse> save(@Valid @RequestBody PublisherSaveRequest publisherSaveRequest){
        Publisher publisher = this.modelMapperService.forRequest().map(publisherSaveRequest,Publisher.class);
        this.publisherService.save(publisher);
        return ResultHelper.created(this.modelMapperService.forResponse().map(publisher, PublisherResponse.class));
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> update(@PathVariable("id")int id){
        Publisher publisher = this.publisherService.get(id);
        return ResultHelper.success(this.modelMapperService.forResponse().map(publisher, PublisherResponse.class));
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<PublisherResponse>> cursor(
            @RequestParam(name = "page", required = false,defaultValue = "0")int page,
            @RequestParam(name = "pageSize",required = false, defaultValue = "3")int pageSize
    ){
        Page<Publisher> publisherPage = this.publisherService.cursor(page,pageSize);
        Page<PublisherResponse> publisherResponsePage = publisherPage
                .map(publisher -> this.modelMapperService.forResponse().map(publisher, PublisherResponse.class));

        return ResultHelper.cursor(publisherResponsePage);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> update(@Valid @RequestBody PublisherUpdateRequest publisherUpdateRequest){
        Publisher publisher = this.modelMapperService.forRequest().map(publisherUpdateRequest,Publisher.class);
        this.publisherService.update(publisher);
        return ResultHelper.success(this.modelMapperService.forResponse().map(publisher, PublisherResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.publisherService.delete(id);
        return new Result(true, Msg.DELETED,"200");
    }

}
