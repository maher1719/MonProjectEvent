package com.example.miniprojetevents.database.dao;

import com.example.miniprojetevents.entities.Event;
import com.example.miniprojetevents.entities.MessageNetwork;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IEvent {
    @GET("/mesWebServices/selectAllEvent.php")
    Call<List<Event>> getEvent();

    @POST("/mesWebServices/addEvent.php")
    Call<MessageNetwork> addEvent(@Body Event event);

    @POST("/mesWebServices/addEvent.php")
    Call<MessageNetwork> addEvent1();

    @FormUrlEncoded
    @POST("/mesWebServices/addEvent.php")
    Call<MessageNetwork> addEventData(
            @Field("titre") String titre,
            @Field("categorie") String categorie,
            @Field("type") String type,
            @Field("imgEvent") String imgEvent,
            @Field("montant") String montant,
            @Field("description") String description,
            @Field("capacite") String capacite,
            @Field("dateDebEvent") String dateDebEvent,
            @Field("timeDebEvent") String timeDebEvent,
            @Field("duree") int duree,
            @Field("dateFin") String dateFin,
            @Field("timeFinEvent") String timeFinEvent,
            @Field("lieuEvent") String lieuEvent,
            @Field("userMail") String userMail
    );


}
