package com.aifeng;

import java.io.File;

/**
 * Created by pro on 17-3-14.
 */
public class Util {

    public static void mkDir(String path) {
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
    }
}
