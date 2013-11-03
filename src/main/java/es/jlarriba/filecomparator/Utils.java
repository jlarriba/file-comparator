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

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

public class Utils {
    
    public static String createChecksum(String filename) {
        String retorno = "";
        try {
            RandomAccessFile aFile = new RandomAccessFile(filename, "r");
            FileChannel inChannel = aFile.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            MessageDigest digester = MessageDigest.getInstance("MD5");
            while (inChannel.read(buffer) > 0) {
                buffer.flip();
                for (int i = 0; i < buffer.limit(); i++) {
                    digester.update(buffer.get());
                    //System.out.print((char) buffer.get());
                }
                buffer.clear(); // do something with the data and clear/compact it.
            }
            byte[] b = digester.digest();
            for (int i = 0; i < b.length; i++) {
                retorno += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
            }
            inChannel.close();
            aFile.close();
        } catch (Exception exc) {
            System.out.println("File " + filename + " no pudo ser leida");
        }
        return retorno;

    }
    
}
