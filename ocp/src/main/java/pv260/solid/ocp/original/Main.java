package pv260.solid.ocp.original;

import java.nio.file.Paths;
import java.util.Date;
import static pv260.solid.ocp.original.PersistenceType.CSV;
import static pv260.solid.ocp.original.PersistenceType.XML;
import static pv260.solid.ocp.original.PersistenceType.JSON;

public class Main {

    public static void main(String[] args) {
        Comment comment = new Comment("My comment",
                "This is interesting...",
                new Date(),
                "Pepa Zdepa");
        try{
            new Persistence(XML, Paths.get("comments.xml")).persist(comment);
            new Persistence(CSV, Paths.get("comments.csv")).persist(comment);
            new Persistence(JSON, Paths.get("comments.JSON")).persist(comment);
        }
        catch (Exception e){
            return ;
        }
    }
}
