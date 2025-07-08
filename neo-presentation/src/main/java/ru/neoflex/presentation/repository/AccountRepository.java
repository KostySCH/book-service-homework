package ru.neoflex.presentation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.presentation.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
