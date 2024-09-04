HISTORY : 개발자로 일하는 친구와 카톡을 나누던 중 CSR에 대한 이야기가 나왔기에 알아 두면 좋을것 같아 적어봅니다.


1. MPA(Multi Page Application)란 여러 개의 페이지로 구성된 어플리케이션이다.

 여러 개의 페이지를 두고 사용자의 요청에 따라 페이지를 요청할 때 마다 해당 페이지의 HTML 파일을 서버로 부터 다시 받는다.
이때, 이미 컨텐츠가 들어있는 상태의 HTML 파일을 받는다. (Server에서 랜더링 된 파일들을 받음)

2. SPA(Single Page Application)란 하나의 페이지로 구성된 어플리케이션이다.

 반면 SPA는 웹 애플리케이션에 필요한 모든 정적 리소스를 최초 한번에 다운로드하며, 사용자의 요청에 따라 필요한 부분만 동적으로 변경한다.
초기 SPA는 AJAX를 활용하는 방식이였지만, 효율적이지 못하기에 이를 도와주는 프레임워크인 React, Vue, Angular등이 도입되었다.

![image](https://github.com/user-attachments/assets/24a308a2-3af1-4fb5-9232-ea90cc75fe22)



3. CSR(Client Side Rendering)은 자바스크립트 파일을 브라우저에서 해석해 렌더링하는 방식이다

4.SSR(Server Side Rendering)은 브라우저에서 자바스크립트 코드를 다운로드 받아 해석 할 때까지 기다리지 않고, 서버에서 보여질 HTML을 미리 준비해 클라이언트한테 응답해주는 방식


![image](https://github.com/user-attachments/assets/a8183e47-0223-48c5-86af-4b16acf083ec)


![image](https://github.com/user-attachments/assets/255c8b89-3c85-47aa-ab64-cd2eef3fc1f5)


※ SSR VS CSR

[SSR]

장점
초반 렌더링 시, 애플리케이션 가동에 필요한 정적 파일을 받아오므로 이후 새로고침을 할 필요가 없어 부드럽고 자연스러운 사용자 경험을 느끼게 해줌.
TTV와 TTI가 동시에 시작되기에, 사용자는 페이지가 로딩되자마자 곧바로 애플리케이션을 사용할 수 있음.
또한 이후의 동작에서 추가적인 정보가 필요한 경우에는 필요한 리소스만 요청하기에, 서버의 부담이 상대적으로 적어진다.
기존에는 서버에서 렌더링 된 페이지를 받았다면, SPA의 경우 최초 요청 이후 내부에서 렌더링을 하기에 서버의 부담을 분산시킨다.

단점
초기에 애플리케이션 가동에 필요한 대부분의 리소스를 받아야 하기에, 곧바로 페이지를 로딩하지 못한다.
즉 TTV (Time To View) 가 상대적으로 늦음. 페이지 렌더링 전까지 사용자는 텅 빈 화면만 바라보아야 함.
이를 해결하기 위해 많은 양의 JS 코드를 번들링 하여 분할 제공하는 Code Splitting 기법이 있으나, 근본적인 해결책은 아님.
SEO (Search Engine Optimization), 즉 검색 엔진 최적화를 진행하기 어려움. 서버에는 초기 HTML이 비어있는 경우가 많기 때문.
사용자의 정보를 클라이언트 측에서 관리할 경우, 보안 이슈가 발생할 수 있음. (저장할 공간이 쿠키, 스토리지 뿐)

[CSR]
장점
서버에서 사전에 렌더링 된 정적 리소스를 받음으로서 빠르게 애플리케이션 화면을 띄울 수 있다.
즉 TTV 가 SPA에 비해 월등히 빠르다. 클라이언트에서는 사전에 렌더링된 페이지를 띄우기만 하면 되기 때문이다.
SEO (Search Engine Optimization), 즉 검색 엔진 최적화(크롤링)를 진행하기 쉬움

단점
화면을 빠르게 띄울 수 있으나, 동적인 인터렉션을 담당하는 JS 파일을 적용하기까지의 텀이 있음.
즉 TTI (Time To Interact) 가 TTV보다 상대적으로 늦고, 그 시간 동안 사용자는 어떠한 인터렉션도 진행할 수 없음
페이지를 이동할 때마다 결국 서버에서 사전에 렌더링 된 파일을 받아야 하기에, 매 요청마다 깜빡임 현상이 발생함.
서버에서 매 요청 시에 보내야 할 데이터의 사이즈가 커짐으로서, 서버 트래픽이 상승할 가능성이 높음.

※ TTV? TTI
TTV (Time To View) : 사용자가 어플리케이션 화면을 "보기까지" 걸리는 시간.
TTI (Time To Interact) : 사용자가 어플리케이션 화면과 "상호작용" 할 수 있기까지 걸리는 시간.
즉 TTV가 빠르면 화면이 빠르게 보이는 것이며, TTI이 빠르다면 웹을 빠르게 사용할 수 있다는 것이다.


정리 
  우선 SPA = CSR인가, MPA = SSR인가에 대해부터 말해야 할 것 같다.
  
결론적으로는 아니다. SPA/MPA는 페이지 수를 기준으로, CSR/SSR은 렌더링이 되는 위치를 기준으로 구분한다고 한다.

즉 개념적으로는 아예 다른것이다.(햄버거 세트가 햄버거 + 감자튀김 이지, 햄버거 = 감자튀김인가?)

다만 SPA는 CSR방식을 많이 채용하며, SSR 또한 사용할 수 있지만, MPA는 SSR 방식밖에 사용하지 못 한다는 것이다.

쉽게 생각해 필자가 자주 사용했던 JSP를 활용한 개발 방식은 MPA + SSR 이고 유명한 프론트엔드 프레임워크인 react, vue 등은 SPA + CSR 인것이다.

vue를 공부하면서 새로고침없이 컴포넌트들이 수정되는것을 보고 신기해 했었지만 굳이? 라는 생각이 들었지만,

이번 공부로 인해 왜 react나 vue를 많이 사용하는가에 대해서 확실하게 알았다.

다만 아무리 찾아봐도 나오지않는 별거 아닌 궁금증이 하나 남아있는데..

굳이 굳이 JSP 페이지 여러개에 AJAX로 화면전환을 모두 처리한다면 이는 MPA에 CSR방식을 사용하는 것일까 좀 더 찾아봐야겠다. 


[참고] https://velog.io/@rookieand/CSR-SSR-SPA-MPA-%EC%9D%B4%EA%B2%83%EB%93%A4%EC%9D%80-%EB%AD%98%EA%B9%8C#2-mpa-multiple-page-application

[참고] https://enjoydev.life/blog/nextjs/1-ssr-ssg-isr

[참고] https://velog.io/@hyun/%EC%84%9C%EB%B2%84-%EC%82%AC%EC%9D%B4%EB%93%9C-%EB%A0%8C%EB%8D%94%EB%A7%81SSR

[참고] https://velog.io/@minw0_o/SPA-vs-MPA-CSR-vs-SSR#ssrserver-side-rendering
