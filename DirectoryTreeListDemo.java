package com.konor.HomeWorkJavaUtilConcurrent;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class DirectoryTreeListDemo {
    public static void main(String[] args) {
       String dirName = "src";
        System.out.println("File tree from " + dirName + ": \n");

        try {
            Files.walkFileTree(Paths.get(dirName),new OurFileVisitor());
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }
}

class OurFileVisitor extends SimpleFileVisitor<Path>{
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println(file);
        return FileVisitResult.CONTINUE;
    }
}

