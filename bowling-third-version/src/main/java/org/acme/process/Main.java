package org.acme.process;

public class Main {

    public static void main(String[] args) {
        PlayerRegistrationObject playerRegistrator = new PlayerRegistrationObject();
        BowlingProcess bowlingProcess =
                new BowlingProcess(playerRegistrator.registerPlayer());
        bowlingProcess.processBowling();
    }

}
