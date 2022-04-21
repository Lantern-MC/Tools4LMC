package net.lanternmc.r1_8.Particle;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class ParticleLine {
    public static void drawParticleLine(Location paramLocation1, Location paramLocation2, ParticleEffects paramParticleEffects) {
        drawParticleLine(paramLocation1, paramLocation2, paramParticleEffects, (int)(paramLocation1.distance(paramLocation2) * 8.0D));
    }
    
    public static void drawParticleLine(Location paramLocation1, Location paramLocation2, ParticleEffects paramParticleEffects, int paramInt) {
        Location location1 = paramLocation1.clone();
        Location location2 = paramLocation2.clone();
        double d = paramLocation1.distanceSquared(paramLocation2) * paramInt;
        Vector vector1 = location2.toVector().subtract(location1.toVector());
        float f1 = (float)vector1.length();
        vector1.normalize();
        float f2 = f1 / (float)d;
        Vector vector2 = vector1.multiply(f2);
        Location location3 = location1.clone().subtract(vector2);
        byte b1 = 0;
        for (byte b2 = 0; b2 < d; b2++) {
            if (b1 >= d)
                b1 = 0; 
            b1++;
            location3.add(vector2);
            paramParticleEffects.display(0.0F, 0.0F, 0.0F, 0.0F, 1, location3, 50.0D);
        } 
    }
}
