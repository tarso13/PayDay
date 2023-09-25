package controller;

import java.nio.file.Path;
import java.nio.file.Paths;


public class ProjectPath {

    public static String initBasePath() {
        Path path = Paths.get("");
        return path.toAbsolutePath().toString();
    }
}
