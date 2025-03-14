package ca.mcmaster.se2aa4.island.teamXXX;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;

import org.json.JSONObject;
import org.json.JSONArray;
import java.util.ArrayList;

public class Response{
    private JSONObject response;
    private JSONObject extras;

    public Response(JSONObject response){
        this.response = response;
        
    }

    public String getStatus(){
        return response.getString("status");
    }

    public int getCost(){
        return response.getInt("cost");
    }
    

    public JSONObject getExtras(){
        extras = response.getJSONObject("extras");
        return response.getJSONObject("extras");
    }

    public int getRange(){
        if (extras == null){
            return -1;
        } else {
            return extras.getInt("range");
        }
    }

    public boolean groundFound(){

        return (extras != null) && "GROUND".equals(extras.getString("found"));
    }

    public String getCreek(){
        if (extras == null){
            return null;
        } 

        ArrayList<Object> creeks = new ArrayList<>(extras.getJSONArray("creeks").toList());
        if (creeks.size() == 0){
            return null;
        } 
        return creeks.get(0).toString();
    }

    public boolean foundSite(){
        if (extras == null){
            return false;
        }

        ArrayList<Object> sites = new ArrayList<>(extras.getJSONArray("sites").toList());
        if (sites.size() == 0){
            return false;
        }
        return true;
    }

    @Override
    public String toString(){
        return this.response.toString(2);
    }


}