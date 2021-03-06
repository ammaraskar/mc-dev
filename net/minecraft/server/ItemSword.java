package net.minecraft.server;

public class ItemSword extends Item {

    private int damage;
    private final EnumToolMaterial b;

    public ItemSword(int i, EnumToolMaterial enumtoolmaterial) {
        super(i);
        this.b = enumtoolmaterial;
        this.maxStackSize = 1;
        this.setMaxDurability(enumtoolmaterial.a());
        this.damage = 4 + enumtoolmaterial.c();
    }

    public float getDestroySpeed(ItemStack itemstack, Block block) {
        return block.id == Block.WEB.id ? 15.0F : 1.5F;
    }

    public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
        itemstack.damage(1, entityliving1);
        return true;
    }

    public boolean a(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving) {
        itemstack.damage(2, entityliving);
        return true;
    }

    public int a(Entity entity) {
        return this.damage;
    }

    public EnumAnimation d(ItemStack itemstack) {
        return EnumAnimation.d;
    }

    public int c(ItemStack itemstack) {
        return 72000;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        entityhuman.a(itemstack, this.c(itemstack));
        return itemstack;
    }

    public boolean canDestroySpecialBlock(Block block) {
        return block.id == Block.WEB.id;
    }

    public int c() {
        return this.b.e();
    }
}
