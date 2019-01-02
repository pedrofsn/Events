package br.com.ufg.www.events.view.user.professional

import androidx.fragment.app.Fragment
import br.com.redcode.easyrestful.library.impl.activity.ActivityMVVM
import br.com.ufg.www.events.R
import br.com.ufg.www.events.databinding.ActivityProfessionalBinding
import br.com.ufg.www.events.view.event.jobs.JobsFragment
import br.com.ufg.www.events.view.event.list.FragmentEvents
import br.com.ufg.www.events.view.places.list.PlacesFragment
import br.com.ufg.www.events.view.skill.SkillFragment

class ProfessionalActivity : ActivityMVVM<ActivityProfessionalBinding, ProfessionalViewModel>() {

    override val classViewModel = ProfessionalViewModel::class.java
    override val layout = R.layout.activity_professional

    override fun afterOnCreate() {
        viewModel.load()
        handleNavigation()
        firstCaseSelection()
    }

    private fun firstCaseSelection() {
        binding.bottomNavigationView.selectedItemId = R.id.nav_jobs
    }

    private fun handleNavigation() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            val fragment = when (it.itemId) {
                R.id.nav_jobs -> JobsFragment()
                R.id.nav_events -> FragmentEvents()
                R.id.nav_skills -> SkillFragment.newInstance(viewModel.skills)
                R.id.nav_places -> PlacesFragment()
                else -> FragmentEvents()
            }

            selectFragment(fragment)

            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun selectFragment(fragment: Fragment) = supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .replace(binding.frameLayout.id, fragment)
            .commit()

}