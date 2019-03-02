package com.mgl.profile.resource;

import com.mgl.profile.entity.Profile;
import com.mgl.profile.model.ProfileDTO;
import com.mgl.profile.service.ProfileService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Customer profile details controller to handle all REST service requests
 */
@RestController
@RequestMapping(path = "/v1/profile")
@Slf4j
@Api(value = "Customer profile", description = "Customer profile REST services", produces = "application/json", consumes = "application/json")
public class ProfileResource {

    private ProfileService profileService;

    @Autowired
    public ProfileResource(ProfileService profileService) {
        this.profileService = profileService;
    }

    /**
     * Retrieve profile for given customer
     *
     * @param id The profile identifier
     * @return Customer profile for supplied id
     */
    @GetMapping(path = "/{id}", produces = "application/json")
    @ApiOperation(value = "Retrieve profile for given customer", consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 500, message = "Internal server error"),
                    @ApiResponse(code = 404, message = "Record not found for given id"),
                    @ApiResponse(code = 200, message = "Success", response = ProfileDTO.class)
            }
    )
    public ProfileDTO getProfile(@ApiParam(name = "id", type = "int") @NotBlank @PathVariable final int id) throws Exception{
        log.info(" retrive customer profile for {}", id);
        return profileService.getProfile(id);
    }


    /**
     * Save profile for given customer
     *
     * @param profile The profile params
     * @return Customer profile unique identifier for supplied customer
     */
    @PostMapping("/")
    @ApiOperation(value = "Retrieve profile for given customer", consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 500, message = "Internal server error"),
                    @ApiResponse(code = 200, message = "Success", response = String.class)
            }
    )
    public int save(@Valid @RequestBody Profile profile) {
        log.info(" retrive request to persist customer profile for ");
        return profileService.persistProfile(profile);
    }


    /**
     * Update profile for given customer
     *
     * @param id      The profile identifier
     * @param profile The profile params
     * @return Customer profile for supplied customer
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "Retrieve profile for given customer", consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 500, message = "Internal server error"),
                    @ApiResponse(code = 200, message = "Success", response = ProfileDTO.class)
            }
    )
    public ProfileDTO update(@PathVariable int id, @Valid @RequestBody Profile profile) {
        log.info(" retrive request to update customer profile for id {}", id);
        return profileService.updateProfile(id, profile);
    }


    /**
     * Delete profile for given customer
     *
     * @param id The profile identifier
     * @return Customer profile for supplied customer
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete profile for given customer", consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 500, message = "Internal server error"),
                    @ApiResponse(code = 200, message = "Success")
            }
    )
    public void delete(@PathVariable int id) {
        // TODO - Need to implement
    }

    /**
     * Retrieve profiles for all customers
     *
     * @return Customer profile for all customers
     */
    @GetMapping("/")
    @ApiOperation(value = "Get profile for all customers. Return List<Profile>", consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 500, message = "Internal server error"),
                    @ApiResponse(code = 200, message = "Success", response = ArrayList.class)
            }
    )
    public List<ProfileDTO> all() {
        // TODO - Need to implement
        return new ArrayList<ProfileDTO>();
    }

}
