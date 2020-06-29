package com.exort.util;

import java.io.IOException;

/**
 * This class provides with some static methods to assist in MySQL operations
 * @author NeilKleistGao
 * @version 1.0.0
 */
public class MySQLUtil {
    private static String name = "exort";

    /**
     * This method exports MySQL database into a SQL file
     * @param path The file path of SQL file
     */
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

    /**
     * This method imports database from a SQL file
     * @param path The file path of SQL file
     */
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
