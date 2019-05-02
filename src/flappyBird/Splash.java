package flappyBird;

import atariCore.LoadingScreen;
import atariCore.Sound;

import static atariCore.Helper.*;
import static atariCore.Helper.clickSound;
import static flappyBird.FlappyHelper.*;

public class Splash extends atariCore.Splash {

    public Splash() {
        super("Flappy Bird", "src/Resources/Atari Core/Fonts/joystix monospace.ttf");

        newGameButton.addActionListener(e -> {
            if (sounds)
                Sound.Play(clickSound, false);
            new SelectPlayer();
        });

        AIButton.addActionListener(e -> {
            running = true;
            AIMode = true;
            startGame = true;
            if (sounds)
                Sound.Play(clickSound, false);
            new FlappyBird("Flappy Bird", "AI-Player");
        });
        LeaderboardsButton.addActionListener(e -> {
            if (sounds)
                Sound.Play(clickSound, false);
            new Leaderboards(pathFile + "Leaderboards.txt");
        });
        settingsButton.addActionListener(e -> {
            if (sounds)
                Sound.Play(clickSound, false);
            new Settings(sounds);
        });

        exitButton.addActionListener(e -> {
                    frame.dispose();
                    if (sounds)
                        Sound.Play(clickSound, false);
                }
        );
    }

    public static void main(String... args) {
        new LoadingScreen();
        FlappyHelper.setImages();
        new Splash();
    }
}

