package pv260.solid.ocp.original.persistanceWritters;

import pv260.solid.ocp.original.Comment;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.*;

public class PersistCSVWritter {

    private final Path path;

    public PersistCSVWritter(Path path) {
        this.path=path;
    }

    public void persistCsv(Comment comment) {
        try (BufferedWriter writer = Files.newBufferedWriter(path,
                UTF_8,
                CREATE,
                APPEND,
                WRITE)) {
            writer.append(formatCsv(comment));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String formatCsv(Comment comment) {
        return comment.getAuthor() + ", " + comment.getEntered() + ", " + comment.getHeadline() + ", "
                + comment.getText() + System.lineSeparator();
    }


}
