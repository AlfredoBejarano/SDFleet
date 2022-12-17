package me.alfredobejarano.sdefleet.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.alfredobejarano.sdefleet.R
import java.io.InputStream

@Module
@InstallIn(SingletonComponent::class)
class FleetModule {
    @Provides
    @FleetJSON
    fun provideInputStream(@ApplicationContext context: Context): InputStream =
        context.resources.openRawResource(R.raw.fleet)

    @Provides
    fun provideGson(): Gson = Gson()
}