package com.gof.gofdesignpattern.creationpattern.factorypattern.after;


/**
 * ShipFactory의 sendEmailTo를 구현하기 위한 클래스
 * 추상 클래스로 만들어 모든 Ship 클래스들에서 공통적으로 사용 가능하게 만들었다.
 *
 * 이 ShipFactory 하위 클래스들의 연관 관계로
 * ShipFactory -> DefaultShipFactory -> White/BlackShipFactory가 된다.
 * **/
public abstract class DefaultShipFactory implements ShipFactory {

    @Override
    public void sendEmailTo(String email, Ship ship) {
        System.out.println(ship.getName() + " 다 만들었습니다.");
    }

}
