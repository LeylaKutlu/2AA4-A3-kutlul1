package ca.mcmaster.se2aa4.island.teamXXX;

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
        if (extras == null || !extras.has("range")){
            return -1;
        } else {
            return extras.getInt("range");
        }
    }

    public boolean groundFound(){
        if (extras == null || !extras.has("found")){
            return false;
        }
        return "GROUND".equals(extras.getString("found"));
    }

    public String getCreek(){
        if (extras == null || !extras.has("creeks")){
            return null;
        } 

        JSONArray creeks = extras.getJSONArray("creeks");
        if (creeks.isEmpty()){
            return null;
        } 
        return creeks.getString(0);
    }

    public boolean foundSite(){
        if (extras == null || !extras.has("sites")){
            return false;
        } 

        JSONArray sites = extras.getJSONArray("sites");
        if (sites.isEmpty()){
            return false;
        } 
        return true;
    }

    @Override
    public String toString(){
        return this.response.toString(2);
    }


}