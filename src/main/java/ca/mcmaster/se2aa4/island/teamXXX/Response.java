package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;
import org.json.JSONArray;

public class Response{
    private JSONObject responseJSON;
    private JSONObject extras;

    public Response(JSONObject responseJSON){
        this.responseJSON = responseJSON;
        
    }

    public String getStatus(){
        return responseJSON.getString("status");
    }

    public int getCost(){
        return responseJSON.getInt("cost");
    }
    

    public JSONObject getExtras(){
        extras = responseJSON.getJSONObject("extras");
        return responseJSON.getJSONObject("extras");
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
        if (creeks.length() == 0){
            return null;
        } 
        return creeks.getString(0);
    }

    public boolean foundSite(){
        if (extras == null || !extras.has("sites")){
            return false;
        } 

        JSONArray sites = extras.getJSONArray("sites");
        return (sites.length() != 0);
    }

    public String getSite(){
        if (extras == null || !extras.has("sites")){
            return null;
        } 

        JSONArray sites = extras.getJSONArray("sites");
        if (sites.length() == 0){
            return null;
        } 
        return sites.getString(0);
    }

    @Override
    public String toString(){
        return this.responseJSON.toString(2);
    }


}