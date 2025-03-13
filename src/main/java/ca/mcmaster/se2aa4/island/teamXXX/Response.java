package ca.mcmaster.se2aa4.island.teamXXX;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;

import org.json.JSONObject;

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

    @Override
    public String toString(){
        return this.response.toString(2);
    }


}