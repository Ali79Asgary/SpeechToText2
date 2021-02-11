package library.assistant.ui.main;

import javafx.scene.layout.AnchorPane;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.util.Objects;

public class NewRecorder implements Runnable {

    AnchorPane anchorPaneShow;

    static final long RECORD_TIME = 60000;

    int filesCount;

    File wavFile;

    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

    TargetDataLine targetDataLine;

    public NewRecorder() {
    }

    public NewRecorder(AnchorPane anchorPaneShow) {
        this.anchorPaneShow = anchorPaneShow;
    }

    @Override
    public void run() {
        try {
            filesCount = new File("./AudioFiles").listFiles().length;
            wavFile = new File("./AudioFiles/RecordFile"+filesCount+".wav");
            AudioFormat format = getAudioFormat();
            final int bufferByteSize = 2048;
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            if (!AudioSystem.isLineSupported(info)){
                System.out.println("Line not supported!");
                System.exit(0);
            }
            targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
            targetDataLine.open(format);

            byte[] buf = new byte[bufferByteSize];
            float[] samples = new float[bufferByteSize / 2];

            float lastPeak = 0f;

            targetDataLine.start();

            for(int b; (b = targetDataLine.read(buf, 0, buf.length)) > -1;) {

                // convert bytes to samples here
                for (int i = 0, s = 0; i < b; ) {
                    int sample = 0;

                    sample |= buf[i++] & 0xFF; // (reverse these two lines
                    sample |= buf[i++] << 8;   //  if the format is big endian)

                    // normalize to range of +/-1.0f
                    samples[s++] = sample / 32768f;
                }

                float rms = 0f;
                float peak = 0f;
                for (float sample : samples) {

                    float abs = Math.abs(sample);
                    if (abs > peak) {
                        peak = abs;
                    }

                    rms += sample * sample;
                }

                rms = (float) Math.sqrt(rms / samples.length);

                if (lastPeak > peak) {
                    peak = lastPeak * 0.875f;
                }

                lastPeak = peak;

                setMeterOnEDT(rms, peak);
            }

            System.out.println("Start Capturing!");

            AudioInputStream audioInputStream = new AudioInputStream(targetDataLine);

            System.out.println("Start Recording!");

            AudioSystem.write(audioInputStream, fileType, wavFile);

        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private AudioFormat getAudioFormat() {
//        float sampleRate = 16000;
        float sampleRate = 44100f;
//        int sampleSizeInBits = 8;
        int sampleSizeInBits = 16;
//        int channels = 2;
        int channels = 1;
//        boolean signed = true;
        boolean signed = true;
//        boolean bigEndian = true;
        boolean bigEndian = false;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
        return format;
    }

    void setMeterOnEDT(final float rms, final float peak) {
        anchorPaneShow.setPrefWidth(rms);
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                meter.setAmplitude(rms);
//                meter.setPeak(peak);
//            }
//        });
    }
}
