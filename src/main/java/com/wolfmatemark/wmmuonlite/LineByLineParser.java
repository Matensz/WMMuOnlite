package com.wolfmatemark.wmmuonlite;

import com.wolfmatemark.wmmuonlite.exception.GameOverException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;

import static java.util.Objects.requireNonNull;

public class LineByLineParser {
    private final CommandLineProcessor commandLineProcessor;

    public LineByLineParser(CommandLineProcessor commandLineProcessor) {
        this.commandLineProcessor = requireNonNull(commandLineProcessor);
    }

    public void start(PrintStream printStream, InputStream inputStream) throws GameOverException {
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            Optional<String> parsableLine;
            try {
                parsableLine = commandLineProcessor.process(line);
            } catch (GameOverException ex){
                throw new GameOverException();
            }
            parsableLine.ifPresent(printStream::println);
        }
    }
}
