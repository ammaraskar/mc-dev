package net.minecraft.server;

public abstract class EntityCreature extends EntityLiving {

    private PathEntity pathEntity;
    protected Entity target;
    protected boolean e = false;
    protected int f = 0;

    public EntityCreature(World world) {
        super(world);
    }

    protected boolean F() {
        return false;
    }

    protected void d_() {
        MethodProfiler.a("ai");
        if (this.f > 0) {
            --this.f;
        }

        this.e = this.F();
        float f = 16.0F;

        if (this.target == null) {
            this.target = this.findTarget();
            if (this.target != null) {
                this.pathEntity = this.world.findPath(this, this.target, f, true, false, false, true);
            }
        } else if (!this.target.isAlive()) {
            this.target = null;
        } else {
            float f1 = this.target.i(this);

            if (this.h(this.target)) {
                this.a(this.target, f1);
            } else {
                this.b(this.target, f1);
            }
        }

        MethodProfiler.a();
        if (!this.e && this.target != null && (this.pathEntity == null || this.random.nextInt(20) == 0)) {
            this.pathEntity = this.world.findPath(this, this.target, f, true, false, false, true);
        } else if (!this.e && (this.pathEntity == null && this.random.nextInt(180) == 0 || this.random.nextInt(120) == 0 || this.f > 0) && this.aV < 100) {
            this.G();
        }

        int i = MathHelper.floor(this.boundingBox.b + 0.5D);
        boolean flag = this.aU();
        boolean flag1 = this.aV();

        this.pitch = 0.0F;
        if (this.pathEntity != null && this.random.nextInt(100) != 0) {
            MethodProfiler.a("followpath");
            Vec3D vec3d = this.pathEntity.a((Entity) this);
            double d0 = (double) (this.width * 2.0F);

            while (vec3d != null && vec3d.d(this.locX, vec3d.b, this.locZ) < d0 * d0) {
                this.pathEntity.a();
                if (this.pathEntity.b()) {
                    vec3d = null;
                    this.pathEntity = null;
                } else {
                    vec3d = this.pathEntity.a((Entity) this);
                }
            }

            this.aZ = false;
            if (vec3d != null) {
                double d1 = vec3d.a - this.locX;
                double d2 = vec3d.c - this.locZ;
                double d3 = vec3d.b - (double) i;
                float f2 = (float) (Math.atan2(d2, d1) * 180.0D / 3.1415927410125732D) - 90.0F;
                float f3 = f2 - this.yaw;

                for (this.aX = this.bb; f3 < -180.0F; f3 += 360.0F) {
                    ;
                }

                while (f3 >= 180.0F) {
                    f3 -= 360.0F;
                }

                if (f3 > 30.0F) {
                    f3 = 30.0F;
                }

                if (f3 < -30.0F) {
                    f3 = -30.0F;
                }

                this.yaw += f3;
                if (this.e && this.target != null) {
                    double d4 = this.target.locX - this.locX;
                    double d5 = this.target.locZ - this.locZ;
                    float f4 = this.yaw;

                    this.yaw = (float) (Math.atan2(d5, d4) * 180.0D / 3.1415927410125732D) - 90.0F;
                    f3 = (f4 - this.yaw + 90.0F) * 3.1415927F / 180.0F;
                    this.aW = -MathHelper.sin(f3) * this.aX * 1.0F;
                    this.aX = MathHelper.cos(f3) * this.aX * 1.0F;
                }

                if (d3 > 0.0D) {
                    this.aZ = true;
                }
            }

            if (this.target != null) {
                this.a(this.target, 30.0F, 30.0F);
            }

            if (this.positionChanged && !this.H()) {
                this.aZ = true;
            }

            if (this.random.nextFloat() < 0.8F && (flag || flag1)) {
                this.aZ = true;
            }

            MethodProfiler.a();
        } else {
            super.d_();
            this.pathEntity = null;
        }
    }

    protected void G() {
        MethodProfiler.a("stroll");
        boolean flag = false;
        int i = -1;
        int j = -1;
        int k = -1;
        float f = -99999.0F;

        for (int l = 0; l < 10; ++l) {
            int i1 = MathHelper.floor(this.locX + (double) this.random.nextInt(13) - 6.0D);
            int j1 = MathHelper.floor(this.locY + (double) this.random.nextInt(7) - 3.0D);
            int k1 = MathHelper.floor(this.locZ + (double) this.random.nextInt(13) - 6.0D);
            float f1 = this.a(i1, j1, k1);

            if (f1 > f) {
                f = f1;
                i = i1;
                j = j1;
                k = k1;
                flag = true;
            }
        }

        if (flag) {
            this.pathEntity = this.world.a(this, i, j, k, 10.0F, true, false, false, true);
        }

        MethodProfiler.a();
    }

    protected void a(Entity entity, float f) {}

    protected void b(Entity entity, float f) {}

    public float a(int i, int j, int k) {
        return 0.0F;
    }

    protected Entity findTarget() {
        return null;
    }

    public boolean canSpawn() {
        int i = MathHelper.floor(this.locX);
        int j = MathHelper.floor(this.boundingBox.b);
        int k = MathHelper.floor(this.locZ);

        return super.canSpawn() && this.a(i, j, k) >= 0.0F;
    }

    public boolean H() {
        return this.pathEntity != null;
    }

    public void setPathEntity(PathEntity pathentity) {
        this.pathEntity = pathentity;
    }

    public Entity I() {
        return this.target;
    }

    public void setTarget(Entity entity) {
        this.target = entity;
    }

    protected float J() {
        if (this.c_()) {
            return 1.0F;
        } else {
            float f = super.J();

            if (this.f > 0) {
                f *= 2.0F;
            }

            return f;
        }
    }
}
