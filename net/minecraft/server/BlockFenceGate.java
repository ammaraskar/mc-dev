package net.minecraft.server;

public class BlockFenceGate extends BlockDirectional {

    public BlockFenceGate(int i, int j) {
        super(i, j, Material.WOOD);
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return !world.getMaterial(i, j - 1, k).isBuildable() ? false : super.canPlace(world, i, j, k);
    }

    public AxisAlignedBB e(World world, int i, int j, int k) {
        int l = world.getData(i, j, k);

        return d(l) ? null : (l != 2 && l != 0 ? AxisAlignedBB.b((double) ((float) i + 0.375F), (double) j, (double) k, (double) ((float) i + 0.625F), (double) ((float) j + 1.5F), (double) (k + 1)) : AxisAlignedBB.b((double) i, (double) j, (double) ((float) k + 0.375F), (double) (i + 1), (double) ((float) j + 1.5F), (double) ((float) k + 0.625F)));
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        int l = b(iblockaccess.getData(i, j, k));

        if (l != 2 && l != 0) {
            this.a(0.375F, 0.0F, 0.0F, 0.625F, 1.0F, 1.0F);
        } else {
            this.a(0.0F, 0.0F, 0.375F, 1.0F, 1.0F, 0.625F);
        }
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public boolean b(IBlockAccess iblockaccess, int i, int j, int k) {
        return d(iblockaccess.getData(i, j, k));
    }

    public int c() {
        return 21;
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving) {
        int l = (MathHelper.floor((double) (entityliving.yaw * 4.0F / 360.0F) + 0.5D) & 3) % 4;

        world.setData(i, j, k, l);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman) {
        int l = world.getData(i, j, k);

        if (d(l)) {
            world.setData(i, j, k, l & -5);
        } else {
            int i1 = (MathHelper.floor((double) (entityhuman.yaw * 4.0F / 360.0F) + 0.5D) & 3) % 4;
            int j1 = b(l);

            if (j1 == (i1 + 2) % 4) {
                l = i1;
            }

            world.setData(i, j, k, l | 4);
        }

        world.a(entityhuman, 1003, i, j, k, 0);
        return true;
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        if (!world.isStatic) {
            int i1 = world.getData(i, j, k);
            boolean flag = world.isBlockIndirectlyPowered(i, j, k);

            if (flag || l > 0 && Block.byId[l].isPowerSource() || l == 0) {
                if (flag && !d(i1)) {
                    world.setData(i, j, k, i1 | 4);
                    world.a((EntityHuman) null, 1003, i, j, k, 0);
                } else if (!flag && d(i1)) {
                    world.setData(i, j, k, i1 & -5);
                    world.a((EntityHuman) null, 1003, i, j, k, 0);
                }
            }
        }
    }

    public static boolean d(int i) {
        return (i & 4) != 0;
    }
}
