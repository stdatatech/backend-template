package com.stdata.backend.repository;

import com.stdata.backend.entity.ApiToken;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("ApiTokenRepository")
public interface ApiTokenRepository extends CrudRepository<ApiToken, Long> {

    Optional<ApiToken> findByToken(String token);

    Optional<ApiToken> findByResourceId(long id);

    void deleteByResourceId(long id);
}
