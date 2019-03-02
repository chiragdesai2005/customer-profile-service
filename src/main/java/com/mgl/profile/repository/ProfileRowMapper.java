package com.mgl.profile.repository;

import com.google.gson.Gson;
import com.mgl.profile.entity.Profile;
import com.mgl.profile.model.ProfileDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;


public class ProfileRowMapper implements RowMapper {
    public ProfileDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("ID");
        String payload = rs.getString("PROFILE_DOCUMENT");
        Profile profile = null;
        ProfileDTO profileDTO = null;
        if (isNotEmpty(payload)) {
            Gson gson = new Gson();
            profile = gson.fromJson(payload, Profile.class);
        }
        if (profile != null) {
            profileDTO = new ProfileDTO(id, profile.getFirstName(), profile.getLastName(), profile.getAge(), profile.getEmail(), profile.getContact(), rs.getString("CREATED_BY"), rs.getString("CREATED_DATE"), rs.getString("UPDATED_BY"), rs.getString("UPDATED_DATE"));
        }
        return profileDTO;
    }
}
