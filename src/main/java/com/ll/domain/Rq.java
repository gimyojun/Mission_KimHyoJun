package com.ll.domain;

import com.ll.standard.util.Ut;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    String order;
    @Getter
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

    int getParamAsInt(String paramName, int defaultValue){
        if(map ==null){
            return defaultValue;
        }
        String paramValue = map.get(paramName);
        if(paramValue!= null){
            return Ut.str.parseInt(paramValue, defaultValue);
        }
        return defaultValue;

    }



}
