package br.com.ufg.www.events.view.places.add_map

import android.app.SearchManager
import android.content.Context
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import br.com.redcode.base.R
import br.com.redcode.base.extensions.getString

abstract class BaseMapLocationSearchActivity : BaseGoogleMapsActivityWithLocation() {

    private var searchItem: MenuItem? = null
    private var searchPlate: EditText? = null
    open var query: String? = null

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(getOptionsMenu(), menu)

        searchItem = menu.findItem(R.id.menuItemSearch)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        val searchView = searchItem?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchPlate = searchView.findViewById(R.id.search_src_text)
        searchPlate?.hint = getString(R.string.search)
        dispararQueryStringVazia()

        return true
    }

    open fun getOptionsMenu() = R.menu.menu_search

    private fun dispararQueryStringVazia() {
        searchPlate?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                search()
            }
            false
        }
    }

    open fun search(query: String? = searchPlate?.getString()) {
        this.query = query
    }

}