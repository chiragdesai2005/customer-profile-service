package com.mgl.profile.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.*;
import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ApiModel()
// TODO - read error/notes messages from .property/.yaml file
public class Profile {
    @Id
    @ApiModelProperty(hidden = true)
    private int id;
    @NotNull(message = "firstName cannot be empty")
    @NotBlank
    @Size(min = 2, message = "firstName should have atleast 2 characters")
    @ApiModelProperty(position = 1, required = true, notes = "Customer first name")
    private String firstName;
    @NotNull(message = "lastName cannot be empty")
    @Size(min = 2, message = "lastName should have atleast 2 characters")
    @ApiModelProperty(position = 2,required = true, notes = "Customer last name")
    private String lastName;
    @NotNull(message = "age cannot be empty")
    @ApiModelProperty(position = 3,required = true, notes = "Customer age")
    private int age;
    @NotNull(message = "E-mail address is required")
    @Email(message = "Invalid E-mail address format")
    @ApiModelProperty(position = 4,required = true, notes = "Customer email address")
    private String email;
    @NotNull(message = "contact cannot be empty")
    @NotBlank(message = "contact cannot be empty")
    @Pattern(regexp="(^$|[0-9]{10})", message = "contact should be numeric digits only")
    @ApiModelProperty(position = 5,required = true, notes = "Customer contact number")
    @Size(min=10,max=10,message = "contact should have atleast 10 characters")
    private String contact;
}
