package com.gitpod;

import org.jdbi.v3.core.Jdbi;

import java.util.Optional;

public class AccountDAO {

    private final Jdbi jdbi;

    public AccountDAO(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    /**
     * Get an account by id, or return Optional.empty() if it doesn't exist.
     */
    public Optional<Account> getAccount(int id) {
        return jdbi.withHandle(h ->
            h.createQuery("SELECT * FROM accounts WHERE id = :id")
                .bind("id", id)
                .mapToBean(Account.class)
                .findOne());
    }
}
