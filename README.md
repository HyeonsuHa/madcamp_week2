# madcamp_week2

개발한 사람 : 황인준, 하현수 

앱소개 : parade라는 보드게임을 어플리케이션화시키려 했던 앱입니다. 로그인과 회원가입 그리고 방입장까지 구현이 되었습니다.

세부내용 구현

![image](https://user-images.githubusercontent.com/94748079/178501960-26b69c84-3f13-47c1-bf48-55cd1796d300.png)
로그인 화면입니다. 아이디 입력 edittext아래에 문구를 클릭 시, 회원가입 화면창으로 전환됩니다. 
올바른 아이디와 비밀번호 입력 시, 로그인이 완료 됩니다. 로그인은 http를 이용하여 post request를 요청하여 true response를 받았을 시에 성공하게 됩니다.
false response를 받을 경우 로그인을 다시 시도해야 합니다. 

![image](https://user-images.githubusercontent.com/94748079/178502405-6da128bd-545a-46c8-bb41-fdc56045560a.png)
회원가입 화면입니다. format에 맞게 아이디와 비밀번호를 입력하고, 비밀번호의 경우, 한 번 더 입력하여 확인을 해야합니다. 또한 format에 맞지 않거나 혹은 
비밀번호와 비밀번호 확인이 서로 다를 경우, 아래와 같은 경고 문구를 남깁니다.
![image](https://user-images.githubusercontent.com/94748079/178502634-4175f22f-af9a-4ec0-a3f9-4151ea134b0d.png)
아래 사진은 올바른 format을 작성하였을 시의 사진입니다.
![image](https://user-images.githubusercontent.com/94748079/178502744-6a015df1-ed0b-488d-b17f-3099c215bef2.png)
그림과 같이 회원가입 버튼이 활성화되면서 회원가입 후, 로그인 화면으로 되돌아 가게 됩니다.
로그인을 성공적으로 끝마칠 시,
![image](https://user-images.githubusercontent.com/94748079/178503153-61638eec-3111-4df3-b636-6e42b4df9735.png)
위 사진처럼 메인 메뉴로 이동하게 됩니다.
버튼을 눌러서 lobby로 이동할 수 있습니다.
앱에서는 정상 작동하지 않았지만, 방 생성을 누를 경우
![image](https://user-images.githubusercontent.com/94748079/178503416-8a62a9aa-cdb8-4998-bf4c-9266335572b6.png)
위와 같이 사진이 뜹니다.
최대 인원 수 설정이 가능하고, 방 생성이 되어 방 정보가 서버로 옮겨집니다.
이상입니다. 감사합니다. 
