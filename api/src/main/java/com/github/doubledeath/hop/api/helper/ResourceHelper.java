package com.github.doubledeath.hop.api.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

/**
 * Created by doubledeath on 2/16/17.
 */
public final class ResourceHelper {

    public static File getResourceFileByName(String fileName) throws FileNotFoundException {
        URL resource = ClassLoader.getSystemClassLoader().getResource(fileName);

        if (resource == null) {
            throw new FileNotFoundException(fileName);
        }

        return new File(resource.getFile());
    }

}
