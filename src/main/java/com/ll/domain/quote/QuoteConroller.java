package com.ll.domain.quote;


import com.ll.base.Rq;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class QuoteConroller {

    private Scanner sc;
    private List<Quote> quotesList;

    public QuoteConroller(Scanner sc){
        this.sc=sc;
        quotesList = new ArrayList<>();
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



    public void jsonLoad() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data.json"))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }

            String jsonString = jsonBuilder.toString();
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonQuote = jsonArray.getJSONObject(i);
                String quote = jsonQuote.getString("quote");
                String writer = jsonQuote.getString("writer");
                quoteWriter(quote, writer);
            }

            System.out.println("data.json 파일에서 명언을 로드했습니다.");
        } catch (IOException e) {
            System.err.println("data.json 파일 로드 중 오류 발생: " + e.getMessage());
        }
    }

    public void jsonSave() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data.json"))) {
            writer.println("[");
            for (int i = 0; i < quotesList.size(); i++) {
                Quote quote = quotesList.get(i);
                writer.println("  {");
                writer.println("    \"quote\": \"" + quote.getQuote() + "\",");
                writer.println("    \"writer\": \"" + quote.getWriter() + "\"");
                writer.print("  }");
                if (i < quotesList.size() - 1) {
                    writer.println(",");
                } else {
                    writer.println();
                }
            }
            writer.println("]");
            System.out.println("data.json 파일의 내용이 갱신되었습니다.");
        } catch (IOException e) {
            System.err.println("data.json 파일 생성 중 오류 발생: " + e.getMessage());
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

    public void register(){
        String quote;
        String writer;
        System.out.println("명언 : ");
        quote = sc.nextLine();
        System.out.println("작가 : ");
        writer = sc.nextLine();
        quoteWriter(quote,writer);
    }
    public void list(){
        System.out.println("번호 / 작가 / 명언");
        for(int i=0;i<quotesList.size();i++){
            System.out.println(i + 1+ " / " + quotesList.get(i).getWriter() + " / " + quotesList.get(i).getQuote());
        }
    }
    public void delete(Rq rq){
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
    public void modify(Rq rq){
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
