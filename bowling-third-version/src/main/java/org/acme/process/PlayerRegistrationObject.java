package org.acme.process;

import org.acme.model.Player;
import org.acme.model.PlayerFrames;

import java.util.ArrayList;
import java.util.List;

public class PlayerRegistrationObject {

    public List<PlayerFrames> registerPlayer() {
        List<PlayerFrames> playerFramesList = new ArrayList<>();
        playerFramesList.add(new PlayerFrames(
                new Player("Mr.", "Spook", "sharp ears")
        ));
        playerFramesList.add(new PlayerFrames(
                new Player("Mickey", "Mouse", "disney hero")
        ));
        return playerFramesList;
    }

}
