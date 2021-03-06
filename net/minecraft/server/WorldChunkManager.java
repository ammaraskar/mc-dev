package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldChunkManager {

    private GenLayer a;
    private GenLayer b;
    private BiomeCache c;
    private List d;

    protected WorldChunkManager() {
        this.c = new BiomeCache(this);
        this.d = new ArrayList();
        this.d.add(BiomeBase.FOREST);
        this.d.add(BiomeBase.PLAINS);
        this.d.add(BiomeBase.TAIGA);
        this.d.add(BiomeBase.TAIGA_HILLS);
        this.d.add(BiomeBase.FOREST_HILLS);
        this.d.add(BiomeBase.JUNGLE);
        this.d.add(BiomeBase.JUNGLE_HILLS);
    }

    public WorldChunkManager(long i, WorldType worldtype) {
        this();
        GenLayer[] agenlayer = GenLayer.a(i, worldtype);

        this.a = agenlayer[0];
        this.b = agenlayer[1];
    }

    public WorldChunkManager(World world) {
        this(world.getSeed(), world.getWorldData().getType());
    }

    public List a() {
        return this.d;
    }

    public BiomeBase getBiome(int i, int j) {
        return this.c.b(i, j);
    }

    public float[] getWetness(float[] afloat, int i, int j, int k, int l) {
        IntCache.a();
        if (afloat == null || afloat.length < k * l) {
            afloat = new float[k * l];
        }

        int[] aint = this.b.a(i, j, k, l);

        for (int i1 = 0; i1 < k * l; ++i1) {
            float f = (float) BiomeBase.biomes[aint[i1]].g() / 65536.0F;

            if (f > 1.0F) {
                f = 1.0F;
            }

            afloat[i1] = f;
        }

        return afloat;
    }

    public float[] getTemperatures(float[] afloat, int i, int j, int k, int l) {
        IntCache.a();
        if (afloat == null || afloat.length < k * l) {
            afloat = new float[k * l];
        }

        int[] aint = this.b.a(i, j, k, l);

        for (int i1 = 0; i1 < k * l; ++i1) {
            float f = (float) BiomeBase.biomes[aint[i1]].h() / 65536.0F;

            if (f > 1.0F) {
                f = 1.0F;
            }

            afloat[i1] = f;
        }

        return afloat;
    }

    public BiomeBase[] getBiomes(BiomeBase[] abiomebase, int i, int j, int k, int l) {
        IntCache.a();
        if (abiomebase == null || abiomebase.length < k * l) {
            abiomebase = new BiomeBase[k * l];
        }

        int[] aint = this.a.a(i, j, k, l);

        for (int i1 = 0; i1 < k * l; ++i1) {
            abiomebase[i1] = BiomeBase.biomes[aint[i1]];
        }

        return abiomebase;
    }

    public BiomeBase[] getBiomeBlock(BiomeBase[] abiomebase, int i, int j, int k, int l) {
        return this.a(abiomebase, i, j, k, l, true);
    }

    public BiomeBase[] a(BiomeBase[] abiomebase, int i, int j, int k, int l, boolean flag) {
        IntCache.a();
        if (abiomebase == null || abiomebase.length < k * l) {
            abiomebase = new BiomeBase[k * l];
        }

        if (flag && k == 16 && l == 16 && (i & 15) == 0 && (j & 15) == 0) {
            BiomeBase[] abiomebase1 = this.c.c(i, j);

            System.arraycopy(abiomebase1, 0, abiomebase, 0, k * l);
            return abiomebase;
        } else {
            int[] aint = this.b.a(i, j, k, l);

            for (int i1 = 0; i1 < k * l; ++i1) {
                abiomebase[i1] = BiomeBase.biomes[aint[i1]];
            }

            return abiomebase;
        }
    }

    public boolean a(int i, int j, int k, List list) {
        int l = i - k >> 2;
        int i1 = j - k >> 2;
        int j1 = i + k >> 2;
        int k1 = j + k >> 2;
        int l1 = j1 - l + 1;
        int i2 = k1 - i1 + 1;
        int[] aint = this.a.a(l, i1, l1, i2);

        for (int j2 = 0; j2 < l1 * i2; ++j2) {
            BiomeBase biomebase = BiomeBase.biomes[aint[j2]];

            if (!list.contains(biomebase)) {
                return false;
            }
        }

        return true;
    }

    public ChunkPosition a(int i, int j, int k, List list, Random random) {
        int l = i - k >> 2;
        int i1 = j - k >> 2;
        int j1 = i + k >> 2;
        int k1 = j + k >> 2;
        int l1 = j1 - l + 1;
        int i2 = k1 - i1 + 1;
        int[] aint = this.a.a(l, i1, l1, i2);
        ChunkPosition chunkposition = null;
        int j2 = 0;

        for (int k2 = 0; k2 < aint.length; ++k2) {
            int l2 = l + k2 % l1 << 2;
            int i3 = i1 + k2 / l1 << 2;
            BiomeBase biomebase = BiomeBase.biomes[aint[k2]];

            if (list.contains(biomebase) && (chunkposition == null || random.nextInt(j2 + 1) == 0)) {
                chunkposition = new ChunkPosition(l2, 0, i3);
                ++j2;
            }
        }

        return chunkposition;
    }

    public void b() {
        this.c.a();
    }
}
