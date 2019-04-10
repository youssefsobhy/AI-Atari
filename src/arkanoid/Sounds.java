package arkanoid;

import atariCore.Sound;

import javax.sound.sampled.Clip;

public class Sounds extends Sound {

    public static Clip lazerSound;
    public static Clip hitSound;
    public static Clip backgroundSplashSound;
    public static Clip BackgroundGameSound;

    public Sounds() {
        lazerSound = setClip("Resources/Sounds/lazer.wav");
        hitSound = setClip("Resources/Sounds/hit.wav");
        backgroundSplashSound = setClip("Resources/Sounds/background.wav");
        BackgroundGameSound = setClip("Resources/Sounds/intro_sound.wav");
    }
}