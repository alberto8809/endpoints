package org.example.users.repository;



import org.example.users.model.Account;
import org.example.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {


    @Query(value = "SELECT * FROM dbmaster.account cc WHERE cc.user_token =:token " ,nativeQuery = true)
    List<Account> getListOfAccountByToken(String token);

}
