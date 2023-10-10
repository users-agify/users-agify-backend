package com.afrasiyab.users.service

import com.afrasiyab.users.model.User
import com.afrasiyab.users.model.UserAgifyResponse
import com.afrasiyab.users.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
@Transactional
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    private val restTemplate = RestTemplate()

    override fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    override fun getUserById(id: Long): User? {
        return userRepository.findById(id).orElse(null)
    }

    override fun createUser(user: User): User {
        val age = fetchAgeFromAgify(user.name)

        if (age != null && age != 0) {
            user.age = age
        }

        return userRepository.save(user)
    }

    override fun updateUser(id: Long, user: User): User? {
        if (userRepository.existsById(id)) {
            user.id = id

            val age = fetchAgeFromAgify(user.name)
            if (age != null && age != 0) {
                user.age = age
            }

            return userRepository.save(user)
        }
        return null
    }

    override fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }

    private fun fetchAgeFromAgify(name: String): Int? {
        val agifyApiUrl = "https://api.agify.io/?name=$name"
        val response = restTemplate.getForObject(agifyApiUrl, UserAgifyResponse::class.java)
        return response?.age
    }
}