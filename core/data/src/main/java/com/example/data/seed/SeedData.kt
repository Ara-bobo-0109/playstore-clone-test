package com.example.data.seed

import com.example.domain.repo.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

object SeedData {
    suspend fun ensureSeeded(repo: AppRepository) = withContext(Dispatchers.IO) {
        // We can’t query count via repo interface; seed from app module instead if desired.
        // This helper stays intentionally minimal.
    }

    fun seededIconSeed(name: String, packageName: String): Int {
        val base = (name + "|" + packageName).hashCode()
        return base xor Random(base).nextInt()
    }
}
