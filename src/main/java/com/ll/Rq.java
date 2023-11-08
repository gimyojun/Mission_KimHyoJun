package com.ll;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class Rq {
    String order;
    String action;
    String queryString;
    Map<String, String> map;

    Rq(String order){
        this.order = order;
        String[] orderBits = order.split("\\?", 2);
        this.action = orderBits[0].trim();
        if(orderBits.length==1){
            return;
        }
        this.queryString = orderBits[1].trim();


        map = new HashMap<>();
        String[] queryStringBits = queryString.split("&");

        for(int i=0;i<queryStringBits.length;i++){
            String queryParamStr  = queryStringBits[i];
            if(!queryParamStr.contains("=")){
               //예외처리
                return;
            }
            String[] tmp = queryParamStr.split("=");
            String paramName = tmp[0];
            if(tmp.length==1){
                return;
            }
            String paramValue = tmp[1];
            map.put(paramName,paramValue);
        }


    }
    String getAction() {
        return action;
    }
    int getParamAsInt(String paramName, int defaultValue){
        if(map ==null){
            return defaultValue;
        }
        String paramValue = map.get(paramName);
        if(paramValue!= null){
            try{
                return parseInt(paramValue);
            }catch (NumberFormatException e){

            }
        }
        return defaultValue;







    }

}
