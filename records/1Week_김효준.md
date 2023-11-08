# 명언 앱


## 생성자

- `QuoteController(Scanner sc)`: 스캐너 객체를 인자로 받는 생성자
- 스캐너 객체 초기화
- `quotesList` 초기화
- 명언 관련 메소드 등 다 컨트롤러로 옮기고 리팩토링

## 메서드

### `initTestData()`

- 명언 데이터를 초기화
- 1부터 10까지의 명언과 작가를 생성
- 현재 주석 상태, json으로 동작중

### `quoteWriter(String quote, String writer)`

- 명언과 작가를 받아 명언(Quote) 객체를 생성하고 리스트에 추가(리팩토링 완)

### `jsonLoad()`

- `data.json` 파일에서 명언 데이터를 로드
- JSON 파일을 읽어와서 `quotesList`에 추가

### `jsonSave()`

- 명언 데이터를 JSON 파일로 저장
- `quotesList`를 JSON 형식으로 작성하여 `data.json` 파일에 저장
- gson으로 리팩토링 가능

### `fileLoad()`

- `quotes.txt` 파일에서 명언 데이터를 로드
- 파일을 읽어와서 `quotesList`에 추가
- 현재 주석

### `fileSave()`

- 명언 데이터를 `quotes.txt` 파일로 저장
- `quotesList`를 탭으로 구분된 형식으로 작성하여 파일에 저장
- 현재 주석

### `register()`

- 사용자로부터 명언과 작가를 입력 받아 명언을 추가(리팩토링 완)

### `list()`

- 현재 명언 리스트를 출력

### `delete(Rq rq)`

- 명언 삭제
- 명령어로부터 `id` 파라미터를 Ut클래스의 parseInt로 추출해 해당 인덱스의 명언을 삭제

### `modify(Rq rq)`

- 명언 수정
- 명령어로부터 `id` 파라미터를 받아 해당 인덱스의 명언을 수정
- 
# Rq 클래스

## 패키지와 클래스

- `com.ll.base` 패키지에 위치한 `Rq` 클래스

## 멤버 변수

- `String order`: 명령어 문자열
- `String action`: 명령어의 액션 부분 (Getter 메서드로 노출)
- `String queryString`: 명령어의 쿼리 스트링 부분
- `Map<String, String> map`: 쿼리 스트링 파라미터를 저장하는 맵

## 생성자

- `Rq(String order)`: 명령어 문자열을 받아 Rq 객체를 초기화
    - 명령어를 액션과 쿼리 스트링으로 분리
    - 쿼리 스트링 파라미터를 파싱하여 `map`에 저장

## 메서드

### `getParamAsInt(String paramName, int defaultValue)`

- 쿼리 스트링에서 정수 파라미터 값을 가져옴
- 파라미터 이름과 기본값을 받아옴
- 파라미터가 없거나 파싱 오류가 발생하면 기본값을 반환

# Quote 클래스

## 패키지와 클래스

- `com.ll.domain.quote` 패키지에 위치한 `Quote` 클래스

## 멤버 변수

- `String quote`: 명언 내용
- `String writer`: 명언 작가

## 애노테이션

- `@AllArgsConstructor`: 모든 필드를 초기화하는 생성자를 자동으로 생성

## Getter 및 Setter

- `@Getter`: 명언 내용과 작가에 대한 Getter 메서드를 자동으로 생성
- `@Setter`: 명언 내용과 작가에 대한 Setter 메서드를 자동으로 생성

# Ut 클래스

## 패키지와 클래스

- `com.ll.standard.util` 패키지에 위치한 `Ut` 클래스

## 내부 클래스

### `str` 내부 클래스

- `Ut` 클래스 내부에 위치한 `str` 내부 클래스

## 메서드

### `parseInt(String value, int defaultValue)`

- 문자열 값을 정수로 파싱하는 메서드
- 파라미터로 문자열 `value`와 기본값 `defaultValue`를 받음
- 문자열을 정수로 파싱하고, 파싱에 실패하면 기본값을 반환

# App 클래스

## 패키지와 클래스

- `com.ll.base` 패키지에 위치한 `App` 클래스

## 멤버 변수

- `Scanner sc`: 사용자 입력을 받는 스캐너 객체

## 생성자

- `App()`: 스캐너 객체를 초기화

## 메서드

### `run()`

- 명언 애플리케이션을 실행하는 메서드
- `QuoteConroller` 객체를 생성하고 초기화
- `jsonLoad()` 메서드로 데이터를 로드
- 사용자에게 명령어를 입력받아 해당 명령어에 따른 기능 실행
- 종료 명령이 입력되면 데이터를 저장하고 애플리케이션 종료







