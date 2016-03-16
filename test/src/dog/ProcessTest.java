package dog;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ProcessTest {

    public static void main(String[] args) {
        new ProcessTest().start();
    }

    private void start() {
        startWatchDog();
        startProcess();
    }

    private boolean abortCondition = false;
    private int watchDogTSleepTime = 3000; //3 sek
    private void startWatchDog() {
        Runnable r = new Runnable() {

            public void run() {
                while(!abortCondition){
                    try {
                        Thread.sleep(watchDogTSleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //check the file touch
                    boolean ok = checkFileTouch();

                    try {
                        //send signals to the process
                        outStream.write("signal".getBytes() );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //if you want, you might try to kill the process
                    process.destroy();
                }
            }
        };

        Thread watchDog = new Thread(r);
        watchDog.start();
        //watchDog.setDaemon(true); //maybe
    }

    private boolean checkFileTouch(){
        //...
        return false;
    }

    private InputStream inStream;
    private OutputStream outStream;
    private Process process;
    private void startProcess() {       
        String[] cmd = new String[]{"foo.exe", "para1", "param2"};
        try {
            //create and start the process
            process = Runtime.getRuntime().exec(cmd);
            inStream = process.getInputStream();
            outStream = process.getOutputStream();          
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
