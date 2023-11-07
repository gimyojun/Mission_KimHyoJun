package com.ll;


import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class App {
    Scanner sc;
    ArrayList<Quote> quotesList;

    App(){
        sc = new Scanner(System.in);
        quotesList = new ArrayList<>();
    }
    void run(){
        while(true){
            System.out.println("== 명언 앱 ==");
            System.out.println("명령)");
            String order = sc.next();
            if(order.equals("종료")){
                break;
            }else if(order.equals("등록")){
                register(sc);
            }else if(order.equals("목록")){
                list();
            }else if(order.startsWith("삭제?")){
                delete(order);
            }else if(order.startsWith("수정?")){
                delete(order);

            }


        }

    }

    void register(Scanner sc){
        String quote;
        String writer;
        System.out.println("명언 : ");
        quote = sc.next();
        System.out.println("작가 : ");
        writer = sc.next();
        Quote quoteTmp = new Quote(quote,writer);
        quotesList.add(quoteTmp);
    }
    void list(){
        System.out.println("번호 / 작가 / 명언");
        for(int i=0;i<quotesList.size();i++){
            System.out.println(i + 1+ " / " + quotesList.get(i).writer + " / " + quotesList.get(i).quote);
        }
    }
    void delete(String order){
        int id = getParamAsInt(order,"id",0);
        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return; // 함수를 끝낸다.
        }
        if(id > quotesList.size()){
            System.out.println("해당 id는 존재하지 않습니다.");
            return;
        }
        quotesList.remove(id-1);
    }
    void modify(String order){
        int id = getParamAsInt(order,"id",0);
        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return; // 함수를 끝낸다.
        }
        quotesList.remove(id-1);
    }

    int getParamAsInt(String cmd, String paramName, int defaultValue) {
        String[] cmdBits = cmd.split("\\?", 2);
        String queryString = cmdBits[1];
        if(cmdBits[1] == ""){
            return 0;
        }

        String[] queryStringBits = queryString.split("&");

        for (int i = 0; i < queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];
            if(!queryParamStr.contains("=")){
                return 0;
            }
            String[] queryParamStrBits = queryParamStr.split("=", 2);

            String _paramName = queryParamStrBits[0];
            String paramValue = queryParamStrBits[1];

            if (_paramName.equals(paramName)) {
                try {
                    // 문제가 없을 경우
                    return Integer.parseInt(paramValue);
                } catch (NumberFormatException e) {
                    // 문제가 생긴 경우
                    return defaultValue;
                }
            }
        }

        return defaultValue;
    }
}