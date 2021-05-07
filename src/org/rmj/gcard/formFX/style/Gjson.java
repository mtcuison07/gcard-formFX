package org.rmj.gcard.formFX.style;

import org.json.simple.JSONObject;

public interface Gjson {
    public void setData(JSONObject json);
    public JSONObject getData();
    public String getMessage();
    public boolean isOkey();   
}
