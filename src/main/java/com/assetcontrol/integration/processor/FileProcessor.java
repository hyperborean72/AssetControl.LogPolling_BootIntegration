package com.assetcontrol.integration.processor;

import com.assetcontrol.model.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

import java.util.Scanner;

public class FileProcessor {

    int lastLineNumber;
    Scanner scanner;

    @Autowired
    Results results;

    public void process(Message<String> msg) {

        String content = msg.getPayload();

        String line = null;
        int count = 0;
        int e = 0, w = 0, i = 0;

        scanner = new Scanner(content).useDelimiter("[|\\n]");
        while (scanner.hasNextLine()) {
            count++;
            /* продвигать маркер необходимо при любых условиях*/
            line = scanner.nextLine();
            if(count > lastLineNumber) {
                String[] tokens = line.split(" ");
                switch (tokens[2]) {
                    case "ERROR":
                        e++;
                        break;
                    case "WARNING":
                        w++;
                        break;
                    case "INFO":
                        i++;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid message type");
                }
            }
        }

        lastLineNumber = count;
        System.out.println(e + ":" + w + ":" + i);
        System.out.println("lastLineNumber: " + lastLineNumber);

        results.setErrorCount(e);
        results.setWarningCount(w);
        results.setInfoCount(i);
    }
}