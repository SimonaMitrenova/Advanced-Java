package com.company.Network;

import com.company.IO.OutputWriter;
import com.company.StaticData.ExceptionMessages;
import com.company.StaticData.SessionData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DowloadManager {
    public static void download(String fileUrl) {

        URL url = null;
        ReadableByteChannel channel = null;
        FileOutputStream writer = null;

        try{
            OutputWriter.writeMessageOnNewLine("Started downloading...");
            url = new URL(fileUrl);
            channel = Channels.newChannel(url.openStream());
            String fileName = extractNameOfFile(url.toString());
            File file = new File(SessionData.currentPath + "/" + fileName);
            writer = new FileOutputStream(file);
            writer.getChannel().transferFrom(channel, 0 , Long.MAX_VALUE);
            OutputWriter.writeMessageOnNewLine("Download complete.");

        } catch (MalformedURLException e) {
            OutputWriter.displayException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if (channel != null){
                    channel.close();
                }
                if (writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                OutputWriter.displayException(e.getMessage());
            }
        }
    }

    public static void downloadOnNewThread(String fileUrl){
        Thread thread = new Thread(() -> download(fileUrl));
        thread.start();
    }

    private static String extractNameOfFile(String fileUrl) throws MalformedURLException {
        int indexOfLastSlash = fileUrl.lastIndexOf('/');
        if (indexOfLastSlash == -1){
            throw new MalformedURLException(ExceptionMessages.INVALID_PATH);
        }

        return fileUrl.substring(indexOfLastSlash + 1);
    }
}
