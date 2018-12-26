package br.com.ufg.www.events.database.dao

import androidx.room.Dao
import br.com.ufg.www.events.database.entities.EventEntity

@Dao
interface EventDAO : BaseDAO<EventEntity>