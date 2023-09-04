package com.gof.gofdesignpattern.creationpattern.factorypattern.after;

public class BlackshipFactory extends DefaultShipFactory {
    @Override
    public Ship createShip() {
        return new Blackship();
    }
}
