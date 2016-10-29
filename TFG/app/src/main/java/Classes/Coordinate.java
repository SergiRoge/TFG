package Classes;

import java.io.Serializable;

import Connection.SQLObject;
import tfg.lostandfound.CoordsActivity;

/**
 * Created by Llango on 19/10/2016.
 */

public class Coordinate extends SQLObject implements Serializable {


    private Double dblXCoordinate;
    private Double dblYCoordinate;

    public Coordinate(Double pdblXCoordinate, Double pdblYCoordinate)
    {
        dblXCoordinate = pdblXCoordinate;
        dblYCoordinate = pdblYCoordinate;

    }
    public Coordinate()
    {

    }
    public Double getDblXCoordinate()
    {
        return dblXCoordinate;
    }

    public void setDblXCoordinate(Double dblXCoordinate)
    {
        this.dblXCoordinate = dblXCoordinate;
    }

    public Double getDblYCoordinate()
    {
        return dblYCoordinate;
    }

    public void setDblYCoordinate(Double dblYCoordinate)
    {
        this.dblYCoordinate = dblYCoordinate;
    }

}
