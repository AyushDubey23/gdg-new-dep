package com.gdgmmmut.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class Event(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    val location: String,
    val imageUrl: String,
    val isUpcoming: Boolean,
    val registrationUrl: String,
    val tags: List<String>
)

@Entity(tableName = "team_members")
data class TeamMember(
    @PrimaryKey val id: String,
    val name: String,
    val role: String,
    val bio: String,
    val imageUrl: String,
    val linkedinUrl: String,
    val githubUrl: String,
    val email: String
)

@Entity(tableName = "resources")
data class Resource(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val url: String,
    val category: String,
    val type: ResourceType,
    val thumbnailUrl: String
)

enum class ResourceType {
    VIDEO, PDF, ARTICLE, TUTORIAL, DOCUMENTATION
}

@Entity(tableName = "quizzes")
data class Quiz(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val questions: List<Question>,
    val difficulty: Difficulty,
    val category: String,
    val timeLimit: Int // in minutes
)

data class Question(
    val id: String,
    val question: String,
    val options: List<String>,
    val correctAnswer: Int,
    val explanation: String
)

enum class Difficulty {
    EASY, MEDIUM, HARD
}

@Entity(tableName = "quiz_results")
data class QuizResult(
    @PrimaryKey val id: String,
    val quizId: String,
    val userName: String,
    val score: Int,
    val totalQuestions: Int,
    val completedAt: Long
)

data class Banner(
    val id: String,
    val title: String,
    val subtitle: String,
    val imageUrl: String,
    val actionUrl: String
)

data class AboutInfo(
    val mission: String,
    val vision: String,
    val description: String,
    val contactEmail: String,
    val contactPhone: String,
    val address: String,
    val socialLinks: SocialLinks
)

data class SocialLinks(
    val facebook: String,
    val instagram: String,
    val twitter: String,
    val linkedin: String,
    val youtube: String
)
