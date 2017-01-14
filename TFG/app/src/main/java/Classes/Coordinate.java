package Classes;

import java.io.Serializable;

import Connection.SQLObject;
import tfg.lostandfound.CoordsActivity;

/**
 * Created by Llango on 19/10/2016.
 *
 * Class that contains all the information of a coordinate.
 * Extends SQLObject to get the inherit methods.
 * Implements Serializable in order to be able to get through activities.
 *
 */

public class Coordinate extends SQLObject implements Serializable {


    private Double dblXCoordinate;
    private Double dblYCoordinate;

    /**
     * Constructor with parameters
     *
     * @param pdblXCoordinate
     * @param pdblYCoordinate
     */
    public Coordinate(Double pdblXCoordinate, Double pdblYCoordinate)
    {
        dblXCoordinate = pdblXCoordinate;
        dblYCoordinate = pdblYCoordinate;

    }

    /**
     * Empty constructor
     */
    public Coordinate()
    {

    }

            /* Setters and Getters */

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
