package arkanoid.menu;

import arkanoid.NewPlayer;
import arkanoid.ObjectList;
import arkanoid.Sounds;
import arkanoid.arkHelper;

import static atariCore.Helper.frame;
import static atariCore.Helper.panel;

import atariCore.Helper;
import atariCore.LoadingScreen;
import atariCore.Sound;

import java.util.concurrent.TimeUnit;

public class Splash extends atariCore.Splash {

    public Splash() {
        super("Arkanoid", "src/Resources/Fonts/Cowboys.otf");
        arkHelper.setCursorImage(panel, "src/Resources/Images/yellowc2.png");


        for(int i=0 ; i<10 ; i++)
        {
            Sounds.backgroundGameSound[i].stop();
        }

        Sounds.backgroundSplashSound.play();

        newGameButton.addActionListener(e -> {

            new NewPlayer();
        });
    }

    public static void main(String[] args) {

        new Helper();
        new LoadingScreen();
        try {
            TimeUnit.SECONDS.sleep(5);
        }
        catch (Exception e)
        {

        }

        new arkHelper();
        new ObjectList();
        new Sounds();

        new Splash();
    }
}
