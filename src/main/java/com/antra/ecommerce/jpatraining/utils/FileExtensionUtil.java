package com.antra.ecommerce.jpatraining.utils;

import org.springframework.stereotype.Component;

@Component
public class FileExtensionUtil {

    public static String getExtension(String fileName) {
        if (fileName == null) {
            return "";
        }
        int lastIndexOfDot = fileName.lastIndexOf(".");
        if (lastIndexOfDot > 0) {
            return fileName.substring(lastIndexOfDot + 1);
        } else {
            return "";
        }
    }

}
