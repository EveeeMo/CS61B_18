public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;

    };

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    };

    public double calcDistance(Planet p) {
        double dx = xxPos - p.xxPos;
        double dy = yyPos - p.yyPos;
        double dis = Math.sqrt(dx * dx + dy * dy);
        return dis;
    }

    public double calcForceExertedBy(Planet p) {

        double G = 6.67e-11;
        double r = calcDistance(p);
        double F = G * mass * p.mass / (r * r);
        return F;
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - xxPos;
        double Fx = dx * calcForceExertedBy(p) / calcDistance(p);
        return Fx;
    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - yyPos;
        double Fy = dy * calcForceExertedBy(p) / calcDistance(p);
        return Fy;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double FxNet = 0;
        for (int i = 0; i < allPlanets.length; i++) {
            if (equals(allPlanets[i])) {
                continue;
            } else {
                FxNet += calcForceExertedByX(allPlanets[i]);
            }
        }
        return FxNet;

    }
    
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double FyNet = 0;
        for (int i = 0; i < allPlanets.length; i++) {
            if (equals(allPlanets[i])) {
                continue;
            } else {
                FyNet += calcForceExertedByY(allPlanets[i]);
            }
        }
        return FyNet;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx / mass;
        double ay = fy / mass;
        xxVel += ax * dt;
        yyVel += ay * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }



    
}