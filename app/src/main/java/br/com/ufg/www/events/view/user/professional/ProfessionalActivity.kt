package br.com.ufg.www.events.view.user.professional

import br.com.redcode.easyrestful.library.impl.activity.ActivityMVVM
import br.com.ufg.www.events.R
import br.com.ufg.www.events.databinding.ActivityProfessionalBinding
import br.com.ufg.www.events.view.skill.SkillFragment

class ProfessionalActivity : ActivityMVVM<ActivityProfessionalBinding, ProfessionalViewModel>() {

    override val classViewModel = ProfessionalViewModel::class.java
    override val layout = R.layout.activity_professional

    private val fragmentEvents by lazy { SkillFragment() }
    private val fragmentSkills by lazy { SkillFragment() }

    override fun afterOnCreate() {
        enableHomeAsUpActionBar()
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            val fragment = when (it.itemId) {
                R.id.nav_events -> fragmentEvents
                R.id.nav_skills -> fragmentSkills
                else -> fragmentSkills
            }

            supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(binding.frameLayout.id, fragment)
                    .commit()

            return@setOnNavigationItemSelectedListener true
        }
    }

}