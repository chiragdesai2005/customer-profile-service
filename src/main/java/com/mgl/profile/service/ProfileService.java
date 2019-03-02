package com.mgl.profile.service;

import com.google.gson.Gson;
import com.mgl.profile.entity.Profile;
import com.mgl.profile.repository.ProfileRepository;
import com.mgl.profile.model.ProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileService {

    private ProfileRepository profileRepository;
    private Gson gson;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, Gson gson) {
        this.profileRepository = profileRepository;
        this.gson = gson;
    }

    /**
     *
     * @param profile
     * @return
     */
    public int persistProfile(final Profile profile) {
        return profileRepository.persist(gson.toJson(profile));
    }

    /**
     *
     * @param id
     * @return
     */
    public ProfileDTO getProfile(final int id) {
        return profileRepository.get(id);
    }

    /**
     *
     * @param id
     * @param profile
     * @return
     */
    public ProfileDTO updateProfile(final int id, final Profile profile) {
        return profileRepository.update(id, gson.toJson(profile));
    }
}
