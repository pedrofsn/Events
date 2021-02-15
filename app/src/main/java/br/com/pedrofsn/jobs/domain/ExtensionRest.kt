package br.com.pedrofsn.jobs.domain

import br.com.pedrofsn.jobs.network.API
import br.com.pedrofsn.jobs.network.APIConnection

fun api(): API = APIConnection.service