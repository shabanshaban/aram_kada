package com.farad.entertainment.aramkada.data.apiService

import androidx.annotation.Keep
import kotlinx.coroutines.Job

@Keep
data class ApiConfig(
    var id: Long,
    val job: Job,
) {
    class Builder {
        private var id: Long = System.nanoTime()
        private var job: Job = Job()


        fun setJob(job: Job): Builder {
            this.job = job
            return this
        }

        fun build(): ApiConfig {
            return ApiConfig(
                id,
                job
            )
        }
    }

    override fun hashCode(): Int {
        return id.toInt()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        } else if (other is ApiConfig) {
            return other.id == id
        }
        return false
    }
}


