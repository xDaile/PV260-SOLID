package pv260.solid.ocp.original.persistanceWritters;

import pv260.solid.ocp.original.Comment;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.*;

public class PersistJSONWritter {
    private final Path path;

    public PersistJSONWritter(Path path) {
        this.path=path;
    }
    public void persistJSON(Comment comment) throws Exception {

        boolean jsonFileExists=false;
        List<String> existingJSON=new ArrayList<>();

        if(Files.exists(path)){
            try{
                existingJSON=loadExistingJSON(path);
                Files.delete(path);
                Files.createFile(path);
                jsonFileExists=true;
            }
            catch(ArrayIndexOutOfBoundsException e){
                jsonFileExists=false;
            }
        }
        try{
            writeJSON(comment,jsonFileExists,existingJSON);
        }
        catch(Exception e){
            throw new Exception(e);
        }

    }

    private void writeJSON(Comment comment, boolean jsonFileExists, List<String> existingJSON) throws Exception {
        try (BufferedWriter writer = Files.newBufferedWriter(path,
                UTF_8,
                CREATE,
                APPEND,
                WRITE)) {
            writer.append(formatJSON(comment,jsonFileExists,existingJSON));
        } catch (IOException e) {
            throw new Exception(e);
        }

    }

    private List<String> loadExistingJSON(Path path) throws Exception {
        List<String> existingJSON=new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(String.valueOf(path)))) {
            existingJSON = lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Input output exception");
        }
        try{
            existingJSON.remove(existingJSON.size()-1);
            existingJSON.remove(existingJSON.size()-1);
        }
        catch (Exception e){
            throw new ArrayIndexOutOfBoundsException();
        }
        return existingJSON;
    }

    private String formatJSON(Comment comment,boolean jsonFileExists,List< String> existingJSON) {

        String JSONEnding=""
                + "]";
        String JSONOpening="[\n"
                + "\t{\n";

        if (jsonFileExists) {
            JSONOpening="";
            for (String existingJSONLine : existingJSON){
                JSONOpening=JSONOpening+ existingJSONLine +"\n";
            }
            JSONOpening=JSONOpening
                    +"\t},\n"
                    +"\t{\n";
        }
        return JSONOpening
                + "\t\t\"Comment\": {"
                + System.lineSeparator()
                + "\t\t\t\"Author\": \""
                + comment.getAuthor()
                + "\",\n"
                + "\t\t\t\"Entered\": \""
                + comment.getEntered()
                + "\",\n"
                + "\t\t\t\"HeadLine\": \""
                + comment.getHeadline()
                + "\",\n"
                + "\t\t\t\"Text\": \""
                + comment.getText()
                + "\"\n"
                + "\t\t}\n"
                + "\t}\n"
                +JSONEnding;

    }


}
