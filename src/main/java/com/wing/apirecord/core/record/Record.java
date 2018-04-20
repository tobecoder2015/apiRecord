package com.wing.apirecord.core.record;

import com.wing.apirecord.core.model.Request;
import com.wing.apirecord.core.model.Response;
import lombok.Data;
import lombok.ToString;

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
            if(((Record) object).getRequest().getUrl().equals(request.getUrl()) &&
                    ((Record) object).getRequest().getQueryParaMap().equals(request.getQueryParaMap())  )
                return true;
            return false;
        }
        return false;

    }

    @Override
    public String toString(){
        return request.getUrl()+" "+response.getBody();
    }
}
