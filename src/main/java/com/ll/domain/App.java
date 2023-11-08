package com.ll.domain;


import java.util.ArrayList;
import java.util.Scanner;
public class App {
    Scanner sc;
    ArrayList<Quote> quotesList;

    public App(){
        sc = new Scanner(System.in);
        quotesList = new ArrayList<>();
    }
    public void run(){

        while(true){
            System.out.println("== 명언 앱 ==");
            System.out.println("명령)");
            String order = sc.next();
            Rq rq = new Rq(order);

            switch (rq.getAction()) {
                case "종료":
                    return;
                case "등록":
                    register();
                    break;
                case "목록":
                    list();
                    break;
                case "삭제":
                    delete(rq);
                    break;
                case "수정":
                    modify(rq);
                    break;
            }


        }

    }

    private void register(){
        String quote;
        String writer;
        System.out.println("명언 : ");
        quote = sc.next();
        System.out.println("작가 : ");
        writer = sc.next();
        Quote quoteTmp = new Quote(quote,writer);
        quotesList.add(quoteTmp);
    }
    private void list(){
        System.out.println("번호 / 작가 / 명언");
        for(int i=0;i<quotesList.size();i++){
            System.out.println(i + 1+ " / " + quotesList.get(i).getWriter() + " / " + quotesList.get(i).getQuote());
        }
    }
    private void delete(Rq rq){
        int id = rq.getParamAsInt("id", 0);
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
    private void modify(Rq rq){
        int id = rq.getParamAsInt("id", 0);
        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return; // 함수를 끝낸다.
        }
        int cnt = quotesList.size();
        if(id > cnt){
            System.out.println(id + "명언은 존재하지 않습니다. 다시 입력해주세요.");
            return;
        }
        System.out.println("수정할 명언을 입력해 주세요.");
        String quote = sc.next();
        System.out.println("수정할 작가명을 입력해 주세요.");
        String writer = sc.next();
        quotesList.get(id - 1).setQuote(quote);
        quotesList.get(id - 1).setWriter(writer);
    }

}