package com.mgl.profile.repository;

import com.mgl.profile.exception.ResourceNotFoundException;
import com.mgl.profile.model.ProfileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Repository
@Slf4j
public class ProfileRepository {

    private final JdbcTemplate jdbcTemplate;
    private final static String INSERT_SQL = "INSERT INTO CUSTOMER_PROFILE_TBL (PROFILE_DOCUMENT, CREATED_DATE, CREATED_BY) VALUES (?,?,?)";
    private final static String GET_PROFILE_SQL = "SELECT * FROM CUSTOMER_PROFILE_TBL WHERE ID = ?";
    private final static String UPDATE_PROFILE_SQL = "UPDATE CUSTOMER_PROFILE_TBL set PROFILE_DOCUMENT = ?, UPDATED_BY = ?, UPDATED_DATE = ? WHERE ID = ?";

    @Autowired
    public ProfileRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @param profileRequest
     * @return
     */
    @Transactional(propagation = REQUIRED)
    public int persist(final String profileRequest) {
        if(isNotEmpty(profileRequest)) {
            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(INSERT_SQL, RETURN_GENERATED_KEYS);
                ps.setString(1, profileRequest);
                ps.setString(2, LocalDateTime.now().toString());
                ps.setString(3, "1000");
                return ps;
            }, holder);
            return holder.getKey().intValue();
        }else{
            // TODO can be custom checked exception
            throw new RuntimeException("Profile document cannot be empty");
        }
    }

    /**
     * Get profile based on given id
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public ProfileDTO get(final int id) {
        try {
            return (ProfileDTO) jdbcTemplate.queryForObject(GET_PROFILE_SQL, new Object[]{id}, new ProfileRowMapper());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Record not found for given id " + id);
        }
    }

    /**
     * Update customer profile for given id
     *
     * @param id
     * @param profileRequest
     * @return updated profile info. for the given customer
     */
    @Transactional(propagation = REQUIRED)
    public ProfileDTO update(final int id, final String profileRequest) {
        if(id == 0){
            log.error(" id can not be empty while updating profile");
            throw new RuntimeException("Sorry something went wrong");
        }
        if(isNotEmpty(profileRequest)) {
            int result = jdbcTemplate.update(UPDATE_PROFILE_SQL, profileRequest, "1000", LocalDateTime.now().toString(), id);
            if (result == 1) {
                return get(id);
            } else {
                log.warn("Record not found while updating record {}", id);
                return null;
            }
        }
        return null;
    }
}
