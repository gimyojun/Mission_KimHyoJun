package com.ll.domain;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class App {
    Scanner sc;
    List<Quote> quotesList;

    public App(){
        sc = new Scanner(System.in);
        quotesList = new ArrayList<>();
        //initTestData();
    }

    private void initTestData() {
        for(int i=1;i<=10;i++){
            quoteWriter("명언" + i,"작가" + i );
        }
    }
    private void quoteWriter(String quote, String writer){
        Quote quoteTmp = new Quote(quote,writer);
        quotesList.add(quoteTmp);
    }

    public void run(){
        fileLoad();
        while(true){
            System.out.println("== 명언 앱 ==");
            System.out.println("명령)");
            String order = sc.nextLine();
            Rq rq = new Rq(order);

            switch (rq.getAction()) {
                case "종료":
                    fileSave();
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

    private void fileLoad() {
        try (BufferedReader reader = new BufferedReader(new FileReader("quotes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t"); // 탭으로 구분되있다.
                if (parts.length == 2) {
                    String quote = parts[0];
                    String writer = parts[1];
                    quoteWriter(quote, writer);
                }
            }
            System.out.println("명언리스트 로딩 완료 quotes.txt.");
        } catch (IOException e) {
            System.err.println("로딩 중 오류  " + e.getMessage());
        }
    }

    private void fileSave() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("quotes.txt"))) {
            for (Quote quote : quotesList) {
                writer.println(quote.getQuote() + "\t" + quote.getWriter());
            }
            System.out.println("명언 저장 quotes.txt.");
        } catch (IOException e) {
            System.err.println("명언 리스트 저장 중 오류: " + e.getMessage());
        }
    }

    private void register(){
        String quote;
        String writer;
        System.out.println("명언 : ");
        quote = sc.next();
        System.out.println("작가 : ");
        writer = sc.next();
        quoteWriter(quote,writer);
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
        String quote = sc.nextLine();
        System.out.println("수정할 작가명을 입력해 주세요.");
        String writer = sc.nextLine();
        quotesList.get(id - 1).setQuote(quote);
        quotesList.get(id - 1).setWriter(writer);
    }


}