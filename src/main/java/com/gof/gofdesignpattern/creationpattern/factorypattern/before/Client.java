package com.gof.gofdesignpattern.creationpattern.factorypattern.before;

public class Client {

    public static void main(String[] args) {
        /**
         * 기본적인 생성 코드
         *
         * Factory Method는 생성자를 한 클래스에 몰아(팩토리)
         * 이 클래스를 통해 여러 클래스를 생성하는 패턴이다.
         * 구체적으로 어떤 인스턴스를 만들지는 서브 클래스가 정한다.
         *
         * 이 코드은 ShipFactory에 Ship의 생성자를 위임하는 역할을 한다.
         * 다만 이 코드는 Ship과 ShipFactory의 코드를 변경하여 추가 사항을 등록해야 한다.
         * 이러한 방식은 객체지향 원칙 SOLID 중 Open Closed Principle에 위배된다.
         * 확장에는 열려있고 변경에는 닫혀야한다.
         * 이 구조를 고처서 배를 변경하기 가능하면서도 기존의 코드를 건드리지 않는 방법을 찾아보자.
         * **/
        Ship whiteship = ShipFactory.orderShip("Whiteship", "keesun@mail.com");
        System.out.println(whiteship);

        Ship blackship = ShipFactory.orderShip("Blackship", "keesun@mail.com");
        System.out.println(blackship);
    }

}
