package com.test.swagger.controller.privateApi;

import java.util.List;

import com.test.swagger.model.TestItem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Api(tags = {"Resource Private Test"})
@SwaggerDefinition(tags = {
    @Tag(name = "Resource Private Test", description = "Private: rest controller")
})
public interface IPrivateController {
	
	@ApiOperation(value = "Private: Get all test items filtered by name")
	public List<TestItem> findAllByName(@ApiParam(value="testItem name") String name);

}
