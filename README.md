### :sunny: :umbrella: :cloud: SMWU 2020-2 모바일프로그래밍 수업 프로젝트
#### :pushpin: Topic : 오늘의 외출 옷차림 공유 커뮤니티 앱

##### :curly_loop: 앱 기능
- 날씨 API를 활용한 현재 날씨 온도 확인 기능 
:arrow_right: http 통신을 이용하여 xml로 된 날씨 정보를 가져와서 표시

- 닉네임, 현재 위치 하는 지역, 추위/더위 타는 체질, 현재 입은 옷차림 및 상태 입력
:arrow_right: RecyclerView 를 활용하여 목록으로 나타냄

- 입력한 내용을 수정 및 삭제 가능

##### :curly_loop: 코드 구성
- activity_main.xml 
- edit_box.xml : 각 내용을 입력할 수 있는 화면
- item.xml : 입력한 각 내용들을 하나의 목록으로 보여주는 화면
- Dictionary.java : 데이터 담을 모델 클래스를 생성하고 Recyclerview에 출력될 값들을 정의해주는 클래스
- CustomAdapter.java : 편집/삭제 기능 등 Recyclerview의 제어 클래스
- MainActivity.java 
