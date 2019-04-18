package arkanoid.board;

import arkanoid.Arkanoid;
import arkanoid.arkHelper;
import atariCore.BaseObject;
import atariCore.Sound;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static atariCore.Helper.AIMode;

public class Player extends BaseObject {

    private int score;
    private String name;
    private int lives;
    public ArrayList<Paddle> paddle;
    public boolean start;
    private JPanel panel;
    private Arkanoid arkanoid;
    private int level;

    public Player(String Name, int lives, Paddle paddle , JPanel panel , Arkanoid arkanoid) {
        super(10 , 10 , null);
        this.name = Name;
        this.lives = lives;
        this.paddle = new ArrayList<>();
        this.paddle.add(paddle);
        this.panel = panel;
        this.arkanoid = arkanoid;
        this.level = 1;

        score = 0;
        start = true;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        if(level%10==1)
        {
            Sound.Stop(arkHelper.backgroundGameSound[(level-1)/10]);
        }
        arkanoid.initializeLevels(level);
    }

    public boolean lostBall() {
        lives--;

        if(lives < 0) {

            die();
        }

        return lives >= 0;
    }

    public void die()
    {
        arkHelper.arkfile.addNewScoreToLeadboard(arkHelper.pathLeaderboards,name,score,level);

        if(!AIMode) {
            arkHelper.running = false;
            Sound.Stop(arkHelper.backgroundGameSound[(level-1)/10]);
            Sound.Play(arkHelper.lossSound,true);
            try {
                TimeUnit.SECONDS.sleep(10);
            }catch (Exception e)
            {

            }
            new arkanoid.menu.Splash();
        } else {
            arkanoid.initializeLevels(1);
        }
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void increaseScore(int add) {
        score += add;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void tick() {
        if(arkHelper.backgroundGameSound[(level-1)/10].isStopped())
        {
            Sound.Play(arkHelper.backgroundGameSound[(level-1)/10],false);
        }
    }

    public void render(Graphics g) {

        g.setFont(arkHelper.font);
        g.setColor(Color.ORANGE);
        g.drawString(name, 10, 30);
        g.drawString(Integer.toString(score), 10, 110);
        g.drawString("Level"+level,10,70);
        drawLives(g);
    }

    public void drawLives(Graphics g) {
        int numOfLives = lives;
        int initialHeight = 120;

        for (int i = 0; numOfLives > 0; i++) {
            int initialWidth = 10;
            for (int j = 0; j < 3 && numOfLives > 0; j++) {
                g.drawImage(arkHelper.life, initialWidth, initialHeight, null);
                initialWidth += arkHelper.life.getWidth(null) + 2;
                numOfLives--;
            }

            initialHeight += arkHelper.life.getHeight(null) + 2;
        }
    }
}
