package com.gof.gofdesignpattern.singletonpattern;

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

        /**
         * Enum으로 싱글톤 생성해서 리플렉션 방지
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

    }
}
