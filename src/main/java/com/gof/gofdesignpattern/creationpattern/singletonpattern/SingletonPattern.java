package com.gof.gofdesignpattern.creationpattern.singletonpattern;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// 직렬화 선언
//
public class SingletonPattern {

    public SingletonPattern() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Setting setting = Setting.getInstance2();
        // 나이브한 싱글톤 패턴 구형
        // 그러나 자바의 멀티스레드 환경에서는 적합하지 않다.
        // 동시에 스레드가 한 코드에 접근할텐데 안전하지 않다.
        System.out.println(setting == Setting.getInstance2());


        System.out.println("------------------------------");


        /**
         * 싱글톤 패턴 깨트리기
         * 1. Reflection
         * * **/
        Setting setting1 = Setting.getInstance4();

        // 아래 세 단계가 new 키워드를 쓴 것과 같다.
        // 새로운 인스턴스 생성
        Constructor<Setting> declaredConstructor = Setting.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        Setting setting2 = declaredConstructor.newInstance();
        System.out.println("reflection");
        System.out.println(setting1 == setting2);

        System.out.println("------------------------------");

        /**
         * 2. 직렬화 & 역직렬화
         * 직렬화 -> 객체를 파일로 저장하겠다.
         * **/

        Setting setting3 = Setting.getInstance4();
        Setting setting4 = null;

        try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("setting.obj"))) {
            out.writeObject(setting3);
        } catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (ObjectInput in = new ObjectInputStream(new FileInputStream("setting.obj"))) {
            setting4 = (Setting) in.readObject();
            System.out.println(setting3 == setting4);
        } catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("------------------------------");

        /**
         * Enum으로 싱글톤 생성해서 리플렉션 방지
         * 단점은 미리 생성된다는 점
         * 또한 상속이 불가능하다.
         * 상속 & Lazy Loading을 구현하고 싶으면 Static Inner Class가 제일 무난하다.
         * 이 방법은 직렬화 역직렬화에 굉장히 안전함
         * Enum은 Serializable을 구현하는 클래스
         * 별다른 장치 없이 역직렬화 가능
         * **/

        /**
         * 싱글톤 패턴 깨트리기
         * 1. Reflection
         * * **/
        SettingEnum setting5 = SettingEnum.INSTANCE;

        // 아래 세 단계가 new 키워드를 쓴 것과 같다.
        // 새로운 인스턴스 생성
        Constructor<SettingEnum> declaredConstructorEnum = SettingEnum.class.getDeclaredConstructor();
        declaredConstructorEnum.setAccessible(true);
        SettingEnum setting6 = declaredConstructorEnum.newInstance();
        System.out.println("reflection");
        System.out.println(setting5 == setting6);

        System.out.println("------------------------------");
        // Enum 방식의 추가 설명
        // 이러면 리플렉션 안에서 생성을 막는다.
        // Enum은 리플렉션에서 생성자를 사용할 수 없도록 막아놨다.
        SettingEnum settingEnum = SettingEnum.INSTANCE;

        SettingEnum settingEnum1 = null;

        Constructor<?>[] declaredConstructors = SettingEnum.class.getDeclaredConstructors();
        for (Constructor<?> constructor : declaredConstructors) {
            constructor.setAccessible(true);
            settingEnum1 = (SettingEnum)constructor.newInstance("INSTANCE");

        }
        System.out.println(settingEnum == settingEnum1);
    }

    /**
     * 결론
     * 싱글톤을 안전하게 구현하는 최종 방법 두 가지
     * 1. Enum으로 구현
     * 2. Static inner Class( Holder Class )
     **/

    /**
     * 싱글톤 패턴이 쓰이는 곳
     * 1. 런타임 인스턴스 => 자바 애플리케이션이 실행되고 있는 환경
     * 2. Spring Bean Application Context -> 빈 생성시 싱글톤으로 생성, 인스턴스를 한 개로 관리,
     *      싱글톤 패턴이라고 보기는 힘들지만 싱글톤 스코프로는 사용
     * 3. 다른 디자인 패턴 (빌더, 퍼사드, 추상 팰토리 등) 구현체의 일부로 쓰인다.
     * **/
}
