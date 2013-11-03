/**
 * Copyright 2013 Juan Larriba

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package es.jlarriba.filecomparator;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class CompareFiles extends SimpleFileVisitor<Path> {

    private final String directory1;
    private final String directory2;
    
    public CompareFiles(String directory1, String directory2) {
        this.directory1 = directory1;
        this.directory2 = directory2;
    }
    
    // Print information about
    // each type of file.
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
        String file1 = file.toString();
        //if (!file1.contains(".java")) {
            String file2 = file.toString().replace(directory1, directory2);
            String csFile1 = Utils.createChecksum(file1);
            String csFile2 = Utils.createChecksum(file2);
            if (!csFile1.equals(csFile2)) {
                System.out.println("<h1>DIFF: " + file1 + " - " + file2 + "</h1><br/>");
            } else {
                //System.out.println(file1 + " is EQUALS to " + file2);
            }
        return CONTINUE;
    }

    // Print each directory visited.
    @Override
    public FileVisitResult postVisitDirectory(Path dir,
                                          IOException exc) {
        return CONTINUE;
    }

    // If there is some error accessing
    // the file, let the user know.
    // If you don't override this method
    // and an error occurs, an IOException 
    // is thrown.
    @Override
    public FileVisitResult visitFileFailed(Path file,
                                       IOException exc) {
        System.err.println(exc);
        return CONTINUE;
    }
}
