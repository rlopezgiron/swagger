package com.test.swagger.dto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="Test item dto description")
public class TestItemDto {
	
	@ApiModelProperty(value="test item id property")
	private String id;
	
	@ApiModelProperty(value="test item name property")
	private String name;
		
	@ApiModelProperty(value="test item createdDate property")
	private Date createdDate;

}
