package com.leo.tank;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Leo
 * @ClassName ImageTest
 * @DATE 2020/12/24 3:50 下午
 * @Description
 */
public class ImageTest {

    @Test
    void test() {

        BufferedImage image = null;
        try {
//            image = ImageIO.read(new File("/Users/Han/working_space/java_intellij/learning_project/tank/src/main/resources/images/0.gif"));
//            assertNotNull(image);
            image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/1.gif"));
            assertNotNull(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
