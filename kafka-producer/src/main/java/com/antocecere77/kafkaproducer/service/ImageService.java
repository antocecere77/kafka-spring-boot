package com.antocecere77.kafkaproducer.service;

import com.antocecere77.kafkaproducer.entity.Image;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class ImageService {

    private static int counter = 0;

    public Image generateImage(String type) {
        counter++;
        var name = "Image " + counter;
        var size = ThreadLocalRandom.current().nextLong(100, 10_000);

        return new Image(name, size, type);
    }
}
