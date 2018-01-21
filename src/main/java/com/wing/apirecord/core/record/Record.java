package com.wing.apirecord.core.record;

import com.wing.apirecord.core.model.Request;
import com.wing.apirecord.core.model.Response;
import lombok.Data;

@Data
public class Record {
    private int id;
    private String createTime;
    private Request request;
    private Response response;

    public void setRequestBody(String body){
        request.setBody(body);
    }


    public void setResponseBody(String body){
        response.setBody(body);
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof Record){
            if(((Record) object).getRequest().getUrl().equals(request.getUrl()))
                return true;
            return false;
        }
        return false;

    }
}
