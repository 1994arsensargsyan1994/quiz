package com.cinema.quiz.repo.guava;

import com.cinema.quiz.entity.FootballPlayers;
import com.cinema.quiz.util.JsonUtil;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.json.JSONArray;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public class FootballGuava {

    public static BiMap<String, String> allFootballPlayersBiMap = HashBiMap.create();
    public static BiMap<String, String> oanFootballPlayersBiMap = HashBiMap.create();


    public void addFootballPlayers(List<FootballPlayers> footballPlayers) {
        String jsonUserValue = JsonUtil.serialize(footballPlayers);
        System.out.println("json array ::: " + jsonUserValue);     // for check
        allFootballPlayersBiMap.put("all", jsonUserValue);
        JSONArray arr = new JSONArray(jsonUserValue);

        // for check
        //  System.out.println("json array ::: " + jsonUserValue);


        for (int i = 0; i < arr.length(); i++) {
            oanFootballPlayersBiMap.put((arr.getJSONObject(i).getString("firstName").toUpperCase()),
                    (arr.getJSONObject(i).getString("lastName")).toUpperCase());
        }

        // for check
        System.out.println(oanFootballPlayersBiMap.get("ROMEI"));

    }

    //
    public static FootballPlayers getFootballPlayersByFirstName(String val) {

        String lastName = oanFootballPlayersBiMap.get(val);
        if (lastName == null) {
            return getFootballPlayersByLastName(val);
        }
        String firstName = oanFootballPlayersBiMap.inverse().get(lastName);
        FootballPlayers footballPlayers = new FootballPlayers();
        footballPlayers.setFirstName(firstName);
        footballPlayers.setLastName(lastName);
        return footballPlayers;

    }

    public static FootballPlayers getFootballPlayersByFirstName(String val1, String val2) {


        FootballPlayers footballPlayers = getFootballPlayersByFirstName(val1);

        if (footballPlayers == null) {
            footballPlayers = getFootballPlayersByFirstName(val2);
            return footballPlayers;
        }
        return footballPlayers;


    }

    private static FootballPlayers getFootballPlayersByLastName(String val) {

        String firstName = oanFootballPlayersBiMap.inverse().get(val);
        if (firstName == null) {
            return null;
        }
        String lastName = oanFootballPlayersBiMap.get(firstName);
        FootballPlayers footballPlayers = new FootballPlayers();
        footballPlayers.setFirstName(firstName);
        footballPlayers.setLastName(lastName);
        return footballPlayers;

    }


}
