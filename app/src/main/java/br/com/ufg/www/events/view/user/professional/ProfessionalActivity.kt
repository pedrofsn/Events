package br.com.ufg.www.events.view.user.professional

import br.com.redcode.easyrestful.library.impl.activity.ActivityMVVM
import br.com.ufg.www.events.R
import br.com.ufg.www.events.databinding.ActivityProfessionalBinding

class ProfessionalActivity : ActivityMVVM<ActivityProfessionalBinding, ProfessionalViewModel>() {

    override val classViewModel = ProfessionalViewModel::class.java
    override val layout = R.layout.activity_professional

    override fun afterOnCreate() {
        enableHomeAsUpActionBar()
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_events -> {
                    showMessage("eventos")
                }
                R.id.nav_skills -> {
                    showMessage("habilidades")
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

//    override fun onResume() {
//        super.onResume()
//        viewModel.load()
//    }

}