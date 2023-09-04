package com.gof.gofdesignpattern.creationpattern.factorypattern.after;

/**
 * ShipFactory - 생성 클래스를 정의하는 추상체
 * 추상체를 만들고 구체적인 세부 옵션은 이 추상체를 구현하는 서브클래스에서 정의한다.
 * 이것을 디자인 패턴에서는 creator 인터페이스라 부른다.
 * 이것을 사용해서 creator 구현체의 설계도를 정리한다.
 *
 * **/
public interface ShipFactory {

    // ShipFactory 서브 클래스들이 해야하는 활동 정의
    // 자바 8버전에서는 deafult를 통해 지정 가능
    // defalut면 굳이 override 안 해도 됨
    default Ship orderShip(String name, String email) {
        validate(name, email);
        prepareFor(name);
        Ship ship = createShip();
        sendEmailTo(email, ship);
        return ship;
    }

    void sendEmailTo(String email, Ship ship);

    // Ship을 만드는 로직은 구현체에서 정의하기
    Ship createShip();

    // 1.9 이후 인터페이스에서도 함수를 구현할 수 있다.
    // private 함수는 구현하겠다는 선언과 비슷하다는 것을 잊지 말자
    private void validate(String name, String email) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("배 이름을 지어주세요.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("연락처를 남겨주세요.");
        }
    }

    private void prepareFor(String name) {
        System.out.println(name + " 만들 준비 중");
    }


    /**
     * 팩토리 매서드 패턴의 장점
     * -> SOLID 중 OCP 원칙을 적용하여 기존 코드를 건드리지 않고
     *    새로운 인스턴스를 다른 방법으로 얼마든지 확장 가능하다.
     *    이것이 가능한 이유는 프로덕트 클래스와 크리에이터 클래스의 관계를 느슨하게 가져갔기 때문
     *    Losely Coupled
     *    기존 코드를 건들지 않고 확장 가능
     *
     * 팩토리 매서드 패턴의 단점
     * -> 각자의 역할을 나누다 보니 클래스가 많아질 수 밖에 없다.
     *
     * OCP
     * -> 기존 코드를 변경하지 않으면서 얼마든지 새로운 기능을 추가할 수 있어야 한다.
     *
     *
     * defualt 메소드
     * -> 인터페이스에 기본 구현체를 만들 수 있다.
     *    해당 인터페이스를 상속받는 구현체나 인터페이스도 사용 가능
     *    그렇기 때문에 자바 8버전부터는 추상 클래스를 많이 사용하지 않고 인터페이스를 많이 사용한다.
     *    또한 인터페이스에 추가된 private 메소드도 존재하여 해당 인터페이스에서 구현을 돕는다.
     * **/

}
