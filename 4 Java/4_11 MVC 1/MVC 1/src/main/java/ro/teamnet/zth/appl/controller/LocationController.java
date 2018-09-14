package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;

@MyController(urlPath = "/locations")
public class LocationController {
    @MyRequestMethod(urlPath = "/all", methodType = "/get")
    String getAllLocations(){
        return "allLocation";
    }

    @MyRequestMethod(urlPath = "/one", methodType = "/get")
    String getOneLocation(){
        return "oneRandomLocation";
    }
}
