package com.test.swagger.controller.publicApi;

import java.util.List;

import com.test.swagger.model.TestItem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Api(tags = {"Resource Public Test"})
@SwaggerDefinition(tags = {
    @Tag(name = "Resource Public Test", description = "Public: rest controller")
})
public interface IPublicController {

	@ApiOperation(value = "Public: Get all test items")
	public List<TestItem> findAll();
}
