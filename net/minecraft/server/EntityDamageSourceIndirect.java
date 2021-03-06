package net.minecraft.server;

public class EntityDamageSourceIndirect extends EntityDamageSource {

    private Entity owner;

    public EntityDamageSourceIndirect(String s, Entity entity, Entity entity1) {
        super(s, entity);
        this.owner = entity1;
    }

    public Entity b() {
        return this.a;
    }

    public Entity getEntity() {
        return this.owner;
    }

    public String getLocalizedDeathMessage(EntityHuman entityhuman) {
        return LocaleI18n.get("death." + this.translationIndex, new Object[] { entityhuman.name, this.owner.getLocalizedName()});
    }
}
