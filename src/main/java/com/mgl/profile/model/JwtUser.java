package com.mgl.profile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class JwtUser {
    @ApiModelProperty(position = 1, required = true, notes = "Username")
    private String userName;
    @ApiModelProperty(position = 2, required = true, notes = "id")
    private long id;
    @ApiModelProperty(position = 3, required = true, notes = "application role")
    private String role;
}
