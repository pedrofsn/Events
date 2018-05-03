package br.com.ufg.www.events.mvp.maps;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.ufg.www.events.R;
import br.com.ufg.www.events.domain.BaseActivity;
import br.com.ufg.www.events.model.Event;

public class List_Places_Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_places_);
        ListView listEvents =  (ListView) findViewById(R.id.listEvents);

        Event event1 = new Event(-16.6824, -49.2567, "Festa de São João");
        Event event2 = new Event(-166778,  -49.2444, "Marcha para Jesus");
        Event event3 = new Event(-3.7281,  -38.5249, "Confraternização Empresarial");

        List<Event> events = new ArrayList<Event>();
        events.add(event1);
        events.add(event2);
        events.add(event3);

        ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(this, android.R.layout.simple_list_item_1, events);
        listEvents.setAdapter(adapter);

    }



}
