package net.minecraft.server;

public class PathfinderGoalRestrictOpenDoor extends PathfinderGoal {

    private EntityCreature a;
    private VillageDoor b;

    public PathfinderGoalRestrictOpenDoor(EntityCreature entitycreature) {
        this.a = entitycreature;
    }

    public boolean a() {
        if (this.a.world.e()) {
            return false;
        } else {
            Village village = this.a.world.villages.getClosestVillage(MathHelper.floor(this.a.locX), MathHelper.floor(this.a.locY), MathHelper.floor(this.a.locZ), 16);

            if (village == null) {
                return false;
            } else {
                this.b = village.b(MathHelper.floor(this.a.locX), MathHelper.floor(this.a.locY), MathHelper.floor(this.a.locZ));
                return this.b == null ? false : (double) this.b.b(MathHelper.floor(this.a.locX), MathHelper.floor(this.a.locY), MathHelper.floor(this.a.locZ)) < 2.25D;
            }
        }
    }

    public boolean b() {
        return this.a.world.e() ? false : !this.b.g && this.b.a(MathHelper.floor(this.a.locX), MathHelper.floor(this.a.locZ));
    }

    public void c() {
        this.a.al().b(false);
        this.a.al().c(false);
    }

    public void d() {
        this.a.al().b(true);
        this.a.al().c(true);
        this.b = null;
    }

    public void e() {
        this.b.e();
    }
}
