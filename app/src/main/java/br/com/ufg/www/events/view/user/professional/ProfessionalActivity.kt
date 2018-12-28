package br.com.ufg.www.events.view.user.professional

import androidx.fragment.app.Fragment
import br.com.redcode.easyrestful.library.impl.activity.ActivityMVVM
import br.com.ufg.www.events.R
import br.com.ufg.www.events.databinding.ActivityProfessionalBinding
import br.com.ufg.www.events.view.event.list.FragmentEvents
import br.com.ufg.www.events.view.skill.SkillFragment

class ProfessionalActivity : ActivityMVVM<ActivityProfessionalBinding, ProfessionalViewModel>() {

    override val classViewModel = ProfessionalViewModel::class.java
    override val layout = R.layout.activity_professional

    private val fragmentEvents by lazy { FragmentEvents() }
    private val fragmentSkills by lazy { SkillFragment() }

    override fun afterOnCreate() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            val fragment = when (it.itemId) {
                R.id.nav_events -> fragmentEvents
                R.id.nav_skills -> fragmentSkills
                else -> fragmentSkills
            }

            selectFragment(fragment)

            return@setOnNavigationItemSelectedListener true
        }

        selectFragment(fragmentEvents)
    }

    private fun selectFragment(fragment: Fragment) = supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .replace(binding.frameLayout.id, fragment)
            .commit()

}