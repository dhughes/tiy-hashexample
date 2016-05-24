package com.theironyard;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by doug on 5/24/16.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}
