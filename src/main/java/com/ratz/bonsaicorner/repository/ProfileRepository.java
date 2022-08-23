package com.ratz.bonsaicorner.repository;

import com.ratz.bonsaicorner.model.Profile;
import com.ratz.bonsaicorner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByUserId(Long id);

    Profile findProfileByUser(User user);

    void deleteProfileByUser(User user);

    @Query("delete from Profile p where p.user =:id")
    @Modifying
    void customDelete(User id);
}
