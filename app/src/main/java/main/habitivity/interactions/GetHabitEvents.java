/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */

package main.habitivity.interactions;

import android.location.Location;
import android.support.annotation.Nullable;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.IHabitRepository;

/**
 * Interaction class to get a list of habitEvents from our repo
 */
public class GetHabitEvents {
    private IHabitRepository habitRepository;

    public GetHabitEvents(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    /**
     * Gets a list of habitEvents from our repo
     * @return a list of HabitEvents
     */
    public List<HabitEvent> getListOfHabitEvents() {
        return habitRepository.getHabitEvents();
    }


    public ArrayList<HabitEvent> getSortedEventsForMaps(final String filter) {
        List<HabitEvent> list = habitRepository.getHabitEvents();
        Collections.sort(list, new Comparator<HabitEvent>() {
            @Override
            public int compare(HabitEvent habitEvent, HabitEvent t1) {
                return t1.getCompletionDate().compareTo(habitEvent.getCompletionDate());
            }
        });
        if (!filter.isEmpty()) {
            list = Lists.newArrayList(Collections2.filter(list, new com.google.common.base.Predicate<HabitEvent>() {
                        @Override
                        public boolean apply(@Nullable HabitEvent habitEvent) {
                            return (habitEvent.getComment().contains(filter) || habitEvent.getId().contains(filter));
                        }
                    }
            ));
        }

        return (ArrayList<HabitEvent>)list;
    }

    /**
     * Gets a list of habit event locations
     * @return a list of habit event locations
     */
    public List<Location> getListOfHabitLocations(){
        return habitRepository.getHabitLocations();
    }

    public Map<String, Location> getListOfFriendsHabitLocations(ArrayList<HabitEvent> habitEventsToFilter ){
        return habitRepository.getListOfFriendsHabitLocations(habitEventsToFilter);
    }

    public Map<String, Location> getListOfMyHabitLocations(ArrayList<HabitEvent> habitEventsToFilter ){
        return habitRepository.getListOfMyHabitLocations(habitEventsToFilter);
    }
}
