package com.ll.base;

import com.ll.domain.quote.QuoteConroller;

import java.util.Scanner;
public class App {
    Scanner sc;


    public App(){
        sc = new Scanner(System.in);
        //initTestData();
    }



    public void run(){

        QuoteConroller quoteConroller = new QuoteConroller(sc);


        //fileLoad();
        quoteConroller.jsonLoad();
        while(true){
            System.out.println("== 명언 앱 ==");
            System.out.println("명령)");
            String order = sc.nextLine();
            Rq rq = new Rq(order);

            switch (rq.getAction()) {
                case "종료":
                    //fileSave();
                    quoteConroller.jsonSave();
                    return;
                case "등록":
                    quoteConroller.register();
                    break;
                case "목록":
                    quoteConroller.list();
                    break;
                case "삭제":
                    quoteConroller.delete(rq);
                    break;
                case "수정":
                    quoteConroller.modify(rq);
                    break;
            }


        }

    }



}