package com.gof.gofdesignpattern.singletonpattern;

import java.io.Serializable;


// 직렬화 선언
public class Setting implements Serializable {

    private static Setting instance1;

    // Eager Intialization
    // 이 방법은 스레드 세이프
    // 이 인스턴스는 클래스를 만들 때 동시에 만들어진다.
    // final -> 불변 선언
    // final로 선언하면 대문자로 선언해주는 것이 좋다. -> 일종의 규약
    private static final Setting INSTANCE2 = new Setting();

    // Double Check Locking은 자바 1.5 이상부터 volatile 키워드를 쓴다.
    private static volatile Setting instance3;

    // 권장 방법 중 하나 static inner class 사용하기
    // Holder 사용하기
    private static class SettingHolder{
        private static final Setting INSTANCE4 = new Setting();
    }

    // 생성자를 Private으로 만들면 밖에서 생성 불가
    private Setting() {
    }

    // global access => static
    // 가장 나이브하게 싱글톤 패턴 구현하기

    // 멀티 스레드 환경에서 가장 적합하게 구현하기 -> synchronized 키워드
    // 다만 이 키워드를 사용하면 동기화 처리 때문에 성능 저하가 이루어진다.

    // 이를 방지하려면 이른 초기화가 필요
    // -> Eager Initialization
    public static synchronized Setting getInstance1(){
        // instance가 null이면 새로 생성
        if(instance1 == null){
            return new Setting();
        }
        // 기존에 있으면 그대로 반환
        return instance1;
    }

    // 필드에서 이미 생성자가 돌아서 상관 없다.
    public static Setting getInstance2(){
        return INSTANCE2;
    }

    // Double Checked Locking
    public static  Setting getInstance3(){
        // 더블 체크
        // if문에서 null인지 체크할 떄 synchronized
        if (instance3 == null){
            // 클래스 싱크로나이즈
            // 매번 동기화 매커니즘을 실행하지 않음
            // 멀티 스레드가 빈번한 경우 if문에 여러 스레드가 들어오는데
            // 그 때 거를 수 있다.
            // 또한 적재 적소의 상황의 생성 가능
            synchronized (Setting.class){
                if(instance3 == null){
                    instance3 = new Setting();
                    return instance3;
                }
            }
        }
        return instance3;
    }

    // static inner 클래스 구현 방식
    // 이러면 멀티스레드도 안전
    // Lazy Loading도 가능
    // 복잡하지 않음
    public static Setting getInstance4(){
        return SettingHolder.INSTANCE4;
    }

    // 역직렬화 대응 방안
    protected Object readResolve(){
        return getInstance4();
    }

}
