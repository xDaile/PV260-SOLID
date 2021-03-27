package pv260.solid.ocp.original;

import java.nio.file.Path;

import pv260.solid.ocp.original.persistanceWritters.PersistCSVWritter;
import pv260.solid.ocp.original.persistanceWritters.PersistJSONWritter;
import pv260.solid.ocp.original.persistanceWritters.PersistXMLWritter;

public class Persistence {

    private PersistenceType type;

    private Path path;



    /**
     * @param type how the records will be stored
     * @param path this is the file records will be persisted to
     *
     */
    public Persistence(PersistenceType type,
                       Path path) {
        this.type = type;
        this.path = path;
    }

    public void persist(Comment comment) throws Exception {
        switch (type) {
            case CSV :
                PersistCSVWritter persistCSVWritter=new PersistCSVWritter(path);
                persistCSVWritter.persistCsv(comment);
                break;
            case XML :
                PersistXMLWritter persistXMLWritter=new PersistXMLWritter(path);
                persistXMLWritter.persistXML(comment);
                break;
            case JSON :
                PersistJSONWritter persistJSONWritter=new PersistJSONWritter(path);
                persistJSONWritter.persistJSON(comment);
                //persistJSON(comment);
                break;
            default :
                throw new IllegalStateException("Unsupported persistence method: " + comment);
        }
    }





}
