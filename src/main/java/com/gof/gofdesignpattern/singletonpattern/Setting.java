package com.gof.gofdesignpattern.singletonpattern;
public class Setting {

    private static Setting instance;

    // 생성자를 Private으로 만들면 밖에서 생성 불가
    private Setting() {

    }
    // global access => static
    public static Setting getInstance(){
        // instance가 null이면 새로 생성
        if(instance == null){
            return new Setting();
        }
        // 기존에 있으면 그대로 반환
        return instance;
    }
}
