package ca.mcmaster.se2aa4.island.teamXXX;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONObject;
import org.json.JSONTokener;

public class Response{
    public JSONObject response;
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
        if (extras == null){
            return false;
        } else {
            return extras.getString("found").equals("GROUND");
        }
    }

    public String getResponse(){
        return this.response.toString(2);
    }


}