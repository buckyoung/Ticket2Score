/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BuckYoung
 */
public class ConvertPoints {

    public static int fromTrainsToPoints(int numberOfTrains) {
        int result;
        switch (numberOfTrains) {
            case 1:
                result = 1;
                break;
            case 2:
                result = 2;
                break;
            case 3:
                result = 4;
                break;
            case 4:
                result = 7;
                break;
            case 5:
                result = 10;
                break;
            case 6:
                result = 15;
                break;
            default:
                result = -1;

        }
        return result;
    }

    public static int fromPointsToTrains(int numberOfPoints) {
        int result;
        switch (numberOfPoints) {
            case 1:
                result = 1;
                break;
            case 2:
                result = 2;
                break;
            case 4:
                result = 3;
                break;
            case 7:
                result = 4;
                break;
            case 10:
                result = 5;
                break;
            case 15:
                result = 6;
                break;
            default:
                result = -1;

        }
        return result;
    }
}
