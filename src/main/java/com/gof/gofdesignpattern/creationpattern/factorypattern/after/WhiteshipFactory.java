package com.gof.gofdesignpattern.creationpattern.factorypattern.after;

public class WhiteshipFactory extends DefaultShipFactory {

    @Override
    public Ship createShip() {
        return new Whiteship();
    }
}
