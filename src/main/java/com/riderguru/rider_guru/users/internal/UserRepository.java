package com.riderguru.rider_guru.users.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}
