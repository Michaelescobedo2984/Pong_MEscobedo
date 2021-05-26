import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Board extends JPanel implements ActionListener {

    final int BALLD = 20, PADDLEW = 20, PADDLEH = 50, BORDER = 10;
    int ballX = 0, ballY = 0, paddleCX = 0, paddlePY = 0, paddlePX = 0, paddleCY = 0;
    int ballDX = 3, ballDY = 3, paddleCDY = 5, paddlePDY=10;
    int playerScore = 0, computerScore = 0;

    Timer timer;


    public Board(){
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.black);
        timer = new Timer(1000/60, this);
    }

    //INITIALIZATION
    public void init(){
        ballX = getWidth()/2-BALLD/2;
        ballY = getHeight()/2-BALLD/2;

        paddlePX= 0+ BORDER;
        paddlePY = getHeight()/2-PADDLEH/2;

        paddleCX = getWidth() - BORDER - PADDLEW;
        paddleCY = getHeight()/2- PADDLEH/2;

        timer.start();
    }

            public void checkCollisions(){
        Rectangle paddle1 = new Rectangle(paddlePX, paddlePY, PADDLEW, PADDLEH);
        Rectangle paddle2 = new Rectangle(paddleCX, paddleCY, PADDLEW, PADDLEH);
        Rectangle ball = new Rectangle(ballX, ballY, BALLD, BALLD);

        if(ball.intersects(paddle1)  || ball.intersects(paddle2)){
            ballDX *= -1;

        }

            }



    public void move(){

        //Ball Movement
        if(ballX + BALLD >= getWidth() || ballX <= 0){
            ballDX*=-1;
            timer.stop();
        }

        if(ballY + BALLD>= getHeight() || ballY <= 0){
            ballDY*=-1;
            timer.stop();
        }

        if(ballX + BALLD >= getWidth()){
            playerScore += 1;
            init();
            ballDX*=-1;
            timer.stop();
        }

        if(ballX <= 0){
            computerScore+=1;
            init();
            ballDX *=-1;
            timer.stop();
        }

        if(ballY + BALLD >= getHeight() || ballY <=0){
            ballDY *= -1;
        }



        ballX += ballDX;
        ballY += ballDY;

        if(ballX + BALLD/2 >getWidth()/2){
            if(ballY > paddleCY+PADDLEH/2){
                paddleCY-=paddleCDY;
            }
            if(ballY <= paddleCY+PADDLEH/2)
            paddleCY -= paddleCDY;

        }

    }

    public void playerUP(){
        if(paddlePY > 0){
            paddlePY -= paddlePDY;
        }
    }

    public void playerDown(){
        if(paddlePY+PADDLEH < getHeight()){
            paddlePY += paddlePDY;
        }
    }

    public void startPause(){
        if(timer.isRunning()){
            timer.stop();
        }else{
            timer.start();
        }
    }




    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        checkCollisions();
        move();
        repaint();
    }





    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.WHITE);

        g.fillOval(ballX, ballY, BALLD, BALLD);
        g.fillRect(paddlePX, paddlePY, PADDLEW,PADDLEH);
        g.fillRect(paddleCX, paddleCY, PADDLEW, PADDLEH);


        System.out.println("PAINTED");

    }

    private void printSimpleString(String s, int width, int XPos, int YPos, Graphics g2d){
        int stringLen = (int)g2d.getFontMetrics().getStringBounds(s,g2d).getWidth();
        int start = width/2 - stringLen/2;
        g2d.drawString(s, start + XPos, YPos);
    }






}







// PAINT IT
// MOVE IT
// COLLIDE IT
// GAMIFY
// PRETTIFY