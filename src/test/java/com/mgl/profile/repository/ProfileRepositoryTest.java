package com.mgl.profile.repository;

import com.google.gson.Gson;
import com.mgl.profile.entity.Profile;
import com.mgl.profile.model.ProfileDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfileRepositoryTest {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private Gson gson;

    @Test(expected = RuntimeException.class)
    public void testPersistWithEmptyDocument() {
        profileRepository.persist("");
    }

    @Test
    public void testPersist() {
        Profile profile = getProfile();
        int id = profileRepository.persist(gson.toJson(profile));
        assertThat(id).isNotZero();
    }

    @Test
    public void testGetProfile() {
        Profile profile = getProfile();
        int id  = profileRepository.persist(gson.toJson(profile));
        ProfileDTO profileDTO = profileRepository.get(id);
        assertThat(profileDTO).isNotNull();
        assertThat(profileDTO.getFirstName()).isEqualTo("chirag");
        assertThat(profileDTO.getLastName()).isEqualTo("desai");
        assertThat(profileDTO.getContact()).isEqualTo("04242424");
        assertThat(profileDTO.getAge()).isEqualTo(30);
        assertThat(profileDTO.getCreatedBy()).isEqualTo("1000");
        assertThat(profileDTO.getCreatedDate()).isNotEmpty();
    }

    @Test
    public void testUpdateProfileWithEmptyProfile() {
        Profile profile = getProfile();
        int id  = profileRepository.persist(gson.toJson(profile));
        ProfileDTO profileDTO  = profileRepository.update(id, null);
        assertThat(profileDTO).isNull();
    }

    @Test
    public void testUpdateProfile() {
        Profile profile = getProfile();
        int id  = profileRepository.persist(gson.toJson(profile));
        profile.setAge(40);
        ProfileDTO profileDTO  = profileRepository.update(id, gson.toJson(profile));
        assertThat(profileDTO).isNotNull();
        assertThat(profileDTO.getFirstName()).isEqualTo("chirag");
        assertThat(profileDTO.getLastName()).isEqualTo("desai");
        assertThat(profileDTO.getContact()).isEqualTo("04242424");
        assertThat(profileDTO.getAge()).isEqualTo(40);
        assertThat(profileDTO.getCreatedBy()).isEqualTo("1000");
        assertThat(profileDTO.getCreatedDate()).isNotEmpty();
    }

    // TODO - parameterized method
    private Profile getProfile() {
        Profile profile = new Profile();
        profile.setFirstName("chirag");
        profile.setLastName("desai");
        profile.setContact("04242424");
        profile.setAge(30);
        return profile;
    }

}