package com.gof.gofdesignpattern.creationpattern.factorypattern.after;

public class Client {

    /**
     * 클라이언트 코드는 디자인 패턴과 무관하게 계속 바뀔 수 있다.
     * 구체적인 세부사항이 추가될 수록 클라이언트는 변경될 여지가 충분하다.
     * 다만 제품과 크리에이터 클래스들이 많이 추가가 될 경우에 효율적인 것이다.
     *
     * 팩토리 메서드 패턴을 사용하는 기술로
     * 1. 자바 Calender -> 그레고리안력, 불교력, 일본력으로 나뉜 하위 객체를 생성할 때
     * 2. Spring Bean Factory -> 빈을 생성할 때 사용하는 Bean Factory가 Creator다.
     *    Bean Factory에서 사용하는 xml타입, 어노테이션 타입을 통해 빈을 지정하는 방식이 달라지는데
     *    이에 따라 빈의 생성 방법도 다르기 때문에 IoC 컨테이너 역할을 Bean Factory가 한다.
     * **/

    public static void main(String[] args) {
        Client client = new Client();
        // 인터페이스화해서 어느 정도의 코드를 의존성 주입으로 넘겨준다.
        // 의존성 주입은 OOP에서 중요한 요소이다.
        // 의존성 주입은 거의 항상 생성자와 인터페이스를 통해서 주입한다.
        client.print(new WhiteshipFactory(), "whiteship", "keesun@mail.com");
        client.print(new BlackshipFactory(), "blackship", "keesun@mail.com");
    }

    private void print(ShipFactory shipFactory, String name, String email) {
        System.out.println(shipFactory.orderShip(name, email));
    }

}
