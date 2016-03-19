package com.lukewaugh.droneguide;

/**
 * Created by Luke on 19/03/16.
 */
public class MathClass {

        enum Compass {
            NE,
            NW,
            SE,
            SW
        }

        public double measureDistance(double lat1, double lat2, double lon1, double lon2) {
            double radius = 6378.137; // Radius of the earth
            // Each distance is a segment of a sphere, so more Pi
            double dLat = (lat2 - lat1) * Math.PI / 180;
            double dLon = (lon2 - lon1) * Math.PI / 180;
            // This is my swift implementation of the havesine formula
            // Jonas B got here first!
            double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                    Math.cos(lat1 * Math.PI / 180)      *
                            Math.cos(lat2 * Math.PI / 180)      *
                            Math.sin(dLon/2) * Math.sin(dLon/2);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
            // And now, convert to KM
            double d = radius * c;

            return d;

        }

        public double getBearing(double startY, double startX, double endX, double endY) {

            //  To get the angle one needs to know the hypotnuse, (distance)
            //  Also the length of each side (verticalDistance and horizontalDistance)
            double verticalDistance;
            double horizontalDistance;

            //  Last 2 arguments are the co ords of a point with the same latitude as starting location
            horizontalDistance = measureDistance(startY, startX, endY,  endX);
            //  Last 2 arguments are the co ords of a point with the same longitude as starting location
            verticalDistance  =  measureDistance(startY, startX, endY,  startX);

            System.out.print("Horizontal distance: " + horizontalDistance);
            System.out.print("Vertical distance: " + verticalDistance);


            double distance  = measureDistance(startY, startX, endY, endX);
            double radians   = Math.asin(verticalDistance / distance);
            Compass direction;


            //  If you are going South,
            if (startY > endY) {
                if (startX > endX) {
                    direction = Compass.SW;
                } else {
                    direction = Compass.SE;
                }
            }
            //  If you are going North,
            else {

                if (startX > endX) {
                    direction = Compass.NW;

                } else {
                    direction = Compass.NE;
                }
            }


            //  The trig functions in Swift return radians by default
            //  So we gotta multiply by 180 over pi
            //  Alternative is to multiply by 57.295 but pi is more precise
            double angle = radians * (180 / Math.PI);
            System.out.print(angle);

            double bearing = 0.0;
            //  And now for the bearing which is 0 degrees when facing North,
            //  Our angle is not...

            switch (direction) {
                case NE:
                    bearing = 90 - angle;
                    break;
                case NW:
                    bearing = 270 + angle;
                    break;
                case SE:
                    bearing = 90 + angle;
                    break;
                case SW:
                    bearing = 180 + (90 - angle);
                    break;
            }

            return bearing;

        }
}
