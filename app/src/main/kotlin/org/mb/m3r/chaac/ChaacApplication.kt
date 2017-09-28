package org.mb.m3r.chaac

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import org.mb.m3r.chaac.di.ApplicationComponent
import org.mb.m3r.chaac.di.DaggerApplicationComponent

/**
 * @author Melby Baldove
 */
class ChaacApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
        init()
    }

    private fun init() {
        applicationComponent = DaggerApplicationComponent.builder()
                .application(this)
                .build()
    }
}

