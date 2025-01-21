package com.JournalApp.Journal.apiResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {

    private Current current;

    @Getter
    @Setter
    public class Current{
        private int temperature;
        @JsonProperty("weather_descriptions") // but in json api sends the info as weather_description so we need it to deserialize json to POJO
        private List<String> weatherDescriptions; // camelCase is standard
        private int feelslike;
    }
}
