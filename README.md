# My-DEV 개발

## 개발 환경
### Backend
- Java 8
- Spring Boot 2.3
- Lombok
- Spring Data JPA
- QueryDSL
### Frontend
- VanillaJS
- ES6
- HTML/CSS
### Infra
- MySQL
- H2

## 개발 제약사항
- 패키지 구조
    - domain: 비지니스 로직
    - service: 트랜잭션 단위
    - controller: API 요청 및 응답 처리
- TDD 적극 도입
    - Controller: Mock을 사용한 단위 테스트
    - Service: 통합 테스트
    - Domain: 단위 테스트
    - 인수 테스트: 추후에 분리하여 만들기
- 일관적인 예외 처리

## 기능 요구사항
### Link Bucket
유용한 개발 컨텐츠 링크를 모아놓은 장소

#### Backend
- Link Entity
    - url
    - title
    - description
    - image
- Tag Entity
    - name
#### Frontend
- 링크 입력: url, tags
    - Open Graph 로 해당 링크 정보 가져오기

### Blog