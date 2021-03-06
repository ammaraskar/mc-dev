package net.minecraft.server;

public class BlockSandStone extends Block {

    public BlockSandStone(int i) {
        super(i, 192, Material.STONE);
    }

    public int a(int i, int j) {
        return i != 1 && (i != 0 || j != 1 && j != 2) ? (i == 0 ? 208 : (j == 1 ? 229 : (j == 2 ? 230 : 192))) : 176;
    }

    public int a(int i) {
        return i == 1 ? this.textureId - 16 : (i == 0 ? this.textureId + 16 : this.textureId);
    }

    protected int getDropData(int i) {
        return i;
    }
}
