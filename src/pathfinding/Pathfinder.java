package pathfinding;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * 07.10.2017
 *
 * @author SWirries
 */
public class Pathfinder {
    Point start;
    Point end;
    ArrayList<ArrayList<Way>> allPaths = new ArrayList<>();
    ArrayList<Way> path = new ArrayList<>();
    int trys = 10;

    public Pathfinder(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public void findPath(){


        /*
        new
         */

        System.out.println("find path");

        Point current = start;
        ArrayList<Way> possibleDirections = getAllpossibleDirections(current, Way.LEFT);
        for(Way direction : possibleDirections){
            goTo(current, direction, new ArrayList<Way>());
        }
        System.out.println(allPaths.size());
        for( ArrayList<Way> path : allPaths){
            System.out.print(path);
        }

        /*
        new end
         */



//
//        System.out.println("END:"+end);
//        Point current = start;
//        int i = 0;
//        do{
//            /**
//             * Wege zur Auswahl
//             */
//            //TOP
//            Path top = checkWay(current,Way.TOP);
//            //RIGHT
//            Path right = checkWay(current,Way.RIGHT);
//            //BOTTOMRIGHT
//            Path bottomright = checkWay(current,Way.BOTTOMRIGHT);
//            /**
//             * Trifft ein weg?
//             */
//
//            Way takeWay = Way.TOP;
//            if(top.isBest()){
//                takeWay = Way.TOP;
//
//            }else if(right.isBest()){
//                takeWay = Way.RIGHT;
//
//            }else if(bottomright.isBest()){
//                takeWay = Way.BOTTOMRIGHT;
//
//            }else if(top.isPossible()){
//                takeWay = Way.TOP;
//            }else if(right.isPossible()){
//                takeWay = Way.RIGHT;
//            }else if(bottomright.isPossible()){
//                takeWay = Way.BOTTOMRIGHT;
//            }
//
//            switch (takeWay){
//                case TOP:
//                    current.y = current.y +1;
//                    System.out.println("TOP");
//                    break;
//                case RIGHT:
//                    current.x = current.x +1;
//                    System.out.println("RIGHT");
//                    break;
//                case BOTTOMRIGHT:
//                    current.x = current.x +1;
//                    current.y = current.y -1;
//                    System.out.println("BOTTOMRIGHT");
//                    break;
//            }
//
//
//            System.out.println("Try:"+i+" Point:"+current.toString().substring(14));
//            i++;
//
//            if(current.x == end.x && current.y >= end.y/2) break;
//        }while ((current.x != end.x || current.y != end.y) && i <= trys);
//        System.out.println("END LOOP:"+current.toString().substring(14));
////        }while ((false) && i <= trys); || i <= trys

    }

    private void goTo(Point currentPoint, Way direction, ArrayList<Way> path){

        currentPoint = goDirection(currentPoint, direction);
        path.add(direction);

        if(currentPoint.getX() == end.getX() && currentPoint.getY() == end.getY()){
            allPaths.add(path);
        }else if(currentPoint.getX() <= end.getX() && currentPoint.getY() <= end.getX()){
            ArrayList<Way> possibleDirections = getAllpossibleDirections(currentPoint, direction);
            for(Way dir : possibleDirections){
                ArrayList<Way> clone = (ArrayList<Way>) path.clone();
                goTo(currentPoint, dir, clone);
            }
            path = null;
        }

    }

    private Point goDirection(Point current, Way direction){

        switch (direction){
            case TOP:
                current.y = current.y +1;
//                System.out.println("TOP");
                break;
            case RIGHT:
                current.x = current.x +1;
//                System.out.println("RIGHT");
                break;
            case BOTTOMRIGHT:
                current.x = current.x +1;
                current.y = current.y -1;
//                System.out.println("BOTTOMRIGHT");
                break;
            case TOPLEFT:
                current.x = current.x -1;
                current.y = current.y +1;
                break;
            case TOPRIGHT:
                current.x = current.x +1;
                current.y = current.y +1;
                break;
        }
        return current;

    }



    public ArrayList<Way> getAllpossibleDirections(Point p, Way direction){

        ArrayList<Way> out = new ArrayList<>();

        for(Way way : Way.values()){
            if(checkWay(p, way)){
                if(!(direction == Way.BOTTOMRIGHT && way == Way.TOPLEFT) && !(direction == Way.TOPLEFT && way == Way.BOTTOMRIGHT))
                    out.add(way);
            }
        }
        return out;

    }

//    private Path checkWay(Point current, Way way){
//        Path possible = new Path(false,false);
//        switch (way){
//            case TOP:
//                possible = checkTopWay(current);
//                break;
//            case LEFT:
//                //
//                break;
//            case RIGHT:
//                possible = checkRightWay(current);
//                break;
//            case BOTTOM:
//                //possible = checkBottomWay(current);
//                break;
//            case TOPLEFT:
//                //possible = checkTopLeftWay(current);
//                break;
//            case TOPRIGHT:
//                //possible = checkTopRightWay(current);
//                break;
//            case BOTTOMLEFT:
//                //
//                break;
//            case BOTTOMRIGHT:
//                possible = checkBottomRightWay(current);
//                break;
//        }
//
//        return possible;
//    }

    private boolean checkWay(Point current, Way way){
        boolean possible = false;
        switch (way){
            case TOP:
                possible = checkTopWay(current);
                break;
            case LEFT:
                //
                break;
            case RIGHT:
                possible = checkRightWay(current);
                break;
            case BOTTOM:
                //possible = checkBottomWay(current);
                break;
            case TOPLEFT:
                possible = checkTopLeftWay(current);
                break;
            case TOPRIGHT:
                possible = checkTopRightWay(current);
                break;
            case BOTTOMLEFT:
                //
                break;
            case BOTTOMRIGHT:
                possible = checkBottomRightWay(current);
                break;
        }

        return possible;
    }

    private boolean checkTopWay(Point current){
        boolean possible = false;
        //Nach oben
        Point top = new Point(current.x,current.y+1);
        if(top.y <= end.x && top.x <= end.x)
            possible = true;

        if(current.x > current.y){
            possible = false;
        }

        if(current.y >= (-current.x) + end.x)
            possible = false;

        return possible;
    }

    private boolean checkTopRightWay(Point current){
        boolean possible = false;
        //Nach oben rechts
        Point top = new Point(current.x+1,current.y+1);
        if(top.y <= end.x && top.x <= end.x)
            possible = true;

        if(current.x > current.y){
            possible = false;
        }
        if(current.x == current.y){
            possible = false;
        }

        if(current.y >= (-current.x) + end.x)
            possible = false;

        return possible;
    }

    private boolean checkRightWay(Point current){
        boolean possible = false;
        //Nach oben rechts
        Point top = new Point(current.x+1,current.y);
        if(top.y <= end.x || top.x <= end.x)
            possible = true;

        if(current.x < current.y){
            possible = false;
        }

        if(current.y >= (-current.x) + end.x)
            possible = false;

        return possible;
    }

    private boolean checkBottomRightWay(Point current){
        boolean possible = false;
        //Nach oben rechts
        Point top = new Point(current.x+1,current.y-1);
        if((top.y <= end.x && top.x <= end.x) && (top.y >= 0 && top.x >= 0))
            possible = true;


        return possible;
    }

    private boolean checkTopLeftWay(Point current){
        boolean possible = false;
        //Nach oben rechts
        Point top = new Point(current.x-1,current.y+1);
        if((top.y <= end.x || top.x <= end.x) && (top.y >= 0 && top.x >= 0))
            possible = true;

        if(current.y >= (-current.x) + end.x)
            possible = false;

        return possible;
    }

    private boolean checkBottomWay(Point current){
        return false;
    }

    private boolean checkMeet(Point next){
        if(next.x == end.x && next.y == end.y)
            return true;

        return false;
    }

    /**
     * Prüft alle Richtungen, wenn Richtung möglich, dann in diese Richtungen gehen und Weg merken.
     * Vom neuen Punkt aus wieder alle Richtungen prüfen (rekursiv).
     * Wenn Richtung/Punkt auf dem Ende liegt, dann gelaufenen Weg merken.
     */
}
