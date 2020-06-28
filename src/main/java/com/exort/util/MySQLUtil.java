package com.exort.util;

import java.io.IOException;

public class MySQLUtil {
    private static String name = "exort";

    public static void exportSQL(String path) {
        String[] cmd = new String[]{"/bin/sh", "-c", "/usr/bin/mysqldump -h localhost -u" + name + " -p" + name + " " + name + " > " + path};
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ps = runtime.exec(cmd);
            ps.waitFor();
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void importSQL(String path) {
        String[] cmd = new String[]{"/bin/sh", "-c", "/usr/bin/mysql -u" + name + " -p" + name + " " + name + " < " + path};
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ps = runtime.exec(cmd);
            ps.waitFor();
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
