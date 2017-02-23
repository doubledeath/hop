package com.github.doubledeath.hop.api3.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

/**
 * Created by doubledeath on 2/22/17.
 */
public class ResourceUtil {

    public static File getResourceFileByName(String fileName) throws FileNotFoundException {
        URL resource = ClassLoader.getSystemClassLoader().getResource(fileName);

        if (resource == null) {
            throw new FileNotFoundException(fileName);
        }

        return new File(resource.getFile());
    }

}
