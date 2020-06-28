package com.exort.util;

import com.exort.dao.SettingsDAO;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GitUtil {
    private static final String filename = "./git_data/exort.sql";
    private static File path = new File("./git_data");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss");

    public static void checkUpdate() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:mysql.xml");
        SettingsDAO dao = (SettingsDAO)context.getBean("settingsDao");
        File path = GitUtil.getPath();

        String repo = dao.find("git").getValue();
        if (!repo.equals("")) {
            if (path.exists() && path.isDirectory()) {
                GitUtil.pull();
            }
            else {
                GitUtil.cloneFrom(repo);
            }

            if (new File(filename).exists()) {
                MySQLUtil.importSQL(filename);
            }
        }
    }

    public static CredentialsProvider createCredentialsProvider(String username, String password) {
        return new UsernamePasswordCredentialsProvider(username, password);
    }

    public static void cloneFrom(String repo) {
        try {
            Git.cloneRepository().setURI(repo).setDirectory(path).call();
        }
        catch (GitAPIException e) {
            e.printStackTrace();
        }

    }

    public static void pull() {
        try {
            Git git = Git.open(path);
            git.pull().setRemoteBranchName("master").call();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void push(CredentialsProvider provider) {
        try {
            Git git = Git.open(path);
            git.add().addFilepattern(".").call();
            git.commit().setMessage("update " + dateFormat.format(new Date())).call();
            git.push().setCredentialsProvider(provider).setRemote("origin").setRefSpecs(new RefSpec(git.getRepository().getBranch())).call();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File getPath() {
        return path;
    }

    public static String getFilename() {
        return filename;
    }
}
