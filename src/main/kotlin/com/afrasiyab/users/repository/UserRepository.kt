package com.afrasiyab.users.repository

import com.afrasiyab.users.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {

}