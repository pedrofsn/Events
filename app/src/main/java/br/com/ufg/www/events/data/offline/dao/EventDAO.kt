package br.com.ufg.www.events.data.offline.dao

import androidx.room.Dao
import br.com.ufg.www.events.data.offline.entities.EventEntity

@Dao
interface EventDAO : BaseDAO<EventEntity>