
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;


import java.awt.*;
import java.net.URI;

public class Main {
    public static void main(String[] args) {

        ProcessBuilder com =new ProcessBuilder();
        Configuration configuration = new Configuration();

        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("src/dic.dic");
        configuration.setLanguageModelPath("src/lm.lm");


        try {

            LiveSpeechRecognizer rec = new LiveSpeechRecognizer(configuration);
            Desktop desktop = Desktop.getDesktop();
            rec.startRecognition(true);

            while (rec.getResult() != null) {
                String result = rec.getResult().getHypothesis();

                System.out.println(result);

                switch (result.toLowerCase()) {

                    case "hello":
                        System.out.println("hello");
                        break;
                    case "chrome":
                        com.command("cmd.exe","/c","start chrome");
                        com.start();
                        break;
                    case "google":
                        URI uri_google = new URI("https://www.google.com/");
                        desktop.browse(uri_google);
                        break;
                    case "close":
                        com.command("cmd.exe","/c","TASKKILL /IM chrome.exe");
                        com.start();


                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
