# REST

## REST 란?

* "Representational State Transfer"의 약자
* 네트워크 상에서 Client와 Server 사이의 통신 방식 중 하나이다.
* 자원을 이름으로 구분하여 해당 자원의 상태(정보)를 주고 받는 모든 것을 의미한다.

**-자원(resource)의 표현에 의한 상태 전달-**

* 자원의 표현: 그 자원을 표현하기 위한 이름
* 상태 전달: 데이터가 요청되어지는 시점에서 자원의 상태를 전달한다.
* JSON 혹은 XML을 통해 데이터를 주고 받는 것이 일반적이다.



### REST의 구체적인 개념

* HTTP URI(Uniform Resource Identifier)를 통해 자원(resource)을 명시하고, HTTP Method(POST, GET, PUT, DELETE)를 통해 해당 자원에 대한 CRUD Operation을 적용하는 것을 의미한다.

**-HTTP Method를 통해 Resource를 처리하도록 설계된 아키텍쳐**

* REST는 모든 자원에 고유한 ID인 HTTP URI를 부여한다.



### REST API의 구성요소

* 자원(Resource) : URI
* 행위(Verb) : HTTP Method
* 표현(Representation of Resource)



### CRUD

* Create : 생성(POST)
* Read : 조회(GET)
* Update : 수정(PUT)
* Delete : 삭제(DELETE)



