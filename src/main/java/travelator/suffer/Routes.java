package travelator.suffer;

import java.util.List;
import java.util.Vector;

public class Routes {

    public static Location getDepartsFrom(List<Journey> route) {
        return route.get(0).getDepartsFrom();
    }

}
