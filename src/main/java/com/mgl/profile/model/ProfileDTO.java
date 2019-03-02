package com.mgl.profile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@ApiModel(value="Profile Model", description="Contains all required fields related to customer profile")
public class ProfileDTO {
    @ApiModelProperty(position = 1, notes = "unique identifier/customer profile id", required = true)
    private int id;
    @ApiModelProperty(position = 2, required = true, notes = "Customer first name")
    private String firstName;
    @ApiModelProperty(position = 3, required = true, notes = "Customer last name")
    private String lastName;
    @ApiModelProperty(position = 4, required = true, notes = "Customer age")
    private int age;
    @ApiModelProperty(position = 5, required = true, notes = "Customer email address")
    private String email;
    private String contact;
    private String createdBy;
    private String createdDate;
    private String updatedBy;
    private String updatedDate;
}
