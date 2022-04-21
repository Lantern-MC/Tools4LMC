package net.lanternmc.AllUtils.MineCraft;

import org.bukkit.util.EulerAngle;

public class AngleUtils {
    public static EulerAngle getAngle(double paramDouble1, double paramDouble2, double paramDouble3) {
        paramDouble1 = (paramDouble1 + 180.0D) % 360.0D - 180.0D;
        paramDouble2 = (paramDouble2 + 180.0D) % 360.0D - 180.0D;
        paramDouble3 = (paramDouble3 + 180.0D) % 360.0D - 180.0D;
        paramDouble1 /= 57.295D;
        paramDouble2 /= 57.295D;
        paramDouble3 /= 57.295D;
        return new EulerAngle(paramDouble1, paramDouble2, paramDouble3);
    }
    
    public static DegreeAngle EulertoDegree(EulerAngle paramEulerAngle) {
        return new DegreeAngle(paramEulerAngle);
    }
    
    public static class DegreeAngle {
        private double x;
        
        private double y;
        
        private double z;
        
        public DegreeAngle(double param1Double1, double param1Double2, double param1Double3) {
            this.x = param1Double1;
            this.y = param1Double2;
            this.z = param1Double3;
        }
        
        public DegreeAngle(EulerAngle param1EulerAngle) {
            this.x = param1EulerAngle.getX() * 57.295D;
            this.y = param1EulerAngle.getY() * 57.295D;
            this.z = param1EulerAngle.getZ() * 57.295D;
        }
        
        public double getX() {
            return this.x;
        }
        
        public double getY() {
            return this.y;
        }
        
        public double getZ() {
            return this.z;
        }
        
        public void setX(double param1Double) {
            this.x = param1Double;
        }
        
        public void setY(double param1Double) {
            this.y = param1Double;
        }
        
        public void setZ(double param1Double) {
            this.z = param1Double;
        }
        
        public EulerAngle toEuler() {
            return AngleUtils.getAngle(this.x, this.y, this.z);
        }
    }
}