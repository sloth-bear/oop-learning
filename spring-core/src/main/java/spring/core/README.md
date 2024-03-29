# 비즈니스 요구사항과 설계

## 정책
요구사항 중 회원 데이터, 할인 정책 같은 부분들은 지금 결정하기 어려운 부분이다.
그렇다고 이런 정책이 결정될 때까지 개발을 무기한 기다릴 수도 없다.
객체 지향 설계 방법으로 인터페이스 먼저 생성 후 언제든 구현체를 갈아끼울 수 있도록 설계할 것이다.

### 회원
* 회원을 가입하고 조회할 수 있다. 
* 회원은 일반과 VIP 두 가지 등급이 있다.
* 회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다. (미확정)

### 쿠폰과 할인 정책
* 회원은 상품을 주문할 수 있다. 
* 회원 등급에 따라 할인 정책을 적용할 수 있다.
* 할인 정책은 다음과 같다. 
  * VIP: 1000원을 할인해주는 고정 금액 할인을 적용해달라 (추후 변경 가능)
* 할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 결정하지 못했고, 오픈 직전까지 고민을 미루고 싶다. 최악의 경우 할인을 적용하지 않을 수도 있다. 


## 설계
### 회원 도메인 
#### 요구사항
* 회원을 가입하고 조회할 수 있다. 
* 회원은 일반과 VIP 두가지 등급이 있다. 
* 회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다. (미확정)