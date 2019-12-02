package com.example.demo.demo;

import it.sauronsoftware.jave.*;

import java.io.File;

public class DemoApplication {

    public static void main(String[] args) {
        File source = new File("C:\\Users\\GEEX177\\Downloads\\wKhvEl3TXvaAJ3MVAD3HpoFJrqU223.mp4");
        File target = new File("C:\\Users\\GEEX177\\Downloads\\xxx1.mp4");

        try {
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("libmp3lame");
            audio.setBitRate(new Integer(56000));
            audio.setChannels(new Integer(1));
            audio.setSamplingRate(new Integer(22050));

            VideoAttributes video = new VideoAttributes();
            video.setCodec("mpeg4");
            video.setBitRate(new Integer(800000));
            video.setFrameRate(new Integer(15));

            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("mp4");
            attrs.setAudioAttributes(audio);
            attrs.setVideoAttributes(video);

            Encoder encoder = new Encoder();
            encoder.encode(source, target, attrs);
            System.out.println("压缩完成...");
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }
}
