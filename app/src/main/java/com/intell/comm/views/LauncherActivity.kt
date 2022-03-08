package com.intell.comm.views

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import androidx.core.animation.doOnEnd
import com.intell.comm.R
import com.intell.comm.localDatabase.sharePreferenace.SharedPref
import com.intell.comm.model.RegisterLoginModel
import com.intell.comm.network.IS_FIRST_TIME
import com.intell.comm.views.homeViews.HomeActivity
import com.intell.comm.views.preHomeViews.PreHomeActivity
import com.intell.comm.views.preHomeViews.welcome.WelcomeActivity

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LauncherActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPref: SharedPref
    private var isFinishSplash = false
    private val splashTime = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Add a callback that's called when the splash screen is animating to
        // the app content.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                // Create your custom animation.
                val slideUp = ObjectAnimator.ofFloat(
                    splashScreenView,
                    View.TRANSLATION_Y,
                    0f,
                    -splashScreenView.height.toFloat()
                )
                slideUp.interpolator = AnticipateInterpolator()
                slideUp.duration = splashTime

                // Call SplashScreenView.remove at the end of your custom animation.
                slideUp.doOnEnd {
                    isFinishSplash = true
                    splashScreenView.remove()
                }

                // Run your animation.
                slideUp.start()
            }

            // Set up an OnPreDrawListener to the root view.
            val content: View = findViewById(android.R.id.content)
            content.viewTreeObserver.addOnPreDrawListener(
                object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        // Check if the initial data is ready.
                        return if (isFinishSplash) {
                            // The content is ready; start drawing.
                            content.viewTreeObserver.removeOnPreDrawListener(this)
                            openNextPage()
                            true
                        } else {
                            // The content is not ready; suspend.
                            false
                        }
                    }
                }
            )

        } else {
            setContentView(R.layout.activity_launcher)
            Handler(Looper.getMainLooper()).postDelayed({
                openNextPage()
            }, splashTime)

        }

    }

    private fun openNextPage() {
//        sharedPref.putBoolean(IS_FIRST_TIME, false)
//        val mm = RegisterLoginModel()
//        mm.id = 1
//        sharedPref.putUserData(mm)

        when {
            sharedPref.getBoolean(IS_FIRST_TIME, true) -> {
                startActivity(
                    Intent(
                        this@LauncherActivity,
                        WelcomeActivity::class.java
                    )
                )
            }
            sharedPref.getUserData().id == null -> {
                startActivity(
                    Intent(
                        this@LauncherActivity,
                        PreHomeActivity::class.java
                    )
                )
            }
            sharedPref.getUserData().id != null -> {
                startActivity(
                    Intent(
                        this@LauncherActivity,
                        HomeActivity::class.java
                    )
                )
            }
        }
        finish()
    }
}