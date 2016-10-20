package Classes;

import java.io.Serializable;

import tfg.lostandfound.CoordsActivity;

/**
 * Created by Llango on 19/10/2016.
 */

public class Coordinate implements Serializable {

    private Double dblXCoordinate;
    private Double dblYCoordinate;

    public Coordinate(Double pdblXCoordinate, Double pdblYCoordinate)
    {
        dblXCoordinate = pdblXCoordinate;
        dblYCoordinate = pdblYCoordinate;

    }
}
